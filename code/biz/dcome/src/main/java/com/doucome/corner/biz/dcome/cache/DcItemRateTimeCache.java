package com.doucome.corner.biz.dcome.cache;

/**
 * 存放某个商品的投票次数
 * 
 * @author ib 2012-8-18 下午9:52:53
 */
public interface DcItemRateTimeCache {

    public int getRateTimes(long itemId);

    public void incTime(long itemId);
}
