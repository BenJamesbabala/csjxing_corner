package com.doucome.corner.biz.dal.dao.ibatis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.dal.DdzRecommendTaskDAO;
import com.doucome.corner.biz.dal.dataobject.DdzRecommendTaskDO;

public class IBatisDdzRecommendTaskDAO extends SqlMapClientDaoSupport implements DdzRecommendTaskDAO {

	@Override
	public DdzRecommendTaskDO queryRecommendTask(String recommendType) {
		return (DdzRecommendTaskDO)getSqlMapClientTemplate().queryForObject("ddzRecommendTask.queryRecommendTask" , recommendType) ;
	}

	@Override
	public void updateRecommendTaskBatch(String recommendType,String lastTaskBatch) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("recommendType", recommendType) ;
		condition.put("lastTaskBatch", lastTaskBatch) ;
		getSqlMapClientTemplate().update("ddzRecommendTask.updateRecommendTaskBatch" , condition) ;
	}

}
