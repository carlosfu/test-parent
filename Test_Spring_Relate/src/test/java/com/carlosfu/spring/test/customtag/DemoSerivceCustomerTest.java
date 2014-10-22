package com.carlosfu.spring.test.customtag;

import javax.annotation.Resource;

import org.junit.Test;

import com.carlosfu.spring.service.DemoService;
import com.carlosfu.spring.test.base.BaseTest;

/**
 * 测试自定义标签
 * 
 * @author leifu
 * @Time 2014年8月26日
 */
public class DemoSerivceCustomerTest extends BaseTest {
	@Resource(name = "testCustom")
	private DemoService testBean;

	@Test
	public void testOnly() {
		testBean.say();
	}

}