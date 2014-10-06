package com.carlosfu.hashcode;

/**
 * 没有重写hashcode和equals
 * 
 * @author leifu
 * @Time 2014年10月5日
 */
public class Cat {
	private int age;// 年龄
	private String name; // 名字

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
