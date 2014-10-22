package com.carlosfu.spring.test.demo;

import javax.annotation.Resource;

import org.junit.Test;

import com.carlosfu.spring.service.DemoService;
import com.carlosfu.spring.test.base.BaseTest;

/**
 * 
 * @author leifu
 * @Date 2014年10月22日
 * @Time 下午7:20:12
 */
public class DemoServiceTest extends BaseTest {

	@Resource(name = "demoService")
	public DemoService demoService;

	@Test
	public void testOnly() {
		demoService.say();
	}

}