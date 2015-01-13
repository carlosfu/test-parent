package com.carlosfu.threadlocal;

/**
 * 单例
 * 
 * @author leifu
 * @Date 2015年1月13日
 * @Time 上午10:09:35
 */
public class SingleInstanceModel {

    /**
     * 说明
     */
    private String info;

    /**
     * 姓名
     */
    private String name;

    private SingleInstanceModel() {
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public synchronized static SingleInstanceModel getInstanceModel(ThreadLocal<SingleInstanceModel> threadData) {
        SingleInstanceModel model = threadData.get();
        if (model == null) {
            model = new SingleInstanceModel();
            threadData.set(model);
        }
        return model;
    }

    @Override
    public String toString() {
        return "SingleInstanceModel [info=" + info + ", name=" + name + "]";
    }

}