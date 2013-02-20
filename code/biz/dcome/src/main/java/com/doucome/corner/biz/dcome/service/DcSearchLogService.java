package com.doucome.corner.biz.dcome.service;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcSearchLogCondition;
import com.doucome.corner.biz.dcome.enums.DcAutoExchangeStatusEnum;
import com.doucome.corner.biz.dcome.model.DcSearchLogDTO;

/**
 * itemService
 * @author langben 2012-7-22
 *
 */
public interface DcSearchLogService {
	/**
	 * 
	 * @param searchLog
	 * @return
	 */
	Long insertSearchLog(DcSearchLogDTO searchLog);
	/**
	 * 
	 * @param condition
	 * @return
	 */
	QueryResult<DcSearchLogDTO> querySearchLogsPage(DcSearchLogCondition condition, Pagination page);
	/**
	 * 
	 * @param condition
	 * @param status
	 * @return
	 */
	int updateSearchLogStatus(DcSearchLogCondition condition, DcAutoExchangeStatusEnum status);
}
