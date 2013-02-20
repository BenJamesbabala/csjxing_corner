package com.doucome.corner.biz.dcome.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dal.dataobject.dcome.DcRateEventDO;
import com.doucome.corner.biz.dal.dcome.DcRateEventDAO;
import com.doucome.corner.biz.dcome.service.DcRateEventService;

/**
 * @author ib 2012-8-18 ÏÂÎç8:52:47
 */
public class DcRateEventServiceImpl implements DcRateEventService {

    @Autowired
    private DcRateEventDAO dcRateEventDAO;

    @Override
    public long insert(DcRateEventDO rateEventDO) {
        if (rateEventDO != null) {
            return dcRateEventDAO.insert(rateEventDO);
        }
        return 0;
    }

    @Override
    public List<DcRateEventDO> queryByItemId(long itemId) {
        if (itemId > 0) {
            return dcRateEventDAO.queryByItemId(itemId);
        } else {
            List<DcRateEventDO> list = new ArrayList<DcRateEventDO>(0);
            return list;
        }
    }

    @Override
    public List<DcRateEventDO> queryByItemId(long itemId, int inMinutes) {
        if (itemId > 0 && inMinutes > 0) {
            return dcRateEventDAO.queryByItemId(itemId, inMinutes);
        } else {
            List<DcRateEventDO> list = new ArrayList<DcRateEventDO>(0);
            return list;
        }
    }

}
