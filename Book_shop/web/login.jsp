<%--
  Created by IntelliJ IDEA.
  User: 孙宁
  Date: 2021/6/4
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>登录</title>
	<link rel="stylesheet" type="text/css" href="css/public.css"/>
	<link rel="stylesheet" type="text/css" href="css/login.css"/>
</head>
<body><!-------------------login-------------------------->
<div class="login">
	<form action="/Book_shop/web/login" method="post"><h1><a href="index.html"><img src="img/temp/logo.png"></a></h1>
		<p></p>
		<div class="msg-warn hide"><b></b>公共场所不建议自动登录，以防账号丢失</div>
		<p><input type="text" name="userName" value="" placeholder="昵称/邮箱/手机号"></p>
		<p><input type="text" name="passWord" value="" placeholder="密码"></p>
		<p><input type="submit" name="" value="登  录"></p>
		<p class="txt"><a class="" href="reg.jsp">免费注册</a><a href="forget.html">忘记密码？</a></p></form>
</div>
</body>
</html>
