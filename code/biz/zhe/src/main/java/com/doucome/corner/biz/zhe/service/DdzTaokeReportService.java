package com.doucome.corner.biz.zhe.service;

import java.util.List;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.taobao.model.TaokeReportSearchCondition;
import com.doucome.corner.biz.dal.condition.DdzTaokeReportSettleSearchCondition;
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
	int updateTaokeReportSettleId(List<Long> reportIds, Long settleId) throws Exception ;
	
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
    
}
