package com.doucome.corner.biz.dal.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.dal.BannerConfigDAO;
import com.doucome.corner.biz.dal.condition.BannerConfigSearchCondition;
import com.doucome.corner.biz.dal.dataobject.BannerConfigDO;

public class IBatisBannerConfigDAO extends SqlMapClientDaoSupport implements BannerConfigDAO {

	@Override
	public BannerConfigDO queryConfigsByBannerId(String bannerId) {
		return (BannerConfigDO)getSqlMapClientTemplate().queryForObject("bannerConfig.queryConfigsByBannerId" , bannerId) ;
	}

	@Override
	public int updateConfigByBannerId(BannerConfigDO config) {
		return getSqlMapClientTemplate().update("bannerConfig.updateConfigByBannerId" , config ) ;
	}

	@Override
	public int countConfigsWithPagination(BannerConfigSearchCondition condition) {
		Map<String,Object> map = condition.toMap() ;
		return NumberUtils.parseInt((Integer)getSqlMapClientTemplate().queryForObject("bannerConfig.countConfigsWithPagination" , map)) ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BannerConfigDO> queryConfigsWithPagination(BannerConfigSearchCondition condition, int start, int size) {
		Map<String,Object> map = condition.toMap() ;
		map.put("start", start-1) ;
		map.put("size", size) ;
		return getSqlMapClientTemplate().queryForList("bannerConfig.queryConfigsWithPagination" , map) ;
	}

	@Override
	public int updateConfigStatusByBannerId(String bannerId, String status) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("bannerId", bannerId) ;
		map.put("status", status) ;
		return getSqlMapClientTemplate().update("bannerConfig.updateConfigStatusByBannerId" , map ) ;
	}

	@Override
	public int updateBindEventById(String bannerId, String bindEvent, String bindEventFunction) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("bannerId", bannerId) ;
		map.put("bindEvent", bindEvent) ;
		map.put("bindEventFunction", bindEventFunction) ;
		return getSqlMapClientTemplate().update("bannerConfig.updateBindEventById" , map) ;
	}

}
