package com.doucome.corner.biz.dcome.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcItemSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcCatCountDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcItemDO;
import com.doucome.corner.biz.dal.dcome.DcItemDAO;
import com.doucome.corner.biz.dcome.enums.DcItemStatusEnum;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.service.DcItemService;
/**
 * 
 * @author shenjia.caosj 2012-7-22
 *
 */
public class DcItemServiceImpl implements DcItemService {
	
	@Autowired 
	private DcItemDAO dcItemDAO ;
	
	private static final Log log = LogFactory.getLog(DcItemServiceImpl.class);
	
	@Override
	public Long createItem(DcItemDO item) {
		try {
			return dcItemDAO.insertItem(item) ;
		} catch (Exception e) {
			log.error(e);
			return -1L;
		}
	}
	
	@Override
	public int updateItem(DcItemDO item) {
		if (item.getId() == null || item.getId() <= 0L) {
			return 0;
		}
		try {
			return dcItemDAO.updateItem(item);
		} catch(Exception e) {
			log.error(e);
			return -1;
		}
	}

	@Override
	public DcItemDTO getItemById(long itemId) {
		DcItemDO item = dcItemDAO.queryItemById(itemId) ;
		if(item == null){
			return null ;
		}
		return new DcItemDTO(item);
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
		//Ï²»¶+1
		return dcItemDAO.incrLoveCount(itemId) ;
	}
	
	@Override
	public int descCommentCount(long itemId) {
		//Ï²»¶-1
		return dcItemDAO.descCommentCount(itemId) ;
	}
	
	@Override
	public int incrCommentCount(long itemId) {
		return dcItemDAO.incrCommentCount(itemId, 1) ;
	}
	
	@Override
	public int incrCommentCount(long itemId, int count) {
		return dcItemDAO.incrCommentCount(itemId, count) ;
	}
	
	@Override
	public int resetItemStatus(Long itemId, DcItemStatusEnum status) {
		if (itemId == null || status == null) {
			return 0;
		}
		return dcItemDAO.updateItemStatus(itemId, status.getValue());
	}

	@Override
	public List<DcItemDTO> getItemsByIds(List<Long> ids) {
		List<DcItemDO> itemList = dcItemDAO.queryItemsByIds(ids) ;
		List<DcItemDTO> dtoList = new ArrayList<DcItemDTO>() ;
        if(!CollectionUtils.isEmpty(itemList)){
	        for(DcItemDO item : itemList){
	        	dtoList.add(new DcItemDTO(item)) ;
	        }
        }
        return dtoList ;
	}

	@Override
	public List<DcCatCountDO> groupCategoryCountByIds(List<Long> ids) {
		return dcItemDAO.groupCategoryCountByIds(ids) ;
	}

	

	

	

}
