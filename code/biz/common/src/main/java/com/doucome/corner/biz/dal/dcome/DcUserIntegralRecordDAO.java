package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.condition.dcome.DcUserIntegralRecordSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserIntegralRecordDO;

/**
 * ���ּ�¼
 * @author langben 2013-1-8
 *
 */
public interface DcUserIntegralRecordDAO {

	/**
	 * ������¼
	 * @param record
	 * @return
	 */
	long insertRecord(DcUserIntegralRecordDO record) ;
	
	/**
	 * ��ѯ
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
	 * ���ݱ����ѯ
	 * @param reportId
	 * @return
	 */
	DcUserIntegralRecordDO queryRecordByReportId(long reportId);
}
