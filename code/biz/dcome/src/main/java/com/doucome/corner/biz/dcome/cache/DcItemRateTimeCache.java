package com.doucome.corner.biz.dcome.cache;

/**
 * ���ĳ����Ʒ��ͶƱ����
 * 
 * @author ib 2012-8-18 ����9:52:53
 */
public interface DcItemRateTimeCache {

    public int getRateTimes(long itemId);

    public void incTime(long itemId);
}
