package com.doucome.corner.biz.core.service;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.SpiderItemSearchCondition;
import com.doucome.corner.biz.dal.dataobject.SpiderItemDO;

/**
 * @author ib 2012-9-15 обнГ11:35:51
 */
public interface SpiderItemService {

    public QueryResult<SpiderItemDO> queryListWithPagination(SpiderItemSearchCondition condition, Pagination pagination);
}
