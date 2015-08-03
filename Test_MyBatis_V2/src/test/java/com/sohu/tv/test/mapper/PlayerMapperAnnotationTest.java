package com.sohu.tv.test.mapper;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.sohu.tv.bean.Player;
import com.sohu.tv.mapper.PlayerAnnotationDao;
/**
 * mybatis-annotation实现方式配置
 * 
 * @author leifu
 * @Date 2015年7月28日
 * @Time 上午9:54:07
 */
public class PlayerMapperAnnotationTest extends BaseTest {
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
        PlayerAnnotationDao playerAnnotationDao = sqlSession.getMapper(PlayerAnnotationDao.class);
        Player player = playerAnnotationDao.getPlayerById(1);
        System.out.println(player);
    }
    @Test
    public void testInsertPlayer() {
        PlayerAnnotationDao playerAnnotationDao = sqlSession.getMapper(PlayerAnnotationDao.class);
        playerAnnotationDao.savePlayer(new Player(-1, "carlos", 34));
    }
    @Test
    public void testDeletePlayer() {
        PlayerAnnotationDao playerAnnotationDao = sqlSession.getMapper(PlayerAnnotationDao.class);
        playerAnnotationDao.deletePlayer(4);
    }
    @Test
    public void testUpdatePlayer() {
        PlayerAnnotationDao playerAnnotationDao = sqlSession.getMapper(PlayerAnnotationDao.class);
        playerAnnotationDao.updatePlayer(new Player(3, "ronaldo", 39));
    }
    @Test
    public void testSelectAllPlayers() {
        PlayerAnnotationDao playerAnnotationDao = sqlSession.getMapper(PlayerAnnotationDao.class);
        List<Player> playerList = playerAnnotationDao.selectAllPlayers();
        if (playerList != null && !playerList.isEmpty()) {
            System.out.println("playerList size: " + playerList.size());
            for (Player player : playerList) {
                System.out.println(player);
            }
        }
    }
}