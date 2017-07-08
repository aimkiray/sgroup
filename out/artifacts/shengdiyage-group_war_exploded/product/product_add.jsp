<%@ page import="com.shengdiyage.service.ProductService" %>
<%@ page import="com.shengdiyage.service.serrviceImplement.ProductServiceImpl" %>
<%@ page import="com.shengdiyage.model.Product" %>
<%@ page import="com.shengdiyage.model.ProductType" %>
<%@ page import="com.shengdiyage.service.ProductTypeService" %>
<%@ page import="com.shengdiyage.service.serrviceImplement.ProductTypeServiceImpl" %>
<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: Akari
  Date: 2017/7/5
  Time: 19:04
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
    ProductService productService = new ProductServiceImpl();
    ProductTypeService productTypeService = new ProductTypeServiceImpl();
//    通过typeId获得Type对象
    ProductType productType = productTypeService.queryTypeByTypeId(productTypeId);
//    获取当前时间
    Date date = new Date();
    Product product = new Product(productName,productPrice,number,productType,date);
    int result = productService.addProduct(product);
    if (result > 0) {
        response.sendRedirect("/product/productlist.jsp");
    } else if (result == 0) {
        out.print("<script>alert('添加失败！请检查是否重名。');history.back();</script>");
    } else {
        out.print("<script>alert('添加失败！');history.back();</script>");
    }
%>
