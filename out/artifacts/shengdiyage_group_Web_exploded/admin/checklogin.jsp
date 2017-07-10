<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.shengdiyage.entity.Admin" %>
<%--
  Created by IntelliJ IDEA.
  User: Akari
  Date: 2017/7/5
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%
    Admin admin = (Admin)session.getAttribute("loginadmin");
    if (admin == null) {
        response.sendRedirect("/index.jsp");
        return;
    }
%>
