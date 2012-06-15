package com.doucome.corner.biz.zhe.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.DdzSearchLogDAO;
import com.doucome.corner.biz.dal.condition.DdzSearchLogCondition;
import com.doucome.corner.biz.dal.dataobject.DdzSearchLogDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;
import com.doucome.corner.biz.zhe.service.DdzSearchLogService;

public class DdzSearchLogServiceImpl implements DdzSearchLogService {

	private static final Log logger = LogFactory.getLog(DdzSearchLogServiceImpl.class);

	@Autowired
	private DdzSearchLogDAO ddzSearchLogDAO;

	@Override
	public long createLog(DdzSearchLogDO searchLog) {
		try {
			return ddzSearchLogDAO.insertLog(searchLog);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return 0;
	}

	@Override
	public QueryResult<DdzSearchLogDO> getSearchLogWithPagination(DdzSearchLogCondition searchCondition, Pagination pagination) {
		int totalRecords = ddzSearchLogDAO.countSearchLogWithPagination(searchCondition) ;
        if (totalRecords <= 0) {
            return new QueryResult<DdzSearchLogDO>(new ArrayList<DdzSearchLogDO>(), pagination, totalRecords);
        }
        List<DdzSearchLogDO> items = ddzSearchLogDAO.querySearchLogWithPagination(searchCondition, pagination.getStart(), pagination.getSize());
        return new QueryResult<DdzSearchLogDO>(items, pagination, totalRecords);
	}

}
