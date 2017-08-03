<%--
  Created by IntelliJ IDEA.
  User: nekuata
  Date: 2017/7/21
  Time: 18:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>修改产品</title>
</head>
<script>
    $(document).ready(function () {
        /*$("#btn_add_product").click(function () {
            $.ajax({
                url: "/productservlet.do?operate=addproduct&flag=ajax",
                cache: false, //禁用缓存
                processData: false, //不对数据进行处理
                contentType: false,
                type: "post",
                data: new FormData($("#uploadForm")[0]),
                success: function (data) {
                    if (data == "true") {
                        $("#tb_product").bootstrapTable('refresh');
                        $(".bootbox-close-button").click();
                    } else {
                        alert("修改失败");
                        $(".bootbox-close-button").click();
                    }
                },
                error: function () {
                    alert("通信失败");
                    $(".bootbox-close-button").click();
                }
            });
        });*/
        $("#btn_update_product").click(function () {
            $.ajax({
                url: "/productservlet.do?operate=updateproduct&flag=ajax",
                cache: false, //禁用缓存
                processData: false, //不对数据进行处理
                contentType: false,
                type: "post",
                data: new FormData($("#uploadForm")[0]),
                success: function (data) {
                    if (data == "true") {
                        $("#tb_product").bootstrapTable('refresh');
                        $(".bootbox-close-button").click();
                    } else {
                        alert("修改失败");
                        $(".bootbox-close-button").click();
                    }
                },
                error: function () {
                    alert("通信失败");
                    $(".bootbox-close-button").click();
                }
            });
        });

        $(".form_datetime").datetimepicker({
            format: "yyyy-mm-dd hh:ii:ss",
            autoclose: true,
            todayBtn: true
        });

        $(document).click(function(e){
            var _con = $(".modal-content");   // 设置目标区域
            if(!_con.is(e.target) && _con.has(e.target).length === 0){ // Mark 1
                $(".bootbox-close-button").click();
            }
        });
    });
</script>
<body>
<c:set var="product" value="${requestScope.product}" />
<fmt:formatDate value="${product.productTime}" pattern="yyyy-MM-dd HH:mm:ss" var="productTime"/>
<form id="uploadForm" enctype="multipart/form-data">
    <input type="hidden" name="productId" value="${product.productId}">
    <div class="form-group">
        <label for="productName">名称</label>
        <input type="text" class="form-control" id="productName" name="productName" value="${product.productName}" placeholder="君の名は。">
    </div>
    <div class="form-group">
        <label for="productPrice">价格</label>
        <input type="text" class="form-control" id="productPrice" name="productPrice" value="${product.productPrice}" placeholder="价格">
    </div>
    <div class="form-group">
        <label for="number">库存</label>
        <input type="text" class="form-control" id="number" name="number" value="${product.number}" placeholder="库存">
    </div>
    <div class="form-group">
        <label for="productTime">日期</label>
        <input class="form_datetime form-control" id="productTime" name="productTime" type="text" value="${productTime}" placeholder="日期" readonly>
    </div>
    <div class="form-group">
        <label for="typeId">类别：</label>
        <select class="form-control" id="typeId" name="productTypeId">
            <option value="0">请选择类别</option>
            <c:forEach items="${requestScope.productTypes}" var="productTypes">
                <option value="${productTypes.typeId}" <c:if test="${productTypes.typeId == requestScope.product.productType.typeId}">selected</c:if>>${productTypes.typeName}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label for="uploadPic">File input</label>
        <input type="file" id="uploadPic" name="uploadPic">
        <p class="help-block">产品图片上传</p>
    </div>

    <button type="button" id="btn_update_product" class="btn btn-default">提交</button>
</form>
</body>
</html>
