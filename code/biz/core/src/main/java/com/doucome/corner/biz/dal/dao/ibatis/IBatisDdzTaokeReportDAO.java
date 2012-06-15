package com.doucome.corner.biz.dal.dao.ibatis;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.taobao.model.TaokeReportSearchCondition;
import com.doucome.corner.biz.core.utils.NumberUtils;
import com.doucome.corner.biz.dal.DdzTaokeReportDAO;
import com.doucome.corner.biz.dal.dataobject.AlipayItemDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;
import com.ibatis.sqlmap.client.SqlMapExecutor;

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
		condition.put("settleId", searchCondition.getSettleId());
		condition.put("settleStatusList", searchCondition.getSettleStatusList()) ;
		
		//condition.put("payBatchno", searchCondition.getPayBatchno()) ;
		condition.put("start", start-1) ;
		condition.put("size", size) ;
		return getSqlMapClientTemplate().queryForList("ddzReport.selectReportsWithPagination" , condition) ;
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
		condition.put("settleId", searchCondition.getSettleId());
		condition.put("settleStatusList", searchCondition.getSettleStatusList()) ;
		return NumberUtils.integerToInt((Integer)getSqlMapClientTemplate().queryForObject("ddzReport.countReportsWithPagination" , condition)) ;
	}
	
	
	@Override
	public int updateSettleStatusBySettleReport(final List<DdzTaokeReportDO> reportDOs) {
		if (reportDOs == null || reportDOs.size() == 0) {
			return 0;
		}
		return this.getSqlMapClientTemplate().execute(new SqlMapClientCallback<Integer>() {

			@Override
			public Integer doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (DdzTaokeReportDO reportDO : reportDOs) {
					executor.update("ddzReport.updateSettleStatusBySettleReport", reportDO);
				}
				return executor.executeBatch();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AlipayItemDO> getUnMergedReportSettleInfo(Pagination pagination) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", pagination.getStart() - 1);
		params.put("size", pagination.getSize());
		return this.getSqlMapClientTemplate().queryForList("ddzReport.getUnMergedReportSettleInfo", params);
	}
	
	@Override
	public int updateTaokeReportSettleId(List<Long> reportIds, Long settleId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("reportIds", reportIds);
		params.put("settleId", settleId);
		return this.getSqlMapClientTemplate().update("ddzReport.updateTaokeReportSettleId", params);
	}

	@Override
	public int updateTaokeReportSettleStatus(List<Integer> settleIds,String settleStatus) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("settleIds", settleIds) ;
		condition.put("settleStatus", settleStatus) ;
		return getSqlMapClientTemplate().update("ddzReport.updateTaokeSettleStatusBySettleIds" , condition) ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DdzTaokeReportDO> selectReportsBySettleId(Integer settleId) {
		return getSqlMapClientTemplate().queryForList("ddzReport.selectReportsBySettleId" , settleId) ;
	}
	
	
}
