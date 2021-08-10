package com.lmonkey.userDao;

import com.lmonkey.c3p0Utils.c3p0Utils;
import com.lmonkey.entity.LMONKEY_CATEGORY;
import com.lmonkey.entity.LMONKEY_PRODUCT;
import com.lmonkey.entity.LMONKEY_USER;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;

public class productDao {

	//查询所有数据
	public static ArrayList<LMONKEY_PRODUCT> findAll() throws SQLException {
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(c3p0Utils.getDataSource());
		//写SQL语句
		String sql = "select *from lmonkey_product";
		ArrayList<LMONKEY_PRODUCT> list = (ArrayList<LMONKEY_PRODUCT>) runner.query(sql, new BeanListHandler<LMONKEY_PRODUCT>(LMONKEY_PRODUCT.class));

		return list;
	}
	//修改数据时根据ID查询数据
	public static LMONKEY_PRODUCT findById(int id) throws SQLException {
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(c3p0Utils.getDataSource());
		//写SQL语句
		String sql = "select *from lmonkey_product where PRODUCT_ID = ?";
		LMONKEY_PRODUCT product = runner.query(sql, new BeanHandler<LMONKEY_PRODUCT>(LMONKEY_PRODUCT.class),new Object[]{id});

		return product;
	}
	//查询所有数据
	public static ArrayList<LMONKEY_PRODUCT> findAll(int cpage,int count,String keyword) throws SQLException {
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(c3p0Utils.getDataSource());
		//写SQL语句
		String sql = "";
		ArrayList<LMONKEY_PRODUCT> list = null;
		if (keyword != null){
			sql = "select * from lmonkey_product where PRODUCT_NAME like ? order by PRODUCT_PRICE asc limit ?,?";
			list = (ArrayList<LMONKEY_PRODUCT>) runner.query(sql, new BeanListHandler<LMONKEY_PRODUCT>(LMONKEY_PRODUCT.class),new Object[]{ "%"+keyword+"%",(cpage-1)*count,count});
		}else{
			sql = "select * from lmonkey_product order by PRODUCT_ID asc limit ?,?";
			list = (ArrayList<LMONKEY_PRODUCT>) runner.query(sql, new BeanListHandler<LMONKEY_PRODUCT>(LMONKEY_PRODUCT.class),new Object[]{ (cpage-1)*count,count});
		}
		return list;

	}
	//查询数据总数
	public static int[] totalPage(int count,String keyword) throws SQLException {
		int arr[] = {0,1};//0 总数，1 页数
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(c3p0Utils.getDataSource());
		String sql = "";
		ArrayList<LMONKEY_PRODUCT> list = null;
		if (keyword != null){
			sql = "select * from lmonkey_product where PRODUCT_NAME like ?";
			list = (ArrayList<LMONKEY_PRODUCT>) runner.query(sql, new BeanListHandler<LMONKEY_PRODUCT>(LMONKEY_PRODUCT.class),new Object[]{"%"+keyword+"%"});
		}else {
			sql = "select * from lmonkey_product";
			list = (ArrayList<LMONKEY_PRODUCT>) runner.query(sql, new BeanListHandler<LMONKEY_PRODUCT>(LMONKEY_PRODUCT.class));
		}

		arr[0] = list.size();
		if (arr[0] % count==0){
			arr[1] = arr[0]/count;
		}else {
			arr[1] = arr[0]/count+1;
		}
		return arr;
	}

