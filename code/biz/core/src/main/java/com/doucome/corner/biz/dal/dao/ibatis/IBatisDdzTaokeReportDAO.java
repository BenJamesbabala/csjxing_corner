package com.doucome.corner.biz.dal.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.taobao.model.TaokeReportSearchCondition;
import com.doucome.corner.biz.core.utils.NumberUtils;
import com.doucome.corner.biz.dal.DdzTaokeReportDAO;
import com.doucome.corner.biz.dal.dataobject.AlipayItemDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;

/**
 * IBatisDdzTaokeReportDAO
 * @author shenjia.caosj 2012-3-7
 *
 */
public class IBatisDdzTaokeReportDAO extends SqlMapClientDaoSupport implements DdzTaokeReportDAO{

	@Override
	public int insertReport(DdzTaokeReportDO report) {
		return (Integer)getSqlMapClientTemplate().insert("ddzReport.insertReport", report);
	}

	@Override
	public int settleByTradeId(DdzTaokeReportSettleDO settle) {
		return getSqlMapClientTemplate().update("ddzReport.settleByTradeId",settle) ;
	}
	
	@Override
	public DdzTaokeReportDO getReportById(Long reportId) {
		return (DdzTaokeReportDO) getSqlMapClientTemplate().queryForObject("ddzReport.getReportById", reportId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DdzTaokeReportDO> selectReportsWithPagination(TaokeReportSearchCondition searchCondition, int start, int size) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		
		condition.put("gmtPaidStart", searchCondition.getGmtPaidStart()) ;
		condition.put("gmtPaidEnd", searchCondition.getGmtPaidEnd()) ;
		condition.put("gmtSettledStart", searchCondition.getGmtSettledStart()) ;
		condition.put("gmtSettledEnd", searchCondition.getGmtSettledEnd()) ;
		condition.put("settleStatus", searchCondition.getSettleStatus()) ;
		condition.put("settleAlipay", searchCondition.getSettleAlipay()) ;
		condition.put("settleTaobaoNick", searchCondition.getSettleTaobaoNick()) ;
		condition.put("settleUid", searchCondition.getSettleUid()) ;
		condition.put("payBatchno", searchCondition.getPayBatchno()) ;
		condition.put("start", start-1) ;
		condition.put("size", size) ;
		return getSqlMapClientTemplate().queryForList("ddzReport.selectReportsWithPagination" , condition) ;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AlipayItemDO> getAlipayItemWithPagination(Pagination pagination) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("start", pagination.getStart() - 1) ;
		condition.put("size", pagination.getSize()) ;
		return getSqlMapClientTemplate().queryForList("ddzReport.getAlipayItemWithPagination" , condition);
	}

	@Override
	public int countReportsWithPagination(TaokeReportSearchCondition searchCondition) {
		Map<String,Object> condition = new HashMap<String,Object>() ;	
		
		condition.put("gmtPaidStart", searchCondition.getGmtPaidStart()) ;
		condition.put("gmtPaidEnd", searchCondition.getGmtPaidEnd()) ;
		condition.put("gmtSettledStart", searchCondition.getGmtSettledStart()) ;
		condition.put("gmtSettledEnd", searchCondition.getGmtSettledEnd()) ;
		condition.put("settleStatus", searchCondition.getSettleStatus()) ;
		condition.put("settleAlipay", searchCondition.getSettleAlipay()) ;
		condition.put("settleTaobaoNick", searchCondition.getSettleTaobaoNick()) ;
		condition.put("settleUid", searchCondition.getSettleUid()) ;
		return NumberUtils.integerToInt((Integer)getSqlMapClientTemplate().queryForObject("ddzReport.countReportsWithPagination" , condition)) ;
	}
	
	@Override
	public Integer countAlipayItem() {
		return (Integer) getSqlMapClientTemplate().queryForObject("ddzReport.countAlipayItem");
	}
	
	@Override
	public int updateTaokeReportSettleStatus(List<String> reportIds, String settleStatus, String internalBatchNO) {
		if (reportIds == null || reportIds.size() == 0 || settleStatus == null) {
			return 0;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("reportIds", reportIds);
		params.put("settleStatus", settleStatus);
		params.put("internalBatchNO", internalBatchNO);
		return this.getSqlMapClientTemplate().update("ddzReport.updateTaokeReportSettleStatus", params);
	}
	
	@Override
	public int updateTaokeReportAlipayResult(List<String> reportIds, String status, String alipayBatchNO) {
		if (reportIds == null || reportIds.size() == 0 || status == null) {
			return -1;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("reportIds", reportIds);
		params.put("alipayStatus", status);
		params.put("alipayBatchNO", alipayBatchNO);
		return this.getSqlMapClientTemplate().update("ddzReport.updateTaokeReportAlipayResult", params);
	}
}
