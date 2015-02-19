package com.carlosfu.threadlocal.jedis;

/**
 * 统计耗时模型
 * @author leifu
 * @Date 2015年2月11日
 * @Time 下午5:32:34
 */
public class CostModel {
    private Long startTime;
    private Long endTime;

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    private CostModel() {
        
    }

    public static CostModel getCostModel(ThreadLocal<CostModel> threadLocal) {
        CostModel costModel = threadLocal.get();
        if (costModel == null) {
            costModel = new CostModel();
            threadLocal.set(costModel);
        }
        return costModel;
    }

    @Override
    public String toString() {
        return "CostModel [startTime=" + startTime + ", endTime=" + endTime + ", cost time: " + (endTime - startTime)
                + "ms]";
    }

}
