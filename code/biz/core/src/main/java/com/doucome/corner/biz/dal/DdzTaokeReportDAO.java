package com.doucome.corner.biz.dal;

import java.util.List;

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
	 * ����SettleId��ѯ����
	 * @param settleId
	 * @return
	 */
	List<DdzTaokeReportDO> selectReportsBySettleId(Integer settleId) ;
	
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
	 * @param pagination
	 * @return
	 */
	List<AlipayItemDO> getUnMergedReportSettleInfo(Pagination pagination);
	/**
	 * 
	 * @param searchCondition
	 * @return
	 */
	int countReportsWithPagination(TaokeReportSearchCondition searchCondition) ;
	
	/**
	 * 
	 * @param settleDOs
	 * @return
	 */
	int updateSettleStatusBySettleReport(List<DdzTaokeReportDO> settleDOs);
	/**
	 * 
	 * @param ids
	 * @return
	 */
	int updateTaokeReportSettleId(List<Long> reportIds, Long settleId);
	
	/**
	 * 
	 * @param reportIds
	 * @param settleStatus
	 * @return
	 */
	int updateTaokeReportSettleStatus(List<Integer> settleIds , String settleStatus) ;
}
