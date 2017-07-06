<%@ page import="com.shengdiyage.model.Admin" %><%--
  Created by IntelliJ IDEA.
  User: Akari
  Date: 2017/7/5
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Admin admin = (Admin)session.getAttribute("loginuser");
    if (admin == null) {
        response.sendRedirect("/index.jsp");
        return;
    }
%>
