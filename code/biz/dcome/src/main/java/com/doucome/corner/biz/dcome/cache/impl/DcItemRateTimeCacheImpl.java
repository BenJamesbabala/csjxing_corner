package com.doucome.corner.biz.dcome.cache.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.doucome.corner.biz.core.cache.AbstractCacheSupport;
import com.doucome.corner.biz.dcome.cache.DcItemRateTimeCache;

/**
 * ��DcItemRateTimeCacheImpl.java��ʵ��������1Сʱ����ƷͶƱ����������
 * 
 * @author ib 2012-8-18 ����9:54:26
 */
public class DcItemRateTimeCacheImpl extends AbstractCacheSupport implements DcItemRateTimeCache {

    private final static Log log = LogFactory.getLog(DcItemRateTimeCacheImpl.class);

    @Override
    public int getRateTimes(long itemId) {
        String key = buildCachekeyWithArgs(itemId);
        String timeStr = cacheClient.get(key);
        Integer times = null;
        if (timeStr == null) {
            times = 0;
            cacheClient.put(key, String.valueOf(times), ONE_HOUR_MILLISECONDS);
        } else {
            try {
                times = Integer.valueOf(timeStr.trim());
            } catch (Exception e) {
                times = 0;
                cacheClient.put(key, String.valueOf(times), ONE_HOUR_MILLISECONDS);
                log.error("translate to int error: " + timeStr, e);
            }
        }
        return times;
    }

    @Override
    public void incTime(long itemId) {
        String key = buildCachekeyWithArgs(itemId);
        // ��ʼ��һ��
        getRateTimes(itemId);
        cacheClient.incr(key, 1);
    }

}
