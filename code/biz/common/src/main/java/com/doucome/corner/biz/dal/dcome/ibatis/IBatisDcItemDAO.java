package com.doucome.corner.biz.dal.dcome.ibatis;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.util.CollectionUtils;

import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.dal.condition.dcome.DcItemSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcCatCountDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcItemDO;
import com.doucome.corner.biz.dal.dcome.DcItemDAO;
import com.ibatis.sqlmap.client.SqlMapExecutor;



/**
 * ∂πﬁ¢…Ã∆∑
 * @author langben 2012-7-21
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
	public List<DcItemDO> getItemsByExtId(String externalId) {
		return getSqlMapClientTemplate().queryForList("DcItem.getItemsByExtId" , externalId) ;
	}
	
	@Override
	public DcItemDO getItemByCreatorAndExtItemId(Long creatorId, String extItemId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("creatorId", creatorId);
		params.put("extItemId", extItemId);
		return (DcItemDO) getSqlMapClientTemplate().queryForObject("DcItem.getItemByCreatorAndExtItemId", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> queryItemIdsWithPagination(
			DcItemSearchCondition searchCondition, int start, int size) {
		Map<String,Object> condition = searchCondition.toMap();
		condition.put("start", start - 1) ;
		condition.put("size", size) ;
		return getSqlMapClientTemplate().queryForList("DcItem.queryItemIdsWithPagination" , condition) ;
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
		return NumberUtils.parseInt((Integer)getSqlMapClientTemplate().queryForObject("DcItem.countItemsWithPagination" , condition)) ;
	}

	@Override
	public int incrLoveCount(long itemId , int count) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("itemId", itemId);
		params.put("count", count);
		return getSqlMapClientTemplate().update("DcItem.incrLoveCount" , params) ;
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
	public int descCommentCount(long itemId , int count) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("itemId", itemId);
		params.put("count", count);
		return getSqlMapClientTemplate().update("DcItem.descCommentCount" , params) ;
	}
	
	@Override
	public int incrRateCount(long itemId, int count) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("itemId", itemId);
		params.put("count", count);
		return getSqlMapClientTemplate().update("DcItem.incrRateCount" , params) ;
	}

	@Override
	public int updateItemStatus(Long itemId, String status) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("itemId", itemId);
		params.put("status", status);
		return getSqlMapClientTemplate().update("DcItem.updateItemStatus", params);
	}
	
	@Override
	public int updateRecommTypeById(Long itemId, String recommType) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("itemId", itemId);
		params.put("recommType",recommType);
		return getSqlMapClientTemplate().update("DcItem.updateRecommTypeById", params);
	}
	
	@Override
	public int incrTaokeSellCount(long itemId, int count) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("itemId", itemId);
		map.put("count", count) ;
		return getSqlMapClientTemplate().update("DcItem.incrTaokeSellCount" , map) ;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DcCatCountDO> groupCategoryCountByIds(List<Long> ids) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("ids", ids) ;
		return getSqlMapClientTemplate().queryForList("DcItem.groupCategoryCountByIds" , condition) ;
	}

	@Override
	public Integer batchUpdateSyncItemInfo(final List<DcItemDO> items) {
		if (CollectionUtils.isEmpty(items)) {
			return 0;
		}
		return this.getSqlMapClientTemplate().execute(new SqlMapClientCallback<Integer>() {
			@Override
			public Integer doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (DcItemDO temp: items) {
					executor.update("DcItem.batchUpdateSyncItemInfo", temp);
				}
				return executor.executeBatch();
			}
		});
	}
	
	@Override
	public int updatePostalEnable(Long itemId, String postalEnable) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("itemId", itemId);
		params.put("postalEnable",postalEnable);
		return getSqlMapClientTemplate().update("DcItem.updatePostalEnable", params);
	}

	@Override
	public int updateDisplayOrderById(Long itemId) {
		return getSqlMapClientTemplate().update("DcItem.updateDisplayOrderById" , itemId) ;
	}

	@Override
	public int updateItemStatusByIds(List<Long> idList, String status) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idList", idList);
		params.put("status",status);
		return getSqlMapClientTemplate().update("DcItem.updateItemStatusByIds" , params) ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DcItemDO> queryItemsByNativeIds(List<String> nativeIds) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("nativeIds", nativeIds) ;
		return getSqlMapClientTemplate().queryForList("DcItem.queryItemsByNativeIds" , map) ;
	}

	@Override
	public int updatePicUrlsById(Long itemId, String picUrls) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("id", itemId) ;
		map.put("picUrls", picUrls) ;
		return getSqlMapClientTemplate().update("DcItem.updatePicUrlsById" , map) ;
	}
	
	@Override
	public int updateGenWay(Long itemId, String genWay, String picUrl) {
		Map<String,Object> params = new HashMap<String,Object>() ;
		params.put("itemId", itemId) ;
		params.put("genWay", genWay) ;
		params.put("picUrl", picUrl) ;
		return getSqlMapClientTemplate().update("DcItem.updateGenWay" , params) ;
	}
	
	@Override
	public int updateCategoryId(Long itemId, Long categoryId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("itemId", itemId);
		params.put("categoryId", categoryId);
		return getSqlMapClientTemplate().update("DcItem.updateCategoryId" , params);
	}
	
	@Override
	public int batchUpdateCategoryId(List<Long> itemIds, Long categoryId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("itemIds", itemIds);
		params.put("categoryId", categoryId);
		return getSqlMapClientTemplate().update("DcItem.batchUpdateCategoryId" , params);
	}
}
