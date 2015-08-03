package com.sohu.tv.mapper;

import java.util.List;



import org.apache.ibatis.annotations.Param;

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
    
    
    /**
     * 根据名称查询
     * @param name
     * @return
     */
    public Club getByName(String name);
    
    /**
     * 更新俱乐部排名
     * @param id
     * @param rank
     */
    public void updateRank(@Param("id") int id, @Param("rank") int rank);
    
    /**
     * 根据id列表获取俱乐部信息
     * @param ids
     * @return
     */
    public List<Club> getByIds(@Param("ids") List<Integer> ids);
    
}