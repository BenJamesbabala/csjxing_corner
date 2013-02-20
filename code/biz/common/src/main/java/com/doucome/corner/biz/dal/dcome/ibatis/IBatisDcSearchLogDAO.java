package com.doucome.corner.biz.dal.dcome.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.dal.condition.dcome.DcSearchLogCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcSearchLogDO;
import com.doucome.corner.biz.dal.dcome.DcSearchLogDAO;

public class IBatisDcSearchLogDAO extends SqlMapClientDaoSupport implements DcSearchLogDAO {
	
	@Override
	public Long insertSearchLog(DcSearchLogDO searchLog) {
		return (Long) getSqlMapClientTemplate().insert("DcSearchLog.insertSearchLog", searchLog);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DcSearchLogDO> querySearchLogsPage(DcSearchLogCondition condition, int start, int size) {
		Map<String, Object> params = condition.toMap();
		params.put("start", start);
		params.put("size", size);
		return (List<DcSearchLogDO>) getSqlMapClientTemplate().queryForList("DcSearchLog.querySearchLogsPage", params);
	}

	@Override
	public Integer countSearchLogs(DcSearchLogCondition condition) {
		return (Integer) getSqlMapClientTemplate().queryForObject("DcSearchLog.countSearchLogs", condition.toMap());
	}
	
	@Override
	public Integer updateSearchLogStatus(DcSearchLogCondition condition, String status) {
		Map<String, Object> params = condition.toMap();
		params.put("status", status);
		return (Integer) getSqlMapClientTemplate().update("DcSearchLog.updateSearchLogStatus", params);
	}
}
