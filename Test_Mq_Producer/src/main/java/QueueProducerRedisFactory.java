import java.util.Map;

import com.carlosfu.constants.TestConstants;
import com.sohu.index.tv.mq.redis.QueueProducerRedis;

/**
 * index-mq初始化
 * 
 * @author leifu
 * @Time 2014年8月11日
 */
public class QueueProducerRedisFactory {
    public static QueueProducerRedis queueProducerRedis = new QueueProducerRedis(TestConstants.CARLOSFU_QUEUE_TYPE);
    static {
        queueProducerRedis.setLogLevel("info");
    }

    public static void publishMessage(Map<String, Object> messageMap) {
        queueProducerRedis.publish(messageMap);
    }

}
