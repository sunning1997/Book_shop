package com.lmonkey.entity;


import java.util.*;
import java.util.stream.Collectors;

public class test {
	public static void main(String[] args) {


		System.out.println("==========================================");
		LMONKEY_CATEGORY lmonkey_category = new LMONKEY_CATEGORY();
		lmonkey_category.setCATE_ID(1);
		lmonkey_category.setCATE_NAME("aaa");
		lmonkey_category.setCATE_PARENT_ID(0);
		List<LMONKEY_CATEGORY> listOne = new ArrayList<LMONKEY_CATEGORY>();
		listOne.add(lmonkey_category);

		/*LMONKEY_PRODUCT product = new LMONKEY_PRODUCT();
		product.setPRODUCT_ID(1);
		product.setPRODUCT_NAME("asd");
		product.setPRODUCT_DESCRIPTION("adfsf");
		product.setPRODUCT_PRICE(123);
		product.setPRODUCT_STOCK(22);
		product.setPRODUCT_CID(1);
		product.setPRODUCT_FID(2);
		product.setPRODUCT_FILENAME("dfsf");
		List<LMONKEY_PRODUCT> listTwo = new ArrayList<LMONKEY_PRODUCT>();
 		listTwo.add(product);*/

		List<String> listTwo = new ArrayList<String>();
		listTwo.add("A");
		listTwo.add("B");
		listTwo.add("C");
		//addAll  添加另一集合里面的元素
		//结果[333, 666, 999, A, B, C]
		//listOne.addAll(listTwo);
		//System.out.println(listOne);


		//add 添加整个集合包括 []
		//结果 [A, B, C, [333, 666, 999, A, B, C]]
		listTwo.add(listOne.toString());
		System.out.println(listTwo.toString());

		Iterator<String> iterator = listTwo.iterator();
		while (iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}

}