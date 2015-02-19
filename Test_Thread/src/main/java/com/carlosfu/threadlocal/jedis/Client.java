package com.carlosfu.threadlocal.jedis;

/**
 * 
 * @author leifu
 * @Date 2015年2月11日
 * @Time 下午5:35:23
 */
public class Client {
    /**
     * 记录CostModel ThreadLocal
     */
    public ThreadLocal<CostModel> threadLocal = new ThreadLocal<CostModel>();
    
    public void sendCommand(String command, int seconds){
        CostModel.getCostModel(threadLocal).setStartTime(System.currentTimeMillis());
        Protocol.sendCommand(command, seconds);
    }
    
    public void process(int seconds){
        Protocol.process(seconds);
        CostModel.getCostModel(threadLocal).setEndTime(System.currentTimeMillis());
        System.out.println(threadLocal.get());
        //必须做清除处理
        threadLocal.remove();
    }
    
    
}
