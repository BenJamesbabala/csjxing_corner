package com.doucome.corner.biz.dal;

import java.util.List;

import com.doucome.corner.biz.core.enums.AlipayStatusEnum;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.taobao.model.TaokeReportSearchCondition;
import com.doucome.corner.biz.dal.dataobject.AlipayItemDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;

/**
 * 报表
 * @author shenjia.caosj 2012-3-7
 *
 */
public interface DdzTaokeReportDAO {

	/**
	 * 插入报表
	 * @param report
	 * @return
	 */
	int insertReport(DdzTaokeReportDO report) ;
	
	/**
	 * 结算
	 * @param settle
	 * @return
	 */
	int settleByTradeId(DdzTaokeReportSettleDO settle) ;
	/**
	 * 根据id查询淘客报表.
	 * @param reportId .
	 * @return
	 */
	DdzTaokeReportDO getReportById(Long reportId);
	/**
	 * 报表查询
	 * @param searchCondition
	 * @param start
	 * @param size
	 * @return
	 */
	List<DdzTaokeReportDO> selectReportsWithPagination(TaokeReportSearchCondition searchCondition , int start , int size ) ;
	
	/**
	 * 
	 * @param searchCondition
	 * @return
	 */
	int countReportsWithPagination(TaokeReportSearchCondition searchCondition) ;
	/**
	 * 
	 * @param pagination
	 * @return
	 */
	List<AlipayItemDO> getAlipayItemWithPagination(Pagination pagination);
	/**
	 * 
	 * @param pagination
	 * @return
	 */
	Integer countAlipayItem();
	/**
	 * 更新淘客报表结算状态.
	 * @param taokeReportIds 淘客报表id.
	 * @param settleStatus 
	 * @return
	 */
	int updateTaokeReportSettleStatus(List<String> reportIds, String settleStatus, String internalBatchNO);
	/**
	 * 更新淘客报表支付宝支付状态.
	 * @param reportIds .
	 * @param status .
	 * @param alipayBatchNO .
	 * @return
	 */
	int updateTaokeReportAlipayResult(List<String> reportIds, String status, String alipayBatchNO);

}
