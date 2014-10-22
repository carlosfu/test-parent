package com.carlosfu.spring.test.demo;



import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.carlosfu.spring.service.DemoService;

/**
 * 测试自定义标签
 * @author leifu
 * @Time 2014年8月26日
 */
public class DemoSerivceCustomerTest {

    public static void main(String[] args) {
        String xml = "classpath:test.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {xml});
        DemoService testBean = (DemoService) context.getBean("testCustom");
        testBean.say();
    }
}