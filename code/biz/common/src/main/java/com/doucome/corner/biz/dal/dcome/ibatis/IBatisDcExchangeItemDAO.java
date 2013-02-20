package com.doucome.corner.biz.dal.dcome.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.dal.condition.dcome.DcExchangeItemCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcExchangeItemDO;
import com.doucome.corner.biz.dal.dcome.DcExchangeItemDAO;

public class IBatisDcExchangeItemDAO extends SqlMapClientDaoSupport implements DcExchangeItemDAO {

	@Override
	public Long insertExchangeItem(DcExchangeItemDO exItem) {
		return (Long) this.getSqlMapClientTemplate().insert("exchangeItem.insertExchangeItem", exItem);
	}

	@Override
	public DcExchangeItemDO getExchangeItem(Long id) {
		return (DcExchangeItemDO) getSqlMapClientTemplate().queryForObject("exchangeItem.getExchangeItem", id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DcExchangeItemDO> queryExchangeItemsNoPage(DcExchangeItemCondition condition) {
		return (List<DcExchangeItemDO>) getSqlMapClientTemplate().queryForList("exchangeItem.queryExchangeItemsNoPage", condition.toMap());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DcExchangeItemDO> queryExchangeItemsPage(DcExchangeItemCondition condition, int start, int size) {
		Map<String, Object> params = condition.toMap();
		params.put("start", start);
		params.put("size", size);
		return (List<DcExchangeItemDO>) getSqlMapClientTemplate().queryForList("exchangeItem.queryExchangeItemsPage", params);
	}
	
	@Override
	public Integer countExchangeItems(DcExchangeItemCondition condition) {
		return (Integer) getSqlMapClientTemplate().queryForObject("exchangeItem.countExchangeItems", condition.toMap());
	}
	
	@Override
	public Integer initExchangeInfo(DcExchangeItemDO exchangeItem) {
		return getSqlMapClientTemplate().update("exchangeItem.initExchangeInfo", exchangeItem);
	}
	
	@Override
	public int decExchangeNum(Long id, int exCount) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("exCount", exCount);
		return (Integer) getSqlMapClientTemplate().update("exchangeItem.decExchangeNum", params);
	}
	
	@Override
	public int delExchangeItem(Long id) {
		return (Integer) getSqlMapClientTemplate().delete("exchangeItem.delExchangeItem", id);
	}
}
