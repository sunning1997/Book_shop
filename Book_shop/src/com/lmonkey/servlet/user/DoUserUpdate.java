package com.lmonkey.servlet.user;

import com.lmonkey.entity.LMONKEY_USER;
import com.lmonkey.userDao.userDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "admin_doUserUpdate",urlPatterns = "/manage/admin_doUserUpdate")
public class DoUserUpdate extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/html;charset=UTF-8");

		String username = request.getParameter("userName");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String address = request.getParameter("address");
		String userStatus = request.getParameter("userStatus");
		int status = 1;
		if (userStatus != null){
			status = Integer.parseInt(userStatus);
		}

		//创建用户实体
		LMONKEY_USER lmonkey_user = new LMONKEY_USER(username,name,password,sex,birthday,null,email,mobile,address,status);
		//加入数据库的表中
		boolean rs = false;
		try {
			rs = userDao.update(lmonkey_user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//成功或失败处理
		if (rs){
			response.sendRedirect("/Book_shop/web/manage/admin_douserselect?cp="+request.getParameter("cpage"));
		}else {
			PrintWriter out = response.getWriter();
			out.write("<script>");
			out.write("alert('修改用户失败！')");
			out.write("location.href='/Book_shop/web/manage/admin_toUserUpdate?id="+username+"'");
			out.write("</script>");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
}
