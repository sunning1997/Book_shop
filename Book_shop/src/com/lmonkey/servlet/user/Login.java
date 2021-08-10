package com.lmonkey.servlet.user;

import com.lmonkey.entity.LMONKEY_PRODUCT;
import com.lmonkey.entity.LMONKEY_USER;
import com.lmonkey.userDao.productDao;
import com.lmonkey.userDao.userDao;

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

@WebServlet(name = "login",urlPatterns = "/login")
public class Login extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String username = request.getParameter("userName");
		String passWord = request.getParameter("passWord");

		int count = 0;
		try {
			count = userDao.checkUserAndPwd(username,passWord);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (count > 0){
			HttpSession session = request.getSession();
			try {
				LMONKEY_USER user = userDao.getUserAndPwd(username,passWord);
				session.setAttribute("username",user);
				session.setAttribute("isLogin","1");

				if (user.getUSER_STATUS() == 2){
					session.setAttribute("admin_isLogin","1");
					response.sendRedirect("selectCateIndex");
				}else {
					response.sendRedirect("selectCateIndex");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			PrintWriter out = response.getWriter();
			out.write("<script>");
			out.write("alert('用户未注册，请先注册！');");
			out.write("location.href='reg.jsp';");
			out.write("</script>");
			out.close();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
}
