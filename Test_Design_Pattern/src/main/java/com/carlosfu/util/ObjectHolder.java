package com.carlosfu.util;

import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 单例对象管理
 * 
 * @author leifu
 * @Date 2014年11月6日
 * @Time 下午5:10:38
 */
public class ObjectHolder {
    private static Logger logger = LoggerFactory.getLogger(ObjectHolder.class);

    /**
     * 对象缓存
     */
    private static ConcurrentHashMap<String, Object> holder = new ConcurrentHashMap<String, Object>();

    // 注册关闭资源的钩子
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                logger.info("ObjectHolder shutdown!");
                shutdown();
            }
        }, "ObjectHolderHook"));
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String name, ObjectGenerator<T> generator) {
        if (holder.containsKey(name)) {
            return (T) holder.get(name);
        }
        synchronized (name.intern()) {
            if (!holder.containsKey(name)) {
                holder.putIfAbsent(name, generator.generate(name));
            }
        }
        return (T) holder.get(name);
    }

    public static ConcurrentHashMap<String, Object> getHolder() {
        return holder;
    }

    /**
     * 实现@IShutdown的进行关闭资源操作
     */
    public static void shutdown() {
        for (String key : holder.keySet()) {
            Object obj = holder.get(key);
            if (obj instanceof IShutdown) {
                ((IShutdown) obj).shutdown();
                logger.info("{} shutdown!", key);
            } else {
                logger.info("{} is not IshutDown interface!", key);
            }
        }
    }

}
