<%--
  Created by IntelliJ IDEA.
  User: nekuata
  Date: 2017/7/22
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>选择商品</title>
    <c:set var="root" value="${pageContext.request.contextPath}"/>
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
