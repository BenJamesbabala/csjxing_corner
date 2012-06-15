package com.doucome.corner.biz.dal.dao.ibatis;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.utils.NumberUtils;
import com.doucome.corner.biz.dal.DdzTaokeReportSettleDAO;
import com.doucome.corner.biz.dal.condition.DdzTaokeReportSettleSearchCondition;
import com.doucome.corner.biz.dal.dataobject.AlipayItemDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;
import com.ibatis.sqlmap.client.SqlMapExecutor;

public class IBatisDdzTaokeReportSettleDAO extends SqlMapClientDaoSupport implements DdzTaokeReportSettleDAO {

	@Override
	public Long insertSettleReport(DdzTaokeReportSettleDO settleDO) {
		return (Long) this.getSqlMapClientTemplate().insert("ddzReportSettle.insertSettleReport", settleDO);
	}
	
	@Override
	public Integer deleteSettleReport(Long id) {
		return (Integer) this.getSqlMapClientTemplate().delete("ddzReportSettle.deleteSettleReport", id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AlipayItemDO> getAliSettleItems(Pagination pagination) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("start", pagination.getStart() - 1) ;
		condition.put("size", pagination.getSize()) ;
		return getSqlMapClientTemplate().queryForList("ddzReportSettle.getAlipayItemWithPagination" , condition);
	}
	
	@Override
	public Integer countAlipayItem() {
		return (Integer) getSqlMapClientTemplate().queryForObject("ddzReportSettle.countAlipayItem");
	}

	@Override
	public int updateSettleStatus(final List<DdzTaokeReportSettleDO> settleDOs) {
		if (settleDOs == null || settleDOs.size() == 0) {
			return 0;
		}
		return this.getSqlMapClientTemplate().execute(new SqlMapClientCallback<Integer>() {

			@Override
			public Integer doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (DdzTaokeReportSettleDO tempSettleDO : settleDOs) {
					executor.update("ddzReportSettle.updateSettleStatus", tempSettleDO);
				}
				return executor.executeBatch();
			}
		});
	}

	@Override
	public int updateAlipayResult(final List<DdzTaokeReportSettleDO> settleDOs) {
		if (settleDOs == null || settleDOs.size() == 0) {
			return 0;
		}
		return this.getSqlMapClientTemplate().execute(new SqlMapClientCallback<Integer>() {

			@Override
			public Integer doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (DdzTaokeReportSettleDO settleDO : settleDOs) {
					executor.update("ddzReportSettle.updateAlipayResult", settleDO);
				}
				return executor.executeBatch();
			}
		});
	}

	@Override
	public int countSettlesWithPagination(DdzTaokeReportSettleSearchCondition searchCondition) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		
		condition.put("gmtSettledStart", searchCondition.getGmtSettledStart()) ;
		condition.put("gmtSettledEnd", searchCondition.getGmtSettledEnd()) ;
		condition.put("settleStatus", searchCondition.getSettleStatus()) ;
		condition.put("settleAlipay", searchCondition.getSettleAlipay()) ;
		condition.put("settleUid", searchCondition.getSettleUid()) ;
		condition.put("emailStatus", searchCondition.getEmailStatus()) ;
		condition.put("settleBatchno", searchCondition.getSettleBatchno()) ;
		condition.put("settleInDays", searchCondition.getSettleInDays());
		return NumberUtils.integerToInt((Integer)getSqlMapClientTemplate().queryForObject("ddzReportSettle.countSettlesWithPagination" , condition)) ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DdzTaokeReportSettleDO> selectSettlesWithPagination(DdzTaokeReportSettleSearchCondition searchCondition, int start, int size) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		
		condition.put("gmtSettledStart", searchCondition.getGmtSettledStart()) ;
		condition.put("gmtSettledEnd", searchCondition.getGmtSettledEnd()) ;
		condition.put("settleStatus", searchCondition.getSettleStatus()) ;
		condition.put("settleAlipay", searchCondition.getSettleAlipay()) ;
		condition.put("settleUid", searchCondition.getSettleUid()) ;
		condition.put("emailStatus", searchCondition.getEmailStatus()) ;
		condition.put("settleBatchno", searchCondition.getSettleBatchno()) ;
		condition.put("settleInDays", searchCondition.getSettleInDays());
		condition.put("start", start-1) ;
		condition.put("size", size) ;
		return getSqlMapClientTemplate().queryForList("ddzReportSettle.selectSettlesWithPagination" , condition) ;
	}

	@Override
	public int updateSettleStatus(List<Integer> settleIds , String settleStatus) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("settleIds", settleIds) ;
		condition.put("settleStatus", settleStatus) ;
		return getSqlMapClientTemplate().update("ddzReportSettle.updateSettleStatus_by_ids" , condition) ;
	}

    @Override
    public DdzTaokeReportSettleDO getById(Long id) {
        return (DdzTaokeReportSettleDO) getSqlMapClientTemplate().queryForObject("ddzReportSettle.selectSettlesById",
                                                                                 id);
    }

    @Override
    public int updateEmailStatus(List<Integer> settleIds, String emailStatus) {
        Map<String,Object> condition = new HashMap<String,Object>() ;
        condition.put("settleIds", settleIds) ;
        condition.put("emailStatus", emailStatus) ;
        return getSqlMapClientTemplate().update("ddzReportSettle.updateEmailStatus" , condition) ;
    }

	@Override
	public BigDecimal sumTotalSettleFee(String settleAlipay , String[] settleStatusList) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
        condition.put("settleAlipay", settleAlipay) ;
        condition.put("settleStatusList", settleStatusList) ;
		return (BigDecimal)getSqlMapClientTemplate().queryForObject("ddzReportSettle.sumTotalSettleFee" , condition) ;
		
	}
	
}
