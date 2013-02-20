package com.doucome.corner.biz.dal.horoscope.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.dal.condition.dcome.hs.HsWeekFateCondition;
import com.doucome.corner.biz.dal.horoscope.HsWeekFateDAO;
import com.doucome.corner.biz.dal.horoscope.dataobject.HsWeekFateDO;

/**
 * ÐÇ×ù±´±´ÓÃ»§dao
 * @author ze2200
 *
 */
public class IBatisHsWeekFateDAO extends SqlMapClientDaoSupport implements HsWeekFateDAO {

	@Override
	public Long insertHsWeekFate(HsWeekFateDO hsFate) {
		return (Long) getSqlMapClientTemplate().insert("HsWeekFate.insertHsWeekFate", hsFate);
	}

	@Override
	public HsWeekFateDO queryHsWeekFate(Long id) {
		return (HsWeekFateDO) getSqlMapClientTemplate().queryForObject("HsWeekFate.queryHsWeekFate", id);
	}

	@Override
	public HsWeekFateDO queryHsWeekFate(Integer hsId, Date weekStart, Date weekEnd) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("hsId", hsId);
		params.put("weekStart", weekStart);
		params.put("weekEnd", weekEnd);
		return (HsWeekFateDO) getSqlMapClientTemplate().queryForObject("HsWeekFate.queryHsWeekFateByDate", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HsWeekFateDO> queryHsWeekFates(HsWeekFateCondition condition, int start, int size) {
		Map<String, Object> params = condition.toMap();
		params.put("start", start);
		params.put("size", size);
		return (List<HsWeekFateDO>) getSqlMapClientTemplate().queryForList("HsWeekFate.queryHsWeekFates", params);
	}

	@Override
	public Integer countHsWeekFates(HsWeekFateCondition condition) {
		return (Integer) getSqlMapClientTemplate().queryForObject("HsWeekFate.countHsWeekFates", condition.toMap());
	}
	
	@Override
	public Integer updateHsWeekFate(HsWeekFateDO hsFate) {
		return (Integer) getSqlMapClientTemplate().update("HsWeekFate.updateHsWeekFate", hsFate);
	}
}
