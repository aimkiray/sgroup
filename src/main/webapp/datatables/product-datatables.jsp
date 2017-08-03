<%--
  Created by IntelliJ IDEA.
  User: nekuata
  Date: 2017/7/19
  Time: 8:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>test</title>
    <c:set var="root" value="${pageContext.request.contextPath}"/>
    <link type="text/css" rel="stylesheet" href="${root}/datatables/css/jquery.dataTables.css">
    <link type="text/css" rel="stylesheet" href="${root}/datatables/css/datatables-custom.css">
    <link type="text/css" rel="stylesheet" href="${root}/datatables/css/buttons.dataTables.min.css">
    <link type="text/css" rel="stylesheet" href="${root}/datatables/css/select.dataTables.min.css">
    <link type="text/css" rel="stylesheet" href="${root}/datatables/css/editor.dataTables.min.css">

    <script src="${root}/js/jquery-3.2.1.min.js" type="text/javascript"></script>
    <script src="${root}/datatables/js/jquery.dataTables.min.js" type="text/javascript"></script>
    <script src="${root}/datatables/js/datatables-custom.js" type="text/javascript"></script>
    <script src="${root}/datatables/js/dataTables.buttons.min.js" type="text/javascript"></script>
    <script src="${root}/datatables/js/dataTables.select.min.js" type="text/javascript"></script>
    <script src="${root}/datatables/js/dataTables.editor.min.js" type="text/javascript"></script>
</head>
<body>
<table id="example" class="display" width="100%" cellspacing="0">
    <thead>
    <tr>
        <th></th>
        <th>名称</th>
        <th>价格</th>
        <th>数量</th>
        <th>类别</th>
        <th width="18%">入库时间</th>
        <th>文件名</th>
    </tr>
    </thead>
</table>

</body>
</html>
