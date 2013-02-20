package com.doucome.corner.biz.dcome.service;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcUserLoginTraceCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserLoginTraceDO;
import com.doucome.corner.biz.dcome.model.DcUserLoginTraceDTO;

/**
 * 
 * @author langben 2012-9-12
 *
 */
public interface DcUserLoginTraceService {

	/**
	 * �½���־
	 * @param loginTrace
	 * @return
	 */
	long createLoginTrace(DcUserLoginTraceDO loginTrace) ;
	
	/**
	 * ��ѯ��־
	 * @param 
	 * @return
	 */
	QueryResult<DcUserLoginTraceDTO> getTraceWithPagination(DcUserLoginTraceCondition condition , Pagination pagination) ;
	
}
