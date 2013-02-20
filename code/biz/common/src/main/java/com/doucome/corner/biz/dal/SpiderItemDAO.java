package com.doucome.corner.biz.dal;

import java.util.List;

import com.doucome.corner.biz.dal.condition.SpiderItemSearchCondition;
import com.doucome.corner.biz.dal.dataobject.SpiderItemDO;

/**
 * @author ib 2012-9-8 обнГ10:31:57
 */
public interface SpiderItemDAO {

    public Long insertItem(SpiderItemDO spiderItemDO);
    
    public SpiderItemDO queryItem(long tbItemId);
    
    public Long countWithPagination(SpiderItemSearchCondition condition);

    public List<SpiderItemDO> queryListWithPagination(SpiderItemSearchCondition condition, long start, long size);
}
