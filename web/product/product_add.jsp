<%@ page import="com.shengdiyage.service.ProductService" %>
<%@ page import="com.shengdiyage.service.serrviceImplement.ProductServiceImplement" %>
<%@ page import="com.shengdiyage.model.Product" %><%--
  Created by IntelliJ IDEA.
  User: Akari
  Date: 2017/7/5
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("utf-8");
    String pname = request.getParameter("pname");
    int pprice = Integer.parseInt(request.getParameter("pprice"));
    int pnumber = Integer.parseInt(request.getParameter("pnumber"));
    int ptype = Integer.parseInt(request.getParameter("ptype"));
    ProductService productService = new ProductServiceImplement();
    Product product = new Product(pname,pprice,pnumber,ptype);
    int result = productService.addProduct(product);
    if (result > 0) {
        out.print("<script>alert('添加成功！');history.back();</script>");
    } else if (result == 0) {
        out.print("<script>alert('添加失败！请检查是否重名。');history.back();</script>");
    } else {
        out.print("<script>alert('添加失败！');history.back();</script>");
    }
%>
