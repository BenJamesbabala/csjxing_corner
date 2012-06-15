package com.doucome.corner.biz.zhe.service;

import java.math.BigDecimal;
import java.util.List;

import com.doucome.corner.biz.core.enums.SettleStatusEnums;
import com.doucome.corner.biz.dal.dataobject.AlipayItemDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;
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
    int updateTaokeSettleStatus(List<Integer> settleIdList , SettleStatusEnums settleStatus) ;
    
    /**
     * ��ȡĳ��Ա�ܹ�����ķ���
     * @param alipayId
     * @param settleStatus
     * @return
     */
    BigDecimal getTotalSettleFee(String settleAlipay , String[] settleStatus) ;
}
