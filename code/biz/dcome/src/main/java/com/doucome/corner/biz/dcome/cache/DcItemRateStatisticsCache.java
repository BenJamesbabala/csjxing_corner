package com.doucome.corner.biz.dcome.cache;

import com.doucome.corner.biz.dcome.model.DcItemRateStatisticsDTO;

/**
 * ���ĳ����Ʒ��ͶƱ����
 * 
 * @author ib 2012-8-18 ����9:52:53
 */
public interface DcItemRateStatisticsCache {

    public void set(long itemId, DcItemRateStatisticsDTO statistics);

    public DcItemRateStatisticsDTO get(long itemId);
}
