import java.util.Map;

import com.carlosfu.constants.TestConstants;
import com.sohu.index.tv.mq.redis.TopicProducerRedis;

/**
 * index-mq初始化
 * 
 * @author leifu
 * @Time 2014年8月11日
 */
public class TopicProducerRedisFactory {
    public static TopicProducerRedis topicProducerRedis = new TopicProducerRedis(TestConstants.CARLOSFU_TOPIC_TYPE);
    static {
        topicProducerRedis.setLogLevel("info");
    }
    public static void publishMessage(Map<String, Object> messageMap) {
        topicProducerRedis.publish(messageMap);
    }

}
