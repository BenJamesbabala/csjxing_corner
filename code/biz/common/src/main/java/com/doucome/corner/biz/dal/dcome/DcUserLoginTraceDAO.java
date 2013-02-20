package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.condition.dcome.DcUserLoginTraceCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserLoginTraceDO;

/**
 * 用户登陆日志
 * @author langben 2012-9-12
 *
 */
public interface DcUserLoginTraceDAO {

	/**
	 * 新建日志
	 * @param loginTrace
	 * @return
	 */
	long insertLoginTrace(DcUserLoginTraceDO loginTrace) ;
	
	/**
	 * 查询一个用户的日志
	 * @param userId
	 * @return
	 */
	List<DcUserLoginTraceDO> queryTraceWithPagination(DcUserLoginTraceCondition searchCondition , int start , int size) ;
	
	/**
	 * 
	 * @param searchCondition
	 * @return
	 */
	int countTraceWithPagination(DcUserLoginTraceCondition searchCondition) ;
}
