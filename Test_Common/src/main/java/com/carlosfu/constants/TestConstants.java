package com.carlosfu.constants;

public class TestConstants {
    /** redis-mq相关  and and push*/
    public static final String CARLOSFU_QUEUE_TYPE = "meixi:hehe:hehe";
    public static final String CARLOSFU_TOPIC_TYPE = "carlosfu:kaka:cafu:ronaldo:topic";
    public static final String UGC_TOPIC_TYPE = "videoService:ugc:changed:topic";
    public static final String VRS_TOPIC_TYPE = "videoService:vrs:changed:topic";
    
    /** rocket-mq相关*/
    // 主题
    public static final String ROCKET_TOPIC = "carlosfu-test-simple-1";
    // 推模式消费者组,唯一
    public static final String ROCKET_PUSH_CONSUMER_GROUP = "carlosfu-test-simple-consumer-1";
    // 生产者组
    public static final String ROCKET_PRODUCER_GROUP = "carlosfu-test-simple-producer-1";
    
}
