<%@ page import="com.shengdiyage.entity.Product" %>
<%@ page import="java.util.List" %>
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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>product</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="/product/css/product.css">
</head>
<body class="product-body">
<a class="product-navigate" href="/admin/right.jsp">首页</a><span class="product-navigate"> > 产品中心</span>
<table class="all-product-list" id="all-product-list">

    <%--产品搜索功能--%>
    <form action="/productservlet.do?operate=product" method="post">
        <tr class="product-table-bottom">
            <td colspan="7">
                <select name="typeId">
                    <option value="${requestScope.product.productType.typeId}">请选择类别</option>
                    <c:forEach items="${requestScope.producttypes}" var="productTypes">
                    <option value="${productTypes.typeId}" <c:if test="${productTypes.typeId == requestScope.product.productType.typeId}">selected</c:if>>${productTypes.typeName}</option>
                    </c:forEach>
                </select>
                <input name="productName" type="text" value="${requestScope.product.productName}" onclick="return this.value = ''">
                <input type="submit" value="搜索">
            </td>
        </tr>
    </form>

    <%--当前列内容的名称--%>
    <tr class="table-head">
        <td>
            <input id="select-all-product" name="check_product" type="checkbox" onclick="checkAll()">
        </td>
        <td>时间</td>
        <td>名称</td>
        <td>价格</td>
        <td>数量</td>
        <td>类型</td>
        <td>操作</td>
    </tr>
    <form id="products" name="products" action="" method="post">
    <%--<%--%>
        <%--List<Product> products = (List<Product>) request.getAttribute("products");--%>
        <%--List<ProductType> productTypes = (List<ProductType>) request.getAttribute("producttypes");--%>
        <%--for (int i = 0; i < products.size(); i++) {--%>
            <%--// 用于在列表中显示的时间--%>
            <%--String dateStr = DateTools.getStrByDate(products.get(i).getProductTime(), "yyyy-MM-dd");--%>
            <%--// 产品修改输入框默认显示的时间--%>
            <%--String dateStrAll = DateTools.getStrByDate(products.get(i).getProductTime(), "yyyy-MM-dd HH-mm-ss");--%>
    <%--%>--%>
        <c:forEach items="${requestScope.products}" var="products" varStatus="productst">

    <%--循环生成产品列表--%>
    <tr class="table-body" id="table-body${productst.count}">
        <td><input type="checkbox" name="check_product" value="${products.productId}" onclick="checkOne()"></td>
        <td>
            <fmt:formatDate value="${products.productTime}" pattern="yyyy-MM-dd" />
            <%--<%=dateStr %>--%>
        </td>
        <td>
            ${products.productName}
            <%--<%=products.get(i).getProductName() %>--%>
        </td>
        <td>
            ${products.productPrice}
            <%--<%=products.get(i).getProductPrice() %>--%>
        </td>
        <td>
            ${products.number}
            <%--<%=products.get(i).getNumber() %>--%>
        </td>
        <td>
            ${products.productType.typeName}
            <%--<%=products.get(i).getProductType().getTypeName() %>--%>
        </td>
        <td class="bottom-buttons">
            <a href="###" onclick="updateProduct(${productst.count})">修改</a>
            &nbsp;&nbsp;
            <a href="/productservlet.do?operate=delproduct&productId=${products.productId}" onclick="return confirm('确认删除？')">删除</a>
        </td>
    </tr>

    <%--修改产品的列表（默认隐藏）--%>
    <tr class="hide-update-table" id="update-product-table${productst.count}">
        <%--<form action="/productservlet.do?operate=updateproduct" method="post">--%>
            <%--隐藏域保存productId，一起提交--%>
            <td>确认提交<input type="hidden" name="productId" value="${products.productId}"></td>
            <td><input class="add-product" type="text" name="producttime" value="<fmt:formatDate value="${products.productTime}" pattern="yyyy-MM-dd HH-mm-ss" />"></td>
            <td><input class="add-product" type="text" name="productName"
                       value="${products.productName}"></td>
            <td><input class="add-product" type="text" name="productPrice"
                       value="${products.productPrice}"></td>
            <td><input class="add-product" type="text" name="number" value="${products.number}"></td>
            <td><select class="add-product" name="productTypeId">
                <option value="">请选择</option>
                <%--<%--%>
                    <%--for (int j = 0; j < productTypes.size(); j++) {--%>
                        <%--// 默认选中该类别--%>
                        <%--if (products.get(i).getProductType().getTypeId() == productTypes.get(j).getTypeId()) {--%>
                <%--%>--%>
                <c:forEach items="${requestScope.producttypes}" var="productTypes" varStatus="typest" >
                    <%--<c:if test="${products.productType.typeId == productTypes.typeId}">--%>
                    <%--<option selected="selected" value="${productTypes.typeId}">${productTypes.typeName}</option>--%>
                    <%--</c:if>--%>
                    <%--<c:if test="${products.productType.typeId != productTypes.typeId}">--%>
                    <%--<option value="${productTypes.typeId}">${productTypes.typeName}</option>--%>
                    <%--</c:if>--%>
                    <option <c:if test="${products.productType.typeId == productTypes.typeId}">selected="selected"</c:if> value="${productTypes.typeId}">${productTypes.typeName}</option>
                </c:forEach>
            </select></td>
            <td><input type="submit" value="确认" onclick="updateProductAction()"></td>
        <%--</form>--%>
    </tr>
    </c:forEach>
    <%--<%--%>
        <%--}--%>
    <%--%>--%>
    </form>

    <%--添加产品的列表（默认隐藏）--%>
    <tr class="hide-update-table" id="add-product-table">
        <form action="/productservlet.do?operate=addproduct" method="post">
            <td colspan="2"><span class="add-product-text">请按类别输入：</span></td>
            <td><input class="add-product" type="text" name="productName"></td>
            <td><input class="add-product" type="text" name="productPrice"></td>
            <td><input class="add-product" type="text" name="number"></td>
            <td><select class="add-product" name="productTypeId">
                <option>请选择</option>
                <c:forEach items="${requestScope.producttypes}" var="productTypes">
                        <option value="${productTypes.typeId}">${productTypes.typeName}</option>
                </c:forEach>
            </select></td>
            <td><input type="submit" value="确认"></td>
        </form>
    </tr>

    <%--分页功能--%>
    <form id="pageForm" action="/productservlet.do?operate=product&typeId=${requestScope.product.productType.typeId}&productName=${requestScope.product.productName}" method="post">
        <tr>
            <td colspan="7">
                <c:if test="${requestScope.curPage != 1}">
                    <a href="/productservlet.do?operate=product&curPage=1&pageSize=${requestScope.pageSize}&typeId=${requestScope.product.productType.typeId}&productName=${requestScope.product.productName}">首页</a>
                    &nbsp;
                    <a href="/productservlet.do?operate=product&curPage=${requestScope.curPage-1}&pageSize=${requestScope.pageSize}&typeId=${requestScope.product.productType.typeId}&productName=${requestScope.product.productName}">上一页</a>
                    &nbsp;
                </c:if>
                <c:if test="${requestScope.curPage == 1}">
                    <span>首页</span>
                    &nbsp;
                    <span>上一页</span>
                    &nbsp;
                </c:if>
                <span>当前是第 </span>
                <select name="curPage" onchange="return document.getElementById('pageForm').submit()">
                    <c:forEach begin="1" end="${requestScope.pages}" varStatus="stPages">
                        <option value="${stPages.count}" <c:if test="${requestScope.curPage == stPages.count}">selected</c:if>>${stPages.count}</option>
                    </c:forEach>
                </select>
                <span> 页</span>
                &nbsp;
                <c:if test="${requestScope.curPage != requestScope.pages}">
                    <a href="/productservlet.do?operate=product&input=next&curPage=${requestScope.curPage+1}&pageSize=${requestScope.pageSize}&typeId=${requestScope.product.productType.typeId}&productName=${requestScope.product.productName}">下一页</a>
                    &nbsp;
                    <a href="/productservlet.do?operate=product&input=lastpage&curPage=${requestScope.pages}&pageSize=${requestScope.pageSize}&typeId=${requestScope.product.productType.typeId}&productName=${requestScope.product.productName}">尾页</a>
                    &nbsp;
                </c:if>
                <c:if test="${requestScope.curPage == requestScope.pages}">
                    <span>下一页</span>
                    &nbsp;
                    <span>尾页</span>
                    &nbsp;
                </c:if>
                <%--输入每页显示的条目数，默认是5条--%>
                <span>每页显示条目：</span>
                <input name="pageSize" type="text" value="${requestScope.pageSize}">
                <input type="submit" value="确认">
                <%--<span>当前是第 ${requestScope.curPage} 页</span>--%>
            </td>
        </tr>
    </form>

    <%--显示添加产品的列表按钮&批量删除选项--%>
    <tr class="product-table-bottom">
        <td colspan="7">
            <a href="###" onclick="addTableTail()">添加产品</a>
            &nbsp;&nbsp;
            <a href="javascript:document.products.submit();" onclick="delMulAction()">删除选中产品</a>
        </td>
    </tr>
</table>
<br><br>

<%--类别列表--%>
<table>
    <tr class="type-list-body">
        <td>已有类别：</td>
    </tr>
    <form action="/productservlet.do?operate=updatetype" method="post">
    <tr class="type-list-body">
        <td>
            <%--循环生成类别列表和修改删除按钮--%>
            <c:forEach items="${requestScope.producttypes}" var="productTypes" varStatus="typest" >
            <span class="type-list" id="type-list${typest.count}">
                <span>${productTypes.typeName}</span>
                <a href="###" onclick="updateType(${typest.count})">修改</a>
                <a href="/productservlet.do?operate=deltype&typeId=${productTypes.typeId}" onclick="return confirm('确认删除？')">删除</a>
            </span>
            <%--默认隐藏的类别修改输入框--%>
            <span class="hide-update-table" id="update-type-input${typest.count}">
                <input type="text" name="productTypeName" value="${productTypes.typeName}"><%--隐藏域保存类别Id--%>
                <input type="hidden" name="productTypeId" value="${productTypes.typeId}">
                <input type="submit" value="确定">
            </span>
            </c:forEach>
        </td>
    </tr>
    </form>

    <%--添加产品类别（默认隐藏）--%>
    <form action="/productservlet.do?operate=addtype" method="post">
        <tr class="hide-update-table" id="add-type-table">
            <td><input type="text" name="productTypeName"><input type="submit" value="确认"></td>
        </tr>
        <tr class="type-list-body">
            <td><input type="button" value="添加" onclick="addProductType()"></td>
        </tr>
    </form>
</table>
</body>
<script src="/product/js/product.js"></script>
</html>
