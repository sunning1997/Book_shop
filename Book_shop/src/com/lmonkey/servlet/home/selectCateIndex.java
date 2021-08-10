package com.lmonkey.servlet.home;

import com.lmonkey.entity.LMONKEY_CATEGORY;
import com.lmonkey.entity.LMONKEY_PRODUCT;
import com.lmonkey.entity.LMONKEY_USER;
import com.lmonkey.servlet.user.UserNameCheck;
import com.lmonkey.userDao.cateDao;
import com.lmonkey.userDao.productDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "selectCateIndex",urlPatterns = "/selectCateIndex")
public class selectCateIndex extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			ArrayList<LMONKEY_CATEGORY> flist = cateDao.selectCate("father");
			request.setAttribute("flist",flist);

			ArrayList<LMONKEY_CATEGORY> clist = cateDao.selectCate("child");
			request.setAttribute("clist",clist);

			ArrayList<LMONKEY_PRODUCT> list = productDao.index_selectAllByFid(18);
			request.setAttribute("list",list);

			ArrayList<LMONKEY_PRODUCT> list_C = productDao.index_C_selectAllByFid(19);
			request.setAttribute("list_c",list_C);
			request.getRequestDispatcher("/index.jsp").forward(request,response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
