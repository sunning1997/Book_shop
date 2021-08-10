package com.lmonkey.servlet.cart;

import com.lmonkey.entity.LMONKEY_CART;
import com.lmonkey.entity.LMONKEY_USER;
import com.lmonkey.userDao.cartDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "cartShow",urlPatterns = "/cartShow")
public class cartShow extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html,charset=UTF-8");

		HttpSession session = request.getSession();
		LMONKEY_USER user =(LMONKEY_USER) session.getAttribute("username");
		String isLogin =(String) session.getAttribute("isLogin");

		if (user != null && isLogin.equals("1")){
			String uid = user.getUSER_ID();

			try {
				ArrayList<LMONKEY_CART> cartList = cartDao.selectAllByUid(uid);
				request.setAttribute("cartList",cartList);
				request.getRequestDispatcher("cart.jsp").forward(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			PrintWriter out = response.getWriter();
			out.write("<script>");
			out.write("alert('请先登录！');");
			out.write("location.href='login.jsp';");
			out.write("</script>");
			out.close();
			return;
		}
	}
}
