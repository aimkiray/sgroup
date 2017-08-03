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
    <script src="${root}/order/js/orderlist-custom.js" type="text/javascript"></script>
</head>
<body>
<div class="panel-body">

    <div class="panel panel-default">
        <div class="panel-heading">查询条件</div>
        <div class="panel-body">

            <form class="form-inline" id="formSearch">

                <%--客户选择框--%>
                <div class="form-group" style="margin-top: 5px">
                    <label for="customerName">客户：</label>
                    <select class="form-control" id="customerName">
                        <option value="0">请选择客户</option>
                    </select>
                </div>

                <%--员工选择框--%>
                <div class="form-group" style="margin-top: 5px">
                    <label for="empName">员工：</label>
                    <select class="form-control" id="empName">
                        <option value="0">请选择员工</option>
                    </select>
                </div>

                <%--确认按钮--%>
                <button type="button" id="btn_query" class="btn btn-primary" style="margin-top: 5px;margin-left: 10px" >查询</button>
            </form>

        </div>
    </div>

    <h3>订单列表</h3>

    <table class="table table-striped table-bordered table-hover" id="show_order_info">
        <tr>
            <td>订单编号</td>
            <td>客户</td>
            <td>店员</td>
            <td>时间</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${requestScope.orders}" var="order">
        <tr>
            <td>${order.orderId}</td>
            <td>${order.customer.customerName}</td>
            <td>${order.employee.empName}</td>
            <td><fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
            <td>
                <div id="toolbar" class="btn-group">
                <button type="button" value="${order.orderId}" onclick="btnToDetail(this.value)" class="btn btn-default">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>详情
                </button>
                <button type="button" value="${order.orderId}" onclick="btnToEdit(this.value)" class="btn btn-default">
                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                </button>
                <button type="button" value="${order.orderId}" onclick="btnToDel(this.value)" class="btn btn-default">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                </button>
                </div>
            </td>
        </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>
