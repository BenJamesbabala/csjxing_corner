package com.doucome.corner.biz.dal;

import java.math.BigDecimal;
import java.util.List;

import com.doucome.corner.biz.common.enums.SumTaokeReportTypeEnums;
import com.doucome.corner.biz.dal.condition.DdzTaokeReportSettleUpdateCondition;
import com.doucome.corner.biz.dal.condition.TaokeReportSearchCondition;
import com.doucome.corner.biz.dal.dataobject.AlipayItemDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;

/**
 * 报表
 * @author langben 2012-3-7
 *
 */
public interface DdzTaokeReportDAO {

	/**
	 * 插入报表
	 * @param report
	 * @return
	 */
	Long insertReport(DdzTaokeReportDO report) ;
	
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
	List<AlipayItemDO> getUnMergedReportSettleInfo(int start , int size);
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
	 * 更新结算状态
	 * @param reportIds
	 * @param settleStatus
	 * @return
	 */
	int updateTaokeReportSettleStatus(List<Long> settleIds , String toSettleStatus) ;
	
	/**
	 * 更新结算状态
	 * @param reportIds
	 * @param settleStatus
	 * @return
	 */
	int updateTaokeReportSettleStatus(DdzTaokeReportSettleUpdateCondition condition , String toSettleStatus) ;
	
	/**
	 * 维权退款
	 * @param reportId
	 * @param refundStats
	 * @return
	 */
	int updateRefundById(Long reportId , String refundStats) ;

	/**
	 * 维权退款
	 * @param ids
	 * @param refundStats
	 * @return
	 */
	int updateRefundByIds(List<Long> ids, String refundStats) ;
	
	/**
	 * 计算结算费用
	 * @param searchCondition
	 * @return
	 */
	BigDecimal sumTaokeReportTotalSettleFee(TaokeReportSearchCondition searchCondition , SumTaokeReportTypeEnums sumType);
	
	/**
	 * 更新itemId 和 userId
	 * @param reportId
	 * @param dcUserId
	 * @param dcItemId
	 * @return
	 */
	int updateDcUserAndItemById(Long reportId , Long dcUserId , Long dcItemId) ;
	
	/**
	 * 更新结算状态
	 * @param reportId
	 * @param settleStatus
	 * @return
	 */
	int updateSettleStatusByIds(List<Long> ids , String settleStatus) ;
	
	/**
	 * 更新结算费用
	 * @param id
	 * @param settleFee
	 * @return
	 */
	int updateSettleFeeById(Long id , BigDecimal settleFee);
	/**
	 * 统计豆蔻用户佣金
	 * @return
	 */
	List<DdzTaokeReportDO> countDcUserCommission();
}
