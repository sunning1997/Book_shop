package com.lmonkey.servlet.product;

import com.jspsmart.upload.*;
import com.lmonkey.entity.LMONKEY_PRODUCT;
import com.lmonkey.userDao.productDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "Admin_doProductAdd",urlPatterns = "/manage/admin_doProductAdd")
public class Admin_doProductAdd extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html,charset=UTF-8");
		//创建smartload组件对象
		SmartUpload smartUpload = new SmartUpload();
		//初始化
		smartUpload.initialize(this.getServletConfig(),request,response);
		//上传过程
		try {
			smartUpload.upload();
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
		//获取上传文件对象
		Files files = smartUpload.getFiles();
		File file = files.getFile(0);
		//获取上传文件名
		String filename = file.getFileName();

		//保存文件
		try {
			smartUpload.save("D:\\interllj IDEA\\Project\\Book_shop\\web\\image\\product");
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}

		Request request1 = smartUpload.getRequest();
		String name = request1.getParameter("productName");
		String cate = request1.getParameter("parentID");
		String price = request1.getParameter("price");
		String count = request1.getParameter("productCount");
		String dec = request1.getParameter("description");

		LMONKEY_PRODUCT lmonkey_product = new LMONKEY_PRODUCT(
				0,
				name,
				dec,
				//Integer.parseInt(price),
				Double.parseDouble(price),
				Integer.parseInt(count),
				Integer.parseInt(cate.split("-")[0]),
				Integer.parseInt(cate.split("-")[1]),
				filename
		);
		boolean rs = false;
		try {
			rs = productDao.insert(lmonkey_product);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//成功或失败处理
		if (rs){
			response.sendRedirect("/Book_shop/web/manage/admin_product");
		}else {
			PrintWriter out = response.getWriter();
			out.write("<script>");
			out.write("alert('添加分类失败！');");
			out.write("location.href('/Book_shop/web/manage/admin_toProductAdd');");
			out.write("</script>");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
}
