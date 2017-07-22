<%--
  Created by IntelliJ IDEA.
  User: Akari
  Date: 2017/7/5
  Time: 9:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Akari</title>
    <c:set var="root" value="${pageContext.request.contextPath}"/>
    <link href="${root}/admin/css/frame.css" rel="stylesheet" type="text/css">
</head>
<body class="left-body">
<div class="left-logo">
    <img src="${root}/logo/left_logo.png">
</div>
<div class="left-option">
    <table>
        <tr>
            <td><a href="${root}/productservlet.do?operate=product" target="right">产品中心</a></td>
        </tr>
        <tr>
            <td><a href="${root}/filemanageservlet.do?operate=fileList" target="right">py交易</a></td>
        </tr>
        <tr>
            <td><a href="${root}/datatables/product-datatables.jsp" target="right">集中销毁</a></td>
        </tr>
        <tr>
            <td><a href="${root}/bootstrap/bootstrap-product.jsp" target="right">零售&批发</a></td>
        </tr>
        <tr>
            <td><a href="${root}/bootstrap/product-center.jsp" target="right">使用方法</a></td>
        </tr>
        <tr>
            <td><a href="${root}/jsgrip/product-table.jsp" target="right">食用方法?</a></td>
        </tr>
        <tr>
            <td><a href="${root}/order/order.jsp" target="right">禁止事项</a></td>
        </tr>
    </table>
</div>
</body>
</html>
