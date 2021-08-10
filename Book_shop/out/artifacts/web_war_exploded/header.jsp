<%--
  Created by IntelliJ IDEA.
  User: 孙宁
  Date: 2021/6/5
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="head">
	<div class="wrapper clearfix">
		<div class="clearfix" id="top"><h1 class="fl"><a href="index.html"><img src="img/logo.png"/></a></h1>
			<div class="fr clearfix" id="top1"><p class="fl">
				<c:if test="${isLogin != 1}">
					<a href="login.jsp" id="login">登录</a><a href="reg.jsp" id="reg">注册</a>
				</c:if>
				<c:if test="${isLogin  == 1}">
					<a href="#" id="reg">${username.USER_ID}</a>
				</c:if>
				<c:if test="${admin_isLogin  == 1}">
					<a href="/Book_shop/web/manage/Admin_index.jsp" id="reg">进入后台</a>
				</c:if>
			</p>
				<form action="#" method="get" class="fl"><input type="text" placeholder="热门搜索：java"/><input
						type="button"/></form>
				<div class="btn fl clearfix">
					<c:if test="${isLogin == 1}">
						<a href="mygxin.jsp"><img src="img/grzx.png"/></a>
						<a href="#" class="er1"><img src="img/ewm.png"/></a>
						<a href="cartShow"><img src="img/gwc.png"/></a>
						<p><a href="#"><img src="img/smewm.png"/></a></p>
					</c:if>
				</div>
			</div>
		</div>
		<ul class="clearfix" id="bott">
			<li><a href="/Book_shop/web/login?userName=${username.USER_ID}&passWord=${username.USER_PASSWORD}">首页</a></li>
			<c:forEach var="f" items="${flist}">
			<li><a href="selectProductList?fid=${f.CATE_ID}">${f.CATE_NAME}</a>
				<div class="sList">
					
					<div class="wrapper  clearfix" style="width: 200px; margin-left: 300px">
						<c:forEach var="c" items="${clist}">
						<c:if test="${c.CATE_PARENT_ID == f.CATE_ID}">
						<a href="selectProductList?cid=${c.CATE_ID}">
						<dl  style="width: 200px">
							<dt><img src="img/Java.jpg"/></dt>
							<dd>${c.CATE_NAME}</dd>
						</dl>
						</a>
						</c:if>
						</c:forEach>
					</div>
					
				</div>
			</li>
			</c:forEach>
			
		</ul>
	</div>
</div>
