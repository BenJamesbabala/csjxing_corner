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
	 * ���뱨��
	 * @param report
	 * @return
	 */
	Long createReport(DdzTaokeReportDO report) ;
	
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
	 * 
	 * @param settleId
	 * @return
	 */
	List<DdzTaokeReportDO> getReportsBySettleId(Integer settleId) ;
	
	/**
	 * ��ѯ����
	 * @param searchCondition
	 * @param pagination
	 * @return
	 */
	QueryResult<DdzTaokeReportDO> getReportsWithPagination(TaokeReportSearchCondition searchCondition, Pagination pagination) ;

    /**
     * ��ѯ����
     * 
     * @param searchCondition
     * @param pagination
     * @return
     */
    List<DdzTaokeReportDO> getReports(TaokeReportSearchCondition searchCondition);

	/**
	 * ���ݽ��������±���
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
	 * ͳ�ƽ������
	 * @param searchCondition
	 * @return
	 */
	BigDecimal calcTaokeReportTotalSettleFee(TaokeReportSearchCondition searchCondition , SumTaokeReportTypeEnums sumType) ;
	
	/**
	 * ��ѯ�����¼
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
	 * άȨ�˿�
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
     * ���½���״̬
     * @param reportId
     * @param status
     * @return
     */
    int updateSettleStatusByIds(List<Long> ids , SettleStatusEnums status) ;
    
    /**
     * ���½������
     * @param id
     * @param settleFee
     * @return
     */
    int updateSettleFeeById(Long id, BigDecimal settleFee) ;
}
