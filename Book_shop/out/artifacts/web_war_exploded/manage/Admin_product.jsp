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
			<span class="crumb-name">图书管理</span>
		</div>
	</div>
	
	<div class="result-wrap">
		<form action="/Book_shop/web/manage/admin_doProductDelete" name="myform" id="myform" method="post">
			<div class="result-title">
				<div class="result-list">
					<a href="/Book_shop/web/manage/admin_toProductAdd"><i class="icon-font"></i>新增图书</a>
					<!--<a id="updateOrd" href="javascript:void(0)"><i class="icon-font"></i>更新排序</a>-->
				</div>
			</div>
			<div class="result-content">
				<table class="result-tab" width="100%">
					<tr>
						<th>ID</th>
						<th>图书名称</th>
						<th>图书介绍</th>
						<th>图书价格</th>
						<th>图书库存</th>
						<th>图书分类</th>
					</tr>
					<c:forEach var="product" items="${productList}" >
						
							<tr>
								<td>${product.PRODUCT_ID}</td>
								<td>${product.PRODUCT_NAME}</td>
								<td>${product.PRODUCT_DESCRIPTION}</td>
								<td>${product.PRODUCT_PRICE}</td>
								<td>${product.PRODUCT_STOCK}</td>
								<td>${product.PRODUCT_FID}-${product.PRODUCT_CID}</td>
								<td>
									<a class="link-update" href="/Book_shop/web/manage/admin_toProductUpdate?id=${product.PRODUCT_ID}">修改</a>
									<a class="link-del" href="javascript:Delete('你确定要删除【${product.PRODUCT_ID}】吗？','/Book_shop/web/manage/admin_doProductDelete?id=${product.PRODUCT_ID}')">删除</a>
								</td>
							</tr>
						
					</c:forEach>
				</table>
				<div class="list-page">
					共${tsum}条，当前${cpage}/${tpage}页
					<a href="admin_douserselect?cp=1${searchParams}">首页</a>
					<a href="admin_douserselect?cp=${cpage-1<1?1:cpage-1}${searchParams}">上一页</a>
					<a href="admin_douserselect?cp=${cpage+1>tpage?tpage:cpage+1}${searchParams}">下一页</a>
					<a href="admin_douserselect?cp=${tpage}${searchParams}">尾页</a>
				</div>
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
