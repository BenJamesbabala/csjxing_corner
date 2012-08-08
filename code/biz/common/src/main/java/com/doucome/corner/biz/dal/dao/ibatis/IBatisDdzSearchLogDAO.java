package com.doucome.corner.biz.dal.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.dal.DdzSearchLogDAO;
import com.doucome.corner.biz.dal.condition.DdzSearchLogCondition;
import com.doucome.corner.biz.dal.dataobject.DdzSearchLogDO;

public class IBatisDdzSearchLogDAO extends SqlMapClientDaoSupport implements DdzSearchLogDAO{

	@Override
	public long insertLog(DdzSearchLogDO searchLog) {
		return (Long)getSqlMapClientTemplate().insert("ddzSearchLog.insertLog" ,searchLog) ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DdzSearchLogDO> querySearchLogWithPagination(DdzSearchLogCondition searchCondition, int start, int size) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("alipayId", searchCondition.getAlipayId()) ;
		condition.put("searchBrief", searchCondition.getSearchBrief()) ;
		condition.put("searchWay", searchCondition.getSearchWay());
		condition.put("gmtCreateStart", searchCondition.getGmtCreateStart()) ;
		condition.put("gmtCreateEnd", searchCondition.getGmtCreateEnd()) ;
		condition.put("start", start) ;
		condition.put("size", size) ;
		return getSqlMapClientTemplate().queryForList("ddzSearchLog.querySearchLog", condition) ;
	}

	@Override
	public int countSearchLogWithPagination(DdzSearchLogCondition searchCondition) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("alipayId", searchCondition.getAlipayId()) ;
		condition.put("searchBrief", searchCondition.getSearchBrief()) ;
		condition.put("searchWay", searchCondition.getSearchWay());
		condition.put("gmtCreateStart", searchCondition.getGmtCreateStart()) ;
		condition.put("gmtCreateEnd", searchCondition.getGmtCreateEnd()) ;
		
		return NumberUtils.integerToInt((Integer)getSqlMapClientTemplate().queryForObject("ddzSearchLog.countSearchLog", condition)) ;
	}

}
