package com.carlosfu.test.bigmemory;

import java.util.HashMap;

import javax.annotation.Resource;

import org.junit.Test;

import com.carlosfu.bigmemory.data.DataComponent;

public class BigMemoryTest extends BaseTest{

	@Resource(name = "heapDataComponent")
	public DataComponent<HashMap<String,String>> heapDataComponent;
	
	/**
	 * TODO 结果出不来。
	 */
    @Test
    public void testStart() {
    	HashMap<String, String> valueMap = new HashMap<String, String>();
    	valueMap.put("hello", "world");
    	
    	String key = "test-ehcache-so";
    	heapDataComponent.add(key, 0, valueMap);
    	
//    	for(int i=0;i<10;i++)
//    	System.out.println("==================get: " + heapDataComponent.get(key) + "================");
    	
    	
        synchronized (BigMemoryTest.class) {
            try {
				BigMemoryTest.class.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
    }

}
