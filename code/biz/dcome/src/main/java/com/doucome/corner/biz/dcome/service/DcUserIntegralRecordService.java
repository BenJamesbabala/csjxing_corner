package com.doucome.corner.biz.dcome.service;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcUserIntegralRecordSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserIntegralRecordDO;
import com.doucome.corner.biz.dcome.enums.DcExchangeApproveStatusEnums;
import com.doucome.corner.biz.dcome.model.DcUserIntegralRecordDTO;

public interface DcUserIntegralRecordService {

	/**
	 * 创建记录
	 * @param record
	 * @return
	 */
	long createRecord(DcUserIntegralRecordDO record) ;
	
	/**
	 * 查询
	 * @param condition
	 * @param start
	 * @param size
	 * @return
	 */
	QueryResult<DcUserIntegralRecordDTO> getRecordsWithPagination(DcUserIntegralRecordSearchCondition condition , Pagination pagination ) ;
	
	/**
	 * 根据结算更新状态
	 * @param status
	 * @param settleId
	 * @return
	 */
	int updateStatusBySettleIdList(DcExchangeApproveStatusEnums toStatus , Long[] settleIdList) ;
	
	/**
	 * 根据报表查询
	 * @param reportId
	 * @return
	 */
	DcUserIntegralRecordDTO getRecordByReportId(long reportId) ;
	
	/**
	 * 
	 * @param id
	 * @param toStatus
	 * @param userMemo
	 * @return
	 */
	int updateStatusAndUserMemoByExApproveId(long exApproveId , DcExchangeApproveStatusEnums toStatus , String userMemo) ;
}
