package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.condition.dcome.DcUserIntegralRecordSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserIntegralRecordDO;

/**
 * 积分记录
 * @author langben 2013-1-8
 *
 */
public interface DcUserIntegralRecordDAO {

	/**
	 * 创建记录
	 * @param record
	 * @return
	 */
	long insertRecord(DcUserIntegralRecordDO record) ;
	
	/**
	 * 查询
	 * @param condition
	 * @param start
	 * @param size
	 * @return
	 */
	List<DcUserIntegralRecordDO> queryRecordsWithPagination(DcUserIntegralRecordSearchCondition condition , int start , int size) ;
	
	/**
	 * count
	 * @param condition
	 * @return
	 */
	int countRecordsWithPagination(DcUserIntegralRecordSearchCondition condition) ;

	/**
	 * 
	 * @param value
	 * @param settleId
	 * @return
	 */
	int updateStatusBySettleIdList(String status, Long[] settleIdList);

	/**
	 * 
	 * @param id
	 * @param value
	 * @param userMemo
	 * @return
	 */
	int updateStatusAndUserMemoByExApproveId(long exApproveId, String status, String userMemo);

	/**
	 * 根据报表查询
	 * @param reportId
	 * @return
	 */
	DcUserIntegralRecordDO queryRecordByReportId(long reportId);
}
