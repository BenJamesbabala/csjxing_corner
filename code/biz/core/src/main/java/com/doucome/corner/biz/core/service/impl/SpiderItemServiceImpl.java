package com.doucome.corner.biz.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.service.SpiderItemService;
import com.doucome.corner.biz.dal.SpiderItemDAO;
import com.doucome.corner.biz.dal.condition.SpiderItemSearchCondition;
import com.doucome.corner.biz.dal.dataobject.SpiderItemDO;

/**
 * @author ib 2012-9-15 ÏÂÎç11:39:22
 */
public class SpiderItemServiceImpl implements SpiderItemService {

    @Autowired
    private SpiderItemDAO spiderItemDAO;

    @Override
    public QueryResult<SpiderItemDO> queryListWithPagination(SpiderItemSearchCondition condition, Pagination pagination) {
        Long totalRecords = spiderItemDAO.countWithPagination(condition);

        if (totalRecords == null || totalRecords <= 0) {
            return new QueryResult<SpiderItemDO>(new ArrayList<SpiderItemDO>(0), pagination, 0);
        }

        List<SpiderItemDO> itemList = spiderItemDAO.queryListWithPagination(condition, pagination.getStart(),
                                                                            pagination.getSize());

        return new QueryResult<SpiderItemDO>(itemList, pagination, totalRecords);
    }

}
