package com.sohu.tv.cache;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sohu.tv.bean.Player;

import com.sohu.tv.mapper.PlayerDao;
import com.sohu.tv.test.mapper.BaseTest;

/**
 * 一级缓存测试
 * 
 * @author leifu
 * @Date 2015-8-3
 * @Time 下午9:51:00
 */
public class FirstCacheTest extends BaseTest {

    private SqlSession sqlSession;

    @Before
    public void before() {
        sqlSession = sessionFactory.openSession();
    }

    @After
    public void after() {
        sqlSession.close();
    }

    @Test
    public void testCache1() throws Exception {
        PlayerDao playerDao = sqlSession.getMapper(PlayerDao.class);

        // 下边查询使用一个SqlSession
        
        // 第一次发起请求，查询id为1的球员
        Player player1 = playerDao.getPlayerById(1);
        System.out.println(player1);

        // 如果sqlSession去执行commit操作（执行插入、更新、删除），清空SqlSession中的一级缓存，这样做的目的为了让缓存中存储的是最新的信息，避免脏读。
        // 更新player1的信息
        player1.setAge(88);
        playerDao.updatePlayer(player1);
        // 执行commit操作去清空缓存
        sqlSession.commit();

        // 第二次发起请求，查询id为1的球员
        Player player2 = playerDao.getPlayerById(1);
        System.out.println(player2);

    }
}
