package com.carlosfu.spring.custom.tag;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author leifu
 * @Date 2014年10月22日
 * @Time 下午8:08:45
 */
public class TestNamespaceHandler extends NamespaceHandlerSupport {

	/**
	 * 初始化时候注册自定义标签
	 */
    public void init() {
        System.out.println("=====================TestNamespaceHandler=======================");
        registerBeanDefinitionParser("custom", new CustomBeanDefinitionParser());
    }
}