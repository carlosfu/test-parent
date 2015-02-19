package com.carlosfu.hystrix.mobil;


import com.netflix.hystrix.*;

/**
 * 投放中HystrixCommand的封装(代码来自投放)
 * @author leifu
 * @Date 2014年12月8日
 * @Time 下午2:50:40
 * @param <T>
 */
public abstract class DataComponentCommand<T> extends HystrixCommand<T> {

    public final static int DEFAULT_TIMEOUT = 500;

    public final static int DEFAULT_POOL_SIZE = 20;

    public DataComponentCommand(String commondKey, String groupKey, String poolKey) {
        this(commondKey, groupKey, poolKey, DEFAULT_TIMEOUT, DEFAULT_POOL_SIZE);
    }

    /**
     * 创建Command对象
     * @param commandKey 命令描述
     * @param groupKey 命令组名(一般对应Service/远程资源,在不使用poolKey情况下,用来定位线程池)
     * @param poolKey 线程池名(根据名称定位线程池)
     * @param timeout 请求超时时间(单位毫秒)
     * @param poolSize 线程池大小(默认:20个)
     */
    public DataComponentCommand(String commandKey, String groupKey, String poolKey, int timeout, int poolSize) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey))
                .andCommandKey(HystrixCommandKey.Factory.asKey(commandKey))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey(poolKey))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(timeout))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(poolSize)));
    }

    public DataComponentCommand(String commonKey, String groupKey, String poolKey, int timeout) {
        this(commonKey, groupKey, poolKey, timeout, DEFAULT_POOL_SIZE);
    }

    @Override
    protected T getFallback() {
        return null;
    }
}
