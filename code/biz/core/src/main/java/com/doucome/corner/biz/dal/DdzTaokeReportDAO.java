package com.doucome.corner.biz.dal;

import java.util.List;

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
	 * 根据SettleId查询报表
	 * @param settleId
	 * @return
	 */
	List<DdzTaokeReportDO> selectReportsBySettleId(Integer settleId) ;
	
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
