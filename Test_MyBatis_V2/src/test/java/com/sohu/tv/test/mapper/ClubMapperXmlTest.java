package com.sohu.tv.test.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sohu.tv.bean.Club;
import com.sohu.tv.mapper.ClubDao;

/**
 * 俱乐部dao测试
 * 
 * @author leifu
 * @Date 2015年8月3日
 * @Time 下午2:33:04
 */
public class ClubMapperXmlTest extends BaseTest {
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
    public void testGetAllClubs() {
        ClubDao clubDao = sqlSession.getMapper(ClubDao.class);
        List<Club> clubList = clubDao.getAllClubs();
        if (clubList != null && !clubList.isEmpty()) {
            System.out.println("clubList size: " + clubList.size());
            for (Club club : clubList) {
                System.out.println(club);
            }
        }
    }
}