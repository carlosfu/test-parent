package com.carlosfu.test.bigmemory;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoServiceDefaultTest {

    @Test
    public void testStart() {
        String xml = "classpath:applicationContext.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {xml});
//        DemoService demoService = (DemoService) context.getBean("demoService");
//        demoService.say();
    }

}
