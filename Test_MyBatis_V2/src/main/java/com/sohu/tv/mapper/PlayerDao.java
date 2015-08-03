package com.sohu.tv.mapper;
import java.util.List;

import com.sohu.tv.bean.Player;
/**
 * 注解方式实现PlayerDao
 * 
 * @author leifu
 * @Date 2015年7月28日
 * @Time 上午10:16:39
 */
public interface PlayerDao {
    
    public int savePlayer(Player player);
    
    public int deletePlayer(int id);
      
    public int updatePlayer(Player player);
      
    public Player getPlayerById(int id);
      
    public List<Player> selectAllPlayers();
}