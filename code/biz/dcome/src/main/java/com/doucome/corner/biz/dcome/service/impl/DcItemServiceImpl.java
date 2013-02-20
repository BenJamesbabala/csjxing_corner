package com.doucome.corner.biz.dcome.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.core.exception.CacheFailedException;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.utils.JacksonHelper;
import com.doucome.corner.biz.dal.condition.dcome.DcItemSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcCatCountDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcItemDO;
import com.doucome.corner.biz.dal.dcome.DcItemDAO;
import com.doucome.corner.biz.dal.model.DcItemPicModel;
import com.doucome.corner.biz.dcome.cache.DcItemCache;
import com.doucome.corner.biz.dcome.enums.DcItemGenWayEnums;
import com.doucome.corner.biz.dcome.enums.DcItemRecommTypeEnums;
import com.doucome.corner.biz.dcome.enums.DcItemStatusEnum;
import com.doucome.corner.biz.dcome.enums.DcYesOrNoEnum;
import com.doucome.corner.biz.dcome.exception.DcDuplicateKeyException;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.service.DcItemService;
/**
 * 
 * @author langben 2012-7-22
 *
 */
public class DcItemServiceImpl implements DcItemService {
	
	@Autowired 
	private DcItemDAO dcItemDAO ;
	
	@Autowired
	private DcItemCache dcItemCache ;
	
	@Override
	public Long createItem(DcItemDO item) throws DcDuplicateKeyException {
		try {
			Long itemId = dcItemDAO.insertItem(item);
			return itemId;
		} catch (DuplicateKeyException e) {
			throw new DcDuplicateKeyException(e);
		}
	}
	
	@Override
	public int updateItem(DcItemDO item) {
		int effectCount = dcItemDAO.updateItem(item);
		dcItemDAO.updateDisplayOrderById(item.getId());
		triggerCacheModified(item.getId()) ;
		return effectCount ;
	}
	
	@Override
	public int updateItemStatusByIds(List<Long> idList, DcItemStatusEnum status) {
		if(CollectionUtils.isEmpty(idList)){
			return 0 ;
		}
		int effectCount = dcItemDAO.updateItemStatusByIds(idList , status.getValue());
		for(Long id : idList){
			triggerCacheModified(id) ;
		}
		return effectCount;
	}
	
	@Override
	public DcItemDTO getItemById(long itemId) {
		DcItemDTO dto = dcItemCache.get(itemId) ;
		if(dto == null){
			DcItemDO item = dcItemDAO.queryItemById(itemId) ;
			if(item == null){
				return null ;
			}
			dto = new DcItemDTO(item);
			dcItemCache.set(dto) ;
		}
		
		return dto ;
	}
	
	@Override
	public List<DcItemDTO> getItemsByExtlId(String externalId) {
		List<DcItemDO> items = dcItemDAO.getItemsByExtId(externalId);
		List<DcItemDTO> result = new ArrayList<DcItemDTO>();
		for (DcItemDO temp: items) {
			result.add(new DcItemDTO(temp));
		}
		return result;
	}
	
	@Override
	public DcItemDTO getItemByCreatorAndExtItemId(Long userId, String extItemId) {
		DcItemDO itemDO = dcItemDAO.getItemByCreatorAndExtItemId(userId, extItemId);
		if (itemDO == null) {
			return null;
		}
		DcItemDTO result = new DcItemDTO(itemDO);
		dcItemCache.set(result);
		return result;
	}
	
	@Override
	public List<Long> getItemIdsNoPagination(
			DcItemSearchCondition searchCondition, Pagination pagination) {
		return dcItemDAO.queryItemIdsWithPagination(searchCondition, pagination.getStart(), pagination.getSize()) ;
	}

	@Override
	public List<DcItemDTO> getItemsNoPagination(DcItemSearchCondition searchCondition, Pagination pagination) {
		List<DcItemDO> itemList = dcItemDAO.queryItemsWithPagination(searchCondition, pagination.getStart(), pagination.getSize());
		List<DcItemDTO> dtoList = new ArrayList<DcItemDTO>() ;
		if(!CollectionUtils.isEmpty(itemList)){
	        for(DcItemDO item : itemList){
	        	dtoList.add(new DcItemDTO(item)) ;
	        }
        }
		return dtoList ;
	}
	
	@Override
	public QueryResult<DcItemDTO> getItemsWithPagination(DcItemSearchCondition searchCondition, Pagination pagination) {
		int totalRecords = dcItemDAO.countItemsWithPagination(searchCondition) ;
        if (totalRecords <= 0) {
            return new QueryResult<DcItemDTO>(new ArrayList<DcItemDTO>(), pagination, totalRecords);
        }
        List<DcItemDO> itemList = dcItemDAO.queryItemsWithPagination(searchCondition, pagination.getStart(), pagination.getSize());
        
        List<DcItemDTO> dtoList = new ArrayList<DcItemDTO>() ;
        if(!CollectionUtils.isEmpty(itemList)){
	        for(DcItemDO item : itemList){
	        	dtoList.add(new DcItemDTO(item)) ;
	        }
        }
        
        return new QueryResult<DcItemDTO>(dtoList, pagination, totalRecords);
	}

	@Override
	public int incrLoveCount(long itemId) {
		//喜欢+1
		int effectCount = dcItemDAO.incrLoveCount(itemId , 1) ;
		triggerCacheModified(itemId) ;
		return effectCount ;
	}
	
	@Override
	public int descCommentCount(long itemId) {
		//喜欢-1
		int effectCount = dcItemDAO.descCommentCount(itemId , 1) ;
		triggerCacheModified(itemId) ;
		return effectCount ;
	}
	
	@Override
	public int incrCommentCount(long itemId) {
		return this.incrCommentCount(itemId, 1) ;
	}
	
