package com.doucome.corner.biz.dcome.service;

import java.util.List;

import com.doucome.corner.biz.dal.dataobject.dcome.DcRateEventDO;

/**
 * 
 * @author ib 2012-8-18 обнГ8:51:02
 */
public interface DcRateEventService {

    public long insert(DcRateEventDO rateEventDO);

    public List<DcRateEventDO> queryByItemId(long itemId);
    
    public List<DcRateEventDO> queryByItemId(long itemId, int inMinutes);
}
