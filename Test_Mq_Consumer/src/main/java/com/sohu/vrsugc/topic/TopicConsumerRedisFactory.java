package com.sohu.vrsugc.topic;

import java.util.HashMap;
import java.util.Map;

import com.carlosfu.constants.TestConstants;
import com.sohu.index.tv.mq.common.ConsumerExecutor;
import com.sohu.index.tv.mq.redis.TopicConsumerRedis;

/**
 * index-mq初始化
 * @author leifu
 * @Time 2014年8月11日
 */
public class TopicConsumerRedisFactory {
    public static TopicConsumerRedis topicConsumerRedis = new TopicConsumerRedis();

    /**
     * 初始化index-MQ
     */
    public static void start() {
        topicConsumerRedis.setLogLevel("info");
        Map<String, ConsumerExecutor> topicConsumerMap = new HashMap<String, ConsumerExecutor>();

        // 订阅ugc消息
//        UgcVideoInfoChangeConsumer ugcVideoInfoChangeConsumer = new UgcVideoInfoChangeConsumer();
//        topicConsumerMap.put(UGC_TOPIC_TYPE, ugcVideoInfoChangeConsumer);

        // 同样可以订阅vrs消息
//        VrsVideoInfoChangeConsumer vrsVideoInfoChangeConsumer = new VrsVideoInfoChangeConsumer();
//        topicConsumerMap.put(VRS_TOPIC_TYPE, vrsVideoInfoChangeConsumer);

        CarlosfuInfoChangeConsumer carlosfuInfoChangeConsumer = new CarlosfuInfoChangeConsumer();
        topicConsumerMap.put(TestConstants.CARLOSFU_TOPIC_TYPE, carlosfuInfoChangeConsumer);
        
        topicConsumerRedis.setTopicConsumerMap(topicConsumerMap);
        // 启动
        topicConsumerRedis.start();
    }

}
