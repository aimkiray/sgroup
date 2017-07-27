<%--
  Created by IntelliJ IDEA.
  User: nekuata
  Date: 2017/7/22
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>选择商品</title>
    <c:set var="root" value="${pageContext.request.contextPath}"/>
    <script src="${root}/order/js/order-add-product.js"></script>
</head>
<body>
<div class="panel-body">

    <%--<form action="" >--%>
    <%--<div id="toolbar" class="btn-group">
        <form class="form-inline" id="formSearch">

            &lt;%&ndash;客户选择&ndash;%&gt;
            <div class="form-group">
                <label for="customName">客户：</label>
                <select class="form-control" id="customName">
                    <option value="0">请选择客户</option>
                </select>
            </div>

            &lt;%&ndash;店员选择&ndash;%&gt;
            <div class="form-group">
                <label for="empName">店员：</label>
                <select class="form-control" id="empName">
                    <option value="0">请选择店员</option>
                </select>
            </div>
            &lt;%&ndash;日期选择&ndash;%&gt;
                <div class="form-group">
                    <label for="empName">日期：</label>
                    <input id="orderDate" name="orderDate" type="text" value="" placeholder="日期" readonly class="order_datetime form-control">
                </div>
        </form>
    </div>--%>
    <table id="tb_product"></table>
    <%--</form>--%>
    <%--生成订单按钮--%>
    <input id="btn_generate_order" class="btn btn-primary" value="生成订单">
</div>
</body>
</html>
