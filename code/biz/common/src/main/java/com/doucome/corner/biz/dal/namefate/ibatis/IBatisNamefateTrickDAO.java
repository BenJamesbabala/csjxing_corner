package com.doucome.corner.biz.dal.namefate.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.dal.namefate.NamefateTrickDAO;
import com.doucome.corner.biz.dal.namefate.condition.NamefateTrickSearchCondition;
import com.doucome.corner.biz.dal.namefate.dataobject.NamefateTrickDO;

public class IBatisNamefateTrickDAO extends SqlMapClientDaoSupport implements NamefateTrickDAO {

	@Override
	public long insertTrick(NamefateTrickDO trick) {
		return NumberUtils.parseLong((Long)getSqlMapClientTemplate().insert("NamefateTrick.insertTrick" , trick)) ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NamefateTrickDO> queryTricksWithPagination(NamefateTrickSearchCondition condition, int start, int size) {
		Map<String,Object> map = condition.toMap() ;
		map.put("start", start - 1) ;
		map.put("size", size) ;
		return getSqlMapClientTemplate().queryForList("NamefateTrick.queryTricksWithPagination" , map) ;
	}

	@Override
	public int countTricksWithPagination(NamefateTrickSearchCondition condition) {
		Map<String,Object> map = condition.toMap() ;
		return NumberUtils.parseInt((Integer)getSqlMapClientTemplate().queryForObject("NamefateTrick.countTricksWithPagination" , map)) ;
	}

	@Override
	public NamefateTrickDO queryTrickById(Long trickId) {
		return (NamefateTrickDO)getSqlMapClientTemplate().queryForObject("NamefateTrick.queryTrickById" , trickId) ;
	}

}
