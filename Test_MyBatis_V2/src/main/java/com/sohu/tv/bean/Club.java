package com.sohu.tv.bean;

/**
 * 俱乐部
 * @author leifu
 * @Date 2015年8月3日
 * @Time 下午1:46:51
 */
public class Club {
    /**
     * 自增id
     */
    private int id;
    
    /**
     * 名称
     */
    private String name;
    
    /**
     * 简介
     */
    private String clubInfo;

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

    public String getClubInfo() {
        return clubInfo;
    }

    public void setClubInfo(String clubInfo) {
        this.clubInfo = clubInfo;
    }

    @Override
    public String toString() {
        return "Club [id=" + id + ", name=" + name + ", clubInfo=" + clubInfo + "]";
    }
    
    
}
