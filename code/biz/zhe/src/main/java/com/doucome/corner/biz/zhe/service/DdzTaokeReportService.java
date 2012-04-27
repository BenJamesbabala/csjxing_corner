package com.doucome.corner.biz.zhe.service;

import java.util.List;

import com.doucome.corner.biz.core.enums.AlipayStatusEnum;
import com.doucome.corner.biz.core.enums.SettleStatusEnums;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.taobao.model.TaokeReportSearchCondition;
import com.doucome.corner.biz.dal.dataobject.AlipayItemDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;


public interface DdzTaokeReportService {

	/**
	 * ���뱨��
	 * @param report
	 * @return
	 */
	int createReport(DdzTaokeReportDO report) ;
	
	/**
	 * ����
	 * @param settle
	 * @return
	 */
	int settleByTradeId(DdzTaokeReportSettleDO settle) ;
	/**
	 * ����id��ѯ����
	 * @param reportId
	 * @return
	 */
	DdzTaokeReportDO getReportById(Long reportId);
	/**
	 * ��ѯ����
	 * @param searchCondition
	 * @param pagination
	 * @return
	 */
	QueryResult<DdzTaokeReportDO> getReportsWithPagination(TaokeReportSearchCondition searchCondition , Pagination pagination) ;
	/**
	 * 
	 * @param pagination
	 * @return
	 */
	QueryResult<AlipayItemDO> getAlipayItemWithPagination(Pagination pagination);
	/**
	 * �����Կͱ������״̬.
	 * @param taokeReportIds �Կͱ���id.
	 * @param settleStatus 
	 * @return
	 */
	int updateTaokeReportSettleStatus(List<String> reportIds, SettleStatusEnums settleStatus, String internalBatchNO);
	/**
	 * 
	 * @param reportIds
	 * @param alipayStatus
	 * @param alipayBatchNO
	 * @return
	 */
	int updateTaokeReportAlipayResult(List<String> reportIds, AlipayStatusEnum alipayStatus, String alipayBatchNO);
	
	
}
