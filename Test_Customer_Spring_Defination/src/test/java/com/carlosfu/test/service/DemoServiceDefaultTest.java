package com.carlosfu.test.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.carlosfu.service.DemoService;

public class DemoServiceDefaultTest {

    @Test
    public void testStart() {
        String xml = "classpath:applicationContext.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {xml});
        DemoService demoService = (DemoService) context.getBean("demoService");
        demoService.say();
    }

}
