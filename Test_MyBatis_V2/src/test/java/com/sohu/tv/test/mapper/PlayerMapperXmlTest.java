package com.sohu.tv.test.mapper;
import java.io.IOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import com.sohu.tv.bean.Player;
import com.sohu.tv.mapper.PlayerDao;
/**
 * mybatis-xml方式配置(原始方式)
 * 
 * @author leifu
 * @Date 2015年7月28日
 * @Time 上午9:54:07
 */
public class PlayerMapperXmlTest {
    @Test
    public void testGetPlayer() throws IOException {
        String resource = "mybatis-base.xml";
        // 加载mybatis的配置文件（它也加载关联的映射文件）
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        // 创建能执行映射文件中sql的sqlSession
        SqlSession sqlSession = sessionFactory.openSession();
        //statement方式
        String statement = "com.sohu.tv.mapper.PlayerDao" + ".getPlayerById";
        Player player1 = sqlSession.selectOne(statement, 1);
        System.out.println("player1: " + player1);
         
        //Mapper代理方式
        PlayerDao playerDao = sqlSession.getMapper(PlayerDao.class);
        Player player2 = playerDao.getPlayerById(2);
        System.out.println("player2: " + player2);
    }
}