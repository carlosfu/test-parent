package com.sohu.tv.test.mapper;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.sohu.tv.bean.Player;
import com.sohu.tv.mapper.PlayerDao;
/**
 * mybatis-xml方式配置
 * 
 * @author leifu
 * @Date 2015年7月28日
 * @Time 上午9:54:07
 */
public class PlayerMapperXmlTestV2 extends BaseTest {
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
    public void testGetPlayer() {
        PlayerDao playerDao = sqlSession.getMapper(PlayerDao.class);
        Player player = playerDao.getPlayerById(2);
        System.out.println(player);
    }
     
    @Test
    public void testInsertPlayer() {
        PlayerDao playerDao = sqlSession.getMapper(PlayerDao.class);
        playerDao.savePlayer(new Player(-1, "cr7", 30));
    }
     
    @Test
    public void testDeletePlayer() {
        PlayerDao playerDao = sqlSession.getMapper(PlayerDao.class);
        playerDao.deletePlayer(3);
    }
    @Test
    public void testUpdatePlayer() {
        PlayerDao playerDao = sqlSession.getMapper(PlayerDao.class);
        playerDao.updatePlayer(new Player(3, "cafu", 45));
    }
     
    @Test
    public void testSelectAllPlayers() {
        PlayerDao playerDao = sqlSession.getMapper(PlayerDao.class);
        List<Player> playerList = playerDao.selectAllPlayers();
        if (playerList != null && !playerList.isEmpty()) {
            System.out.println("playerList size: " + playerList.size());
            for (Player player : playerList) {
                System.out.println(player);
            }
        }
    }
}