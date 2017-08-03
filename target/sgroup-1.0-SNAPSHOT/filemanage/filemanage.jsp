<%--
  Created by IntelliJ IDEA.
  User: nekuata
  Date: 2017/7/15
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>某存在感稀薄的文件管理系统</title>
</head>
<body>
<table>
    <tr>
        <td>名称</td>
        <td>上传日期</td>
        <td>操作</td>
    </tr>
    <%--使用jstl循环生成文件列表--%>
    <c:forEach items="${requestScope.fileDemos}" var="fileDemo" varStatus="fileSt">
        <tr>
            <td>${fileDemo.name}</td>
            <td>${fileDemo.date}</td>
            <td>
                <a href="/filemanageservlet.do?operate=delFile&fileId=${fileDemo.id}">删除</a>
                <a href="/filemanageservlet.do?operate=downloadFile&fileName=${fileDemo.name}">下载</a>
            </td>
        </tr>
    </c:forEach>
    <form action="/filemanageservlet.do?operate=addFile" method="post" enctype="multipart/form-data">
    <tr>
        <td colspan="4">
            <input type="file" name="name">
            <input type="submit" value="上传">
        </td>
    </tr>
    </form>
    <tr>
    <tr>
        <td colspan="4">
            <c:if test="${requestScope.curPage != 1}">
                <a href="/filemanageservlet.do?operate=fileList&curPage=1&pageSize=${requestScope.pageSize}">首页</a>
                &nbsp;
                <a href="/filemanageservlet.do?operate=fileList&curPage=${requestScope.curPage-1}&pageSize=${requestScope.pageSize}">上一页</a>
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
                <a href="/filemanageservlet.do?operate=fileList&input=next&curPage=${requestScope.curPage+1}&pageSize=${requestScope.pageSize}">下一页</a>
                &nbsp;
                <a href="/filemanageservlet.do?operate=fileList&input=lastpage&curPage=${requestScope.pages}&pageSize=${requestScope.pageSize}">尾页</a>
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
        </td>
    </tr>
</table>
</body>
</html>
