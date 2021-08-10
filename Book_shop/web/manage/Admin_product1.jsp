<%@ page import="javax.servlet.descriptor.TaglibDescriptor" %><%--
  Created by IntelliJ IDEA.
  User: 孙宁
  Date: 2021/4/1
  Time: 20:46
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
		<div class="search-wrap">
			<div class="search-content">
				<form action="/Book_shop/web/manage/admin_product" method="get">
					<table class="search-tab">
						<tr>
							<!--<th width="120">选择分类:</th>
							<td>
								<select name="search-sort" id="">
									<option value="">全部</option>
									<option value="19">精品界面</option><option value="20">推荐界面</option>
								</select>
							</td>-->
							<th width="70">关键字:</th>
							<td><input class="common-text" placeholder="关键字" name="keywords" value="${param.keywords}" id="" type="text"></td>
							<td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="result-wrap">
			<form action="/Book_shop/web/manage/admin_doProductDelete?cpage=${cpage}" name="myform" id="myform" method="post">
				<div class="result-title">
					<div class="result-list">
						<input type="hidden" name="cpage" value="${cpage}">
						<a href="/Book_shop/web/manage/admin_toProductAdd"><i class="icon-font"></i>新增图书</a>
						<a id="batchDel" href="javascript:Delmore('你确定要删除这些用户吗？','myform')"><i class="icon-font"></i>批量删除</a>
						<!--<a id="updateOrd" href="javascript:void(0)"><i class="icon-font"></i>更新排序</a>-->
					</div>
				</div>
				<div class="result-content">
					<table class="result-tab" width="100%">
						<tr>
							<th class="tc" width="5%"><input class="allChoose" name="" onclick="getAll(this)" type="checkbox"></th>
							<th>ID</th>
							<th>图书名称</th>
							<th>图书介绍</th>
							<th>图书价格</th>
							<th>图书库存</th>
							
						</tr>
						<c:forEach var="product" items="${productList}" >
						<tr>
							<td class="tc"><input name="id[]" value="${product.PRODUCT_ID}" type="checkbox"></td>
							<td>${product.PRODUCT_ID}</td>
							<td><img src="../image/product/${product.PRODUCT_FILENAME}" width="80" height="80">
									${product.PRODUCT_NAME}
							</td>
							<td>${product.PRODUCT_DESCRIPTION}</td>
							<td>${product.PRODUCT_PRICE}</td>
							<td>${product.PRODUCT_STOCK}</td>
							
							<td>
								<a class="link-update" href="/Book_shop/web/manage/admin_toProductUpdate?id=${product.PRODUCT_ID}&cpage=${cpage}&cid=${product.PRODUCT_CID}">修改</a>
								<a class="link-del" href="javascript:Delete('你确定要删除【${product.PRODUCT_NAME}】吗？','/Book_shop/web/manage/admin_doProductDelete?id=${product.PRODUCT_ID}&cpage=${cpage}')">删除</a>
								
							</td>
						</tr>
						</c:forEach>
					</table>
					<div class="list-page">
						共${tsum}条，当前${cpage}/${tpage}页
						<a href="admin_product?cp=1${searchParams}">首页</a>
						<a href="admin_product?cp=${cpage-1<1?1:cpage-1}${searchParams}">上一页</a>
						<a href="admin_product?cp=${cpage+1>tpage?tpage:cpage+1}${searchParams}">下一页</a>
						<a href="admin_product?cp=${tpage}${searchParams}">尾页</a>
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
