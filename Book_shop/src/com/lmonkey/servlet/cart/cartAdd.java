package com.lmonkey.servlet.cart;

import com.lmonkey.entity.LMONKEY_CART;
import com.lmonkey.entity.LMONKEY_PRODUCT;
import com.lmonkey.entity.LMONKEY_USER;
import com.lmonkey.userDao.cartDao;
import com.lmonkey.userDao.productDao;
import org.apache.taglibs.standard.lang.jstl.NullLiteral;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "cartAdd",urlPatterns = "/cartAdd")
public class cartAdd extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html,charset=UTF-8");

		String pid = request.getParameter("id");
		String count = request.getParameter("count");
		String url = request.getParameter("url");
		String cid = request.getParameter("cid");

		HttpSession session = request.getSession();
		LMONKEY_USER user =(LMONKEY_USER) session.getAttribute("username");
		String isLogin =(String) session.getAttribute("isLogin");

		LMONKEY_PRODUCT product = null;
		if (user != null && isLogin.equals("1")){

			String uid = user.getUSER_ID();

			//根据UID和proID查询购物车中是否已存在此商品
			try {
				LMONKEY_CART cartPro = cartDao.selectCartById(Integer.parseInt(pid),uid);
				if (cartPro != null){
					int srccount = cartPro.getCART_QUANTITY();
					int newcount = srccount + Integer.parseInt(count);
					if (newcount >= 5){
						newcount = 5;
					}
					cartDao.update(cartPro.getCART_ID(),newcount);
				}else{

					if (pid != null){
						try {
							product = productDao.findById(Integer.parseInt(pid));
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					LMONKEY_CART cart = new LMONKEY_CART(
							0,
							product.getPRODUCT_FILENAME(),
							product.getPRODUCT_NAME(),
							product.getPRODUCT_PRICE(),
							Integer.parseInt(count),
							product.getPRODUCT_STOCK(),
							product.getPRODUCT_ID(),
							user.getUSER_ID(),
							1
					);
					boolean rs = false;
					try {
						rs = cartDao.insert(cart);
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}
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
		if (url.equals("z")){
			response.sendRedirect("cartShow");
		}else {
			response.sendRedirect("productDetail?id="+pid);
		}
	}
}
