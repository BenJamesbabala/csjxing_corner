package com.doucome.corner.biz.zhe.service;

import java.util.List;

import com.doucome.corner.biz.core.enums.AlipayStatusEnum;
import com.doucome.corner.biz.core.enums.SettleStatusEnums;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.dataobject.AlipayItemDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;


public interface AlipayService {
	/**
	 * 
	 * @return
	 */
	List<AlipayItemDO> getAlipayItems();
	/**
	 * 
	 * @param payItems
	 * @param settleStatus
	 * @param internalBatchNO
	 * @return
	 */
    int updateTaokeReportSettleStatus(List<String> expectedIds, List<String> actualIds, SettleStatusEnums settleStatus);
    /**
     * 
     * @param settleStatus
     * @param pagination
     * @return
     */
    QueryResult<DdzTaokeReportDO> getTodaySettleRelatedReport(SettleStatusEnums settleStatus, Pagination pagination);
    /**
     * 更新支付宝付款结果.
     * @param reportIds .
     * @param settleStatus .
     * @param aliPayBatchNO .
     * @return .
     */
    int updateTaokeReportAlipayResult(List<String> reportIds, AlipayStatusEnum alipayStatus, String alipayBatchNO);
}
