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
			<a class="crumb-name" href="/Book_shop/web/manage/admin_product">图书管理</a>
			<span class="crumb-step">&gt;</span>
			<span>新增图书</span>
		</div>
	</div>
	<div class="result-wrap">
		<div class="result-content">
			<form action="/Book_shop/web/manage/admin_doProductAdd" method="post" enctype="multipart/form-data" id="myform" name="myform" >
				<table class="insert-tab" width="100%">
					<tbody>
					<tr>
						<th><i class="require-red">*</i>商品名称：</th>
						<td>
							<input class="common-text required" id="name" name="productName" size="50" value="" type="text">
						</td>
					</tr>
					<tr>
						<th><i class="require-red">*</i>图书分类：</th>
						<td>
							<select class="common-text required" name="parentID">
								<c:forEach var="fcate" items="${flist}" >
									<option value="${fcate.CATE_ID}" disabled="disabled">|-${fcate.CATE_NAME}</option>
									<c:forEach var="ccate" items="${clist}" >
										<c:if test="${ccate.CATE_PARENT_ID == fcate.CATE_ID}">
											<option value="${fcate.CATE_ID}-${ccate.CATE_ID}">&nbsp;&nbsp;&nbsp;&nbsp;|-${ccate.CATE_NAME}</option>
										</c:if>
										
									</c:forEach>
								</c:forEach>
							</select>
						</td>
					</tr>
					
					<tr>
						<th><i class="require-red">*</i>商品价格：</th>
						<td>
							<input class="common-text required" id="price" name="price" size="50" value="" type="text">
						</td>
					</tr>
					<tr>
						<th><i class="require-red">*</i>库存：</th>
						<td>
							<input class="common-text required" id="count" name="productCount" size="50" value="" type="text">
						</td>
					</tr>
					<tr>
						<th><i class="require-red">*</i>商品图片：</th>
						<td>
							<input class="common-text required" id="file" name="productFile" size="50" value="" type="file">
						</td>
					</tr>
					<tr>
						<th><i class="require-red">*</i>商品介绍：</th>
						<td>
							<input class="common-text required" id="description" name="description" size="50" value="" type="text">
						</td>
					</tr>
					
					<tr>
						<th></th>
						<td>
							<input class="btn btn-primary btn6 mr10" value="提交" type="submit">
							<input class="btn btn6" onClick="history.go(-1)" value="返回" type="button">
						</td>
					</tr>
					</tbody></table>
			</form>
		</div>
	</div>

</div>
<!--/main-->
</div>
</body>
</html>
