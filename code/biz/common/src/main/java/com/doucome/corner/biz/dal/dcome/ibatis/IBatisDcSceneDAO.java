package com.doucome.corner.biz.dal.dcome.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.dal.condition.dcome.DcSceneSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcSceneDO;
import com.doucome.corner.biz.dal.dcome.DcSceneDAO;

public class IBatisDcSceneDAO extends SqlMapClientDaoSupport implements DcSceneDAO {

	@Override
	public Long insertScene(DcSceneDO scene) {
		return (Long)getSqlMapClientTemplate().insert("DcScene.insertScene" , scene) ;
	}
	
	@Override
	public DcSceneDO querySceneById(long sceneId) {
		return (DcSceneDO)getSqlMapClientTemplate().queryForObject("DcScene.querySceneById" , sceneId) ;
	}

	@Override
	public int updateSceneById(DcSceneDO scene) {
		return getSqlMapClientTemplate().update("DcScene.updateSceneById" , scene) ;
	}

	@Override
	public int updateSceneActiveById(long sceneId,String active) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("id", sceneId);
		condition.put("active", active) ;
		return getSqlMapClientTemplate().update("DcScene.updateSceneActiveById" , condition) ;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DcSceneDO> queryScenesWithPagination(DcSceneSearchCondition searchCondition, int start, int size) {
		Map<String,Object> condition = searchCondition.toMap() ;
		condition.put("start", start - 1);
		condition.put("size", size) ;
		return getSqlMapClientTemplate().queryForList("DcScene.queryScenesWithPagination",condition) ;
	}

	@Override
	public int countScenesWithPagination(DcSceneSearchCondition searchCondition) {
		Map<String,Object> condition = searchCondition.toMap() ;
		return NumberUtils.parseInt((Integer)getSqlMapClientTemplate().queryForObject("DcScene.countScenesWithPagination" , condition));
	}

}
