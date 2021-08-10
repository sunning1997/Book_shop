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

@WebServlet(name = "productDetail",urlPatterns = "/productDetail")
public class productDetail extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html,charset=UTF-8");

		String cid = request.getParameter("cid");
		String id = request.getParameter("id");
		//获取session,将访问的图书ID放入session
		HttpSession session = request.getSession();
		ArrayList<Integer> ids =(ArrayList<Integer>) session.getAttribute("ids");

		if (ids == null){
			ids = new ArrayList<Integer>();
		}
		//判断IDS大小是否大于5,如果大于则删除第一个
		if (ids.size() >= 5){
			ids.remove(0);
		}
		if (id != null && (!ids.contains(Integer.parseInt(id)))){
			ids.add(Integer.parseInt(id));
		}
		//将IDS列表放入session中
		session.setAttribute("ids",ids);
		//从session取出ids
		ids =(ArrayList<Integer>) session.getAttribute("ids");
		if (ids != null){
			ArrayList<LMONKEY_PRODUCT> lastlyList = null;
			try {
				lastlyList = productDao.selectAllById(ids);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("lastlyList",lastlyList);
		}
		//String fid = request.getParameter("fid");

		try {
			ArrayList<LMONKEY_CATEGORY> flist = cateDao.selectCate("father");
			request.setAttribute("flist",flist);

			ArrayList<LMONKEY_CATEGORY> clist = cateDao.selectCate("child");
			request.setAttribute("clist",clist);

			LMONKEY_PRODUCT product = productDao.findById(Integer.parseInt(id));

			LMONKEY_CATEGORY proList = cateDao.findById(product.getPRODUCT_CID());
			request.setAttribute("proList",proList.getCATE_NAME());

			ArrayList<LMONKEY_PRODUCT> reProduct = productDao.selectAllByCid(product.getPRODUCT_CID());

			request.setAttribute("product",product);
			request.setAttribute("reProduct",reProduct);

			LMONKEY_USER user =(LMONKEY_USER) session.getAttribute("username");
			ArrayList<LMONKEY_CART> cartList = cartDao.selectAllByUid(user.getUSER_ID());
			int count = cartList.size();
			request.setAttribute("count",count);

			request.getRequestDispatcher("/productDetail.jsp").forward(request,response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
