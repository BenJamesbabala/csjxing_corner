package com.doucome.corner.biz.dcome.service;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcUserIntegralRecordSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserIntegralRecordDO;
import com.doucome.corner.biz.dcome.enums.DcExchangeApproveStatusEnums;
import com.doucome.corner.biz.dcome.model.DcUserIntegralRecordDTO;

public interface DcUserIntegralRecordService {

	/**
	 * ������¼
	 * @param record
	 * @return
	 */
	long createRecord(DcUserIntegralRecordDO record) ;
	
	/**
	 * ��ѯ
	 * @param condition
	 * @param start
	 * @param size
	 * @return
	 */
	QueryResult<DcUserIntegralRecordDTO> getRecordsWithPagination(DcUserIntegralRecordSearchCondition condition , Pagination pagination ) ;
	
	/**
	 * ���ݽ������״̬
	 * @param status
	 * @param settleId
	 * @return
	 */
	int updateStatusBySettleIdList(DcExchangeApproveStatusEnums toStatus , Long[] settleIdList) ;
	
	/**
	 * ���ݱ����ѯ
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
