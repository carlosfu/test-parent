package com.carlosfu.test.bigmemory;

import java.util.HashMap;

import javax.annotation.Resource;

import org.junit.Test;

import com.carlosfu.bigmemory.data.DataComponent;

/**
 * 简单测试bigmemory
 * @author leifu
 * @Time 2014-10-15
 */
public class BigMemoryTest extends BaseTest {

	@Resource(name = "heapDataComponent")
	public DataComponent<HashMap<String, String>> heapDataComponent;

	/**
	 * 注意单元测试结束了，缓存丢失了，因为jvm关闭了
	 */
	@Test
	public void testSetGet() {
		// 键值
		String key = "test-ehcache";

		// 因为ehcache的value需要序列化,需要实现Serializable接口
		HashMap<String, String> valueMap = new HashMap<String, String>();
		valueMap.put("a", "b");
		valueMap.put("hello", "world");

		// 添加k-v 0代表不过期
		heapDataComponent.add(key, 0, valueMap);

		for (int i = 0; i < 2; i++)
			System.out.println("====key: " + key + ", value: "
					+ heapDataComponent.get(key) + "====");

	}

}
