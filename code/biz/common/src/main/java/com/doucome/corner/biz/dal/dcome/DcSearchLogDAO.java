package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.condition.dcome.DcSearchLogCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcSearchLogDO;


/**
 * ∂πﬁ¢…Ã∆∑
 * @author langben 2012-7-21
 *
 */
public interface DcSearchLogDAO {
	/**
	 * 
	 * @param searchLog
	 * @return
	 */
	Long insertSearchLog(DcSearchLogDO searchLog);
	/**
	 * 
	 * @param condition
	 * @param start
	 * @param size
	 * @return
	 */
	List<DcSearchLogDO> querySearchLogsPage(DcSearchLogCondition condition, int start, int size);
	/**
	 * 
	 * @param condition
	 * @return
	 */
	Integer countSearchLogs(DcSearchLogCondition condition);
	/**
	 * 
	 * @param condition
	 * @param status
	 * @return
	 */
	Integer updateSearchLogStatus(DcSearchLogCondition condition, String status);
}
