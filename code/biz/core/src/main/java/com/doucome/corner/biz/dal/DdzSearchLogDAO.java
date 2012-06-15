package com.doucome.corner.biz.dal;

import java.util.List;

import com.doucome.corner.biz.dal.condition.DdzSearchLogCondition;
import com.doucome.corner.biz.dal.dataobject.DdzSearchLogDO;

/**
 * ������־
 * @author shenjia.caosj 2012-3-7
 *
 */
public interface DdzSearchLogDAO {

	/**
	 * ������־
	 * @param searchLog
	 * @return
	 */
	long insertLog(DdzSearchLogDO searchLog) ;
	
	/**
	 * ��ѯ��־
	 * @return
	 */
	List<DdzSearchLogDO> querySearchLogWithPagination(DdzSearchLogCondition seachCondition , int start , int size) ;
	
	/**
	 * ͳ����־
	 * @return
	 */
	int countSearchLogWithPagination(DdzSearchLogCondition searchCondition) ;
}
