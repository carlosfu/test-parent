package com.sohu.tv.spring;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * spring-mybatis基类测试
 * @author leifu
 * @Date 2015年1月12日
 * @Time 下午2:34:21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring.xml"})
public class MyBatisSpringBaseTest  extends Assert {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

}