package com.carlosfu.spring.custom.tag;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import com.carlosfu.spring.service.DemoService;

public class CustomBeanDefinitionParser implements BeanDefinitionParser {
    public CustomBeanDefinitionParser(){
        System.out.println("==================CustomBeanDefinitionParser构造函数======================");
    }
    
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        System.out.println("==================BeanDefinition parse======================");
        String id = element.getAttribute("id");
        String name = element.getAttribute("name");

        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(DemoService.class);
        beanDefinition.getPropertyValues().addPropertyValue("name", name);
        parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);

        return beanDefinition;
    }
}