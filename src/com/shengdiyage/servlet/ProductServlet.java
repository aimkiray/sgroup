package com.shengdiyage.servlet;

import com.alibaba.fastjson.JSON;
import com.shengdiyage.entity.Product;
import com.shengdiyage.entity.ProductType;
import com.shengdiyage.service.ProductService;
import com.shengdiyage.service.ProductTypeService;
import com.shengdiyage.service.serviceImplement.ProductServiceImpl;
import com.shengdiyage.service.serviceImplement.ProductTypeServiceImpl;
import com.shengdiyage.utils.BootstrapTable;
import com.shengdiyage.utils.DataTables;
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
import java.util.*;

/**
 * Created by nekuata on 2017/7/10.
 */
public class ProductServlet extends HttpServlet {
    // 当前类的版本，修改此类的同时也要修改版本号。
    private static final long serialVersionUID = 1L;

//    上传文件储存目录
    private static final String UPLOAD_DIRECTORY = "uploads";

//    上传配置
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

//    定义全局变量
    private ProductTypeService productTypeService = null;
    private ProductService productService = null;
//    private String charset = "";

    public ProductServlet() {
        super();
//    新建产品类别和产品の实现类
        productTypeService = new ProductTypeServiceImpl();
        productService = new ProductServiceImpl();
    }

    /**
     * 带参数的初始化，只执行一次
     * @param config web.xml的配置
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
//        读取编码格式
//        charset = config.getInitParameter("charset");

    }

    /**
     * 无参的初始化，只执行一次
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        super.init();
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
//        设置post提交方式的编码格式，在web.xml中设置（已改用Filter）
//        req.setCharacterEncoding(charset);
        String operate = req.getParameter("operate");
        switch (operate) {
            case "addtype":
                addType(req, resp);
                break;
            case "deltype":
                delType(req, resp);
                break;
            case "updatetype":
                updateType(req, resp);
                break;
            case "addproduct":
                addProduct(req, resp);
                break;
            case "delproduct":
                delProduct(req, resp);
                break;
            case "updateproduct":
                updateProduct(req, resp);
                break;
            case "product":
                queryProduct(req, resp);
                break;
            case "muldel":
                mulDel(req, resp);
                break;
            case "checkTypeName":
                checkTypeName(req, resp);
                break;
            case "checkProductName":
                checkProductName(req, resp);
                break;
            case "productsByType":
                getProductsByType(req, resp);
                break;
            case "queryProductToDataTables":
                queryProductToDataTables(req, resp);
                break;
            case "queryProductToBootstrap":
                queryProductToBootstrap(req, resp);
                break;
            case "queryProductType":
                queryProductType(req, resp);
                break;
            case "getaddproductjsp":
                getAddProductJsp(req, resp);
                break;
            case "getUpdateProductJsp":
                getUpdateProductJsp(req, resp);
                break;
            default:
//                安心吧，一般不会触发的（学下过滤器先）。
                break;
        }
    }

    /**
     * post方式提交的数据
     * @param req 客户端发送的数据封装类
     * @param resp 服务器发送的数据封装类（大概）
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void queryProductType(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<ProductType> productTypes = productTypeService.queryAllProductType();
        String strTypes = JSON.toJSONString(productTypes);
        resp.getWriter().print(strTypes);
    }

    protected void getAddProductJsp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductType> productTypes = productTypeService.queryAllProductType();
//        String strTypes = JSON.toJSONString(productTypes);
        req.setAttribute("productTypes",productTypes);
        req.getRequestDispatcher("/bootstrap/addproduct.jsp").forward(req, resp);
    }

    protected void getUpdateProductJsp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("productId"));
        Product product = productService.queryProduct(productId);
        List<ProductType> productTypes = productTypeService.queryAllProductType();
//        String strTypes = JSON.toJSONString(productTypes);
        req.setAttribute("productTypes",productTypes);
        req.setAttribute("product",product);
        req.getRequestDispatcher("/bootstrap/updateproduct.jsp").forward(req, resp);
    }

    /**
     * (Ajax)异步验证产品名是否存在
     * @param req
     * @param resp
     */
    protected void checkProductName(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String productName = req.getParameter("productName");
        if (productService.queryProductByName(productName)) {
            resp.getWriter().print(true);
        } else {
            resp.getWriter().print(false);
        }
    }

    /**
     * (Ajax)异步验证类别名是否存在
     * @param req
     * @param resp
     */
    protected void checkTypeName(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String typeName = req.getParameter("typeName");
        if (productTypeService.queryTypeByTypeName(typeName)) {
            resp.getWriter().print(true);
        } else {
            resp.getWriter().print(false);
        }
    }

