package com.doucome.corner.biz.zhe.service;

import java.math.BigDecimal;
import java.util.List;

import com.doucome.corner.biz.core.enums.SettleStatusEnums;
import com.doucome.corner.biz.dal.condition.DdzTaokeReportSettleSearchCondition;
import com.doucome.corner.biz.dal.condition.DdzTaokeReportSettleUpdateCondition;
import com.doucome.corner.biz.dal.dataobject.AlipayItemDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleStatisticsDO;
import com.doucome.corner.biz.zhe.model.SettleResult;


public interface DdzTaokeReportSettleService {
	/**
	 * 
	 * @param settleDO
	 * @return
	 */
	Long insertSettleReport(DdzTaokeReportSettleDO settleDO);
	/**
	 * 
	 * @param id
	 * @return
	 */
	Integer deleteSettleReport(Long id);
	
	DdzTaokeReportSettleDO getById(Long id);
	/**
	 * 
	 * @return
	 */
	List<AlipayItemDO> getAlipayItems(int pageSize);
	/**
	 * 
	 * @param payItems
	 * @param settleStatus
	 * @param internalBatchNO
	 * @return
	 */
	SettleResult updateSettleStatus(List<DdzTaokeReportSettleDO> settleDOs);
    /**
     * ����֧����������.
     * @param reportIds .
     * @param settleStatus .
     * @param aliPayBatchNO .
     * @return .
     */
    int updateAlipayResult(List<DdzTaokeReportSettleDO> settleDOs);
    
    /**
     * �����Կͽ���״̬
     * @param settleIdList
     * @param settleStatus
     * @return
     */
    int updateTaokeSettleStatus(List<Long> settleIdList , SettleStatusEnums toSettleStatus) ;
    
    /**
     * �����Կͽ���״̬
     * @param settleIdList
     * @param settleStatus
     * @return
     */
    int updateTaokeSettleStatus(DdzTaokeReportSettleUpdateCondition condition , SettleStatusEnums toSettleStatus , String settleBatchno) ;
    
    /**
	 * 
	 * @param searchCondition
	 * @return
	 */
	DdzTaokeReportSettleStatisticsDO statisticsWithPagination(DdzTaokeReportSettleSearchCondition searchCondition) ;
    
    /**
     * ���±�ע
     * @param settleId
     * @param memo
     * @return
     */
    int updateMemoById(Integer settleId , String memo) ;
    
    /**
     * ��ȡĳ��Ա�ܹ�����ķ���
     * @param alipayId
     * @param settleStatus
     * @return
     */
    BigDecimal getTotalSettleFee(String settleAlipay , String[] settleStatus) ;
    
    /**
     * ͳ��һ��֧��������Ĵ���
     */
    int countTotalSettle(String settleAlipay , String[] settleStatus) ;
    
    /**
     * @param searchCondition
     * @return
     */
    int countSettleReports(DdzTaokeReportSettleSearchCondition searchCondition);
    
}
