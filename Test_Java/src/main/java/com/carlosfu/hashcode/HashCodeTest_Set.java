package com.carlosfu.hashcode;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 主要是实体作为key时候，需要对hashcode进行重写. hashcode帮助找到桶所在的链表行，equals是对比具体的值，
 * 如果hashcode都不同，则立刻认为不是同一个对象。 想一想hashmap数组列表的结构
 * 
 * @author leifu
 * @Time 2014年10月5日
 */
public class HashCodeTest_Set {

	/**
	 * hashcode equals都没有重写，所以Set也没法过滤
	 */
	@Test
	public void testOne() {
		Cat cat1 = new Cat();
		cat1.setAge(1);
		cat1.setName("a");
		Cat cat2 = new Cat();
		cat2.setAge(1);
		cat2.setName("a");

		Set<Cat> catSet = new HashSet<Cat>();
		catSet.add(cat1);
		catSet.add(cat2);

		System.out.println("catSet size: " + catSet.size());
		assertEquals(2, catSet.size());
	}

	/**
	 * hashcode重写 equals都没有重写，找到了行，但是不equals,所以Set也没法过滤
	 */
	@Test
	public void testTwo() {
		CatHasHashcode cat1 = new CatHasHashcode();
		cat1.setAge(1);
		cat1.setName("a");
		CatHasHashcode cat2 = new CatHasHashcode();
		cat2.setAge(1);
		cat2.setName("a");

		Set<CatHasHashcode> catSet = new HashSet<CatHasHashcode>();
		catSet.add(cat1);
		catSet.add(cat2);

		System.out.println("catSet size: " + catSet.size());
		assertEquals(2, catSet.size());
	}

	/**
	 * hashcode没有重写(用的Object,Object用的本地方法)，所以根本没找到对应的行，即使equals重写了照样没用
	 * System.out.println出来hashcode值不同
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

		Set<CatHasEquals> catSet = new HashSet<CatHasEquals>();
		catSet.add(cat1);
		catSet.add(cat2);

		System.out.println("catSet size: " + catSet.size());
		assertEquals(2, catSet.size());
	}

	/**
	 * 只有hashcode和equals全重新，泛型集合类才能过滤掉重复的 hashcode保证了分布均匀, equals保证过了正确
	 * 想一想HashMap的数组链表结构
	 */
	@Test
	public void testFour() {
		CatHasHashcodeAndEqual cat1 = new CatHasHashcodeAndEqual();
		cat1.setAge(1);
		cat1.setName("a");

		CatHasHashcodeAndEqual cat2 = new CatHasHashcodeAndEqual();
		cat2.setAge(1);
		cat2.setName("a");

		Set<CatHasHashcodeAndEqual> catSet = new HashSet<CatHasHashcodeAndEqual>();
		catSet.add(cat1);
		catSet.add(cat2);

		System.out.println("catSet size: " + catSet.size());
		assertEquals(1, catSet.size());
	}

}
