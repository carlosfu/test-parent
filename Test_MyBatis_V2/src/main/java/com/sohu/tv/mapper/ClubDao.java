package com.sohu.tv.mapper;

import java.util.List;


import com.sohu.tv.bean.Club;

/**
 * 俱乐部Dao
 * @author leifu
 * @Date 2015年8月3日
 * @Time 下午2:30:58
 */
public interface ClubDao {

    /**
     * 获取所有俱乐部信息
     * @return
     */
    public List<Club> getAllClubs();
}