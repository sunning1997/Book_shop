package com.lmonkey.servlet.user;

import com.lmonkey.userDao.userDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "Servlet2",urlPatterns = "/manage/admin_doUserDelete")
public class DoUserDelete extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String ids[] = request.getParameterValues("id[]");
		//批量删除数据库表的数据
		boolean rs = false;
		/*
		for (int i = 0; i < ids.length; i++){
			try {
				rs = userDao.delete(ids[i]);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}*/
		try {
			rs = userDao.deleteMore(ids);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//成功或失败处理
		response.sendRedirect("/Book_shop/web/manage/admin_douserselect?cp="+request.getParameter("cpage"));

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		//删除数据库的表中
		boolean rs = false;
		try {
			rs = userDao.delete(request.getParameter("id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//成功或失败处理
		if (rs){
			response.sendRedirect("/Book_shop/web/manage/admin_douserselect?cp="+request.getParameter("cpage"));
		}else {
			PrintWriter out = response.getWriter();
			out.write("<script>");
			out.write("alert('删除用户失败！')");
			out.write("location.href='/Book_shop/web/manage/admin_douserselect?cp="+request.getParameter("cpage")+"'");
			out.write("</script>");
		}
	}
}
