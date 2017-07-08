<%@ page import="com.shengdiyage.service.ProductService" %>
<%@ page import="com.shengdiyage.service.serrviceImplement.ProductServiceImpl" %>
<%@ page import="com.shengdiyage.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="com.shengdiyage.service.ProductTypeService" %>
<%@ page import="com.shengdiyage.service.serrviceImplement.ProductTypeServiceImpl" %>
<%@ page import="com.shengdiyage.model.ProductType" %>
<%@ page import="com.shengdiyage.utils.DateTools" %>
<%--
  Created by IntelliJ IDEA.
  User: Akari
  Date: 2017/7/5
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>product</title>
    <link rel="stylesheet" href="css/product.css">
</head>
<body>
<table class="all-product-list" id="all-product-list">
    <tr class="table-head">
        <td><input id="select-all-product" type="checkbox"></td>
        <td>时间</td>
        <td>名称</td>
        <td>价格</td>
        <td>数量</td>
        <td>类型</td>
        <td>操作</td>
    </tr>
    <%
        ProductService productService = new ProductServiceImpl();
        ProductTypeService productTypeService = new ProductTypeServiceImpl();
        List<ProductType> productTypes = productTypeService.queryAllProductType();
        List<Product> products = productService.queryProduct();
        for (int i = 0; i < productService.queryProduct().size(); i++) {
            String dateStr = DateTools.getStrByDate(products.get(i).getProductTime(),"yyyy-MM-dd");
            String dateStrAll = DateTools.getStrByDate(products.get(i).getProductTime(),"yyyy-MM-dd HH-mm-ss");
    %>
    <%--循环生成产品列表--%>
    <tr class="table-body">
        <td><input type="checkbox"></td>
        <td><%=dateStr %></td>
        <td><%=products.get(i).getProductName() %></td>
        <td><%=products.get(i).getProductPrice() %></td>
        <td><%=products.get(i).getNumber() %></td>
        <td><%=productTypeService.queryTypeByTypeId(products.get(i).getProductType().getTypeId()).getTypeName() %></td>
        <td class="bottom-buttons"><a href="###" onclick="updateProduct(<%=i+1 %>)" >修改</a>&nbsp;&nbsp;<a href="product_del.jsp?productId=<%=products.get(i).getProductId() %>" onclick="return confirm('确认删除？')">删除</a></td>
    </tr>

    <%--修改产品的列表（默认隐藏）--%>
    <tr class="hide-update-table" id="update-product-table<%=i+1 %>">
        <form action="/product/product_update.jsp" method="post">
            <%--隐藏域保存productId，一起提交。--%>
            <td>确认提交<input type="hidden" name="productId" value="<%=products.get(i).getProductId()%>"></td>
            <td><input class="add-product" type="text" name="producttime" value="<%=dateStrAll %>"></td>
            <td><input class="add-product" type="text" name="productName" value="<%=products.get(i).getProductName() %>"></td>
            <td><input class="add-product" type="text" name="productPrice" value="<%=products.get(i).getProductPrice() %>"></td>
            <td><input class="add-product" type="text" name="number" value="<%=products.get(i).getNumber() %>"></td>
            <td><select class="add-product" name="productTypeId">
                <option value="">请选择</option>
                <%
                    for (int j = 0; j < productTypes.size(); j++) {
                        // 默认选中该类别
                        if(products.get(j).getProductType().getTypeId() == productTypes.get(j).getTypeId())
                        {
                %>
                <option selected="selected" value="<%=productTypes.get(j).getTypeId()%>"><%=productTypes.get(j).getTypeName()%></option>
                <%
                            continue;
                        }
                %>
                <option value="<%=productTypes.get(j).getTypeId()%>"><%=productTypes.get(j).getTypeName()%></option>
                <%
                    }
                %>
            </select></td>
            <td><input type="submit" value="确认"></td>
        </form>
    </tr>
    <%
        }
    %>
    <%--添加产品的列表（默认隐藏）--%>
    <tr class="hide-update-table" id="add-product-table">
            <form action="/product/product_add.jsp" method="post">
                <td colspan="2"><span class="add-product-text">请按类别输入：</span></td>
                <td><input class="add-product" type="text" name="productName"></td>
                <td><input class="add-product" type="text" name="productPrice"></td>
                <td><input class="add-product" type="text" name="number"></td>
                <td><select class="add-product" name="productTypeId">
                    <option value="">请选择</option>
                    <%
                        for (int i = 0; i < productTypes.size(); i++) {
                    %>
                    <option value="<%=productTypes.get(i).getTypeId()%>"><%=productTypes.get(i).getTypeName()%></option>
                    <%
                        }
                    %>
                </select></td>
                <td><input type="submit" value="确认"></td>
            </form>
    </tr>
    <tr>
        <td colspan="7"><input type="button" value="添加" onclick="addTableTail()"></td>
    </tr>
</table>
</body>
<script src="/product/js/product.js"></script>
</html>
