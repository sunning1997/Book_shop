package com.lmonkey.userDao;

import com.lmonkey.c3p0Utils.c3p0Utils;
import com.lmonkey.entity.LMONKEY_USER;
import org.apache.commons.dbutils.BaseResultSetHandler;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class userDao {
	//查询所有数据
	public static ArrayList<LMONKEY_USER> findAll(int cpage,int count,String keyword) throws SQLException {
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(c3p0Utils.getDataSource());
		//写SQL语句
		String sql = "";
		ArrayList<LMONKEY_USER> list = null;
		if (keyword != null){
			sql = "select * from lmonkey_user where USER_NAME like ? order by USER_BIRTHDAY desc limit ?,?";
			list = (ArrayList<LMONKEY_USER>) runner.query(sql, new BeanListHandler<LMONKEY_USER>(LMONKEY_USER.class),new Object[]{ "%"+keyword+"%",(cpage-1)*count,count});
		}else{
			sql = "select * from lmonkey_user order by USER_BIRTHDAY desc limit ?,?";
			list = (ArrayList<LMONKEY_USER>) runner.query(sql, new BeanListHandler<LMONKEY_USER>(LMONKEY_USER.class),new Object[]{ (cpage-1)*count,count});
		}
		return list;

	}
	//查询数据总数
	public static int[] totalPage(int count,String keyword) throws SQLException {
		int arr[] = {0,1};//0 总数，1 页数
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(c3p0Utils.getDataSource());
		String sql = "";
		ArrayList<LMONKEY_USER> list = null;
		if (keyword != null){
			sql = "select * from lmonkey_user where USER_NAME like ?";
			list = (ArrayList<LMONKEY_USER>) runner.query(sql, new BeanListHandler<LMONKEY_USER>(LMONKEY_USER.class),new Object[]{"%"+keyword+"%"});
		}else {
			sql = "select * from lmonkey_user";
			list = (ArrayList<LMONKEY_USER>) runner.query(sql, new BeanListHandler<LMONKEY_USER>(LMONKEY_USER.class));
		}

		arr[0] = list.size();
		if (arr[0] % count==0){
			arr[1] = arr[0]/count;
		}else {
			arr[1] = arr[0]/count+1;
		}
		return arr;
	}
	//根据ID查询数据
	public static LMONKEY_USER selectById(String id) throws SQLException {
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(c3p0Utils.getDataSource());
		//写SQL语句
		String sql = "select * from lmonkey_user where USER_ID = ?";
		LMONKEY_USER user = runner.query(sql,new BeanHandler<>(LMONKEY_USER.class),new Object[]{id});
		return user;
	}
	//注册根据ID查询数据用于判断用户是否存在
	public static int selectByName(String id) throws SQLException {
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(c3p0Utils.getDataSource());
		//写SQL语句
		String sql = "select * from lmonkey_user where USER_ID = ?";
		List list = runner.query(sql,new BeanListHandler<>(LMONKEY_USER.class),new Object[]{id});
		int count = list.size();
		return count;
	}
	//登录时验证用户密码是否存在
	public static int checkUserAndPwd(String username,String password) throws SQLException {
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(c3p0Utils.getDataSource());
		//写SQL语句
		String sql = "select * from lmonkey_user where USER_ID = ? and USER_PASSWORD = ?";
		List list = runner.query(sql,new BeanListHandler<>(LMONKEY_USER.class),new Object[]{username,password});
		int count = list.size();
		return count;
	}
	//登录时将用户名和密码存入session
	public static LMONKEY_USER getUserAndPwd(String username,String password) throws SQLException {
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(c3p0Utils.getDataSource());
		//写SQL语句
		String sql = "select * from lmonkey_user where USER_ID = ? and USER_PASSWORD = ?";
		LMONKEY_USER user = runner.query(sql,new BeanHandler<>(LMONKEY_USER.class),new Object[]{username,password});
		return user;
	}
	//插入数据
	public static boolean insert(LMONKEY_USER lmonkey_user) throws SQLException {
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(c3p0Utils.getDataSource());
		//写SQL语句
		String sql = "insert into lmonkey_user values(?,?,?,?,DATE_FORMAT(?,'%Y-%m-%d'),?,?,?,?,?)";
		int sum = runner.update(sql,new Object[]{
				lmonkey_user.getUSER_ID(),
				lmonkey_user.getUSER_NAME(),
				lmonkey_user.getUSER_PASSWORD(),
				lmonkey_user.getUSER_SEX(),
				lmonkey_user.getUSER_BIRTHDAY(),
				lmonkey_user.getUSER_IDENITY_CODE(),
				lmonkey_user.getUSER_EMAIL(),
				lmonkey_user.getUSER_MOBLE(),
				lmonkey_user.getUSER_ADDRESS(),
				lmonkey_user.getUSER_STATUS(),
		});
		if (sum > 0){
			return true;
		}else{
			return false;
		}
	}
	//根据ID修改用户数据
	public static boolean update(LMONKEY_USER lmonkey_user) throws SQLException {
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(c3p0Utils.getDataSource());
		//写SQL语句
		String sql ="update lmonkey_user set USER_NAME=?,USER_PASSWORD=?," +
							"USER_SEX=?,USER_BIRTHDAY=?,USER_IDENITY_CODE=?,USER_EMAIL=?," +
							" USER_MOBLE=?,USER_ADDRESS=?,USER_STATUS=? where USER_ID=?";
		int sum = runner.update(sql,new Object[]{
				lmonkey_user.getUSER_NAME(),
				lmonkey_user.getUSER_PASSWORD(),
				lmonkey_user.getUSER_SEX(),
				lmonkey_user.getUSER_BIRTHDAY(),
				lmonkey_user.getUSER_IDENITY_CODE(),
				lmonkey_user.getUSER_EMAIL(),
				lmonkey_user.getUSER_MOBLE(),
				lmonkey_user.getUSER_ADDRESS(),
				lmonkey_user.getUSER_STATUS(),
				lmonkey_user.getUSER_ID(),
		});
		if (sum > 0){
			return true;
		}else{
			return false;
		}
	}
	//根据用户ID删除用户数据
	public static boolean delete(String id) throws SQLException {
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(c3p0Utils.getDataSource());
		//写SQL语句
		String sql = "delete from lmonkey_user where USER_ID = ?";
		int sum = runner.update(sql,id);
		if (sum > 0){
			return true;
		}else{
			return false;
		}
	}
	//批量删除用户数据
	public static boolean deleteMore(String[] ids) throws SQLException {
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(c3p0Utils.getDataSource());
		//写SQL语句
		String sql = "delete from lmonkey_user where USER_ID = ?";
		int sum = 0;
		for (int i =0; i<ids.length; i++){
			 sum = runner.update(sql,ids[i]);
		}
		if (sum > 0){
			return true;
		}else{
			return false;
		}
	}

	/*
	public static void main(String[] args) throws SQLException {

		List<LMONKEY_USER> list =  userDao.findAll();
		for (LMONKEY_USER user1 : list){
			System.out.println(user1);
		}

	}*/
}
