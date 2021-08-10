package com.lmonkey.entity;

import jdk.nashorn.internal.ir.IndexNode;

public class LMONKEY_CART {
	private int CART_ID;
	private String CART_PRO_FILENAME;
	private String CART_PRO_NAME;
	private double CART_PRO_PRICE;
	private int CART_QUANTITY;
	private int CART_PRO_STOCK;
	private int CART_PRO_ID;
	private String CART_USER_ID;
	private int CART_CALID;

	public LMONKEY_CART() {
	}

	public LMONKEY_CART(int CART_ID, String CART_PRO_FILENAME, String CART_PRO_NAME, double CART_PRO_PRICE, int CART_QUANTITY, int CART_PRO_STOCK, int CART_PRO_ID, String CART_USER_ID, int CART_CALID) {
		this.CART_ID = CART_ID;
		this.CART_PRO_FILENAME = CART_PRO_FILENAME;
		this.CART_PRO_NAME = CART_PRO_NAME;
		this.CART_PRO_PRICE = CART_PRO_PRICE;
		this.CART_QUANTITY = CART_QUANTITY;
		this.CART_PRO_STOCK = CART_PRO_STOCK;
		this.CART_PRO_ID = CART_PRO_ID;
		this.CART_USER_ID = CART_USER_ID;
		this.CART_CALID = CART_CALID;
	}

	public int getCART_ID() {
		return CART_ID;
	}

	public void setCART_ID(int CART_ID) {
		this.CART_ID = CART_ID;
	}

	public String getCART_PRO_FILENAME() {
		return CART_PRO_FILENAME;
	}

	public void setCART_PRO_FILENAME(String CART_PRO_FILENAME) {
		this.CART_PRO_FILENAME = CART_PRO_FILENAME;
	}

	public String getCART_PRO_NAME() {
		return CART_PRO_NAME;
	}

	public void setCART_PRO_NAME(String CART_PRO_NAME) {
		this.CART_PRO_NAME = CART_PRO_NAME;
	}

	public double getCART_PRO_PRICE() {
		return CART_PRO_PRICE;
	}

	public void setCART_PRO_PRICE(double CART_PRO_PRICE) {
		this.CART_PRO_PRICE = CART_PRO_PRICE;
	}

	public int getCART_QUANTITY() {
		return CART_QUANTITY;
	}

	public void setCART_QUANTITY(int CART_QUANTITY) {
		this.CART_QUANTITY = CART_QUANTITY;
	}

	public int getCART_PRO_STOCK() {
		return CART_PRO_STOCK;
	}

	public void setCART_PRO_STOCK(int CART_PRO_STOCK) {
		this.CART_PRO_STOCK = CART_PRO_STOCK;
	}

	public int getCART_PRO_ID() {
		return CART_PRO_ID;
	}

	public void setCART_PRO_ID(int CART_PRO_ID) {
		this.CART_PRO_ID = CART_PRO_ID;
	}

	public String getCART_USER_ID() {
		return CART_USER_ID;
	}

	public void setCART_USER_ID(String CART_USER_ID) {
		this.CART_USER_ID = CART_USER_ID;
	}

	public int getCART_CALID() {
		return CART_CALID;
	}

	public void setCART_CALID(int CART_CALID) {
		this.CART_CALID = CART_CALID;
	}
}
