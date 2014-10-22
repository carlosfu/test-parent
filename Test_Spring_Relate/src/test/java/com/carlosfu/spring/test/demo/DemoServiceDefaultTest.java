package com.carlosfu.spring.test.demo;


import javax.annotation.Resource;

import org.junit.Test;

import com.carlosfu.spring.service.DemoService;
import com.carlosfu.spring.test.base.BaseTest;

public class DemoServiceDefaultTest extends BaseTest{

	@Resource(name = "demoService")
	public DemoService demoService;

	@Test
	public void testOnly() {
		demoService.say();
	}

}
