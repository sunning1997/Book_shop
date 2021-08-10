package com.lmonkey.userDao;

import com.lmonkey.c3p0Utils.c3p0Utils;
import com.lmonkey.entity.LMONKEY_CATEGORY;
import com.lmonkey.entity.LMONKEY_USER;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class cateDao {
	//查询所有数据
	public static ArrayList<LMONKEY_CATEGORY> findAll() throws SQLException {
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(c3p0Utils.getDataSource());
		//写SQL语句
		String sql = "select *from lmonkey_category";
		ArrayList<LMONKEY_CATEGORY> list = (ArrayList<LMONKEY_CATEGORY>) runner.query(sql, new BeanListHandler<LMONKEY_CATEGORY>(LMONKEY_CATEGORY.class));

		return list;
	}

	//修改数据时根据ID查询数据
	public static LMONKEY_CATEGORY findById(int cate_id) throws SQLException {
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(c3p0Utils.getDataSource());
		//写SQL语句
		String sql = "select *from lmonkey_category where CATE_ID = ?";
		LMONKEY_CATEGORY cate = runner.query(sql, new BeanHandler<LMONKEY_CATEGORY>(LMONKEY_CATEGORY.class),new Object[]{cate_id});

		return cate;
	}
	//修改产品数据时根据ID查询数据
	public static String selectById(int cate_id) throws SQLException {
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(c3p0Utils.getDataSource());
		//写SQL语句
		String sql = "select *from lmonkey_category where CATE_ID = ?";
		LMONKEY_CATEGORY cate = runner.query(sql, new BeanHandler<LMONKEY_CATEGORY>(LMONKEY_CATEGORY.class),new Object[]{cate_id});

		return cate.getCATE_NAME();
	}
	//根据ID查询分类数据
	public static ArrayList<LMONKEY_CATEGORY> findCateById(Integer[] cate_id) throws SQLException {
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(c3p0Utils.getDataSource());
		//写SQL语句
		String sql = "select * from lmonkey_category where CATE_ID = ?";
		LMONKEY_CATEGORY cate = new LMONKEY_CATEGORY();
		ArrayList<LMONKEY_CATEGORY> list = new ArrayList<LMONKEY_CATEGORY>();
		for (int i =0 ;i<cate_id.length;i++){
			cate =(LMONKEY_CATEGORY) runner.query(sql, new BeanHandler(LMONKEY_CATEGORY.class),cate_id[i]);
			list.add(cate);
		}
		//System.out.println("**"+ cate1.getCATE_NAME());
		return list;
	}
	//首页查询分类数据
	public static ArrayList<LMONKEY_CATEGORY> selectCate(String flag) throws SQLException {
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(c3p0Utils.getDataSource());
		//写SQL语句
		String sql = null;
		if (flag != null && flag.equals("father")){
			sql = "select *from lmonkey_category where CATE_PARENT_ID = 0";

		}else {
			sql = "select *from lmonkey_category where CATE_PARENT_ID != 0";
		}
		ArrayList<LMONKEY_CATEGORY> list = (ArrayList<LMONKEY_CATEGORY>) runner.query(sql, new BeanListHandler<LMONKEY_CATEGORY>(LMONKEY_CATEGORY.class));

		return list;
	}

	//插入数据
	public static boolean insert(LMONKEY_CATEGORY lmonkey_category) throws SQLException {
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(c3p0Utils.getDataSource());
		//写SQL语句
		String sql = "insert into lmonkey_category values(?,?,?)";
		int sum = runner.update(sql,new Object[]{
				lmonkey_category.getCATE_ID(),
				lmonkey_category.getCATE_NAME(),
				lmonkey_category.getCATE_PARENT_ID()
		});
		if (sum > 0){
			return true;
		}else{
			return false;
		}
	}
	//根据ID修改数据
	public static boolean update(LMONKEY_CATEGORY lmonkey_category) throws SQLException {
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(c3p0Utils.getDataSource());
		//写SQL语句
		String sql ="update lmonkey_category set CATE_NAME=?,CATE_PARENT_ID=? where CATE_ID=?";
		int sum = runner.update(sql,new Object[]{
				lmonkey_category.getCATE_NAME(),
				lmonkey_category.getCATE_PARENT_ID(),
				lmonkey_category.getCATE_ID(),
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
		String sql = "delete from lmonkey_category where CATE_ID = ? or CATE_PARENT_ID = ?";
		int sum = runner.update(sql,new Object[]{id,id});
		if (sum > 0){
			return true;
		}else{
			return false;
		}
	}
}
