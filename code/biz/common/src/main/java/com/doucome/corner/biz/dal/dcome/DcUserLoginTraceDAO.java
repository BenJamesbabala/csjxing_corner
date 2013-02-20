package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.condition.dcome.DcUserLoginTraceCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserLoginTraceDO;

/**
 * �û���½��־
 * @author langben 2012-9-12
 *
 */
public interface DcUserLoginTraceDAO {

	/**
	 * �½���־
	 * @param loginTrace
	 * @return
	 */
	long insertLoginTrace(DcUserLoginTraceDO loginTrace) ;
	
	/**
	 * ��ѯһ���û�����־
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
