package com.doucome.corner.biz.zhe.service;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.DdzSearchLogCondition;
import com.doucome.corner.biz.dal.dataobject.DdzSearchLogDO;

public interface DdzSearchLogService {

	/**
	 * 
	 * @param searchLog
	 * @return
	 */
	long createLog(DdzSearchLogDO searchLog) ;
	
	/**
	 * ≤È—Ø»’÷æ
	 * @param searchCondition
	 * @param pagination
	 * @return
	 */
	QueryResult<DdzSearchLogDO> getSearchLogWithPagination(DdzSearchLogCondition searchCondition , Pagination pagination) ;
}
