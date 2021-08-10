package com.lmonkey.servlet.product;

import com.lmonkey.entity.LMONKEY_CATEGORY;
import com.lmonkey.entity.LMONKEY_PRODUCT;
import com.lmonkey.entity.LMONKEY_USER;
import com.lmonkey.userDao.cateDao;
import com.lmonkey.userDao.productDao;
import com.lmonkey.userDao.userDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "Admin_toProductUpdate",urlPatterns = "/manage/admin_toProductUpdate")
public class Admin_toProductUpdate extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		//获取ID
		int id =Integer.parseInt(request.getParameter("id"));
		int cid =Integer.parseInt(request.getParameter("cid"));
		LMONKEY_PRODUCT product = new LMONKEY_PRODUCT();
		ArrayList<LMONKEY_CATEGORY> flist = new ArrayList<LMONKEY_CATEGORY>();
		ArrayList<LMONKEY_CATEGORY> clist = new ArrayList<LMONKEY_CATEGORY>();
		String cateName = null;
		//根据ID查询数据库
		try {
			product = productDao.findById(id);

			flist = cateDao.selectCate("father");

			clist = cateDao.selectCate("child");

			cateName = cateDao.selectById(cid);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("product",product);
		request.setAttribute("flist",flist);
		request.setAttribute("clist",clist);
		request.setAttribute("cpage",request.getParameter("cpage"));
		request.setAttribute("cName",cateName);

		request.getRequestDispatcher("Admin_productUpdate.jsp").forward(request,response);
	}
}
