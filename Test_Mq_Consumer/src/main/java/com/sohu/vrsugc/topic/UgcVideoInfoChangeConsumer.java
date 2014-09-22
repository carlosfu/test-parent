package com.sohu.vrsugc.topic;


import java.util.Map;

import com.sohu.index.tv.mq.common.ConsumerExecutor;

/**
 * 视频信息变化消费者
 * 
 * @author leifu
 * @Time 2014年8月11日
 */
public class UgcVideoInfoChangeConsumer implements ConsumerExecutor {
    @Override
    public void execute(Map<String, Object> messageMap) {
        System.out.println("ugc: " + messageMap);
    }

}