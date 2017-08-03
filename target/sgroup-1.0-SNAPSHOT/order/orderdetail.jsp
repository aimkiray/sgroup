<%--
  Created by IntelliJ IDEA.
  User: nekuata
  Date: 2017/7/25
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>订单列表</title>
    <c:set var="root" value="${pageContext.request.contextPath}"/>
    <link type="text/css" rel="stylesheet" href="${root}/bootstrap/css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="${root}/bootstrap/css/bootstrap-theme.css">
    <link type="text/css" rel="stylesheet" href="${root}/bootstrap/css/bootstrap-table.css">
    <link type="text/css" rel="stylesheet" href="${root}/bootstrap/css/bootstrap-editable.css">
    <link type="text/css" rel="stylesheet" href="${root}/bootstrap/css/product-custom.css">
    <link type="text/css" rel="stylesheet" href="${root}/bootstrap/css/bootstrap-datetimepicker.min.css">

    <script src="${root}/js/jquery-3.2.1.min.js" type="text/javascript"></script>
    <script src="${root}/bootstrap/js/bootstrap.js" type="text/javascript"></script>
    <script src="${root}/bootstrap/js/bootstrap-table.js" type="text/javascript"></script>
    <script src="${root}/bootstrap/js/bootstrap-table-zh-CN.js" type="text/javascript"></script>
    <script src="${root}/bootstrap/extension/editable/bootstrap-editable.js" type="text/javascript"></script>
    <script src="${root}/bootstrap/extension/editable/bootstrap-table-editable.js" type="text/javascript"></script>

    <script src="${root}/js/bootbox.min.js" type="text/javascript"></script>
    <script src="${root}/bootstrap/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
    <script src="${root}/bootstrap/js/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"></script>
    <script src="${root}/order/js/orderdetail-custom.js" type="text/javascript"></script>
</head>
<body>
<div class="panel-body">

    <h3>订单信息：</h3>
    <table class="table table-striped table-bordered table-hover" id="show_order_info">
        <tr>
            <td>订单编号</td>
            <td>客户</td>
            <td>员工</td>
            <td>时间</td>
        </tr>
        <%--<c:forEach items="${requestScope.orders}" var="order">--%>
        <tr>
            <td>${requestScope.order.orderId}</td>
            <td>${requestScope.order.customer.customerName}</td>
            <td>${requestScope.order.employee.empName}</td>
            <td><fmt:formatDate value="${requestScope.order.orderDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
        </tr>
        <%--</c:forEach>--%>
    </table>

    <h3>订单详情：</h3>
    <table class="table table-striped table-bordered table-hover" id="show_order_detail">
        <tr>
            <td>名称</td>
            <td>价格</td>
            <td>折扣</td>
            <td>数量</td>
        </tr>
        <c:forEach items="${requestScope.orderDetails}" var="orderDetail">
        <tr>
            <td>${orderDetail.product.productName}</td>
            <td>${orderDetail.product.productPrice}</td>
            <td>${orderDetail.discount}</td>
            <td>${orderDetail.quantity}</td>
        </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
