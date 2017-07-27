<%--
  Created by IntelliJ IDEA.
  User: nekuata
  Date: 2017/7/21
  Time: 9:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>订单中心</title>
    <c:set var="root" value="${pageContext.request.contextPath}"/>
    <link type="text/css" rel="stylesheet" href="${root}/bootstrap/css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="${root}/bootstrap/css/bootstrap-theme.css">
    <link type="text/css" rel="stylesheet" href="${root}/bootstrap/css/bootstrap-table.css">
    <link type="text/css" rel="stylesheet" href="${root}/bootstrap/css/bootstrap-editable.css">
    <link type="text/css" rel="stylesheet" href="${root}/bootstrap/css/bootstrap-datetimepicker.min.css">

    <link rel="stylesheet" href="${root}/order/css/order-custom.css">

    <script src="${root}/js/jquery-3.2.1.min.js" type="text/javascript"></script>
    <script src="${root}/bootstrap/js/bootstrap.js" type="text/javascript"></script>
    <script src="${root}/bootstrap/js/bootstrap-table.js" type="text/javascript"></script>
    <script src="${root}/bootstrap/js/bootstrap-table-zh-CN.js" type="text/javascript"></script>
    <script src="${root}/bootstrap/extension/editable/bootstrap-editable.js" type="text/javascript"></script>
    <script src="${root}/bootstrap/extension/editable/bootstrap-table-editable.js" type="text/javascript"></script>
    <script src="${root}/js/bootbox.min.js" type="text/javascript"></script>
    <script src="${root}/bootstrap/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
    <script src="${root}/bootstrap/js/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"></script>

    <script src="${root}/order/js/order-custom.js" type="text/javascript"></script>
</head>
<body>
<div class="panel-body">
    <form class="form-group" action="/orderservlet.do?operate=toOrderList" method="post">

        <%--客户选择--%>
        <div class="form-group">
            <label for="customerName">客户：</label>
            <select class="form-control" id="customerName" name="customerId">
                <option value="0">请选择客户</option>
            </select>
        </div>

        <%--店员选择--%>
        <div class="form-group">
            <label for="empName">店员：</label>
            <select class="form-control" id="empName" name="empId">
                <option value="0">请选择店员</option>
            </select>
        </div>
        <%--日期选择--%>
        <div class="form-group">
            <label for="empName">日期：</label>
            <input id="orderDate" name="orderDate" type="text" value="" placeholder="日期" readonly class="order_datetime form-control">
        </div>

        <div id="toolbar" class="btn-group">
            <div class="form-group">
                <button id="btn_show_product" type="button" class="btn btn-default">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>购买
                </button>
                <button id="btn_none" type="button" class="btn btn-default">
                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                </button>
            </div>
        </div>
        <%--订单列表--%>
        <table id="tb_order_detail"></table>

        <%--提交订单按钮--%>
        <input type="hidden" id="jsonData" name="jsonData" value="">
        <input id="btn_submit_order" class="btn btn-primary" type="submit" value="提交订单">
        <%--<button id="btn_submit_order" class="btn btn-primary"></button>--%>
    </form>

</div>
</body>
</html>
