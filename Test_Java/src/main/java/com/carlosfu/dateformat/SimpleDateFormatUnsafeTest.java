package com.carlosfu.dateformat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 验证SimpleDateFormat是线程不安全的
 * 
 * @author leifu
 * @Date 2015年2月6日
 * @Time 下午3:31:21
 */
public class SimpleDateFormatUnsafeTest {
    /**
     * 这样用是线程不安全的
     */
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 数据源，存储2000-01-01到4999-01-01三千个Date数据
     */
    private static BlockingDeque<Date> dateQueue = new LinkedBlockingDeque<Date>();

    /**
     * 中间结果(把每个线程的sdf.format放到ConcurrentHashMap里，这样就可以过滤重复的sdf.format结果，
     * 验证SimpleDateFormat的不安全)
     */
    private static ConcurrentHashMap<String, Integer> finalResult = new ConcurrentHashMap<String, Integer>();

    /**
     * 中间结果（把每个线程的sdf.format放到List里）
     */
    private static CopyOnWriteArrayList<String> finalList = new CopyOnWriteArrayList<String>();

    /**
     * 计数器，取到固定个数后，退到主线程做统计
     */
    private static AtomicInteger counter = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        // 存储2000-01-01到4999-01-01一千个Date数据
        initDateData(2000, 5000);
        System.out.println("total dates size is " + dateQueue.size());

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        // 500个线程从queue中各取一个date，然后format放到List和ConcurrentHashMap，这样可以验证SimpleDateFormat不安全
        final int randomTotal = 500;
        for (int i = 0; i < randomTotal; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Date date = dateQueue.poll();
                        String dateFormat = sdf.format(date);
                        finalResult.putIfAbsent(dateFormat, 1);
                        finalList.add(dateFormat);
                        counter.getAndIncrement();
                        // 取足够的条数
                        if (counter.get() == randomTotal) {
                            countDownLatch.countDown();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        // 等待通知
        countDownLatch.await();
        System.out.println("counter: " + counter.get());
        System.out.println("final List size: " + finalList.size());
        System.out.println("final Map key size:" + finalResult.size());
    }

    /**
     * 准备数据
     */
    private static void initDateData(int startYear, int endYear) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = startYear; i < endYear; i++) {
            String date = i + "-01-01";
            try {
                dateQueue.push(simpleDateFormat.parse(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

}
