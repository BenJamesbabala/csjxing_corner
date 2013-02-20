package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.condition.dcome.DcUserExchangeApproveSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserExchangeApproveDO;

public interface DcUserExchangeApproveDAO {

	/**
	 * 
	 * @param exchangeApprove
	 * @return
	 */
	long insertExchangeApprove(DcUserExchangeApproveDO exchangeApprove) ;
	
	/**
	 * 
	 * @return
	 */
	List<DcUserExchangeApproveDO> queryExchangeApproveWithPagination(DcUserExchangeApproveSearchCondition condition , int start , int size) ;
	
	/**
	 * 
	 * @return
	 */
	int countExchangeApproveWithPagination(DcUserExchangeApproveSearchCondition condition) ;

	/**
	 * 
	 * @param value
	 * @param settleIdList
	 * @return
	 */
	int updateStatusBySettleIdList(String value, Long[] settleIdList);

	/**
	 * 
	 * @param id
	 * @param status
	 * @param userMemo
	 * @return
	 */
	int updateStatusAndUserMemoById(long id,String status, String userMemo);
	
	
}
