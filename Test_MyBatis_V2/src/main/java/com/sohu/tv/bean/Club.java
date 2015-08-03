package com.sohu.tv.bean;

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
    private String clubName;

    /**
     * 俱乐部描述
     */
    private String clubInfo;

    public Club(int id, String clubName, String clubInfo) {
        this.id = id;
        this.clubName = clubName;
        this.clubInfo = clubInfo;
    }

    public Club() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubInfo() {
        return clubInfo;
    }

    public void setClubInfo(String clubInfo) {
        this.clubInfo = clubInfo;
    }

    @Override
    public String toString() {
        return "Club [id=" + id + ", clubName=" + clubName + ", clubInfo=" + clubInfo + "]";
    }

}