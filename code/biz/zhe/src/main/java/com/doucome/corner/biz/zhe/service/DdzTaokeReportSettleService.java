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
     * 更新支付宝付款结果.
     * @param reportIds .
     * @param settleStatus .
     * @param aliPayBatchNO .
     * @return .
     */
    int updateAlipayResult(List<DdzTaokeReportSettleDO> settleDOs);
    
    /**
     * 更新淘客结算状态
     * @param settleIdList
     * @param settleStatus
     * @return
     */
    int updateTaokeSettleStatus(List<Long> settleIdList , SettleStatusEnums toSettleStatus) ;
    
    /**
     * 更新淘客结算状态
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
     * 更新备注
     * @param settleId
     * @param memo
     * @return
     */
    int updateMemoById(Integer settleId , String memo) ;
    
    /**
     * 获取某会员总共结算的费用
     * @param alipayId
     * @param settleStatus
     * @return
     */
    BigDecimal getTotalSettleFee(String settleAlipay , String[] settleStatus) ;
    
    /**
     * 统计一个支付宝结算的次数
     */
    int countTotalSettle(String settleAlipay , String[] settleStatus) ;
    
    /**
     * @param searchCondition
     * @return
     */
    int countSettleReports(DdzTaokeReportSettleSearchCondition searchCondition);
    
}
