package com.doucome.corner.biz.dal.dcome.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.dal.dataobject.dcome.DcSceneDetailDO;
import com.doucome.corner.biz.dal.dcome.DcSceneDetailDAO;

public class IBatisDcSceneDetailDAO extends SqlMapClientDaoSupport implements DcSceneDetailDAO {

	@Override
	public Long insertSceneDetail(DcSceneDetailDO detail) {
		return (Long)getSqlMapClientTemplate().insert("DcSceneDetail.insertSceneDetail" , detail) ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> queryItemsBySceneWithPagination(long sceneId, int start,int size) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("sceneId", sceneId) ;
		condition.put("start", start - 1) ;
		condition.put("size", size) ;
		return getSqlMapClientTemplate().queryForList("DcSceneDetail.queryItemsBySceneWithPagination" , condition) ;
	}
	
	
	@Override
	public int countItemsBySceneWithPagination(long sceneId) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("sceneId", sceneId) ;
		return NumberUtils.parseInt((Integer)getSqlMapClientTemplate().queryForObject("DcSceneDetail.countItemsBySceneWithPagination" , condition)) ;
	}

	@Override
	public int deleteSceneDetail(long itemId, long sceneId) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("itemId", itemId) ;
		condition.put("sceneId", sceneId) ;
		return getSqlMapClientTemplate().delete("DcSceneDetail.deleteSceneDetail" , condition) ;
	}

}
