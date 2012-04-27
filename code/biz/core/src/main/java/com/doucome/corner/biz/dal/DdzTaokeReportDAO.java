package com.doucome.corner.biz.dal;

import java.util.List;

import com.doucome.corner.biz.core.enums.AlipayStatusEnum;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.taobao.model.TaokeReportSearchCondition;
import com.doucome.corner.biz.dal.dataobject.AlipayItemDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;

/**
 * ����
 * @author shenjia.caosj 2012-3-7
 *
 */
public interface DdzTaokeReportDAO {

	/**
	 * ���뱨��
	 * @param report
	 * @return
	 */
	int insertReport(DdzTaokeReportDO report) ;
	
	/**
	 * ����
	 * @param settle
	 * @return
	 */
	int settleByTradeId(DdzTaokeReportSettleDO settle) ;
	/**
	 * ����id��ѯ�Կͱ���.
	 * @param reportId .
	 * @return
	 */
	DdzTaokeReportDO getReportById(Long reportId);
	/**
	 * �����ѯ
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
	 * �����Կͱ������״̬.
	 * @param taokeReportIds �Կͱ���id.
	 * @param settleStatus 
	 * @return
	 */
	int updateTaokeReportSettleStatus(List<String> reportIds, String settleStatus, String internalBatchNO);
	/**
	 * �����Կͱ���֧����֧��״̬.
	 * @param reportIds .
	 * @param status .
	 * @param alipayBatchNO .
	 * @return
	 */
	int updateTaokeReportAlipayResult(List<String> reportIds, String status, String alipayBatchNO);

}
