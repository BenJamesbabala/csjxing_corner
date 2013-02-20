package com.doucome.corner.biz.dal.dao.ibatis;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.common.enums.SumTaokeReportTypeEnums;
import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.dal.DdzTaokeReportDAO;
import com.doucome.corner.biz.dal.condition.DdzTaokeReportSettleUpdateCondition;
import com.doucome.corner.biz.dal.condition.TaokeReportSearchCondition;
import com.doucome.corner.biz.dal.dataobject.AlipayItemDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;
import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * IBatisDdzTaokeReportDAO
 * @author langben 2012-3-7
 *
 */
public class IBatisDdzTaokeReportDAO extends SqlMapClientDaoSupport implements DdzTaokeReportDAO {

	@Override
	public Long insertReport(DdzTaokeReportDO report) {
		return NumberUtils.parseLong((Long)getSqlMapClientTemplate().insert("ddzReport.insertReport", report));
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
		Map<String,Object> condition = searchCondition.toMap();
		
		condition.put("start", start-1) ;
		condition.put("size", size) ;
		return getSqlMapClientTemplate().queryForList("ddzReport.selectReportsWithPagination" , condition) ;
	}

	@Override
	public int countReportsWithPagination(TaokeReportSearchCondition searchCondition) {
		Map<String,Object> condition = searchCondition.toMap();	
		
		return NumberUtils.parseInt((Integer)getSqlMapClientTemplate().queryForObject("ddzReport.countReportsWithPagination" , condition)) ;
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
	public List<AlipayItemDO> getUnMergedReportSettleInfo(int start , int size) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", start - 1);
		params.put("size", size);
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
	public int updateTaokeReportSettleStatus(List<Long> settleIds,String toSettleStatus) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("settleIds", settleIds) ;
		condition.put("settleStatus", toSettleStatus) ;
		return getSqlMapClientTemplate().update("ddzReport.updateTaokeSettleStatusBySettleIds" , condition) ;
	}


	@Override
	public int updateTaokeReportSettleStatus(DdzTaokeReportSettleUpdateCondition condition, String toSettleStatus) {
		Map<String,Object> map = condition.toMap() ;
		map.put("settleStatus", toSettleStatus) ;
		return getSqlMapClientTemplate().update("ddzReport.updateTaokeSettleStatusBySettleIds" , map) ;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DdzTaokeReportDO> selectReportsBySettleId(Integer settleId) {
		return getSqlMapClientTemplate().queryForList("ddzReport.selectReportsBySettleId" , settleId) ;
	}

	@Override
	public int updateRefundById(Long reportId, String refundStatus) {
		
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("id", reportId) ;
		condition.put("refundStatus", refundStatus) ;
		return getSqlMapClientTemplate().update("ddzReport.updateRefundById" , condition) ;
	}
	
	@Override
	public int updateRefundByIds(List<Long> ids, String refundStatus) {
		
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("ids", ids) ;
		condition.put("refundStatus", refundStatus) ;
		return getSqlMapClientTemplate().update("ddzReport.updateRefundById" , condition) ;
	}

	@Override
	public BigDecimal sumTaokeReportTotalSettleFee(TaokeReportSearchCondition searchCondition ,SumTaokeReportTypeEnums sumType) {
		Map<String,Object> map = searchCondition.toMap() ;
		map.put("sumType", sumType.toString()) ;
		BigDecimal amount = (BigDecimal)getSqlMapClientTemplate().queryForObject("ddzReport.sumTaokeReportTotalSettleFee",map) ;
		return amount ;
	}

	@Override
	public int updateDcUserAndItemById(Long reportId, Long dcUserId,
			Long dcItemId) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("id", reportId) ;
		map.put("dcUserId", dcUserId) ;
		map.put("dcItemId", dcItemId) ;
		return getSqlMapClientTemplate().update("ddzReport.updateDcUserAndItemById" , map) ;
	}

	@Override
	public int updateSettleStatusByIds(List<Long> ids, String settleStatus) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("ids", ids) ;
		map.put("settleStatus", settleStatus) ;
		return getSqlMapClientTemplate().update("ddzReport.updateSettleStatusByIds" , map ) ;
	}

	@Override
	public int updateSettleFeeById(Long id, BigDecimal settleFee) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("id", id) ;
		map.put("settleFee", settleFee) ;
		return getSqlMapClientTemplate().update("ddzReport.updateSettleFeeById" , map) ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DdzTaokeReportDO> countDcUserCommission() {
		return getSqlMapClientTemplate().queryForList("ddzReport.countDcUserCommission");
	}
	
}
