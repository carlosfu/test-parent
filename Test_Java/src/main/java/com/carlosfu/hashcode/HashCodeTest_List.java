package com.carlosfu.hashcode;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 对于List来讲，contains方法只判断equals方法，所以hashcode没有用,
 * 其实从字面意思也可以看出和哈希有关
 * 
 * @author leifu
 * @Time 2014年10月5日
 */
public class HashCodeTest_List {

	/**
	 * hashcode equals都没有重写, 由于equals没有重写所以过滤不掉
	 */
	@Test
	public void testOne() {
		List<Cat> catList = new ArrayList<Cat>();

		Cat cat1 = new Cat();
		cat1.setAge(1);
		cat1.setName("a");
		catList.add(cat1);

		Cat cat2 = new Cat();
		cat2.setAge(1);
		cat2.setName("a");
		if (!catList.contains(cat2)) {
			catList.add(cat2);
		}

		System.out.println("catList size: " + catList.size());
		assertEquals(2, catList.size());
	}

	/**
	 * hashcode重写 equals都没有重写，由于List的contains只判断equals，所以仍然无法过滤
	 */
	@Test
	public void testTwo() {
		CatHasHashcode cat1 = new CatHasHashcode();
		cat1.setAge(1);
		cat1.setName("a");
		CatHasHashcode cat2 = new CatHasHashcode();
		cat2.setAge(1);
		cat2.setName("a");

		List<Cat> catList = new ArrayList<Cat>();
		catList.add(cat1);
		if (!catList.contains(cat2)) {
			catList.add(cat2);
		}


		System.out.println("catList size: " + catList.size());
		assertEquals(2, catList.size());
	}

	/**
	 * 由于equals方法进行了重写，所以过滤了
	 */
	@Test
	public void testThree() {
		CatHasEquals cat1 = new CatHasEquals();
		cat1.setAge(1);
		cat1.setName("a");
		System.out.println("cat1 hashcode: " + cat1.hashCode());
		CatHasEquals cat2 = new CatHasEquals();
		cat2.setAge(1);
		cat2.setName("a");
		System.out.println("cat2 hashcode: " + cat2.hashCode());

		List<Cat> catList = new ArrayList<Cat>();
		catList.add(cat1);
		if (!catList.contains(cat2)) {
			catList.add(cat2);
		}

		System.out.println("catList size: " + catList.size());
		assertEquals(1, catList.size());
	}

	/**
	 * 都重写，结果同上。
	 */
	@Test
	public void testFour() {
		CatHasHashcodeAndEqual cat1 = new CatHasHashcodeAndEqual();
		cat1.setAge(1);
		cat1.setName("a");

		CatHasHashcodeAndEqual cat2 = new CatHasHashcodeAndEqual();
		cat2.setAge(1);
		cat2.setName("a");

		List<Cat> catList = new ArrayList<Cat>();
		catList.add(cat1);
		if (!catList.contains(cat2)) {
			catList.add(cat2);
		}

		System.out.println("catList size: " + catList.size());
		assertEquals(1, catList.size());
	}

}
