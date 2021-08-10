package com.lmonkey.servlet.cart;

import com.lmonkey.userDao.cartDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.interfaces.RSAKey;
import java.sql.SQLException;

@WebServlet(name = "cartShopNum",urlPatterns = "/cartShopNum")
public class cartShopNum extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		String count = request.getParameter("count");

		try {
			cartDao.update(Integer.parseInt(id),Integer.parseInt(count));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
