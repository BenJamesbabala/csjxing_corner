package com.doucome.corner.biz.dcome.cache;

import com.doucome.corner.biz.dcome.model.DcItemRateStatisticsDTO;

/**
 * 存放某个商品的投票次数
 * 
 * @author ib 2012-8-18 下午9:52:53
 */
public interface DcItemRateStatisticsCache {

    public void set(long itemId, DcItemRateStatisticsDTO statistics);

    public DcItemRateStatisticsDTO get(long itemId);
}
