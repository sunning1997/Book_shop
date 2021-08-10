package com.lmonkey.servlet.user;

import com.lmonkey.entity.LMONKEY_USER;
import com.lmonkey.userDao.userDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "admin_toUserUpdate",urlPatterns = "/manage/admin_toUserUpdate")
public class ToUserUpdate extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		//获取ID
		String id = request.getParameter("id");
		LMONKEY_USER user = null;
		//根据ID查询数据库
		try {
			user = userDao.selectById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("user",user);
		request.setAttribute("cpage",request.getParameter("cpage"));

		request.getRequestDispatcher("/manage/Admin_userupdate.jsp").forward(request,response);
	}
}
