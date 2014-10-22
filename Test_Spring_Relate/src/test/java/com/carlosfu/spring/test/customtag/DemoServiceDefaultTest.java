package com.carlosfu.spring.test.customtag;


import javax.annotation.Resource;

import org.junit.Test;

import com.carlosfu.spring.service.DemoService;
import com.carlosfu.spring.test.base.BaseTest;

/**
 * 原生spring标签
 * @author leifu
 * @Date 2014年10月22日
 * @Time 下午7:48:10
 */
public class DemoServiceDefaultTest extends BaseTest{

	@Resource(name = "demoService")
	private DemoService demoService;

	@Test
	public void testOnly() {
		demoService.setName("d");
		demoService.say();
	}

}
