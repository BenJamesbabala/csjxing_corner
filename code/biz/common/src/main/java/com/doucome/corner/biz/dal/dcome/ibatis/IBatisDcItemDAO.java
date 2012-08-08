package com.doucome.corner.biz.dal.dcome.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.dal.condition.dcome.DcItemSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcCatCountDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcItemDO;
import com.doucome.corner.biz.dal.dcome.DcItemDAO;



/**
 * ∂πﬁ¢…Ã∆∑
 * @author shenjia.caosj 2012-7-21
 *
 */
public class IBatisDcItemDAO  extends SqlMapClientDaoSupport implements DcItemDAO  {

	@Override
	public Long insertItem(DcItemDO item) {
		return (Long)getSqlMapClientTemplate().insert("DcItem.insertItem", item) ;
	}
	
	@Override
	public int updateItem(DcItemDO item) {
		return (int) getSqlMapClientTemplate().update("DcItem.updateItem", item);
	}

	@Override
	public DcItemDO queryItemById(long itemId) {
		return (DcItemDO)getSqlMapClientTemplate().queryForObject("DcItem.queryItemById" , itemId) ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DcItemDO> queryItemsWithPagination(DcItemSearchCondition searchCondition, int start, int size) {
		Map<String,Object> condition = searchCondition.toMap();
		condition.put("start", start - 1) ;
		condition.put("size", size) ;
		return getSqlMapClientTemplate().queryForList("DcItem.queryItemsWithPagination" , condition) ;
	}

	@Override
	public int countItemsWithPagination(DcItemSearchCondition searchCondition) {
		Map<String,Object> condition = searchCondition.toMap() ;
		return NumberUtils.integerToInt((Integer)getSqlMapClientTemplate().queryForObject("DcItem.countItemsWithPagination" , condition)) ;
	}

	@Override
	public int incrLoveCount(long itemId) {
		return getSqlMapClientTemplate().update("DcItem.incrLoveCount" , itemId) ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DcItemDO> queryItemsByIds(List<Long> ids) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("ids", ids) ;
		return getSqlMapClientTemplate().queryForList("DcItem.queryItemsByIds" , condition) ;
	}

	@Override
	public int incrCommentCount(long itemId, int count) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("itemId", itemId);
		params.put("count", count);
		return getSqlMapClientTemplate().update("DcItem.incrCommentCount" , params) ;
	}
	
	@Override
	public int descCommentCount(long itemId) {
		return getSqlMapClientTemplate().update("DcItem.descCommentCount" , itemId) ;
	}

	@Override
	public int updateItemStatus(Long itemId, String status) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("itemId", itemId);
		params.put("status", status);
		return getSqlMapClientTemplate().update("DcItem.updateItemStatus", params);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DcCatCountDO> groupCategoryCountByIds(List<Long> ids) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("ids", ids) ;
		return getSqlMapClientTemplate().queryForList("DcItem.groupCategoryCountByIds" , condition) ;
	}
	
}
