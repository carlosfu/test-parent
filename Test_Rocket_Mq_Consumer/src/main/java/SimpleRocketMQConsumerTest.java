import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.carlosfu.constants.TestConstants;
import com.sohu.index.tv.mq.rocketmq.PushConsumerRocketMQ;

import java.util.concurrent.TimeUnit;

public class SimpleRocketMQConsumerTest {
    // 推模式消费者
    private static PushConsumerRocketMQ pushConsumerRocketMQ;

    static {
        pushConsumerRocketMQ = new PushConsumerRocketMQ(TestConstants.ROCKET_PUSH_CONSUMER_GROUP,
                TestConstants.ROCKET_TOPIC);
        pushConsumerRocketMQ.setDebug(true);
        pushConsumerRocketMQ.setReconsume(true);
        pushConsumerRocketMQ.setBroadcast(true);
        pushConsumerRocketMQ.setConsumeMessageBatchMaxSize(12);
        pushConsumerRocketMQ.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
    }

    public static void main(String[] args) throws InterruptedException {

        RocketMqConsumer consumer = new RocketMqConsumer();
        pushConsumerRocketMQ.setConsumerExecutor(consumer);
        pushConsumerRocketMQ.start();
        System.out.println("pushConsumerRocketMQ is started");
        TimeUnit.SECONDS.sleep(120);

        pushConsumerRocketMQ.shutdown();

    }

}