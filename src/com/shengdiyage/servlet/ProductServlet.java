package com.shengdiyage.servlet;

import com.shengdiyage.entity.Product;
import com.shengdiyage.entity.ProductType;
import com.shengdiyage.service.ProductService;
import com.shengdiyage.service.ProductTypeService;
import com.shengdiyage.service.serrviceImplement.ProductServiceImpl;
import com.shengdiyage.service.serrviceImplement.ProductTypeServiceImpl;
import com.shengdiyage.utils.DateTools;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

/**
 * Created by Akari on 2017/7/10.
 */
public class ProductServlet extends HttpServlet {

    private ProductTypeService productTypeService = null;
    private ProductService productService = null;
    private String charset = "";

    public ProductServlet() {
        super();
//    新建产品类别和产品の实现类
        productTypeService = new ProductTypeServiceImpl();
        productService = new ProductServiceImpl();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        charset = config.getInitParameter("charset");

    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(charset);
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
            default:
//                安心吧，一般不会触发的（学下过滤器）。
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    /**
     * 查询所有产品和所有产品类别
     * @param req 客户端发送的数据封装类
     * @param resp 服务器发送的数据封装类（大概）
     * @throws ServletException
     * @throws IOException
     */
    protected void queryProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductType> productTypes = productTypeService.queryAllProductType();
        List<Product> products = productService.queryProduct();
        req.setAttribute("products", products);
        req.setAttribute("producttypes", productTypes);
        req.getRequestDispatcher("/product/product.jsp").forward(req, resp);
    }

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
//    获取用户输入
        String productName = req.getParameter("productName");
        int productPrice = Integer.parseInt(req.getParameter("productPrice"));
        int number = Integer.parseInt(req.getParameter("number"));
        int productTypeId = Integer.parseInt(req.getParameter("productTypeId"));
//    通过typeId获得Type对象
        ProductType productType = productTypeService.queryTypeByTypeId(productTypeId);
//    获取当前时间
        Date date = new Date();
        Product product = new Product(productName,productPrice,number,productType,date);
        int result = productService.addProduct(product);
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
        } else if (result == 0) {
            out.print("<script>alert('删除失败！请检查该类别下是否存在产品');history.back();</script>");
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
//    获取用户输入
        String productName = req.getParameter("productName");
        int productPrice = Integer.parseInt(req.getParameter("productPrice"));
        int number = Integer.parseInt(req.getParameter("number"));
        int productTypeId = Integer.parseInt(req.getParameter("productTypeId"));
        String productTime = req.getParameter("producttime");
        int productId = Integer.parseInt(req.getParameter("productId"));
//    新建产品类别和产品の实现类

//    通过typeId获得Type对象
        ProductType productType = productTypeService.queryTypeByTypeId(productTypeId);
//    获取当前时间
        Date date = DateTools.getDateByStr(productTime,"yyyy-MM-dd HH-mm-ss");
//    用更新后的信息创建一个产品类
        Product product = new Product(productId,productName,productPrice,number,productType,date);
        int result = productService.updateProduct(product);
        if (result > 0) {
            resp.sendRedirect("/product/product.jsp");
        } else {
            PrintWriter out = resp.getWriter();
            out.print("<script>alert('修改失败！');history.back();</script>");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
