<%--
  Created by IntelliJ IDEA.
  User: nekuata
  Date: 2017/7/19
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>jsGrip</title>
    <c:set var="root" value="${pageContext.request.contextPath}" />
    <link type="text/css" rel="stylesheet" href="${root}/jsgrip/css/jsgrid.min.css" />
    <link type="text/css" rel="stylesheet" href="${root}/jsgrip/css/jsgrid-theme.min.css" />

    <script type="text/javascript" src="${root}/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${root}/jsgrip/js/jsgrid.min.js"></script>
    <script type="text/javascript" src="${root}/jsgrip/js/product-table.js"></script>
</head>
<%--<script>--%>
    <%--$(document).ready(function () {--%>
        <%--alert(1);--%>
    <%--})--%>
<%--</script>--%>
<body>
<div id="jsGrid"></div>

</body>
</html>
