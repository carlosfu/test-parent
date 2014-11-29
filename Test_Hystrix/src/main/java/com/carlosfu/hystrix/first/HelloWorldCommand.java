package com.carlosfu.hystrix.first;

import java.util.concurrent.TimeUnit;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * 第一个Hystrix命令(有点像线程定义) 可以把HelloWorldCommand看错是一个服务或者工具类
 * 
 * @author leifu
 * @Date 2014年11月28日
 * @Time 下午3:26:36
 */
public class HelloWorldCommand extends HystrixCommand<String> {
    private String name;

    private String groupName;

    /**
     * 默认方法执行1秒
     */
    private int commandCostTime = 0;

    public HelloWorldCommand(String name, String groupName) {
        // must
        super(HystrixCommandGroupKey.Factory.asKey(groupName));
        this.name = name;
    }

    /**
     * 命令服务的逻辑
     */
    @Override
    protected String run() throws Exception {
        System.out.println("HelloWorldCommand要等待" + commandCostTime + "秒钟!!");
        TimeUnit.SECONDS.sleep(commandCostTime);
        System.out.println("=========方法执行完成==============");
        return "Hello " + name + ", thread is " + Thread.currentThread().getName();
    }

//    @Override
//    protected String getFallback() {
//        String defaultValue = "!!!!hehe!!!!";
//        System.out.println("出现了未知错误,只能返回默认值了：" + defaultValue);
//        return defaultValue;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getCommandCostTime() {
        return commandCostTime;
    }

    public void setCommandCostTime(int commandCostTime) {
        this.commandCostTime = commandCostTime;
    }

}