    /**
     * (Ajax)根据类别Id查找产品
     * @param req
     * @param resp
     * @throws IOException
     */
    protected void getProductsByType(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int typeId = Integer.parseInt(req.getParameter("typeId"));
        ProductTypeService productTypeService = new ProductTypeServiceImpl();
        List<Product> products = productTypeService.queryProductsByTypeId(typeId);
        String strProducts = JSON.toJSONString(products);
        resp.getWriter().print(strProducts);
    }

    /**
     * 分页查询产品&查询所有产品类别&产品模糊搜素
     * @param req 客户端发送的数据封装类
     * @param resp 服务器发送的数据封装类（大概）
     * @throws ServletException
     * @throws IOException
     */
    protected void queryProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        每页默认显示5条数据
        String size = req.getParameter("pageSize");
        int pageSize = size == null ? 5 : Integer.parseInt(size);
//        当前页
        String pageIndex = req.getParameter("curPage");
        int curPage = pageIndex == null ? 1 : Integer.parseInt(pageIndex);
//        产品Id，默认为0
        String realProductId = req.getParameter("productId");
        int productId = realProductId == null ? 0 : Integer.parseInt(realProductId);
//        产品名称，默认为""
        String realProductName = req.getParameter("productName");
        String productName = realProductName == null ? "" : realProductName;
//        产品类别Id，默认为"0"
        String realTypeId = req.getParameter("typeId");
        int typeId = realTypeId == null ? 0 : Integer.parseInt(realTypeId);

        Product product = new Product();
        product.setProductId(productId);
        product.setProductName(productName);
        ProductType productType = new ProductType();
        productType.setTypeId(typeId);
        product.setProductType(productType);

//        查询产品总数
        int productNum = productService.queryProductNum(product);
//        计算总页数
        int pages;
        if (productNum%pageSize != 0) {
            pages = (productNum/pageSize) + 1;
        } else {
            pages = (productNum/pageSize);
        }
//        避免超出数据范围
        if (curPage > pages) {
            curPage = pages;
        }
        if (curPage < 1) {
            curPage = 1;
        }
//        查询符合条件的产品和产品类别
        List<ProductType> productTypes = productTypeService.queryAllProductType();
        List<Product> products = productService.queryProduct(product, curPage, pageSize);
        req.setAttribute("products", products);
        req.setAttribute("producttypes", productTypes);
        req.setAttribute("curPage",curPage);
        req.setAttribute("pageSize",pageSize);
        req.setAttribute("pages",pages);
        req.setAttribute("product",product);
        req.getRequestDispatcher("/product/product.jsp").forward(req, resp);
    }

    /**
     * (bootstrap-table)分页查询产品&查询所有产品类别&产品模糊搜素
     * @param req 客户端发送的数据封装类
     * @param resp 服务器发送的数据封装类（大概）
     * @throws ServletException
     * @throws IOException
     */
    protected void queryProductToBootstrap(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


//        每页默认显示5条数据
        String size = req.getParameter("pageSize");
        int pageSize = size == null ? 10 : Integer.parseInt(size);
//        当前页
        String pageIndex = req.getParameter("pageNumber");
        int pageNumber = pageIndex == null ? 1 : Integer.parseInt(pageIndex);
//        产品Id，默认为0
        String realProductId = req.getParameter("productId");
        int productId = realProductId == null ? 0 : Integer.parseInt(realProductId);
//        产品名称，默认为""
        String realProductName = req.getParameter("productName");
        String productName = realProductName == null ? "" : realProductName;
//        产品类别Id，默认为"0"
        String realTypeId = req.getParameter("typeId");
        int typeId = realTypeId == null ? 0 : Integer.parseInt(realTypeId);

        Product product = new Product();
        product.setProductId(productId);
        product.setProductName(productName);
        ProductType productType = new ProductType();
        productType.setTypeId(typeId);
        product.setProductType(productType);
//
////        查询产品总数
        int productNum = productService.queryProductNum(product);
//        计算总页数
        int pages;
        if (productNum%pageSize != 0) {
            pages = (productNum/pageSize) + 1;
        } else {
            pages = (productNum/pageSize);
        }
//        避免超出数据范围
        if (pageNumber > pages) {
            pageNumber = pages;
        }
        if (pageNumber < 1) {
            pageNumber = 1;
        }
//        查询符合条件的产品和产品类别
//        List<ProductType> productTypes = productTypeService.queryAllProductType();
        List<Product> products = productService.queryProduct(product, pageNumber, pageSize);

        BootstrapTable bootstrapTable = new BootstrapTable();
        bootstrapTable.setTotal(productNum);
        bootstrapTable.setRows(products);
        String jsonProducts = JSON.toJSONString(bootstrapTable);
        resp.getWriter().print(jsonProducts);
    }

    /**
     * (dataTables)分页查询产品&查询所有产品类别&产品模糊搜素
     * @param req 客户端发送的数据封装类
     * @param resp 服务器发送的数据封装类（大概）
     * @throws ServletException
     * @throws IOException
     */
    protected void queryProductToDataTables(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        每页默认显示5条数据
        String length = req.getParameter("length");
        int curLength = length == null ? 10 : Integer.parseInt(length);
//        当前页
        String start = req.getParameter("start");
        int curStart = start == null ? 0 : Integer.parseInt(start);

        DataTables dataTables = new DataTables();

//        查询全部的产品数
        int productNum = productService.queryProductNum();
        dataTables.setRecordsFiltered(productNum);
        dataTables.setRecordsTotal(productNum);

        List<Product> products = productService.queryProductToTable(null, curStart, curLength);
        dataTables.setData(products);

        String jsonProducts = JSON.toJSONString(dataTables);
        resp.getWriter().print(jsonProducts);
    }

