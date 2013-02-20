package com.doucome.corner.biz.apps.namefate.service;

import com.doucome.corner.biz.apps.namefate.model.NamefateTrickDTO;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.namefate.condition.NamefateTrickSearchCondition;
import com.doucome.corner.biz.dal.namefate.dataobject.NamefateTrickDO;

public interface NamefateTrickService {

	/**
	 * 
	 * @param trick
	 * @return
	 */
	long createTrick(NamefateTrickDO trick) ;
	
	/**
	 * 
	 * @param trickId
	 * @return
	 */
	NamefateTrickDTO getTrickById(Long trickId) ;
	
	/**
	 * 
	 * @param condition
	 * @param start
	 * @param size
	 * @return
	 */
	QueryResult<NamefateTrickDTO> getTricksWithPagination(NamefateTrickSearchCondition condition , Pagination pagination) ;
	
	
}
