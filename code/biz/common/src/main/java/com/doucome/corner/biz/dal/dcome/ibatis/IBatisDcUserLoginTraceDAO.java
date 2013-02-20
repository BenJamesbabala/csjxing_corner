package com.doucome.corner.biz.dal.dcome.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.dal.condition.dcome.DcUserLoginTraceCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserLoginTraceDO;
import com.doucome.corner.biz.dal.dcome.DcUserLoginTraceDAO;

public class IBatisDcUserLoginTraceDAO extends SqlMapClientDaoSupport implements DcUserLoginTraceDAO {

	@Override
	public long insertLoginTrace(DcUserLoginTraceDO loginTrace) {
		return NumberUtils.parseLong((Long)getSqlMapClientTemplate().insert("DcUserLoginTrace.insertLoginTrace" , loginTrace)) ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DcUserLoginTraceDO> queryTraceWithPagination(DcUserLoginTraceCondition searchCondition, int start, int size) {
		Map<String,Object> condition = searchCondition.toMap() ;
		condition.put("start", start - 1) ;
		condition.put("size", size) ;
		return getSqlMapClientTemplate().queryForList("DcUserLoginTrace.queryTraceWithPagination" , condition) ;
	}

	@Override
	public int countTraceWithPagination(DcUserLoginTraceCondition searchCondition) {
		Map<String,Object> condition = searchCondition.toMap() ;
		return NumberUtils.parseInt((Integer)getSqlMapClientTemplate().queryForObject("DcUserLoginTrace.countTraceWithPagination" , condition)) ;
	}

}
