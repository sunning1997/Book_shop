package com.lmonkey.servlet.cate;

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

@WebServlet(name = "admin_toCateUpdate",urlPatterns = "/manage/admin_toCateUpdate")
public class Admin_toCateUpdate extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int cateId = Integer.parseInt(request.getParameter("id"));
		try {
			LMONKEY_CATEGORY cate = cateDao.findById(cateId);
			ArrayList<LMONKEY_CATEGORY> catelist = cateDao.findAll();
			request.setAttribute("cateList",catelist);
			request.setAttribute("cate",cate);
			request.getRequestDispatcher("/manage/Admin_cateUpdate.jsp").forward(request,response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
