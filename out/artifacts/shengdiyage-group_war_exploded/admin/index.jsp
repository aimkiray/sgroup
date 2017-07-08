<%@ page import="com.shengdiyage.model.Admin" %>
<%--
  Created by IntelliJ IDEA.
  User: Akari
  Date: 2017/7/5
  Time: 8:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ include file="checklogin.jsp" %>
<html>
<head>
    <link href="/admin/css/one.css" rel="stylesheet" type="text/css">
</head>
<frameset rows="22%,*" noresize frameborder="no" >
    <frame src="/admin/top.jsp" scrolling="no"/>
    <frameset cols="20%,*">
        <frame src="/admin/left.jsp"/>
        <frame src="/admin/right.jsp" name="right"/>
    </frameset>
</frameset>
<noframes>
    <div>(ノ｀Д)ノ</div>
</noframes>
<script type="text/javascript" src="js/one.js"></script>
</html>
