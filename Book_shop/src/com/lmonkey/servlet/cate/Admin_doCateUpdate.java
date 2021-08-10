package com.lmonkey.servlet.cate;

import com.lmonkey.entity.LMONKEY_CATEGORY;
import com.lmonkey.userDao.cateDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "admin_doCateUpdate",urlPatterns = "/manage/admin_doCateUpdate")
public class Admin_doCateUpdate extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		int cateId = Integer.parseInt(request.getParameter("id"));
		int parentID = Integer.parseInt(request.getParameter("parentID"));
		String cateName = request.getParameter("cateName");

		//创建用户实体
		LMONKEY_CATEGORY lmonkey_category = new LMONKEY_CATEGORY(cateId,cateName,parentID);
		//加入数据库的表中
		boolean rs = false;
		try {
			rs = cateDao.update(lmonkey_category);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//成功或失败处理
		if (rs){
			response.sendRedirect("/Book_shop/web/manage/selectCate");
		}else {
			PrintWriter out = response.getWriter();
			out.write("<script>");
			out.write("alert('修改分类失败！');");
			out.write("location.href('/Book_shop/web/manage/admin_toCateUpdate');");
			out.write("</script>");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
}
