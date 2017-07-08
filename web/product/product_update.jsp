<%@ page import="com.shengdiyage.service.ProductService" %>
<%@ page import="com.shengdiyage.service.serrviceImplement.ProductServiceImpl" %>
<%@ page import="com.shengdiyage.model.Product" %>
<%@ page import="com.shengdiyage.model.ProductType" %>
<%@ page import="com.shengdiyage.service.ProductTypeService" %>
<%@ page import="com.shengdiyage.service.serrviceImplement.ProductTypeServiceImpl" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.shengdiyage.utils.DateTools" %>
<%--
  Created by IntelliJ IDEA.
  User: Akari
  Date: 2017/7/6
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
//    设置request的字符集为utf8
    request.setCharacterEncoding("utf-8");
//    获取用户输入
    String productName = request.getParameter("productName");
    int productPrice = Integer.parseInt(request.getParameter("productPrice"));
    int number = Integer.parseInt(request.getParameter("number"));
    int productTypeId = Integer.parseInt(request.getParameter("productTypeId"));
    String productTime = request.getParameter("producttime");
    int productId = Integer.parseInt(request.getParameter("productId"));
//    新建产品类别和产品の实现类
    ProductService productService = new ProductServiceImpl();
    ProductTypeService productTypeService = new ProductTypeServiceImpl();
//    通过typeId获得Type对象
    ProductType productType = productTypeService.queryTypeByTypeId(productTypeId);
//    获取当前时间
    Date date = DateTools.getDateByStr(productTime,"yyyy-MM-dd HH-mm-ss");
//    用更新后的信息创建一个产品类
    Product product = new Product(productId,productName,productPrice,number,productType,date);
    int result = productService.updateProduct(product);
    if (result > 0) {
        response.sendRedirect("/product/productlist.jsp");
    } else {
        out.print("<script>alert('修改失败！');history.back();</script>");
    }
%>
