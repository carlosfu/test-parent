package com.sohu.tv.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Insert;
import com.sohu.tv.bean.Player;
/**
 * 注解方式实现PlayerDao
 * @author leifu
 * @Date 2015年7月28日
 * @Time 上午10:16:39
 */
public interface PlayerAnnotationDao {
    @Insert("insert into players(name, age) values(#{name}, #{age})")
    public int savePlayer(Player player);
  
    @Delete("delete from players where id=#{id}")
    public int deletePlayer(int id);
     
    @Update("update players set name=#{name},age=#{age} where id=#{id}")
    public int updatePlayer(Player player);
     
    @Select("select id,name,age from players where id=#{id}")
    public Player getPlayerById(int id);
     
    @Select("select id,name,age from players")
    public List<Player> selectAllPlayers();
}