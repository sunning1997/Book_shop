package com.lmonkey.userDao;

import com.lmonkey.c3p0Utils.c3p0Utils;
import com.lmonkey.entity.LMONKEY_CART;
import com.lmonkey.entity.LMONKEY_CATEGORY;
import com.lmonkey.entity.LMONKEY_PRODUCT;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;

public class cartDao {
	//插入数据
	public static boolean insert(LMONKEY_CART lmonkey_cart) throws SQLException {
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(c3p0Utils.getDataSource());
		//写SQL语句
		String sql = "insert into lmonkey_cart values(?,?,?,?,?,?,?,?,?)";
		int sum = runner.update(sql,new Object[]{
				lmonkey_cart.getCART_ID(),
				lmonkey_cart.getCART_PRO_FILENAME(),
				lmonkey_cart.getCART_PRO_NAME(),
				lmonkey_cart.getCART_PRO_PRICE(),
				lmonkey_cart.getCART_QUANTITY(),
				lmonkey_cart.getCART_PRO_STOCK(),
				lmonkey_cart.getCART_PRO_ID(),
				lmonkey_cart.getCART_USER_ID(),
				lmonkey_cart.getCART_CALID()
		});
		if (sum > 0){
			return true;
		}else{
			return false;
		}
	}

	//根据UID查询购物车数据
	public static ArrayList<LMONKEY_CART> selectAllByUid(String id) throws SQLException {
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(c3p0Utils.getDataSource());
		//写SQL语句
		String sql = "select *from lmonkey_cart where CART_USER_ID = ? and CART_CALID=1 order by CART_ID desc";
		ArrayList<LMONKEY_CART> list = (ArrayList<LMONKEY_CART>) runner.query(sql, new BeanListHandler<LMONKEY_CART>(LMONKEY_CART.class),new Object[]{id});

		return list;
	}
	//根据UID和pro_id查询购物车数据
	public static LMONKEY_CART selectCartById(int pid,String uid) throws SQLException {
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(c3p0Utils.getDataSource());
		//写SQL语句
		String sql = "select *from lmonkey_cart where CART_PRO_ID = ? and CART_USER_ID = ? and CART_CALID=1 order by CART_ID desc";
		LMONKEY_CART cartPro = runner.query(sql, new BeanHandler<LMONKEY_CART>(LMONKEY_CART.class),new Object[]{pid,uid});

		return cartPro;
	}

	//根据ID修改商品数量
	public static boolean update(int id,int count) throws SQLException {
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(c3p0Utils.getDataSource());
		//写SQL语句
		String sql ="update lmonkey_cart set CART_QUANTITY=? where CART_ID=?";
		int sum = runner.update(sql,new Object[]{
				count,id
		});
		if (sum > 0){
			return true;
		}else{
			return false;
		}
	}

	//根据ID删除数据
	public static boolean delete(int id) throws SQLException {
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(c3p0Utils.getDataSource());
		//写SQL语句
		String sql = "delete from lmonkey_cart where CART_ID = ?";
		int sum = runner.update(sql,new Object[]{id});
		if (sum > 0){
			return true;
		}else{
			return false;
		}
	}
}
