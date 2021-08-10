<%--
  Created by IntelliJ IDEA.
  User: 孙宁
  Date: 2021/6/6
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="Admin_menu.jsp"%>
<!--/sidebar-->
<div class="main-wrap">
	
	<div class="crumb-wrap">
		<div class="crumb-list">
			<i class="icon-font"></i>
			<a href="/Book_shop/web/manage/Admin_index.jsp">首页</a>
			<span class="crumb-step">&gt;</span>
			<span class="crumb-name">用户管理</span>
		</div>
	</div>
	
	<div class="result-wrap">
		<form action="/Book_shop/web/manage/admin_doCateDelete" name="myform" id="myform" method="post">
			<div class="result-title">
				<div class="result-list">
					<input type="hidden" name="cpage" value="${cpage}">
					<a href="/Book_shop/web/manage/admin_toCateAdd?id=${cate.CATE_ID}"><i class="icon-font"></i>新增分类</a>
					<!--<a id="updateOrd" href="javascript:void(0)"><i class="icon-font"></i>更新排序</a>-->
				</div>
			</div>
			<div class="result-content">
				<table class="result-tab" width="60%">
					<tr>
						<th>ID</th>
						<th>分类名称</th>
						<th>操作</th>
					</tr>
					<c:forEach var="cate" items="${cateList}" >
						<c:if test="${cate.CATE_PARENT_ID == 0}">
							<tr>
								<td>${cate.CATE_ID}</td>
								<td>|-${cate.CATE_NAME}</td>
								<td>
									<a class="link-update" href="/Book_shop/web/manage/admin_toCateUpdate?id=${cate.CATE_ID}">修改</a>
									<a class="link-del" href="javascript:Delete('你确定要删除【${cate.CATE_ID}】吗？','/Book_shop/web/manage/admin_doCateDelete?id=${cate.CATE_ID}')">删除</a>
								</td>
							</tr>
							<c:forEach var="zcate" items="${cateList}" >
								<c:if test="${zcate.CATE_PARENT_ID == cate.CATE_ID}">
								<tr>
									<td>${zcate.CATE_ID}</td>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-${zcate.CATE_NAME}</td>
									<td>
										<a class="link-update" href="/Book_shop/web/manage/admin_toCateUpdate?id=${zcate.CATE_ID}">修改</a>
										<a class="link-del" href="javascript:Delete('你确定要删除【${zcate.CATE_ID}】吗？','/Book_shop/web/manage/admin_doCateDelete?id=${zcate.CATE_ID}')">删除</a>
									</td>
								</tr>
								</c:if>
							</c:forEach>
						</c:if>
					</c:forEach>
				</table>
			</div>
		</form>
	</div>
</div>
<!--/main-->
</div>
</body>
<script>
	function getAll(o) {
		var a = document.getElementsByName('id[]');
		for (var i = 0;i<a.length;i++){
			a[i].checked = o.checked;
		}
	}
	function Delete(mess,url) {
		if (confirm(mess)){
			location.href=url;
		}
	}
	function Delmore(message,formname) {
		if (confirm(message)){
			var form = document.getElementById(formname);
			form.submit();
		}
	}
</script>
</html>
