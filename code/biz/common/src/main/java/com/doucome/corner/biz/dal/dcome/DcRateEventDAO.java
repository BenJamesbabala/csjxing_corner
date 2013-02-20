package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.dataobject.dcome.DcRateEventDO;

/**
 * ��DcRateEventDAO.java��ʵ��������TODO ��ʵ������
 * 
 * @author ib 2012-8-18 ����4:34:14
 */
public interface DcRateEventDAO {

    public long insert(DcRateEventDO rateEventDO);

    public List<DcRateEventDO> queryByItemId(long itemId);

    public List<DcRateEventDO> queryByItemId(long itemId, int inMinutes);
}
