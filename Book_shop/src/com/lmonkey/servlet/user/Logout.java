package com.lmonkey.servlet.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "logout",urlPatterns = "/logout")
public class Logout extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();

		session.removeAttribute("username");
		session.removeAttribute("isLogin");
		session.removeAttribute("admin_isLogin");

		PrintWriter out = response.getWriter();
		out.write("<script>");
		out.write("alert('退出成功！');");
		out.write("location.href='index.jsp';");
		out.write("</script>");
		out.close();
	}
}
