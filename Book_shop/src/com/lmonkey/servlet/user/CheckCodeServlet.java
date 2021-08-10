package com.lmonkey.servlet.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CheckCode",urlPatterns="/CheckCode")
public class CheckCodeServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String code = request.getParameter("code");
		HttpSession session = request.getSession();
		String res = (String) session.getAttribute("code");
		PrintWriter out = response.getWriter();
		if (res.equals(code)){
			out.write("true");
		}else{
			out.write("false");
		}

		out.close();
	}
}
