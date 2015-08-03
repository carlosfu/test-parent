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
public interface PlayerDao {
 
    public Player getPlayerById(int id);
    
}