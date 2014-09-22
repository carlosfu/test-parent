package com.carlosfu.dramamanual;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import redis.clients.jedis.JedisSentinelPool;

import com.carlosfu.redis.util.RedisSentinelFactory;
import com.sohu.tv.mobil.common.manual.drama.enums.DramaRecOperateEnum;
import com.sohu.tv.mobil.common.manual.drama.enums.DramaRecScopeTypeEnum;
import com.sohu.tv.mobil.common.manual.drama.service.DramaManualServiceImpl;

public class TestDramaManual {

    private static DramaManualServiceImpl dramaManualService = new DramaManualServiceImpl();

    String recTypeValue = "99";
    
    DramaRecScopeTypeEnum recType = DramaRecScopeTypeEnum.SCOPE_PID;

    @BeforeClass
    public static void setUp() {
        JedisSentinelPool redisSentinelPool = RedisSentinelFactory.getJedisSentinelPool();
        dramaManualService.setRedisSentinelPool(redisSentinelPool);
    }

    @Test
    public void testGet() {
        Map<String, String> resultMap = dramaManualService.getFromRedisSentinel(recType.value(), recTypeValue);
        System.out.println("resultMap: " + resultMap);
    }

    @Test
    public void testAdd() {
        Map<String, Object> messageMap = new HashMap<String, Object>();
        messageMap.put("a", "b");
        messageMap.put("cc", "sohutv");
        messageMap.put("endTime", "2014-09-17 12:00:00");
        dramaManualService.dealWithManualMessage(recType.value(), recTypeValue, DramaRecOperateEnum.OPERATE_UPDATE.value(),
                messageMap);
    }
    
    @Test
    public void testDelte(){
        dramaManualService.dealWithManualMessage(recType.value(), recTypeValue, DramaRecOperateEnum.OPERATE_DELETE.value(), new HashMap<String, Object>());
    }
    
}
