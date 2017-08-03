<%--
  Created by IntelliJ IDEA.
  User: nekuata
  Date: 2017/7/31
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Cookie cookieAdminName = new Cookie("admin","");
    cookieAdminName.setMaxAge(-1);
    cookieAdminName.setPath("/");
    response.addCookie(cookieAdminName);
    response.sendRedirect("/admin/login.jsp");
%>
