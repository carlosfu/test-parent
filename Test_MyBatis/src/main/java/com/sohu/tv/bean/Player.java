package com.sohu.tv.bean;

/**
 * 用户实体
 * 
 * @author leifu
 * @Date 2015年7月28日
 * @Time 上午9:48:09
 */
public class Player {
    /**
     * 用户id
     */
    private int id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 年龄
     */
    private int age;

    public Player() {
    }

    public Player(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Player [id=" + id + ", name=" + name + ", age=" + age + "]";
    }

}