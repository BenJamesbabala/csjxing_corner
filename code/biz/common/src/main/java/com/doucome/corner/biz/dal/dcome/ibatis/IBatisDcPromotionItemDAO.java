package com.doucome.corner.biz.dal.dcome.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.dal.condition.dcome.DcPromotionItemSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionItemDO;
import com.doucome.corner.biz.dal.dcome.DcPromotionItemDAO;

/**
 * 
 * @author ze2200
 *
 */
public class IBatisDcPromotionItemDAO extends SqlMapClientDaoSupport implements DcPromotionItemDAO {

	@Override
	public Long insertPromotionItem(DcPromotionItemDO promotionItemDO) {
		return (Long) getSqlMapClientTemplate().insert("DcPromotionItem.insertPromotionItem", promotionItemDO);
	}
	
	@Override
	public DcPromotionItemDO queryPromotionItemById(Long promotionItemId) {
		return (DcPromotionItemDO)getSqlMapClientTemplate().queryForObject("DcPromotionItem.queryPromotionItemById" , promotionItemId) ;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DcPromotionItemDO> queryPromotionItemsWithPagination(DcPromotionItemSearchCondition searchCondition, int start, int size) {
		Map<String,Object> condition = searchCondition.toMap() ;
		condition.put("start", start - 1);
		condition.put("size", size);
		return getSqlMapClientTemplate().queryForList("DcPromotionItem.queryPromotionItemsWithPagination" , condition) ;
	}
	
	@Override
	public int incrRateCountById(Long promotionItemId, int count) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("id", promotionItemId) ;
		condition.put("count", count) ;
		return getSqlMapClientTemplate().update("DcPromotionItem.incrRateCountById" , condition) ;
	}
	
	@Override
	public int decrRateCountById(Long promotionItemId, int count) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("id", promotionItemId) ;
		condition.put("count", count) ;
		return getSqlMapClientTemplate().update("DcPromotionItem.decrRateCountById" , condition) ;
	}

	@Override
	public int drawRateCountByHour(Long promotionItemId, int hour) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("id", promotionItemId) ;
		condition.put("hour", hour) ;
		return getSqlMapClientTemplate().update("DcPromotionItem.drawRateCountByHour" , condition) ;
	}
	
	@Override
	public int decrDrawCountByHourAndId(Long promotionItemId, int hour,int count) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("id", promotionItemId) ;
		condition.put("hour", hour) ;
		condition.put("count", count) ;
		return getSqlMapClientTemplate().update("DcPromotionItem.decrDrawCountByHourAndId" , condition) ;
	}
	
	@Override
	public int incrRateCountByIdAndUser(Long promotionItemId, int count , Long userId) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("id", promotionItemId) ;
		condition.put("count", count) ;
		condition.put("userId", userId) ;
		return getSqlMapClientTemplate().update("DcPromotionItem.incrRateCountById" , condition) ;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<DcPromotionItemDO> getUsersPromItems(Long userId, int pageStart, int pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("pageStart", pageStart);
		params.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList("DcPromotionItem.getUsersPromItems", params);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<DcPromotionItemDO> getUsersPromItems(Long userId, Long promotionId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("promotionId", promotionId);
		return getSqlMapClientTemplate().queryForList("DcPromotionItem.getUsersPromItems", params);
	}

	@Override
	public Integer updatePromItemShareStatus(Long promItemId, String shareStatus) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("promItemId", promItemId);
		params.put("shareStatus", shareStatus);
		return (Integer) getSqlMapClientTemplate().update("DcPromotionItem.markPromItemShared", params);
	}

	@Override
	public int updateNewGuide(Long promItemId, Long newGuide) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("promItemId", promItemId);
		params.put("newGuide", newGuide);
		return (Integer) getSqlMapClientTemplate().update("DcPromotionItem.updateNewGuide", params);
	}

	@Override
	public int updateRateCountById(Long promItemId, int rateCount) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("promItemId", promItemId);
		params.put("rateCount", rateCount);
		return (Integer) getSqlMapClientTemplate().update("DcPromotionItem.updateRateCountById", params);
	}

	@Override
	public int updateUserNickById(Long promItemId, String userNick) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("promItemId", promItemId);
		params.put("userNick", userNick);
		return (Integer) getSqlMapClientTemplate().update("DcPromotionItem.updateUserNickById", params);
	}
}
