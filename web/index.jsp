<%--
  Created by IntelliJ IDEA.
  User: Akari
  Date: 2017/7/4
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<script>
    if(top != self) {
        top.location.href = "/index.jsp";
    }
</script>
<html>
<head>
    <title>美国圣地亚戈</title>
    <link rel="stylesheet" type="text/css" href="/css/login.css">
</head>
<body>
<form class="login-content" name="login" action="/loginservlet.do" method="post">
    <table class="login-table">
        <tr>
            <td colspan="4" class="title">欢迎访问美国圣地亚戈国际集团</td>
        </tr>
        <tr>
            <td class="login-text">用户名：</td>
            <td colspan="2"><input type="text" name="userName" id="userName" onblur="checkRegular('username',this)"></td>
            <td class="login-prompt"><span style="color: #00FFFF">&nbsp;中文</span></td>
        </tr>
        <tr>
            <td class="login-text">密码：</td>
            <td colspan="2"><input type="password" name="userPassword" id="userPassword" onblur="checkRegular('userpassword',this)"></td>
            <td class="login-prompt"><span style="color: #00FFFF">&nbsp;数字，字母</span></td>
        </tr>
        <tr>
            <td colspan="4" class="bottom-buttons">
                <a href="javascript:document.login.submit();">登陆</a>
                <a href="login_old.html">注册</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
