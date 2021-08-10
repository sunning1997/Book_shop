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
			<a class="crumb-name" href="/Book_shop/web/manage/selectCate">分类管理</a>
			<span class="crumb-step">&gt;</span>
			<span>新增分类</span>
		</div>
	</div>
	<div class="result-wrap">
		<div class="result-content">
			<form action="/Book_shop/web/manage/admin_doCateAdd" method="post" id="myform" name="myform" >
				<table class="insert-tab" width="100%">
					<tbody>
					<tr>
						<th><i class="require-red">*</i>父级分类：</th>
						<td>
							<select class="common-text required" name="parentID">
								<option value="0" selected="selected">根分类</option>
								<c:forEach var="cate" items="${cateList}" >
									<c:if test="${cate.CATE_PARENT_ID == 0}">
										<option value="${cate.CATE_ID}">${cate.CATE_NAME}</option>
									</c:if>
									
								</c:forEach>
								
							</select>
						</td>
					</tr>
					<tr>
						<th><i class="require-red">*</i>分类名称：</th>
						<td>
							<input class="common-text required" id="name" name="cateName" size="50" value="" type="text">
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
