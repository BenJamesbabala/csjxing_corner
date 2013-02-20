package com.doucome.corner.biz.dal.horoscope.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.dal.condition.dcome.hs.HsDailyFateCondition;
import com.doucome.corner.biz.dal.horoscope.HsDailyFateDAO;
import com.doucome.corner.biz.dal.horoscope.dataobject.HsDailyFateDO;

/**
 * ÐÇ×ù±´±´ÓÃ»§dao
 * @author ze2200
 *
 */
public class IBatisHsDailyFateDAO extends SqlMapClientDaoSupport implements HsDailyFateDAO {

	@Override
	public Long insertHsDailyFate(HsDailyFateDO hsFate) {
		return (Long) getSqlMapClientTemplate().insert("HsDailyFate.insertHsDailyFate", hsFate);
	}

	@Override
	public HsDailyFateDO queryHsDailyFate(Long id) {
		return (HsDailyFateDO) getSqlMapClientTemplate().queryForObject("HsDailyFate.queryHsDailyFate", id);
	}

	@Override
	public HsDailyFateDO queryHsDailyFate(Integer hsId, Date dayStart, Date dayEnd) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("hsId", hsId);
		params.put("dayStart", dayStart);
		params.put("dayEnd", dayEnd);
		return (HsDailyFateDO) getSqlMapClientTemplate().queryForObject("HsDailyFate.queryHsDailyFateByDate", params);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HsDailyFateDO> queryHsDailyFates(HsDailyFateCondition condition, int start, int size) {
		Map<String, Object> params = condition.toMap();
		params.put("start", start);
		params.put("size", size);
		return (List<HsDailyFateDO>) getSqlMapClientTemplate().queryForList("HsDailyFate.queryHsDailyFates", params);
	}
	
	@Override
	public Integer countHsDailyFates(HsDailyFateCondition condition) {
		return (Integer) getSqlMapClientTemplate().queryForObject("HsDailyFate.countHsDailyFates", condition.toMap());
	}
	
	@Override
	public Integer updateHsDailyFate(HsDailyFateDO hsFate) {
		return (Integer) getSqlMapClientTemplate().update("HsDailyFate.updateHsDailyFate", hsFate);
	}
}
