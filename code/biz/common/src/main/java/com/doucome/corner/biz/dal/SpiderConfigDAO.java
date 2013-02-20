package com.doucome.corner.biz.dal;

import java.util.List;

import com.doucome.corner.biz.dal.condition.SpiderItemSearchCondition;
import com.doucome.corner.biz.dal.dataobject.SpiderConfigDO;
import com.doucome.corner.biz.dal.dataobject.SpiderItemDO;

/**
 * @author ib
 */
public interface SpiderConfigDAO {

    public List<SpiderConfigDO> queryEnableList();
}
