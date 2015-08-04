package com.carlosfu.entity;

import java.io.Serializable;
import java.util.List;

import com.carlosfu.mobil.enums.ChannelEnum;


/**
 * 频道下的结果集
 * @author bboniao
 *
 */
public class ChannelResult implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1763363613342943439L;

    /** vv数 */
    private int vv;

    private ChannelEnum channel;

    /** 结果集,已经虑重 */
    private List<String> results;

    private List<Double> ranks;

    public ChannelEnum getChannel() {
        return channel;
    }

    public void setChannel(ChannelEnum channel) {
        this.channel = channel;
    }

    public int getVv() {
        return vv;
    }

    public void setVv(int vv) {
        this.vv = vv;
    }

    public List<String> getResults() {
        return results;
    }

    public List<Double> getRanks() {
        return ranks;
    }

    public void setRanks(List<Double> ranks) {
        this.ranks = ranks;
    }

    public void setResults(List<String> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "ChannelResult{" +
                "vv=" + vv +
                ", results=" + results +
                '}';
    }
}
