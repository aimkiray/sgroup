<%@ page import="com.shengdiyage.service.ProductService" %>
<%@ page import="com.shengdiyage.service.serrviceImplement.ProductServiceImplement" %>
<%@ page import="com.shengdiyage.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="com.shengdiyage.service.ProductTypeService" %>
<%@ page import="com.shengdiyage.service.serrviceImplement.ProductTypeServiceImplement" %>
<%--
  Created by IntelliJ IDEA.
  User: Akari
  Date: 2017/7/5
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>product</title>
    <link rel="stylesheet" href="/product/css/product.css">
</head>
<body>
<table class="all-product-list" id="all-product-list">
    <tr class="table-head">
        <td><input id="select-all-product" type="checkbox"></td>
        <td>编号</td>
        <td>名称</td>
        <td>价格</td>
        <td>数量</td>
        <td>类型</td>
        <td>操作</td>
    </tr>
    <%
        ProductService productService = new ProductServiceImplement();
        ProductTypeService productTypeService = new ProductTypeServiceImplement();
        List<Product> products = productService.queryProduct();
        for (int i = 0; i < productService.queryProduct().size(); i++) {
    %>
    <tr class="table-body">
        <td><input type="checkbox"></td>
        <td><%=products.get(i).getPid() %></td>
        <td><%=products.get(i).getPname() %></td>
        <td><%=products.get(i).getPprice() %></td>
        <td><%=products.get(i).getNumber() %></td>
        <td><%=productTypeService.queryTypeByTypeId(products.get(i).getPtype()).getTypename() %></td>
        <%--<td class="bottom-buttons"><a href="#">修改</a><a href="#">删除</a></td>--%>
        <td class="bottom-buttons"><input type="button" value="修改" ><input type="button" value="删除"></td>
    </tr>
    <%
        }
    %>
    <tr>
        <td colspan="7"><input type="button" value="添加" onclick="addTableTail()"></td>
    </tr>
</table>
</body>
<script src="/product/js/product.js"></script>
</html>
