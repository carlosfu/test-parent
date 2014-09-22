package com.sohu.vrsugc.queue;

import java.util.HashMap;
import java.util.Map;

import com.carlosfu.constants.TestConstants;
import com.sohu.index.tv.mq.common.ConsumerExecutor;
import com.sohu.index.tv.mq.redis.QueueConsumerRedis;

/**
 * 
 * @author leifu
 * @Time 2014年9月3日
 */
public class QueueConsumerRedisFactory {
    public static QueueConsumerRedis queueConsumerRedis = new QueueConsumerRedis();

    /**
     * 初始化index-MQ
     */
    public static void start() {
        queueConsumerRedis.setLogLevel("error");
        Map<String, ConsumerExecutor> queueConsumerMap = new HashMap<String, ConsumerExecutor>();

        CarlosfuInfoChangeQueueConsumer carlosfuInfoChangeConsumer = new CarlosfuInfoChangeQueueConsumer();
        queueConsumerMap.put(TestConstants.CARLOSFU_QUEUE_TYPE, carlosfuInfoChangeConsumer);
        
        queueConsumerRedis.setQueueConsumerMap(queueConsumerMap);
        // 启动
        queueConsumerRedis.start();
    }

}
