package com.lmonkey.c3p0Utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;

public class c3p0Utils {
	private static DataSource dataSource = null;
	static {
		dataSource = new ComboPooledDataSource("mysql");
	}
	public static DataSource getDataSource(){
		return dataSource;
	}


}
