package com.doucome.corner.biz.dcome.model;

import java.util.Map;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 类DcItemRateStatisticsDTO.java的实现描述：统计结果
 * 
 * @author ib 2012-8-25 下午11:51:46
 */
public class DcItemRateStatisticsDTO extends AbstractModel {

    private static final long    serialVersionUID = -3920046805468085690L;
    private long                 itemId;
    /**
     * 投票次数
     */
    private int                  times;
    /**
     * 投票成功次数
     */
    private int                  successTimes;
    private Map<String, Integer> ipMap;
    private Map<String, Integer> ipShortTimeMap;
    private Map<String, Integer> agentMap;
    private Map<String, Integer> statusMap;

    public Map<String, Integer> getIpMap() {
        return ipMap;
    }

    public void setIpMap(Map<String, Integer> ipMap) {
        this.ipMap = ipMap;
    }

    public Map<String, Integer> getIpShortTimeMap() {
        return ipShortTimeMap;
    }

    public void setIpShortTimeMap(Map<String, Integer> ipShortTimeMap) {
        this.ipShortTimeMap = ipShortTimeMap;
    }

    public Map<String, Integer> getAgentMap() {
        return agentMap;
    }

    public void setAgentMap(Map<String, Integer> agentMap) {
        this.agentMap = agentMap;
    }

    public Map<String, Integer> getStatusMap() {
        return statusMap;
    }

    public void setStatusMap(Map<String, Integer> statusMap) {
        this.statusMap = statusMap;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public int getSuccessTimes() {
        return successTimes;
    }

    public void setSuccessTimes(int successTimes) {
        this.successTimes = successTimes;
    }

}
