package com.doucome.corner.biz.dal.dao.ibatis;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.dal.DdzTaokeReportSettleDAO;
import com.doucome.corner.biz.dal.condition.DdzTaokeReportSettleSearchCondition;
import com.doucome.corner.biz.dal.condition.DdzTaokeReportSettleUpdateCondition;
import com.doucome.corner.biz.dal.dataobject.AlipayItemDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleStatisticsDO;
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
	public List<AlipayItemDO> getAliSettleItems(int start , int size) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("start", start - 1) ;
		condition.put("size", size) ;
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
		
		Map<String,Object> condition = searchCondition.toMap() ;
		
		return NumberUtils.parseInt((Integer)getSqlMapClientTemplate().queryForObject("ddzReportSettle.countSettlesWithPagination" , condition)) ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DdzTaokeReportSettleDO> selectSettlesWithPagination(DdzTaokeReportSettleSearchCondition searchCondition, int start, int size) {
		Map<String,Object> condition = searchCondition.toMap() ;
		condition.put("start", start-1) ;
		condition.put("size", size) ;
		return getSqlMapClientTemplate().queryForList("ddzReportSettle.selectSettlesWithPagination" , condition) ;
	}
	
	@Override
	public DdzTaokeReportSettleStatisticsDO statisticsWithPagination(DdzTaokeReportSettleSearchCondition searchCondition) {
		Map<String,Object> condition = searchCondition.toMap() ;
		return (DdzTaokeReportSettleStatisticsDO)getSqlMapClientTemplate().queryForObject("ddzReportSettle.statisticsWithPagination" , condition) ;
	}


	@Override
	public int updateSettleStatus(List<Long> settleIds , String settleStatus) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("settleIds", settleIds) ;
		condition.put("settleStatus", settleStatus) ;
		return getSqlMapClientTemplate().update("ddzReportSettle.updateSettleStatus_by_ids" , condition) ;
	}
	
	@Override
	public int updateSettleStatus(DdzTaokeReportSettleUpdateCondition condition, String toSettleStatus , String settleBatchno) {
		Map<String,Object> map = condition.toMap() ;
		map.put("settleStatus", toSettleStatus) ;
		map.put("settleBatchno", settleBatchno) ;
		return getSqlMapClientTemplate().update("ddzReportSettle.updateSettleStatus_by_ids" , map) ;
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
	public int updateMobileStatus(List<Integer> settleIds, String mobileStatus) {
    	Map<String,Object> condition = new HashMap<String,Object>() ;
        condition.put("settleIds", settleIds) ;
        condition.put("mobileStatus", mobileStatus) ;
        return getSqlMapClientTemplate().update("ddzReportSettle.updateMobileStatus" , condition) ;
	}

	@Override
	public BigDecimal sumTotalSettleFee(String settleAlipay , String[] settleStatusList) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
        condition.put("settleAlipay", settleAlipay) ;
        condition.put("settleStatusList", settleStatusList) ;
		return (BigDecimal)getSqlMapClientTemplate().queryForObject("ddzReportSettle.sumTotalSettleFee" , condition) ;
		
	}

	@Override
	public int countTotalSettle(String settleAlipay, String[] settleStatus) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
        condition.put("settleAlipay", settleAlipay) ;
        condition.put("settleStatus", settleStatus) ;
		return NumberUtils.parseInt((Integer)getSqlMapClientTemplate().queryForObject("ddzReportSettle.countTotalSettle" , condition)) ;
	}

	@Override
	public int updateMemoById(Integer settleId, String memo) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
        condition.put("id", settleId) ;
        condition.put("memo", memo) ;
		return getSqlMapClientTemplate().update("ddzReportSettle.updateMemoById" , condition) ;
	}

}
