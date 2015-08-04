import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TopicTest {

    public static void main(String[] args) {

        int i = 0;
        while (true) {
            Map<String, Object> messageMap = new HashMap<String, Object>();
            messageMap.put(i + UUID.randomUUID().toString().substring(0, 5), i + "-dog");
            TopicProducerRedisFactory.publishMessage(messageMap);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }

    }
}
