package com.carlosfu.redis.cluster.list.manage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.carlosfu.redis.util.RedisClusterFactory;
import com.carlosfu.serialize.ProtostuffSerializer;

import redis.clients.jedis.PipelineCluster;

public class RedisListMapManage {
    private static PipelineCluster redisCluster = RedisClusterFactory.getRedisCluster();

    private static String key = "carlosfu:reids:cluster:list:manage:kaka";

    private ProtostuffSerializer protostuffSerializer = new ProtostuffSerializer();

    public static void main(String[] args) {
        RedisListMapManage m = new RedisListMapManage();
        List<DramaMessage> list = m.getList();
        System.out.println(list);
    }

    public void setToList(DramaMessage model) {
        List<DramaMessage> lst = getList();
        boolean flag = true;
        for (DramaMessage dramaMessage : lst) {
            if (dramaMessage.equals(model)) {
                dramaMessage = model;
                flag = false;
                break;
            }
        }
        if (flag) {
            lst.add(model);
        }
        

        // List<String> list = redisCluster.lrange(key, 0L, -1L);
        // System.out.println("list: " + list);
        // list.set(location, str);
        // redisCluster.del(key);
        // for (String temp : list) {
        // redisCluster.lpush(key, temp);
        // }
    }

    public void deleteFromList(Integer location) {
        // List<String> list = redisCluster.lrange(key, 0L, -1L);
        // boolean isMoveSuccess = list.remove(location);
        // if (isMoveSuccess) {
        // if (list == null || list.isEmpty()) {
        // redisCluster.del(key);
        // } else {
        // redisCluster.del(key);
        // for (String str : list) {
        // redisCluster.lpush(key, str);
        // }
        // }
        // }
    }

    public List<DramaMessage> getList() {
        List<byte[]> lstBtyes = redisCluster.lrangeBytes(key, 0L, -1L);
        if (lstBtyes == null || !lstBtyes.isEmpty()) {
            return Collections.emptyList();
        } else {
            List<DramaMessage> list = new ArrayList<DramaMessage>();
            for (byte[] tmp : lstBtyes) {
                list.add((DramaMessage) protostuffSerializer.deserialize(tmp));
            }
            return list;
        }

    }
}
