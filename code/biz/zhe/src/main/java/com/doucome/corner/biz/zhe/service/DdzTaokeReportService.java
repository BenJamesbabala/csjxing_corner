package com.doucome.corner.biz.zhe.service;

import java.math.BigDecimal;
import java.util.List;

import com.doucome.corner.biz.common.enums.SumTaokeReportTypeEnums;
import com.doucome.corner.biz.core.enums.SettleStatusEnums;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.DdzTaokeReportSettleSearchCondition;
import com.doucome.corner.biz.dal.condition.TaokeReportSearchCondition;
import com.doucome.corner.biz.dal.dataobject.AlipayItemDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;
import com.doucome.corner.biz.zhe.enums.DdzRefundStatusEnums;


public interface DdzTaokeReportService {

	/**
	 * 插入报表
	 * @param report
	 * @return
	 */
	Long createReport(DdzTaokeReportDO report) ;
	
	/**
	 * 结算
	 * @param settle
	 * @return
	 */
	int settleByTradeId(DdzTaokeReportSettleDO settle) ;
	/**
	 * 根据id查询报表
	 * @param reportId
	 * @return
	 */
	DdzTaokeReportDO getReportById(Long reportId);
	
	/**
	 * 
	 * @param settleId
	 * @return
	 */
	List<DdzTaokeReportDO> getReportsBySettleId(Integer settleId) ;
	
	/**
	 * 查询报表
	 * @param searchCondition
	 * @param pagination
	 * @return
	 */
	QueryResult<DdzTaokeReportDO> getReportsWithPagination(TaokeReportSearchCondition searchCondition, Pagination pagination) ;

    /**
     * 查询报表
     * 
     * @param searchCondition
     * @param pagination
     * @return
     */
    List<DdzTaokeReportDO> getReports(TaokeReportSearchCondition searchCondition);

	/**
	 * 根据结算结果更新报表
	 * @param settleDOs
	 * @return
	 */
	int updateTaokeReportSettleStatusBySettleReport(List<DdzTaokeReportDO> reportDOs);
	/**
	 * 
	 * @param pagination
	 * @return
	 */
	List<AlipayItemDO> getUnMergedReportSettleInfo(Pagination pagination);
	/**
	 * 
	 * @param ids
	 * @return
	 */
	int updateTaokeReportSettleId(List<Long> reportIds, Long settleId) ;
	
	/**
	 * 统计结算费用
	 * @param searchCondition
	 * @return
	 */
	BigDecimal calcTaokeReportTotalSettleFee(TaokeReportSearchCondition searchCondition , SumTaokeReportTypeEnums sumType) ;
	
	/**
	 * 查询结算记录
	 * @param searchCondition
	 * @param pagination
	 * @return
	 */
	QueryResult<DdzTaokeReportSettleDO> getSettlesWithPagination(DdzTaokeReportSettleSearchCondition searchCondition, Pagination pagination) ;

    /**
     * @param searchCondition
     * @return
     */
    List<DdzTaokeReportSettleDO> getSettleReports(DdzTaokeReportSettleSearchCondition searchCondition);
    
    /**
	 * 维权退款
	 * @param reportId
	 * @param refundStats
	 * @return
	 */
    int updateRefundById(Long reportId , DdzRefundStatusEnums refundStats) ;
    
    /**
     * 
     * @param ids
     * @param refundStats
     * @return
     */
    int updateRefundByIds(List<Long> ids, DdzRefundStatusEnums refundStats) ;
    
    /**
     * 更新结算状态
     * @param reportId
     * @param status
     * @return
     */
    int updateSettleStatusByIds(List<Long> ids , SettleStatusEnums status) ;
    
    /**
     * 更新结算费用
     * @param id
     * @param settleFee
     * @return
     */
    int updateSettleFeeById(Long id, BigDecimal settleFee) ;
}
