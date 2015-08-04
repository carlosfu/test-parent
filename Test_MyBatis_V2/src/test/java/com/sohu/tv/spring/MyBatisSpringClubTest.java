package com.sohu.tv.spring;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.sohu.tv.bean.Club;
import com.sohu.tv.mapper.ClubDao;

/**
 * spring-mybatis clubDao测试
 * 
 * @author leifu
 * @Date 2015年8月4日
 * @Time 下午12:02:21
 */
public class MyBatisSpringClubTest extends MyBatisSpringBaseTest {

    @Resource(name = "clubDao")
    private ClubDao clubDao;

    @Test
    public void testGetAllClubs() {
        List<Club> clubList = clubDao.getAllClubs();
        if (clubList != null && !clubList.isEmpty()) {
            System.out.println("clubList size: " + clubList.size());
            for (Club club : clubList) {
                System.out.println(club);
            }
        }
    }
}
