package com.lmonkey.servlet.product;

import com.lmonkey.entity.LMONKEY_CATEGORY;
import com.lmonkey.entity.LMONKEY_PRODUCT;
import com.lmonkey.userDao.cateDao;
import com.lmonkey.userDao.productDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "Admin_product1",urlPatterns = "/manage/admin_product")
public class Admin_product1 extends HttpServlet {
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
			arr = productDao.totalPage(count,keyword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//获取用户数据
		ArrayList<LMONKEY_PRODUCT> list = null;
		try {
			list =  productDao.findAll(cpage,count,keyword);;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("productList",list);

		//获取分类数据
		List<Integer> cidList = list.stream().map(LMONKEY_PRODUCT -> LMONKEY_PRODUCT.getPRODUCT_CID()).collect(Collectors.toList());
		ArrayList<LMONKEY_CATEGORY> cid = null;
		//Integer[] id =(Integer[]) cidList.toArray()[new Integer[]{}];
		Integer[] id = (Integer[]) cidList.toArray(new Integer[]{});
		try {
			cid = cateDao.findCateById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//将用户数据转发

		request.setAttribute("tsum",arr[0]);
		request.setAttribute("tpage",arr[1]);
		request.setAttribute("cpage",cpage);
		if (keyword != null){
			request.setAttribute("searchParams","&keywords="+keyword);
		}
		//request.setAttribute("cid",cid);
		request.getRequestDispatcher("Admin_product1.jsp").forward(request,response);
	}
}
