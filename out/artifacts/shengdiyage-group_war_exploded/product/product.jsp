<%@ page import="com.shengdiyage.service.ProductService" %>
<%@ page import="com.shengdiyage.service.serrviceImplement.ProductServiceImpl" %>
<%@ page import="com.shengdiyage.entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="com.shengdiyage.service.ProductTypeService" %>
<%@ page import="com.shengdiyage.service.serrviceImplement.ProductTypeServiceImpl" %>
<%@ page import="com.shengdiyage.entity.ProductType" %>
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
    <%--列表名称--%>
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
            // 用于在列表中显示的时间
            String dateStr = DateTools.getStrByDate(products.get(i).getProductTime(), "yyyy-MM-dd");
            // 产品修改输入框默认显示的时间
            String dateStrAll = DateTools.getStrByDate(products.get(i).getProductTime(), "yyyy-MM-dd HH-mm-ss");
    %>
    <%--循环生成产品列表--%>
    <tr class="table-body" id="table-body<%=i %>">
        <td><input type="checkbox"></td>
        <td><%=dateStr %>
        </td>
        <td><%=products.get(i).getProductName() %>
        </td>
        <td><%=products.get(i).getProductPrice() %>
        </td>
        <td><%=products.get(i).getNumber() %>
        </td>
        <td><%=productTypeService.queryTypeByTypeId(products.get(i).getProductType().getTypeId()).getTypeName() %>
        </td>
        <td class="bottom-buttons">
            <a href="###" onclick="updateProduct(<%=i %>)">修改</a>
            &nbsp;&nbsp;
            <a href="product_del.jsp?productId=<%=products.get(i).getProductId() %>" onclick="return confirm('确认删除？')">删除</a>
        </td>
    </tr>

    <%--修改产品的列表（默认隐藏）--%>
    <tr class="hide-update-table" id="update-product-table<%=i %>">
        <form action="/product/product_update.jsp" method="post">
            <%--隐藏域保存productId，一起提交--%>
            <td>确认提交<input type="hidden" name="productId" value="<%=products.get(i).getProductId()%>"></td>
            <td><input class="add-product" type="text" name="producttime" value="<%=dateStrAll %>"></td>
            <td><input class="add-product" type="text" name="productName"
                       value="<%=products.get(i).getProductName() %>"></td>
            <td><input class="add-product" type="text" name="productPrice"
                       value="<%=products.get(i).getProductPrice() %>"></td>
            <td><input class="add-product" type="text" name="number" value="<%=products.get(i).getNumber() %>"></td>
            <td><select class="add-product" name="productTypeId">
                <option value="">请选择</option>
                <%
                    for (int j = 0; j < productTypes.size(); j++) {
                        // 默认选中该类别
                        if (products.get(i).getProductType().getTypeId() == productTypes.get(j).getTypeId()) {
                %>
                <option selected="selected"
                        value="<%=productTypes.get(j).getTypeId()%>"><%=productTypes.get(j).getTypeName()%>
                </option>
                <%
                        continue;
                    }
                %>
                <option value="<%=productTypes.get(j).getTypeId()%>"><%=productTypes.get(j).getTypeName()%>
                </option>
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
                <option value="<%=productTypes.get(i).getTypeId()%>"><%=productTypes.get(i).getTypeName()%>
                </option>
                <%
                    }
                %>
            </select></td>
            <td><input type="submit" value="确认"></td>
        </form>
    </tr>
    <%--显示添加产品的列表--%>
    <tr>
        <td colspan="7"><input type="button" value="添加" onclick="addTableTail()"></td>
    </tr>
</table>
<table>
    <tr class="type-list-body">
        <td>已有类别：</td>
    </tr>
    <tr class="type-list-body">
        <td>
            <%--循环生成类别列表和修改删除按钮--%>
            <%
                for (int i = 0; i < productTypes.size(); i++) {
            %>
            <span class="type-list" id="type-list<%=i %>">
                    <span><%=productTypes.get(i).getTypeName() %></span>
                    <a href="###" onclick="updateType(<%=i %>)">修改</a>
                    <a href="producttype_del.jsp?typeId=<%=productTypes.get(i).getTypeId()%>">删除</a>
                </span>
            <%--默认隐藏的类别修改输入框--%>
            <span class="hide-update-table" id="update-type-input<%=i %>">
                    <form action="producttype_update.jsp" method="post">
                        <input type="text" name="productTypeName" value="<%=productTypes.get(i).getTypeName() %>">
                        <%--隐藏域保存类别Id--%>
                        <input type="hidden" name="productTypeId" value="<%=productTypes.get(i).getTypeId() %>">
                        <input type="submit" value="确定">
                    </form>
                </span>
            <%
                }
            %>
        </td>
    </tr>
    <%--添加产品类别（默认隐藏）--%>
    <form action="producttype_add.jsp" method="post">
        <tr class="hide-update-table" id="add-type-table">
            <td><input type="text" name="productTypeName"><input type="submit" value="确认"></td>
        </tr>
        <tr class="type-list-body">
            <td><input type="button" value="添加" onclick="addProductType()"></td>
        </tr>
    </form>
</table>
</body>
<script src="js/product.js"></script>
</html>
