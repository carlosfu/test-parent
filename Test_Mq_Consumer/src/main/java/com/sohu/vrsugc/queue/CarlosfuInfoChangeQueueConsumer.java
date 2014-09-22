package com.sohu.vrsugc.queue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.sohu.index.tv.mq.common.ConsumerExecutor;

/**
 * carlosfu
 * 
 * @author leifu
 * @Time 2014年8月11日
 */
public class CarlosfuInfoChangeQueueConsumer implements ConsumerExecutor {
    @Override
    public void execute(Map<String, Object> messageMap) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date()) + ": ===================carlosfu queue: " + messageMap
                + "======================");
    }

}