<%@ page import="com.shengdiyage.entity.ProductType" %>
<%@ page import="com.shengdiyage.service.ProductTypeService" %>
<%@ page import="com.shengdiyage.service.serrviceImplement.ProductTypeServiceImpl" %>
<%--
  Created by IntelliJ IDEA.
  User: Akari
  Date: 2017/7/8
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
//    设置request的字符集为utf8
    request.setCharacterEncoding("utf-8");
//    获取用户输入
    String productTypeName = request.getParameter("productTypeName");
    int productTypeId = Integer.parseInt(request.getParameter("productTypeId"));
    ProductTypeService productTypeService = new ProductTypeServiceImpl();
    ProductType productType = new ProductType(productTypeId,productTypeName);
    int result = productTypeService.updateProductTypeByType(productType);
    if (result > 0) {
        response.sendRedirect("/product/product.jsp");
    } else {
        out.print("<script>alert('添加失败！');history.back();</script>");
    }
%>
