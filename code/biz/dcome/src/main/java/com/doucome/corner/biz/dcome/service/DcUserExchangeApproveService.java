package com.doucome.corner.biz.dcome.service;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcUserExchangeApproveSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserExchangeApproveDO;
import com.doucome.corner.biz.dcome.enums.DcExchangeApproveStatusEnums;
import com.doucome.corner.biz.dcome.model.DcUserExchangeApproveDTO;

public interface DcUserExchangeApproveService {

	/**
	 * 
	 * @param exchangeApprove
	 * @return
	 */
	long createExchangeApprove(DcUserExchangeApproveDO exchangeApprove) ;
	
	/**
	 * 
	 * @return
	 */
	QueryResult<DcUserExchangeApproveDTO> getExchangeApproveWithPagination(DcUserExchangeApproveSearchCondition condition , Pagination pagination) ;
	
	/**
	 * 根据结算更新状态
	 * @param status
	 * @param settleId
	 * @return
	 */
	int updateStatusBySettleIdList(DcExchangeApproveStatusEnums toStatus , Long[] settleIdList) ;
	
	/**
	 * 
	 * @param id
	 * @param toStatus
	 * @param userMemo
	 * @return
	 */
	int updateStatusAndUserMemoById(long id , DcExchangeApproveStatusEnums toStatus , String userMemo) ;
	
}
