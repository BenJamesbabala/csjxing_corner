package com.doucome.corner.biz.dal.horoscope.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.dal.condition.dcome.hs.HsTopicCondition;
import com.doucome.corner.biz.dal.horoscope.HsTopicDAO;
import com.doucome.corner.biz.dal.horoscope.dataobject.HsTopicDO;

/**
 * 
 * @author ze2200
 *
 */
public class IBatisHsTopicDAO extends SqlMapClientDaoSupport implements HsTopicDAO {

	@Override
	public Long insertHsTopic(HsTopicDO topicDO) {
		return (Long) getSqlMapClientTemplate().insert("HsTopic.insertHsTopic", topicDO);
	}

	@Override
	public HsTopicDO queryHsTopic(Long topicId) {
		return (HsTopicDO) getSqlMapClientTemplate().queryForObject("HsTopic.queryHsTopic", topicId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HsTopicDO> queryHsTopics(HsTopicCondition condition, int start, int size) {
		Map<String, Object> params = condition.toMap();
		params.put("start", start);
		params.put("size", size);
		return (List<HsTopicDO>) getSqlMapClientTemplate().queryForList("HsTopic.queryHsTopics", params);
	}

	@Override
	public Integer countHsTopics(HsTopicCondition condition) {
		return (Integer) getSqlMapClientTemplate().queryForObject("HsTopic.countHsTopics", condition.toMap());
	}
	
	@Override
	public Integer updateHsTopic(HsTopicDO topicDO) {
		return (Integer) getSqlMapClientTemplate().update("HsTopic.updateHsTopic", topicDO);
	}
	
	@Override
	public Integer deleteHsTopic(Long topicId) {
		return (Integer) getSqlMapClientTemplate().delete("HsTopic.deleteHsTopic", topicId);
	}
}
