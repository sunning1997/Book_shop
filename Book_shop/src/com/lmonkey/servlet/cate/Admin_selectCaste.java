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

@WebServlet(name = "selectCate",urlPatterns = "/manage/selectCate")
public class Admin_selectCaste extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		try {
			ArrayList<LMONKEY_CATEGORY> catelist = cateDao.findAll();
			request.setAttribute("cateList",catelist);
			request.getRequestDispatcher("/manage/Admin_cate.jsp").forward(request,response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
