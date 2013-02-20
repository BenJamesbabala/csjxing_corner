package com.doucome.corner.biz.dal.dcome.ibatis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.dal.dataobject.dcome.DcGameRuleDO;
import com.doucome.corner.biz.dal.dcome.DcGameRuleDAO;

/**
 * 
 * @author ze2200
 *
 */
public class IbatisDcGameRuleDAO extends SqlMapClientDaoSupport implements DcGameRuleDAO {

	@Override
	public Long insertGameRule(DcGameRuleDO gameRule) {
		return (Long) this.getSqlMapClientTemplate().insert("DcGameRule.insertGameRule", gameRule);
	}
	
	@Override
	public DcGameRuleDO getGameRule(Long id) {
		return (DcGameRuleDO) this.getSqlMapClientTemplate().queryForObject("DcGameRule.getGameRule", id);
	}
	
	@Override
	public DcGameRuleDO getUserGameRule(Long userId, String type, String timeId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("type", type);
		params.put("timeId", timeId);
		return (DcGameRuleDO) this.getSqlMapClientTemplate().queryForObject("DcGameRule.getUserGameRule", params);
	}
	
	/**
	@Override
	public int updateGameDataByID(DcGameRuleDO gameRule) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", gameRule.getId());
		params.put("todayClickCount", gameRule.getTodayClickCount());
		params.put("todayPlayCount", gameRule.getTodayPlayCount());
		return getSqlMapClientTemplate().update("DcGameRule.updateGameDataByID", params);
	}
	
	@Override
	public int updateGameDataByUserIdAndGame(DcGameRuleDO gameRule) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", gameRule.getUserId());
		params.put("gameType", gameRule.getType());
		params.put("todayClickCount", gameRule.getTodayClickCount());
		params.put("todayPlayCount", gameRule.getTodayPlayCount());
		return getSqlMapClientTemplate().update("DcGameRule.updateGameDataByUserIdAndGame", params);
	}
	**/
	
	@Override
	public int incClickCount(Long id, int count) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("count", count);
		return getSqlMapClientTemplate().update("DcGameRule.incClickCount", params);
	}
	
	@Override
	public int incUserClickCount(Long userId, String gameType, String timeId, int count) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("gameType", gameType);
		params.put("timeId", timeId);
		params.put("count", count);
		return getSqlMapClientTemplate().update("DcGameRule.incUserClickCount", params);
	}
	
	@Override
	public int incPlayCount(Long id, int count) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("count", count);
		return getSqlMapClientTemplate().update("DcGameRule.incPlayCount", params);
	}
}
