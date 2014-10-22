package com.carlosfu.spring.custom.tag;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import com.carlosfu.spring.service.DemoService;

/**
 * 标签解析器
 * 
 * @author leifu
 * @Date 2014年10月22日
 * @Time 下午8:06:42
 */
public class CustomBeanDefinitionParser implements BeanDefinitionParser {
	public CustomBeanDefinitionParser() {
		System.out.println("=======CustomBeanDefinitionParser构造函数=======");
	}

	/**
	 * 解析
	 */
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		System.out.println("=======BeanDefinition parse=======");

		String id = element.getAttribute("id");
		String name = element.getAttribute("name");

		// 自定义bean
		RootBeanDefinition beanDefinition = new RootBeanDefinition();
		beanDefinition.setBeanClass(DemoService.class);
		beanDefinition.getPropertyValues().addPropertyValue("name", name);

		// 类似于注册中心，用于注册bean
		parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);

		return beanDefinition;
	}
}