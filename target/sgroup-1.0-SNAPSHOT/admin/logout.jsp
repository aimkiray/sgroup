<%--
  Created by IntelliJ IDEA.
  User: Akari
  Date: 2017/7/6
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
//    session.removeAttribute("admin");
    session.invalidate();
    response.sendRedirect("/admin/login.jsp");
%>
