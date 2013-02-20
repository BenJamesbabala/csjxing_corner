package com.doucome.corner.biz.dal.horoscope.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.dal.condition.dcome.hs.HsMonthFateCondition;
import com.doucome.corner.biz.dal.horoscope.HsMonthFateDAO;
import com.doucome.corner.biz.dal.horoscope.dataobject.HsMonthFateDO;

/**
 * ÐÇ×ù±´±´ÓÃ»§dao
 * @author ze2200
 *
 */
public class IBatisHsMonthFateDAO extends SqlMapClientDaoSupport implements HsMonthFateDAO {

	@Override
	public Long insertHsMonthFate(HsMonthFateDO hsFate) {
		return (Long) getSqlMapClientTemplate().insert("HsMonthFate.insertHsMonthFate", hsFate);
	}

	@Override
	public HsMonthFateDO queryHsMonthFate(Long id) {
		return (HsMonthFateDO) getSqlMapClientTemplate().queryForObject("HsMonthFate.queryHsMonthFate", id);
	}

	@Override
	public HsMonthFateDO queryHsMonthFate(Integer hsId, Date monthStart, Date monthEnd) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("hsId", hsId);
		params.put("monthStart", monthStart);
		params.put("monthEnd", monthEnd);
		return (HsMonthFateDO) getSqlMapClientTemplate().queryForObject("HsMonthFate.queryHsMonthFateByDate", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HsMonthFateDO> queryHsMonthFates(HsMonthFateCondition condition, int start, int size) {
		Map<String, Object> params = condition.toMap();
		params.put("start", start);
		params.put("size", size);
		return (List<HsMonthFateDO>) getSqlMapClientTemplate().queryForList("HsMonthFate.queryHsMonthFates", params);
	}

	@Override
	public Integer countHsMonthFates(HsMonthFateCondition condition) {
		return (Integer) getSqlMapClientTemplate().queryForObject("HsMonthFate.countHsMonthFates", condition.toMap());
	}
	
	@Override
	public Integer updateHsMonthFate(HsMonthFateDO hsFate) {
		return (Integer) getSqlMapClientTemplate().update("HsMonthFate.updateHsMonthFate", hsFate);
	}
}
