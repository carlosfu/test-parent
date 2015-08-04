package com.sohu.tv.test.mapper;

import java.util.ArrayList;
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
        sqlSession = sessionFactory.openSession(true);
    }

    @After
    public void after() {
        sqlSession.close();
    }

    @Test
    public void testGetAllClubs() {
        ClubDao clubDao = sqlSession.getMapper(ClubDao.class);
        List<Club> clubList = clubDao.getAllClubs();
        printClubList(clubList);
    }
    
    private void printClubList(List<Club> clubList) {
        if (clubList != null && !clubList.isEmpty()) {
            System.out.println("clubList size: " + clubList.size());
            for (Club club : clubList) {
                System.out.println(club);
            }
        }        
    }

    @Test
    public void testGetByName() {
        ClubDao clubDao = sqlSession.getMapper(ClubDao.class);
        Club club = clubDao.getByName("AC");
        System.out.println(club);
    }
    
    @Test
    public void testUpdateRank() {
        ClubDao clubDao = sqlSession.getMapper(ClubDao.class);
        clubDao.updateRank(1, 1000);
    }
    
    @Test
    public void testGetByIds() {
        ClubDao clubDao = sqlSession.getMapper(ClubDao.class);
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(1);
        ids.add(2);
        
        printClubList(clubDao.getByIds(ids));
    }
}