package com.doucome.corner.biz.dal;

import java.util.List;

import com.doucome.corner.biz.dal.condition.DdzSearchLogCondition;
import com.doucome.corner.biz.dal.dataobject.DdzSearchLogDO;

/**
 * 搜索日志
 * @author shenjia.caosj 2012-3-7
 *
 */
public interface DdzSearchLogDAO {

	/**
	 * 插入日志
	 * @param searchLog
	 * @return
	 */
	long insertLog(DdzSearchLogDO searchLog) ;
	
	/**
	 * 查询日志
	 * @return
	 */
	List<DdzSearchLogDO> querySearchLogWithPagination(DdzSearchLogCondition seachCondition , int start , int size) ;
	
	/**
	 * 统计日志
	 * @return
	 */
	int countSearchLogWithPagination(DdzSearchLogCondition searchCondition) ;
}