	@Override
	public int incrCommentCount(long itemId, int count) {
		int effectCount = dcItemDAO.incrCommentCount(itemId, count) ;
		triggerCacheModified(itemId) ;
		return effectCount ;
	}
	
	@Override
	public int incrRateCount(long itemId) {
		int effectCount = dcItemDAO.incrRateCount(itemId, 1) ;
		triggerCacheModified(itemId) ;
		return effectCount ;
	}

	
	@Override
	public int resetItemStatus(Long itemId, DcItemStatusEnum status) {
		int effectCount =  dcItemDAO.updateItemStatus(itemId, status.getValue());
		triggerCacheModified(itemId) ;
		return effectCount ;
	}

	@Override
	public List<DcItemDTO> getItemsByIds(List<Long> idList) {
		if(CollectionUtils.isEmpty(idList)){
			return new ArrayList<DcItemDTO>();
		}
		Map<Long,DcItemDTO> itemMap = dcItemCache.getCacheMap(idList) ;
		
		List<DcItemDTO> dtoList = new ArrayList<DcItemDTO>() ;
		//Step 1 :从缓存中取
		if(MapUtils.isNotEmpty(itemMap)) {
			for(Iterator<Long> i = idList.iterator() ;i.hasNext() ;){
				Long id = i.next() ;
				DcItemDTO dto = itemMap.get(id) ;
				if(dto != null){ //缓存中存在
					dtoList.add(itemMap.get(id)) ;
					i.remove() ;
				}
			}
		}
		//Step 2 : 缓存中不存在的继续从数据库中取
		if(CollectionUtils.isNotEmpty(idList)) { 
			List<DcItemDO> itemList = dcItemDAO.queryItemsByIds(idList) ;
			if(CollectionUtils.isNotEmpty(itemList)){
		        for(DcItemDO item : itemList){
		        	DcItemDTO dto = new DcItemDTO(item) ; 
		        	dtoList.add(dto) ; 
		        	dcItemCache.set(dto) ; //加到缓存
		        }
	        }
		}
        
        return dtoList ;
	}
	
	@Override
    public long countItemsWithPagination(DcItemSearchCondition condition) {
        return dcItemDAO.countItemsWithPagination(condition) ;
    }

	@Override
	public List<DcCatCountDO> groupCategoryCountByIds(List<Long> ids) {
		return dcItemDAO.groupCategoryCountByIds(ids) ;
	}
	
	@Override
	public boolean isUserReSubmitItem(Long userId, String tbItemId) throws CacheFailedException {
		return !dcItemCache.putIfAbsent(userId, tbItemId, 5000L);
	}

	@Override
	public int updateRecommTypeById(Long itemId, DcItemRecommTypeEnums recommType) {
		int count = dcItemDAO.updateRecommTypeById(itemId, recommType.getValue()) ;
		triggerCacheModified(itemId);
		return count;
	}
	
	@Override
	public int updatePostalEnable(Long itemId, DcYesOrNoEnum postalEnable) {
		int count = dcItemDAO.updatePostalEnable(itemId, postalEnable.getValue());
		triggerCacheModified(itemId);
		return count;
	}

	@Override
	public int batchUpdateSyncItemInfo(List<DcItemDO> items) {
		int effectCount = dcItemDAO.batchUpdateSyncItemInfo(items) ;
		for(DcItemDO item : items){
			triggerCacheModified(item.getId()) ;
		}
		return effectCount ;
	}
	
	private void triggerCacheModified(Long itemId) {
		dcItemCache.remove(itemId) ;
	}

	@Override
	public int updateDisplayOrderById(Long itemId) {
		int effectCount = dcItemDAO.updateDisplayOrderById(itemId) ;
		triggerCacheModified(itemId) ;
		return effectCount ;
	}

	@Override
	public int incrTaokeSellCount(long itemId, int count) {
		int effectCount = dcItemDAO.incrTaokeSellCount(itemId, count) ;
		triggerCacheModified(itemId) ;
		return effectCount ;
	}

	@Override
	public List<DcItemDTO> getItemsByNativeIds(List<String> nativeIds) {
		List<DcItemDO> doList = dcItemDAO.queryItemsByNativeIds(nativeIds) ;
		
		List<DcItemDTO> dtoList = new ArrayList<DcItemDTO>() ;
		if(!CollectionUtils.isEmpty(doList)){
			for(DcItemDO item : doList){
				dtoList.add(new DcItemDTO(item)) ;
			}
		}
		return dtoList ;
	}

	@Override
	public int updatePicUrlsById(Long itemId,List<DcItemPicModel> picUrls) {
		int effectCount = dcItemDAO.updatePicUrlsById(itemId, JacksonHelper.toJSON(picUrls)) ;
		triggerCacheModified(itemId) ;
		return effectCount ;
	}
	
	@Override
	public int updateGenWay(Long itemId, DcItemGenWayEnums genWay, String picUrl) {
		if (IDUtils.isNotCorrect(itemId) || genWay == null || genWay == DcItemGenWayEnums.UNKNOWN) {
			return 0;
		}
		return dcItemDAO.updateGenWay(itemId, genWay.getValue(), picUrl);
	}
	
	@Override
	public int updateCategoryId(Long itemId, Long categoryId) {
		if (IDUtils.isNotCorrect(itemId) || IDUtils.isNotCorrect(categoryId)) {
			return 0;
		}
		return dcItemDAO.updateCategoryId(itemId, categoryId);
	}
	
	@Override
	public int batchUpdateCategoryId(List<Long> itemIds, Long categoryId) {
		if (CollectionUtils.isEmpty(itemIds)) {
			return 0;
		}
		return dcItemDAO.batchUpdateCategoryId(itemIds, categoryId);
	}
}
