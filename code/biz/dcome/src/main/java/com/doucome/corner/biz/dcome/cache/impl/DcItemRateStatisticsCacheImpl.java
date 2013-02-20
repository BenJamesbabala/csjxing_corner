package com.doucome.corner.biz.dcome.cache.impl;

import com.doucome.corner.biz.core.cache.AbstractCacheSupport;
import com.doucome.corner.biz.dcome.cache.DcItemRateStatisticsCache;
import com.doucome.corner.biz.dcome.model.DcItemRateStatisticsDTO;

/**
 * 类DcItemRateTimeCacheImpl.java的实现描述：1小时内商品投票次数计数器
 * 
 * @author ib 2012-8-18 下午9:54:26
 */
public class DcItemRateStatisticsCacheImpl extends AbstractCacheSupport implements DcItemRateStatisticsCache {

    @Override
    public void set(long itemId, DcItemRateStatisticsDTO statistics) {
        String key = buildCachekeyWithArgs(itemId);
        cacheClient.put(key, statistics, ONE_DAY_MILLISECONDS);
    }

    @Override
    public DcItemRateStatisticsDTO get(long itemId) {
        String key = buildCachekeyWithArgs(itemId);
        return cacheClient.get(key);
    }

}
