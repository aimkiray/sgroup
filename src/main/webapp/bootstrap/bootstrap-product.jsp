<%--
  Created by IntelliJ IDEA.
  User: nekuata
  Date: 2017/7/20
  Time: 8:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>产品中心</title>
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
    <script src="${root}/bootstrap/js/product-custom.js" type="text/javascript"></script>
    <%--<link type="text/css" rel="stylesheet" href="${root}/content/bootstrap/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="${root}/content/bootstrap/bootstrap-theme.css">
    <link type="text/css" rel="stylesheet" href="${root}/content/bootstrap-table/bootstrap-table.css">
    <link type="text/css" rel="stylesheet" href="${root}/content/bootstrap3-editable/css/bootstrap-editable.css">
    <link type="text/css" rel="stylesheet" href="${root}/bootstrap/css/product-custom.css">
    <link type="text/css" rel="stylesheet" href="${root}/content/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css">

    <script src="${root}/content/jQuery/jquery-3.2.1.min.js" type="text/javascript"></script>
    <script src="${root}/content/bootstrap/bootstrap.js" type="text/javascript"></script>
    <script src="${root}/content/bootstrap-table/bootstrap-table.js" type="text/javascript"></script>
    <script src="${root}/content/bootstrap-table/locale/bootstrap-table-zh-CN.min.js" type="text/javascript"></script>
    <script src="${root}/content/bootstrap-table/extensions/editable/bootstrap-table-editable.min.js" type="text/javascript"></script>
    <script src="${root}/content/bootstrap3-editable/js/bootstrap-editable.min.js" type="text/javascript"></script>
    <script src="${root}/bootstrap/js/product-custom.js" type="text/javascript"></script>
    <script src="${root}/js/bootbox.min.js" type="text/javascript"></script>
    <script src="${root}/content/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
    <script src="${root}/content/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"></script>--%>
</head>

<body>

<div class="panel-body">
    <div class="panel panel-default">
        <div class="panel-heading">查询条件</div>
        <div class="panel-body">

                    <form class="form-inline" id="formSearch">

                        <%--产品类别选择框--%>
                        <div class="form-group" style="margin-top: 5px">
                            <label for="typeIdSearch">类别：</label>
                            <select class="form-control" id="typeIdSearch">
                                <option value="0">请选择类别</option>
                            </select>
                        </div>

                        <%--产品名称模糊搜索--%>
                        <div class="form-group" style="margin-top: 5px;margin-left: 10px">
                            <label for="productNameSearch">名称：</label>
                            <input type="text" class="form-control" id="productNameSearch" placeholder="产品名称">
                        </div>

                        <%--确认按钮--%>
                <button type="button" id="btn_query" class="btn btn-primary" style="margin-top: 5px;margin-left: 10px" >查询</button>
            </form>

        </div>
    </div>

    <div id="toolbar" class="btn-group">
        <button id="btn_add" type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
        </button>
        <button id="btn_edit" type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
        </button>
        <button id="btn_delete" type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
        </button>
    </div>
    <table id="tb_product"></table>
</div>
</body>
</html>
