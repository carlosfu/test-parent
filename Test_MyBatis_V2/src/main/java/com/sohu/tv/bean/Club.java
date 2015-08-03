package com.sohu.tv.bean;

import java.util.Date;

/**
 * 俱乐部
 * 
 * @author leifu
 * @Date 2015年7月28日
 * @Time 下午1:43:53
 */
public class Club {
    /**
     * 俱乐部id
     */
    private int id;
    /**
     * 俱乐部名
     */
    private String name;

    /**
     * 俱乐部描述
     */
    private String info;

    /**
     * 创建日期
     */
    private Date createDate;
    
    /**
     * 排名
     */
    private int rank;

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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Club [id=" + id + ", name=" + name + ", info=" + info + ", createDate=" + createDate
                + ", rank=" + rank + "]";
    }



}