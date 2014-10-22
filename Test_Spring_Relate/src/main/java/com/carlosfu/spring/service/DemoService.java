package com.carlosfu.spring.service;

/**
 * 测试service
 * 
 * @author leifu
 * @Time 2014年8月26日
 */
public class DemoService {
    private String name;

    public DemoService() {
        System.out.println("=================DemoService构造函数=====================");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void say() {
        System.out.println("name: " + name);
    }
    
}