	//根据FID查询数据
	public static ArrayList<LMONKEY_PRODUCT> selectAllByFid(int id) throws SQLException {
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(c3p0Utils.getDataSource());
		//写SQL语句
		String sql = "select *from lmonkey_product where PRODUCT_FID = ?";
		ArrayList<LMONKEY_PRODUCT> list = (ArrayList<LMONKEY_PRODUCT>) runner.query(sql, new BeanListHandler<LMONKEY_PRODUCT>(LMONKEY_PRODUCT.class),new Object[]{id});

		return list;
	}
	//首页根据FID查询Java图书数据
	public static ArrayList<LMONKEY_PRODUCT> index_selectAllByFid(int id) throws SQLException {
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(c3p0Utils.getDataSource());
		//写SQL语句
		String sql = "select *from lmonkey_product where PRODUCT_FID = ? limit 6";
		ArrayList<LMONKEY_PRODUCT> list = (ArrayList<LMONKEY_PRODUCT>) runner.query(sql, new BeanListHandler<LMONKEY_PRODUCT>(LMONKEY_PRODUCT.class),new Object[]{id});

		return list;
	}
	//首页根据FID查询C图书数据
	public static ArrayList<LMONKEY_PRODUCT> index_C_selectAllByFid(int id) throws SQLException {
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(c3p0Utils.getDataSource());
		//写SQL语句
		String sql = "select *from lmonkey_product where PRODUCT_FID = ? limit 8";
		ArrayList<LMONKEY_PRODUCT> list = (ArrayList<LMONKEY_PRODUCT>) runner.query(sql, new BeanListHandler<LMONKEY_PRODUCT>(LMONKEY_PRODUCT.class),new Object[]{id});

		return list;
	}
	//根据CID查询数据
	public static ArrayList<LMONKEY_PRODUCT> selectAllByCid(int id) throws SQLException {
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(c3p0Utils.getDataSource());
		//写SQL语句
		String sql = "select *from lmonkey_product where PRODUCT_CID = ?";
		ArrayList<LMONKEY_PRODUCT> list = (ArrayList<LMONKEY_PRODUCT>) runner.query(sql, new BeanListHandler<LMONKEY_PRODUCT>(LMONKEY_PRODUCT.class),new Object[]{id});

		return list;
	}
	//获取最近点击图书，放入session
	public static ArrayList<LMONKEY_PRODUCT> selectAllById(ArrayList<Integer> ids) throws SQLException {
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(c3p0Utils.getDataSource());
		ArrayList<LMONKEY_PRODUCT> list = new ArrayList<LMONKEY_PRODUCT>();
		LMONKEY_PRODUCT product = new LMONKEY_PRODUCT();
		for (int i = 0;i<ids.size();i++){
			//写SQL语句
			String sql = "select *from lmonkey_product where PRODUCT_ID = ?";
			 product =  runner.query(sql, new BeanHandler<LMONKEY_PRODUCT>(LMONKEY_PRODUCT.class),ids.get(i));
			 list.add(product);
		}

		return list;
	}
	//插入数据
	public static boolean insert(LMONKEY_PRODUCT lmonkey_product) throws SQLException {
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(c3p0Utils.getDataSource());
		//写SQL语句
		String sql = "insert into lmonkey_product values(?,?,?,?,?,?,?,?)";
		int sum = runner.update(sql,new Object[]{
				lmonkey_product.getPRODUCT_ID(),
				lmonkey_product.getPRODUCT_NAME(),
				lmonkey_product.getPRODUCT_DESCRIPTION(),
				lmonkey_product.getPRODUCT_PRICE(),
				lmonkey_product.getPRODUCT_STOCK(),
				lmonkey_product.getPRODUCT_FID(),
				lmonkey_product.getPRODUCT_CID(),
				lmonkey_product.getPRODUCT_FILENAME()
		});
		if (sum > 0){
			return true;
		}else{
			return false;
		}
	}
	//根据ID修改数据
	public static boolean update(LMONKEY_PRODUCT lmonkey_product) throws SQLException {
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(c3p0Utils.getDataSource());
		//写SQL语句
		String sql ="update lmonkey_product set PRODUCT_NAME=?,PRODUCT_DESCRIPTION=?,PRODUCT_PRICE=?,PRODUCT_STOCK=?,PRODUCT_FID=?,PRODUCT_CID=?,PRODUCT_FILENAME=? where PRODUCT_ID=?";
		int sum = runner.update(sql,new Object[]{
				lmonkey_product.getPRODUCT_NAME(),
				lmonkey_product.getPRODUCT_DESCRIPTION(),
				lmonkey_product.getPRODUCT_PRICE(),
				lmonkey_product.getPRODUCT_STOCK(),
				lmonkey_product.getPRODUCT_FID(),
				lmonkey_product.getPRODUCT_CID(),
				lmonkey_product.getPRODUCT_FILENAME(),
				lmonkey_product.getPRODUCT_ID()
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
		String sql = "delete from lmonkey_product where PRODUCT_ID = ?";
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
		String sql = "delete from lmonkey_product where PRODUCT_ID = ?";
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
}
