import com.alibaba.rocketmq.client.producer.SendResult;
import com.sohu.index.tv.mq.common.Result;
import com.sohu.index.tv.mq.rocketmq.ProducerRocketMQ;

import java.util.HashMap;
import java.util.Map;

public class DramaTabManualMqRocketProducer {
    // 生产者
    private static ProducerRocketMQ producerRocketMQ;
    // 主题
    public static final String ROCKET_TOPIC = "mobil-dramatab-manual-topic";
    // 生产者组
    public static final String ROCKET_PRODUCER_GROUP = "mobil-dramatab-manual-producer";

    static {
        producerRocketMQ = new ProducerRocketMQ(ROCKET_PRODUCER_GROUP, ROCKET_TOPIC);
        producerRocketMQ.start();
    }

    public static boolean addMessage(Map<String, Object> messageMap) {
        Result<SendResult> result = producerRocketMQ.publish(messageMap);
        return result.isSuccess();
    }

    public static void main(String[] args) {
        Map<String, Object> messageMap = new HashMap<String, Object>();
        messageMap.put("id", "99299");

        boolean result = addMessage(messageMap);
        System.out.println("produce result: " + result);
    }

}