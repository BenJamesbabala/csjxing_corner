package com.doucome.corner.biz.dal.dcome.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.dal.condition.dcome.DcUserIntegralRecordSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserIntegralRecordDO;
import com.doucome.corner.biz.dal.dcome.DcUserIntegralRecordDAO;

public class IBatisDcUserIntegralRecordDAO extends SqlMapClientDaoSupport implements DcUserIntegralRecordDAO {

	@Override
	public long insertRecord(DcUserIntegralRecordDO record) {
		return NumberUtils.parseLong((Long)getSqlMapClientTemplate().insert("DcUserIntegralRecord.insertRecord" , record)) ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DcUserIntegralRecordDO> queryRecordsWithPagination(DcUserIntegralRecordSearchCondition condition, int start, int size) {
		Map<String,Object> map = condition.toMap() ;
		map.put("start", start - 1) ;
		map.put("size", size) ;
		return getSqlMapClientTemplate().queryForList("DcUserIntegralRecord.queryRecordsWithPagination" , map) ;
	}

	@Override
	public int countRecordsWithPagination(DcUserIntegralRecordSearchCondition condition) {
		Map<String,Object> map = condition.toMap() ;
		return NumberUtils.parseInt((Integer)getSqlMapClientTemplate().queryForObject("DcUserIntegralRecord.countRecordsWithPagination" , map)) ;
	}

	@Override
	public int updateStatusBySettleIdList(String status, Long[] settleIdList) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("status", status) ;
		map.put("settleIdList", settleIdList) ;
		return getSqlMapClientTemplate().update("DcUserIntegralRecord.updateStatusBySettleIdList" , map);
	}

	@Override
	public int updateStatusAndUserMemoByExApproveId(long exApproveId, String status,
			String userMemo) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("exchangeApproveId", exApproveId) ;
		map.put("status", status) ;
		map.put("userMemo", userMemo) ;
		return getSqlMapClientTemplate().update("DcUserIntegralRecord.updateStatusAndUserMemoByExApproveId" , map);
	}

	@Override
	public DcUserIntegralRecordDO queryRecordByReportId(long reportId) {
		return (DcUserIntegralRecordDO)getSqlMapClientTemplate().queryForObject("DcUserIntegralRecord.queryRecordByReportId" , reportId) ;
	}

}
