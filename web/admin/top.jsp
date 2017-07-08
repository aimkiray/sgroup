<%@ page import="com.shengdiyage.model.Admin" %>
<%--
  Created by IntelliJ IDEA.
  User: Akari
  Date: 2017/7/5
  Time: 9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="checklogin.jsp" %>
<html>
<head>
    <link href="/admin/css/frame.css" rel="stylesheet" type="text/css">
    <title>Akari</title>
</head>
<body class="top-body">
<!--<p class="toptitle">美国圣地亚戈国际集团</p>-->
<!--<div class="toparea">-->
<!--<p>美国圣地亚戈农业基地，金坷垃的研发生产中心，全球五百强企业第一。金坷垃生产基地坐落于密林环绕的圣地亚戈，有5000名特种兵日夜把守、3000海军陆战队保护。在美国总统的历届竞选中，金坷垃集团也发挥了举足轻重的作用，历届美国总统上任，必定到这里亲临拜访并参拜金坷垃科研中心比如奥观海。</p>-->
<!--</div>-->
<div class="top-logo">
    <table>
        <tr>
            <td><img src="/logo/mainlogo.jpg" style="width: 50%"></td><td class="title">您好～<%=admin.getAname() %>！欢迎光临美国圣地亚戈国际农业集团！</td>
        </tr>
    </table>
</div>
<div class="subtitle">
    <span style="text-shadow: blue 2px 2px 5px;margin-bottom: 0px">上帝中心</span><a href="/admin/logout.jsp" style="position: fixed; right: 10px;">退出</a><br/>
    <hr style="border-bottom: solid blue;box-shadow:2px 2px 5px blue;margin-top: 2px"/>
</div>
</body>
</html>