//    protected void searchProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////        每页默认显示2条数据
//        String size = req.getParameter("pageSize");
//
//        int pageSize = size == null ? 2 : Integer.parseInt(size);
////        查询产品总数
//        int productNum = productService.queryProductNum();
////        计算总页数
//        int pages;
//        if (productNum%pageSize != 0) {
//            pages = (productNum/pageSize) + 1;
//        } else {
//            pages = (productNum/pageSize);
//        }
////        当前页
//        String pageIndex = req.getParameter("curPage");
//        int curPage = pageIndex == null ? 1 : Integer.parseInt(pageIndex);
//
//        List<ProductType> productTypes = productTypeService.queryAllProductType();
//        List<Product> products = productService.queryProduct(curPage, pageSize);
//        req.setAttribute("products", products);
//        req.setAttribute("producttypes", productTypes);
//        req.setAttribute("curPage",curPage);
//        req.setAttribute("pageSize",pageSize);
//        req.setAttribute("pages",pages);
//        req.getRequestDispatcher("/product/product.jsp").forward(req, resp);
//    }

    /**
     * 添加产品类别
     * @param req 客户端发送的数据封装类
     * @param resp 服务器发送的数据封装类（大概）
     * @throws IOException
     */
    protected void addType(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String productTypeName = req.getParameter("productTypeName");
        ProductType productType = new ProductType(productTypeName);
        int result = productTypeService.addProductTypeByType(productType);
        PrintWriter out = resp.getWriter();
        if (result > 0) {
            resp.sendRedirect("/productservlet.do?operate=product");
        } else if (result == 0) {
            out.print("<script>alert('添加失败！请检查是否重名。');history.back();</script>");
        } else {
            out.print("<script>alert('添加失败！');history.back();</script>");
        }
    }

    /**
     * 删除产品类别
     * @param req 客户端发送的数据封装类
     * @param resp 服务器发送的数据封装类（大概）
     * @throws IOException
     */
    protected void delType(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//    获取用户输入
        int typeId = Integer.parseInt(req.getParameter("typeId"));
        int result = productTypeService.deleteProductTypeByTypeId(typeId);
        PrintWriter out = resp.getWriter();
        if (result > 0) {
            resp.sendRedirect("/productservlet.do?operate=product");
        } else if (result == 0) {
            out.print("<script>alert('删除失败！请检查该类别下是否存在产品');history.back();</script>");
        } else {
            out.print("<script>alert('删除失败！');history.back();</script>");
        }
    }

    /**
     * 修改产品类别
     * @param req 客户端发送的数据封装类
     * @param resp 服务器发送的数据封装类（大概）
     * @throws IOException
     */
    protected void updateType(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String productTypeName = req.getParameter("productTypeName");
        int productTypeId = Integer.parseInt(req.getParameter("productTypeId"));
        ProductType productType = new ProductType(productTypeId,productTypeName);
        int result = productTypeService.updateProductTypeByType(productType);
        PrintWriter out = resp.getWriter();
        if (result > 0) {
            resp.sendRedirect("/productservlet.do?operate=product");
        } else {
            out.print("<script>alert('修改失败！');history.back();</script>");
        }
    }

    /**
     * 添加产品
     * @param req 客户端发送的数据封装类
     * @param resp 服务器发送的数据封装类（大概）
     * @throws IOException
     */
    private void addProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {

//        配置上传参数
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
//        配置临时目录
        diskFileItemFactory.setRepository(new File("D:\\test"));
//        设置内存临界值，超过后生成临时文件
        diskFileItemFactory.setSizeThreshold(MEMORY_THRESHOLD);
//        处理form中多文件上传的类，继承自FileUpload
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
//        设置文件上传的最大值，异常提醒（待填坑）
        servletFileUpload.setFileSizeMax(MAX_FILE_SIZE);
//        设置文件请求的最大值，异常提醒（待填坑）
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
//                          判断是否传文件
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
            } finally {
                // 关闭输入输出流
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        }

