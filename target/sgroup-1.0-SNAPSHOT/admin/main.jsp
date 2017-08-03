<%@ page import="com.sgroup.entity.Admin" %>
<%--
  Created by IntelliJ IDEA.
  User: Akari
  Date: 2017/7/5
  Time: 8:52
  To change this template use FileDemo | Settings | FileDemo Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>美国圣地亚戈集团</title>
    <link href="/admin/css/one.css" rel="stylesheet" type="text/css">
</head>
<frameset rows="22%,*" noresize frameborder="no" >
    <%--顶部页面--%>
    <frame src="/admin/top.jsp" scrolling="no"/>
    <frameset cols="20%,*">
        <%--左边的页面（选项）--%>
        <frame src="/admin/left.jsp"/>
        <%--右边的页面（操作页面）--%>
        <frame src="/admin/right.jsp" name="right"/>
    </frameset>
</frameset>
如果没有
<noframes>
    <div>(ノ｀Д)ノ</div>
</noframes>
<script src="/admin/js/index.js"></script>
</html>
