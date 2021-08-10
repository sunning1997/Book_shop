package com.lmonkey.fitler;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "AdminLogin",urlPatterns = "/manage/*")
public class AdminLogin implements javax.servlet.Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

		HttpServletRequest request =(HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		//设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
		String flag = (String) session.getAttribute("admin_isLogin");

		String request_uri = request.getRequestURI();
		String ctxPath = request.getContextPath();
		String uri = request_uri.substring(ctxPath.length());

		if (uri.contains("Admin_")){
			if (flag != null && flag.equals("1")){
				chain.doFilter(req, resp);
			}else {
				PrintWriter out = response.getWriter();
				out.write("<script>");
				out.write("alert('请先登录！');");
				out.write("location.href='Adminlogin.jsp';");
				out.write("</script>");
				out.close();
			}
		}else {
			chain.doFilter(req, resp);
		}
		return;
	}

	public void init(FilterConfig config) throws ServletException {

	}

}
