package com.carlosfu.spring.test.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 基础测试类
 * @author leifu
 * @Date 2014年10月22日
 * @Time 下午7:48:31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring*.xml"})
public class BaseTest {

}
