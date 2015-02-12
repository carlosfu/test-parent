package com.carlosfu.collections.threadsafe;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ConcurrentHashMap线程安全使用方法
 * @author leifu
 * @Date 2015年2月11日
 * @Time 下午6:12:58
 */
public class ConcurrentHashMapTest {
    /**
     * 计数
     */
    private static ConcurrentHashMap<String, AtomicInteger> counterMap = new ConcurrentHashMap<String, AtomicInteger>();

    /**
     * 计数器
     */
    private static AtomicInteger counter = new AtomicInteger();

    /**
     * 线程数
     */
    private static final int randomTotal = 500;

    /**
     * 模拟key
     */
    private static final String key = "2015-02-11";

    public static void main(String[] args) throws InterruptedException {
        
        long startTime = System.currentTimeMillis();

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        
        for (int i = 0; i < randomTotal; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    threadNotSafeMethod();
                    threadSafeMethod2();

                    // 为了防止混淆,单独计数
                    counter.getAndIncrement();
                    if (counter.get() >= randomTotal) {
                        countDownLatch.countDown();
                    }
                }
                
                private void threadSafeMethod2() {
                    if (counterMap.contains(key)) {
                        counterMap.get(key).getAndIncrement();
                    }else{
                        AtomicInteger tmpAtomicInteger = new AtomicInteger(1);
                        AtomicInteger result = counterMap.putIfAbsent(key, tmpAtomicInteger);
                        if (result != null) {
                            result.getAndIncrement();
                        }
                    }
                }

                private void threadSafeMethod() {
                    AtomicInteger tmpAtomicInteger = new AtomicInteger(1);
                    AtomicInteger result = counterMap.putIfAbsent(key, tmpAtomicInteger);
                    if (result != null) {
                        result.getAndIncrement();
                    }
                }

                private void threadNotSafeMethod() {
                    if (counterMap.contains(key)) {
                        counterMap.get(key).getAndIncrement();
                    } else {
                        AtomicInteger tmpAtomicInteger = new AtomicInteger(1);
                        counterMap.put(key, tmpAtomicInteger);
                    }

                }
            }).start();
        }
        // 等待通知
        countDownLatch.await();
        System.out.println("final concurrentMap: " + counterMap + ", costTime: "
                + (System.currentTimeMillis() - startTime) + "ms");
    }

}
