package com.lmonkey.servlet.cate;

import com.lmonkey.userDao.cateDao;
import com.lmonkey.userDao.userDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "admin_doCateDelete",urlPatterns = "/manage/admin_doCateDelete")
public class Admin_doCateDel extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html,charset=UTF-8");

		int id = Integer.parseInt(request.getParameter("id"));
		//删除数据库的表中
		boolean rs = false;
		try {
			rs = cateDao.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//成功或失败处理
		if (rs){
			response.sendRedirect("/Book_shop/web/manage/selectCate");
		}else {
			PrintWriter out = response.getWriter();
			out.write("<script>");
			out.write("alert('删除分类失败！')");
			out.write("location.href='/Book_shop/web/manage/selectCate");
			out.write("</script>");
		}
	}
}
