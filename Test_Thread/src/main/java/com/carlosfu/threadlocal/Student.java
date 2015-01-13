package com.carlosfu.threadlocal;

/**
 * 学生
 * 
 * @author leifu
 * @Date 2015年1月13日
 * @Time 上午10:09:35
 */
public class Student {

    /**
     * 年龄
     */
    private int age;

    /**
     * 姓名
     */
    private String name;

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Student() {
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student [age=" + age + ", name=" + name + "]";
    }

}