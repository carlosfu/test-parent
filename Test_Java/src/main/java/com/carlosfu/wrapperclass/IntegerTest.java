package com.carlosfu.wrapperclass;

/**
 * 原因看Integer源码中的 IntegerCache
 * @author leifu
 * @Date 2014年10月26日
 * @Time 下午10:05:14
 */
public class IntegerTest {
	public static void main(String[] args) {
		Integer a = 1;
		Integer b = 1;
		Integer c = 200;
		Integer d = 200;
		System.out.println(a == b);//true
		System.out.println(c == d);//false
	}
}
