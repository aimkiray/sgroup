<%@ page import="com.shengdiyage.model.Admin" %>
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
//    String aname = new String(request.getParameter("userName").getBytes("iso-8859-1"),"utf-8");
    request.setCharacterEncoding("utf-8");
    String aname = request.getParameter("userName");
    String apassword = request.getParameter("userPassword");
    Admin admin = new Admin(aname, apassword);
    AdminService adminService = new AdminServiceImpl();

    if (adminService.verifyAdmin(admin)) {
        admin = adminService.queryAdminByName(aname);
        session.setAttribute("loginadmin",admin);
        response.sendRedirect("/admin/index.jsp");
    } else {
        out.print("<script>alert('登陆失败，请检查用户名和密码！');history.back();</script>");
    }
%>