//    获取用户输入
        String productName = values.get("productName").toString();
        int productPrice = Integer.parseInt(values.get("productPrice").toString());
        int number = Integer.parseInt(values.get("number").toString());
        int productTypeId = Integer.parseInt(values.get("productTypeId").toString());
        String picName = values.get("uploadPic").toString();
//    通过typeId获得Type对象
        ProductType productType = productTypeService.queryTypeByTypeId(productTypeId);
        String inputDate = values.get("productTime").toString();
//    获取当前时间
        Date date = inputDate == null ? new Date() : DateTools.getDateByStr(inputDate,"yyyy-MM-dd HH:mm:ss");
        Product product = new Product(productName,productPrice,number,productType,date,picName);
        int result = productService.addProduct(product);
        PrintWriter out = resp.getWriter();
        String flag = req.getParameter("flag");
        if (result > 0) {
            if (flag != null && "ajax".equals(flag)) {
                out.print("true");
            } else {
                resp.sendRedirect("/productservlet.do?operate=product");
            }
        } else if (result == 0) {
            if (flag != null && "ajax".equals(flag)) {
                out.print("false");
            } else {
                out.print("<script>alert('添加失败！请检查是否重名。');history.back();</script>");
            }
        } else {
            if (flag != null && "ajax".equals(flag)) {
                out.print("false");
            } else {
                out.print("<script>alert('添加失败！');history.back();</script>");
            }
        }
    }

    /**
     * 删除产品
     * @param req 客户端发送的数据封装类
     * @param resp 服务器发送的数据封装类（大概）
     * @throws IOException
     */
    private void delProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//    获取用户输入
        int productId = Integer.parseInt(req.getParameter("productId"));
        int result = productService.deleteProduct(productId);
        PrintWriter out = resp.getWriter();
        if (result > 0) {
            resp.sendRedirect("/productservlet.do?operate=product");
        } else {
            out.print("<script>alert('删除失败！');history.back();</script>");
        }
    }

    /**
     * 批量删除功能
     * @param req 客户端发送的数据封装类
     * @param resp 服务器发送的数据封装类（大概）
     */
    private void mulDel(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] strPids = req.getParameterValues("check_product");
        Integer[] pids = new Integer[strPids.length];
        for (int i = 0; i<pids.length; i++) {
            pids[i] = Integer.parseInt(strPids[i]);
        }
        int result = productService.delMulProduct(pids);
        PrintWriter out = resp.getWriter();
        if (result > 0) {
            resp.sendRedirect("/productservlet.do?operate=product");
        } else {
            out.print("<script>alert('删除失败！');history.back();</script>");
        }
    }

    /**
     * 修改产品
     * @param req 客户端发送的数据封装类
     * @param resp 服务器发送的数据封装类（大概）
     * @throws IOException
     */
    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {

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
//                            如果没加就是空的
                            values.put(fileItem.getFieldName(),fileName);
                        }
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            } finally {
                // 关闭输入输出流
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        }

//        由于提交了全部产品表单，需要判断修改哪个产品（需要学下jQuery，动态生成form）
        String toWhat = req.getParameter("what");
        String what = toWhat == null ? "" : toWhat;

        int productId = Integer.parseInt(values.get("productId"+what).toString());
        String productName = values.get("productName"+what).toString();
        int productPrice = Integer.parseInt(values.get("productPrice"+what).toString());
        int number = Integer.parseInt(values.get("number"+what).toString());
        int productTypeId = Integer.parseInt(values.get("productTypeId"+what).toString());
        String productTime = values.get("productTime"+what).toString();
        String picName = values.get("uploadPic"+what).toString();
//    通过typeId获得Type对象
        ProductType productType = productTypeService.queryTypeByTypeId(productTypeId);
//    获取当前时间
        Date date = DateTools.getDateByStr(productTime,"yyyy-MM-dd HH:mm:ss");
//    用更新后的信息创建一个产品类
        Product product = new Product(productId,productName,productPrice,number,productType,date,picName);
        int result = productService.updateProduct(product);
        PrintWriter out = resp.getWriter();
        String flag = req.getParameter("flag");
        if (result > 0) {
            if (flag != null && "ajax".equals(flag)) {
                out.print("true");
            } else {
                resp.sendRedirect("/productservlet.do?operate=product");
            }
        } else {
            if (flag != null && "ajax".equals(flag)) {
                out.print("false");
            } else {
                out.print("<script>alert('修改失败！');history.back();</script>");
            }
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
