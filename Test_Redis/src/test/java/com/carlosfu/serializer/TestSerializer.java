package com.carlosfu.serializer;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.carlosfu.redis.cluster.list.manage.DramaMessage;
import com.carlosfu.serialize.ProtostuffSerializer;

/**
 * 简单测试protostuff
 * 
 * @author leifu
 * @Time 2014年9月10日
 */
public class TestSerializer {

    public static ProtostuffSerializer protostuffSerializer = new ProtostuffSerializer();

    @Test
    public void testTest() {
        DramaMessage drama = new DramaMessage(2, "message");
        byte[] bytes = protostuffSerializer.serialize(drama);

        DramaMessage newDrama = protostuffSerializer.deserialize(bytes);
        System.out.println(newDrama);
    }

    @Test
    public void testComplex() {
        Map<String, DramaMessage> map = new HashMap<String, DramaMessage>();
        map.put("a", new DramaMessage(2, "message--a"));
        map.put("b", new DramaMessage(3, "message--b"));
        byte[] bytes = protostuffSerializer.serialize(map);

        Map<String, DramaMessage> newMap = protostuffSerializer.deserialize(bytes);
        System.out.println(newMap);
    }
}
