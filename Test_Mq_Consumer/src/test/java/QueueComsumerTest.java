import java.util.concurrent.CountDownLatch;

import com.sohu.vrsugc.queue.QueueConsumerRedisFactory;

public class QueueComsumerTest {
    private static CountDownLatch latch = new CountDownLatch(1);
    public static void main(String[] args) {
        QueueConsumerRedisFactory.start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
