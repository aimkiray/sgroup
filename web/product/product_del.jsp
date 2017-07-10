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
  Date: 2017/7/6
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
//    设置request的字符集为utf8
    request.setCharacterEncoding("utf-8");
//    获取用户输入
    int productId = Integer.parseInt(request.getParameter("productId"));
    ProductService productService = new ProductServiceImpl();
    int result = productService.deleteProduct(productId);
    if (result > 0) {
        response.sendRedirect("/product/product.jsp");
    } else {
        out.print("<script>alert('删除失败！');history.back();</script>");
    }
%>