package com.sohu.tv.cache;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.sohu.tv.bean.Player;
import com.sohu.tv.mapper.PlayerDao;
import com.sohu.tv.test.mapper.BaseTest;

/**
 * 二级缓存测试
 * 
 * @author leifu
 * @Date 2015-8-3
 * @Time 下午10:10:34
 */
public class SecondCacheTest extends BaseTest {
    
    /**
     * 
     * @throws Exception
     */
    @Test
    public void testCache2() throws Exception {
        SqlSession sqlSession1 = sessionFactory.openSession();
        SqlSession sqlSession2 = sessionFactory.openSession();
        SqlSession sqlSession3 = sessionFactory.openSession();

        PlayerDao playerDao1 = sqlSession1.getMapper(PlayerDao.class);
        // 第一次发起请求，查询id为1的球员
        Player player1 = playerDao1.getPlayerById(1);
        System.out.println("player1: " + player1);
        // 这里执行关闭操作，将sqlsession中的数据写到二级缓存区域
        sqlSession1.close();

        // 使用sqlSession3执行commit()操作
        // 第一次发起请求，查询id为1的球员
        PlayerDao playerDao3 = sqlSession3.getMapper(PlayerDao.class);
        Player player3 = playerDao3.getPlayerById(1);
        System.out.println("player3: " + player3);
        player3.setAge(60);
        playerDao3.updatePlayer(player3);
        // 执行提交，清空UserMapper下边的二级缓存
        sqlSession3.commit();
        sqlSession3.close();

        PlayerDao playerDao2 = sqlSession2.getMapper(PlayerDao.class);
        // 第二次发起请求，查询id为1的用户
        Player player2 = playerDao2.getPlayerById(1);
        System.out.println("player2: " + player2);
        sqlSession2.close();

    }
}
