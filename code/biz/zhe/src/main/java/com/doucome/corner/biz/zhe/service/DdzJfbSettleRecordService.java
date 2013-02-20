package com.doucome.corner.biz.zhe.service;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.DdzJfbSettleRecordSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzJfbSettleRecordDO;
import com.doucome.corner.biz.zhe.exception.DdzDuplicateKeyException;

/**
 * ���ֱ����ż�¼
 * @author langben 2012-12-19
 *
 */
public interface DdzJfbSettleRecordService {

	/**
	 * �������ֱ������¼
	 * @param settleRecord
	 * @return
	 */
	long createRecord(DdzJfbSettleRecordDO settleRecord) ;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	DdzJfbSettleRecordDO getRecordById(Long id) ;
	
	/**
	 * 
	 * @param condition
	 * @param start
	 * @param size
	 * @return
	 */
	QueryResult<DdzJfbSettleRecordDO> getRecordsWithPagination(DdzJfbSettleRecordSearchCondition condition , Pagination pagination) ;
	
	/**
	 * 
	 * @param id
	 * @param settleRecord
	 * @return
	 */
	int updateSuccessInfoById(Long id , DdzJfbSettleRecordDO settleRecord) throws DdzDuplicateKeyException ;
	
	
}
