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
    <link type="text/css" rel="stylesheet" href="${root}/bootstrap/css/product-custom.css">
    <link type="text/css" rel="stylesheet" href="${root}/bootstrap/css/bootstrap-datetimepicker.min.css">

    <script src="${root}/js/jquery-3.2.1.min.js" type="text/javascript"></script>
    <script src="${root}/bootstrap/js/bootstrap.js" type="text/javascript"></script>
    <script src="${root}/bootstrap/js/bootstrap-table.js" type="text/javascript"></script>
    <script src="${root}/bootstrap/js/bootstrap-table-zh-CN.js" type="text/javascript"></script>
    <script src="${root}/bootstrap/extension/editable/bootstrap-editable.js" type="text/javascript"></script>
    <script src="${root}/bootstrap/extension/editable/bootstrap-table-editable.js" type="text/javascript"></script>
    <script src="${root}/bootstrap/js/product-custom.js" type="text/javascript"></script>
    <script src="${root}/js/bootbox.min.js" type="text/javascript"></script>
    <script src="${root}/bootstrap/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
    <script src="${root}/bootstrap/js/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"></script>
</head>
<body>
<div class="panel-body">
    <div class="panel panel-default">
        <div class="panel-heading">查询条件</div>
        <div class="panel-body">
        </div>
    </div>

    <div id="toolbar" class="btn-group">

        <form class="form-inline" id="formSearch">

            <%--产品类别选择框--%>
            <div class="form-group" style="margin-top: 5px">
                <label for="typeId">类别：</label>
                <select class="form-control" id="typeId">
                    <option value="0">请选择类别</option>
                    <c:forEach items="${requestScope.producttypes}" var="productTypes">
                        <option value="${productTypes.typeId}" <c:if test="${productTypes.typeId == requestScope.product.productType.typeId}">selected</c:if>>${productTypes.typeName}</option>
                    </c:forEach>
                </select>
            </div>

            <%--产品名称模糊搜索--%>
            <div class="form-group" style="margin-top: 5px;margin-left: 10px">
                <label for="productName">名称：</label>
                <input type="text" class="form-control" id="productName" placeholder="产品名称">
            </div>

            <%--确认按钮--%>
            <button type="button" id="btn_query" class="btn btn-primary" style="margin-top: 5px;margin-left: 10px" >查询</button>
        </form>

    </div>
    <table id="tb_product"></table>
</div>
</body>
</html>
