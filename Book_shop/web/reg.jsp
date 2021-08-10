<%--
  Created by IntelliJ IDEA.
  User: 孙宁
  Date: 2021/4/7
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>注册</title>
	<link rel="stylesheet" type="text/css" href="css/public.css"/>
	<link rel="stylesheet" type="text/css" href="css/login.css"/>
	<script src="js/jquery-1.12.4.min.js"></script>
	<script src="js/function.js"></script>
	
	<style type="text/css">
		.reg p .regClass{
			margin-left: 20px;
			font-family: 新宋体;
			font-size: 20px;
			color: red;
		}
	</style>
</head>
<body><!-------------------reg-------------------------->
<div class="reg">
	<form action="/Book_shop/web/Register" method="post" onsubmit="return checkForm(this)"><h1><a href="index.html"><img src="img/temp/logo.png"></a></h1>
		<h1 style="font-size: 20px;padding: 0px;margin: 0px;background: #3344AA;text-align: center;line-height: 40px;color: #FFFFFF">用户注册</h1>
		<p><input type="text" name="userName" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请输入用户名"><span class="regClass"></span></p>
		<p><input type="text" name="name" value="" placeholder="请输入姓名"><span></span></p>
		<p><input type="text" name="password" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请输入密码"><span class="regClass"></span></p>
		<p><input type="text" name="rePassword" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请确认密码"><span class="regClass"></span></p>
		<p>
			<input style="height: 20px;width: 20px" type="radio" name="sex" value="T" checked="checked"><span style="font-size: 20px">男</span>
			<input style="height: 20px;width: 20px" type="radio" name="sex" value="F" ><span style="font-size: 20px">女</span>
		</p>
		<p><input type="text" name="birthday" value="" placeholder="请输入出生日期"><span></span></p>
		<p><input type="text" name="email" value="" placeholder="请输入邮箱"><span></span></p>
		<p><input type="text" name="mobile" value="" placeholder="请输入手机号码"><span></span></p>
		<p><input type="text" name="address" value="" placeholder="请确认送货地址"><span></span></p>
		<p><input class="code" type="text" name="codeValue" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="验证码">
			<img id="imgObj" style="width: 100px;height: 30px;" src="${pageContext.request.contextPath}/getCode">
			<a style="width: 50px;height: 30px" href="" onclick="changeImg()">换一张</a><span class="regClass"></span></p>
		<p><input type="submit" name="" value="注册"></p>
		<p class="txtL txt">完成此注册，即表明您同意了我们的<a href=""><使用条款和隐私策略></a></p>
		<p class="txt"><a href="login.jsp"><span></span>已有账号登录</a></p>
		<!--<a href="#" class="off"><img src="img/temp/off.png"></a>--></form>
</div>
</body>
</html>
