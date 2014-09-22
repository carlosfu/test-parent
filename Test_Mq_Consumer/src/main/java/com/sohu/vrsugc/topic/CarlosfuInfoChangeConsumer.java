package com.sohu.vrsugc.topic;


import java.util.Map;

import com.sohu.index.tv.mq.common.ConsumerExecutor;

/**
 * carlosfu
 * 
 * @author leifu
 * @Time 2014年8月11日
 */
public class CarlosfuInfoChangeConsumer implements ConsumerExecutor {
    @Override
    public void execute(Map<String, Object> messageMap) {
        System.out.println("11111111111111111111carlosfu: " + messageMap);
    }

}