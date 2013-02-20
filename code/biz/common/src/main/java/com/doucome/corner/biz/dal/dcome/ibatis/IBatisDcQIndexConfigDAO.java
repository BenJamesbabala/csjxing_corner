package com.doucome.corner.biz.dal.dcome.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.dal.dataobject.dcome.DcQIndexConfigDO;
import com.doucome.corner.biz.dal.dcome.DcQIndexConfigDAO;

/**
 * 
 * @author langben 2012-7-26
 *
 */
public class IBatisDcQIndexConfigDAO extends SqlMapClientDaoSupport implements DcQIndexConfigDAO {

	@Override
	public Long insertConfig(DcQIndexConfigDO config) {
		return (Long)getSqlMapClientTemplate().insert("DcQIndexConfig.insertConfig" , config) ;
	}

	@Override
	public int updateSceneIdBySysAndStatus(Long sceneId , Long systemId , String pubStatus) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("systemId", systemId) ;
		condition.put("pubStatus", pubStatus) ;
		condition.put("sceneId", sceneId) ;
		return getSqlMapClientTemplate().update("DcQIndexConfig.updateSceneIdBySysAndStatus" ,condition) ;
	}

	@Override
	public DcQIndexConfigDO queryConfigBySysAndStatus(Long systemId,String pubStatus) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("systemId", systemId) ;
		condition.put("pubStatus", pubStatus) ;
		return (DcQIndexConfigDO)getSqlMapClientTemplate().queryForObject("DcQIndexConfig.queryConfigBySysAndStatus", condition) ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DcQIndexConfigDO> queryConfigsByStatus(String value) {
		return getSqlMapClientTemplate().queryForList("DcQIndexConfig.queryConfigByStatus" , value) ;
	}

	
}
