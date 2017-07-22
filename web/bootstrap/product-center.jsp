<%--
  Created by IntelliJ IDEA.
  User: nekuata
  Date: 2017/7/20
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>产品中心</title>
    <c:set var="root" value="${pageContext.request.contextPath}"/>
    <link type="text/css" rel="stylesheet" href="${root}/">
    <link type="text/css" rel="stylesheet" href="${root}/bootstrap/css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="${root}/bootstrap/css/bootstrap-theme.css">
    <link type="text/css" rel="stylesheet" href="${root}/bootstrap/css/bootstrap-table.css">
    <link type="text/css" rel="stylesheet" href="${root}/bootstrap/css/bootstrap-editable.css">
    <link type="text/css" rel="stylesheet" href="${root}/bootstrap/css/product-custom.css">

    <script src="${root}/js/jquery-3.2.1.min.js" type="text/javascript"></script>
    <script src="${root}/bootstrap/js/bootstrap.js" type="text/javascript"></script>
    <script src="${root}/bootstrap/js/bootstrap-table.js" type="text/javascript"></script>
    <script src="${root}/bootstrap/js/bootstrap-table-zh-CN.js" type="text/javascript"></script>
    <script src="${root}/bootstrap/extension/editable/bootstrap-editable.js" type="text/javascript"></script>
    <script src="${root}/bootstrap/extension/editable/bootstrap-table-editable.js" type="text/javascript"></script>
    <script src="${root}/bootstrap/js/product-custom-new.js" type="text/javascript"></script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content" id="userLogin">
    <div id="dateSearch">
        <span><strong>开始日期: </strong></span>
        <input type="text" class="laydate-icon startDate"  placeholder="开始日期">
        <span><strong>结束日期: </strong></span>
        <input type="text" class="laydate-icon endDate"  placeholder="结束日期">
        <span><strong>用户ID: </strong></span>
        <input type="text" class="default-input form-control imuserid"   placeholder="请输入用户id">
        <button type="button" class="btn btn btn-info search"> <i class="fa fa-search"></i> 搜索</button>
    </div>
    <div class="row" id="infoArea">
        <div class="col-sm-12" style="padding: 0 10px;">
            <ul class="nav nav-tabs" id="navList">
                <li data-name = "loginLogTab" class="active"><a data-toggle="tab" href="#loginLogTab"><i class="fa fa-user"></i>登录信息</a> </li>
                <li data-name = "receiveLogTab" class=""><a data-toggle="tab" href="#receiveLogTab"><i class="fa fa-briefcase"></i> 订购信息</a> </li>
                <li data-name = "socketInputTab" class=""><a data-toggle="tab" href="#socketInputTab"><i class="fa fa-briefcase"></i> 反馈信息</a> </li>
                <li data-name = "socketOutputTab" class=""><a data-toggle="tab" href="#socketOutputTab"><i class="fa fa-briefcase"></i> 出行信息</a> </li>
            </ul>
            <div class="tab-content" id="tabContent">
                <div id="toolbar" class="btn-group">
                    <button id="btn_add" type="button" class="btn btn-info btn-sm rightSize">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                    </button>
                    <button id="btn_edit" type="button" class="btn btn-info btn-sm rightSize">
                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                    </button>
                    <button id="btn_delete" type="button" class="btn btn-info btn-sm rightSize">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                    </button>
                </div>
                <div id="loginLogTab" class="tab-pane active">
                    <div class="table-responsive">
                        <table id="loginLog-table"></table>
                    </div>
                </div>
                <div id="receiveLogTab" class="tab-pane">
                    <div class="table-responsive">
                        <table id="receiveLog-table"></table>
                    </div>
                </div>
                <div id="socketInputTab" class="tab-pane">
                    <div class="table-responsive">
                        <table id="sockctInput-table"></table>
                    </div>
                </div>
                <div id="socketOutputTab" class="tab-pane">
                    <div class="table-responsive">
                        <table id="sockctOutput-table"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
