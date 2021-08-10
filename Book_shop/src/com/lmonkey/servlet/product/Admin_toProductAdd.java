package com.lmonkey.servlet.product;

import com.lmonkey.entity.LMONKEY_CATEGORY;
import com.lmonkey.userDao.cateDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "admin_toProductAdd",urlPatterns = "/manage/admin_toProductAdd")
public class Admin_toProductAdd extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html,charset=UTF-8");

		try {
			ArrayList<LMONKEY_CATEGORY> flist = cateDao.selectCate("father");
			request.setAttribute("flist",flist);

			ArrayList<LMONKEY_CATEGORY> clist = cateDao.selectCate("child");
			request.setAttribute("clist",clist);

			request.getRequestDispatcher("/manage/Admin_productAdd.jsp").forward(request,response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
