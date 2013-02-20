package com.doucome.corner.biz.dal;

import java.util.List;

import com.doucome.corner.biz.dal.condition.DdzJfbSettleRecordSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzJfbSettleRecordDO;


/**
 * 积分宝结算记录
 * @author langben 2012-12-19
 *
 */
public interface DdzJfbSettleRecordDAO {

	/**
	 * 新增集分宝结算记录
	 * @param settleRecord
	 * @return
	 */
	long insertRecord(DdzJfbSettleRecordDO settleRecord) ;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	DdzJfbSettleRecordDO queryRecordById(Long id);
	
	/**
	 * 
	 * @param condition
	 * @param start
	 * @param size
	 * @return
	 */
	List<DdzJfbSettleRecordDO> queryRecordsWithPagination(DdzJfbSettleRecordSearchCondition condition , int start , int size) ;
	
	/**
	 * 
	 * @param condition
	 * @return
	 */
	int countRecordsWithPagination(DdzJfbSettleRecordSearchCondition condition) ;
	
	/**
	 * 
	 * @param id
	 * @param settleRecord
	 * @return
	 */
	int updateSuccessInfoById(Long id , DdzJfbSettleRecordDO settleRecord) ;

	
}
