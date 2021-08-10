package com.lmonkey.servlet.user;

import com.lmonkey.entity.LMONKEY_USER;
import com.lmonkey.userDao.userDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "userServlet",urlPatterns = "/manage/admin_douserselect")
public class DoUserSelect extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cpage = 1;//当前页
		int count = 5;//每页显示条数
		int arr[] = new int[0];
		//查询关键字
		String keyword = request.getParameter("keywords");
		//获取用户指定页面
		String cp = request.getParameter("cp");
		if (cp!=null){
			cpage = Integer.parseInt(cp);
		}

		try {
			arr = userDao.totalPage(count,keyword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//获取用户数据
		ArrayList<LMONKEY_USER> list = null;
		try {
			list =  userDao.findAll(cpage,count,keyword);;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println(list);
		//将用户数据转发
		request.setAttribute("userList",list);
		request.setAttribute("tsum",arr[0]);
		request.setAttribute("tpage",arr[1]);
		request.setAttribute("cpage",cpage);
		if (keyword != null){
			request.setAttribute("searchParams","&keywords="+keyword);
		}
		request.getRequestDispatcher("/manage/Admin_user.jsp").forward(request,response);
	}
}
