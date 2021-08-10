package com.lmonkey.servlet.home;

import com.lmonkey.entity.LMONKEY_CART;
import com.lmonkey.entity.LMONKEY_CATEGORY;
import com.lmonkey.entity.LMONKEY_PRODUCT;
import com.lmonkey.entity.LMONKEY_USER;
import com.lmonkey.userDao.cartDao;
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

@WebServlet(name = "selectProductList",urlPatterns = "/selectProductList")
public class selectProductList extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html,charset=UTF-8");

		HttpSession session = request.getSession();
		LMONKEY_USER user =(LMONKEY_USER) session.getAttribute("username");

		String fid = request.getParameter("fid");
		String cid = request.getParameter("cid");
		try {
			ArrayList<LMONKEY_CATEGORY> flist = cateDao.selectCate("father");
			request.setAttribute("flist",flist);

			ArrayList<LMONKEY_CATEGORY> clist = cateDao.selectCate("child");
			request.setAttribute("clist",clist);
			int id = 0;
			ArrayList<LMONKEY_PRODUCT> list = new ArrayList<LMONKEY_PRODUCT>();
			if (fid != null){
				id = Integer.parseInt(fid);
				list = productDao.selectAllByFid(id);
			}
			if (cid != null){
				id = Integer.parseInt(cid);
				list = productDao.selectAllByCid(id);
			}
			LMONKEY_CATEGORY proList = cateDao.findById(id);
			request.setAttribute("proList",proList.getCATE_NAME());
			request.setAttribute("list",list);

			ArrayList<LMONKEY_CART> cartList = cartDao.selectAllByUid(user.getUSER_ID());
			int count = cartList.size();
			request.setAttribute("count",count);

			request.getRequestDispatcher("/productList.jsp").forward(request,response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
