<%@ page import="com.shengdiyage.entity.Admin" %>
<%@ page import="com.shengdiyage.service.AdminService" %>
<%@ page import="com.shengdiyage.service.serrviceImplement.AdminServiceImpl" %>
<%--
  Created by IntelliJ IDEA.
  User: Akari
  Date: 2017/7/4
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
//    String adminName = new String(request.getParameter("userName").getBytes("iso-8859-1"),"utf-8");
    request.setCharacterEncoding("utf-8");
    String adminName = request.getParameter("userName");
    String adminPassword = request.getParameter("userPassword");
    Admin admin = new Admin(adminName, adminPassword);
    AdminService adminService = new AdminServiceImpl();

    if (adminService.verifyAdmin(admin)) {
        admin = adminService.queryAdminByName(adminName);
        session.setAttribute("loginadmin",admin);
        response.sendRedirect("/admin/index.jsp");
    } else {
        out.print("<script>alert('登陆失败，请检查用户名和密码！');history.back();</script>");
    }
%>