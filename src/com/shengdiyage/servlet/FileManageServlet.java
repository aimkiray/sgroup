package com.shengdiyage.servlet;

import com.shengdiyage.entity.FileDemo;
import com.shengdiyage.service.FileService;
import com.shengdiyage.service.serrviceImplement.FileServiceImpl;
import com.shengdiyage.utils.DateTools;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nekuata on 2017/7/15.
 */
public class FileManageServlet extends HttpServlet {

    // 当前类的版本，修改此类的同时也要修改版本号。
    private static final long serialVersionUID = 1L;

    //    上传文件储存目录
    private static final String UPLOAD_DIRECTORY = "upload";

    //    上传配置
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

    //    定义全局变量
    private FileDemo fileDemo = null;
    FileService fileService = null;

    public FileManageServlet() {
        super();
        // 新建文件实现类
        fileDemo = new FileDemo();
        fileService = new FileServiceImpl();
    }

    /**
     * get方式传递的数据，包括连接后面用？传过来的数据
     * @param req 客户端发送的数据封装类
     * @param resp 服务器发送的数据封装类（大概）
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get方式传递来的，用于判断执行哪项操作
        String opreate = req.getParameter("operate");
        switch (opreate) {
            case "addFile":
                uploadFile(req, resp);
                break;
            case "delFile":
                delFile(req, resp);
                break;
            case "downloadFile":
                downloadFile(req, resp);
                break;
            case "fileList":
                toFileList(req, resp);
                break;
        }
    }

    /**
     * post方式提交的数据（也丢给doGet处理）
     * @param req 客户端发送的数据对象
     * @param resp 服务器发送的数据对象（大概）
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    /**
     * 获取文件列表
     * @param req 客户端发送的数据对象
     * @param resp 服务器发送的数据对象（大概）
     */
    protected void toFileList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        每页默认显示2条数据
        String size = req.getParameter("pageSize");

        int pageSize = size == null ? 5 : Integer.parseInt(size);
//        查询文件总数
        int fileNum = fileService.queryFileNum();
//        计算总页数
        int pages;
        if (fileNum%pageSize != 0) {
            pages = (fileNum/pageSize) + 1;
        } else {
            pages = (fileNum/pageSize);
        }
//        当前页
        String pageIndex = req.getParameter("curPage");
        int curPage = pageIndex == null ? 1 : Integer.parseInt(pageIndex);

        List<FileDemo> fileDemos = fileService.queryFile(curPage, pageSize);
        req.setAttribute("fileDemos", fileDemos);
        req.setAttribute("curPage",curPage);
        req.setAttribute("pageSize",pageSize);
        req.setAttribute("pages",pages);
        req.getRequestDispatcher("/filemanage/filemanage.jsp").forward(req, resp);
    }

    /**
     * 上传文件
     * @param req 客户端发送的数据对象
     * @param resp 服务器发送的数据对象（大概）
     */
    protected void uploadFile(HttpServletRequest req, HttpServletResponse resp) {
//        配置上传参数
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
//        配置临时目录
        diskFileItemFactory.setRepository(new File("D:\\test"));
//        设置内存临界值，超过后生成临时文件
        diskFileItemFactory.setSizeThreshold(MEMORY_THRESHOLD);
//        处理form中多文件上传的类，继承自FileUpload
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
//        设置文件上传的最大值，用异常提醒（待填坑）
        servletFileUpload.setFileSizeMax(MAX_FILE_SIZE);
//        设置文件请求的最大值，用异常提醒（待填坑）
        servletFileUpload.setSizeMax(MAX_REQUEST_SIZE);
//        设置编码
        servletFileUpload.setHeaderEncoding("utf-8");
//        获取根目录地址
        String uploadPath = getServletContext().getRealPath("/");
//        获取上传目录地址，自动判断分隔符（windows&linux）
        uploadPath = uploadPath + File.separator + UPLOAD_DIRECTORY;
//        如果目录不存在，自动创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
//        储存表单数据
        Map<String, Object> values = new HashMap<String, Object>();
//        文件上传时的原名
        String realFileName = "";
//        修改后的名称
        String fileName = "";
//        检测是否为多媒体上传
        if (ServletFileUpload.isMultipartContent(req)) {
//            表单数据列表
            List<FileItem> fileItems = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
//                将多媒体转换为request
                fileItems = servletFileUpload.parseRequest(req);
//                循环所有request
                for (FileItem fileItem : fileItems) {
//                    如果该项是表单数据
                    if (fileItem.isFormField()) {
//                        获取表单Name
                        String fieldName = fileItem.getFieldName();
//                        获取表单Value
                        String itemValue = fileItem.getString();
//                        中文乱码处理
                        itemValue = new String(itemValue.getBytes("iso-8859-1"),"utf-8");
//                        将数据存入Map
                        values.put(fieldName,itemValue);
                    } else {
                        inputStream = fileItem.getInputStream();
//                          判断有没有传文件
                        if (inputStream != null && inputStream.available() > 0) {
//                            获取上传的文件名称（new File用于去除可能存在的路径）
                            realFileName = new File(fileItem.getName()).getName();
//                            获取文件后缀名
                            String ext = FilenameUtils.getExtension(realFileName);
//                            随机生成文件名
                            fileName = DateTools.getFileName(ext);
//                            获取文件路径
                            String filePath = uploadPath + File.separator + fileName;
//                            创建文件
                            File storeFile = new File(filePath);
//                            写入文件
                            outputStream = new FileOutputStream(storeFile);
                            byte[] bytes = new byte[1024];
                            int len = 0;
                            while ((len = inputStream.read(bytes)) > 0) {
                                outputStream.write(bytes, 0, len);
                            }
//                            没执行则是空值，避免空指针
                            values.put(fileItem.getFieldName(),fileName);
                        }
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // 关闭输入输出流
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            String name = values.get("name").toString();
            Date date = new Date();
            FileDemo fileDemo = new FileDemo();
//            fileDemo.setId(id);
            fileDemo.setName(name);
            fileDemo.setDate(date);
            int result = fileService.addFile(fileDemo);
            if (result > 0) {
                try {
                    resp.sendRedirect("/filemanageservlet.do?operate=fileList");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void downloadFile(HttpServletRequest req, HttpServletResponse resp) {
//        获得要下载的文件名
        String fileName = req.getParameter("fileName");
//        获取文件真实路径
        String realFilePath = getServletContext().getRealPath("/") +File.separator+ UPLOAD_DIRECTORY + File.separator + fileName;

    }

    protected void delFile(HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
