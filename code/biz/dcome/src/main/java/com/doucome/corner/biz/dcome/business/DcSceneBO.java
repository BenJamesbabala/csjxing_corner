package com.doucome.corner.biz.dcome.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dcome.cache.DcQIndexCache;
import com.doucome.corner.biz.dcome.model.DcCategoryDTO;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.model.DcSceneDTO;
import com.doucome.corner.biz.dcome.model.facade.DcSceneQIndexFacade;
import com.doucome.corner.biz.dcome.service.DcCategoryService;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.biz.dcome.service.DcSceneDetailService;
import com.doucome.corner.biz.dcome.service.DcSceneService;

/**
 * 场景
 * @author shenjia.caosj 2012-7-24
 *
 */
public class DcSceneBO {

	@Autowired
	private DcSceneService dcSceneService ;
	
	@Autowired
	private DcSceneDetailService dcSceneDetailService ;
	
	@Autowired
	private DcItemService dcItemService ;
	
	@Autowired
	private DcQIndexCache dcQIndexCache ;
	
	@Autowired
	private DcCategoryService dcCategoryService ;
	
	/**
	 * 查询场景（从DB取）
	 * @param sceneId
	 * @return
	 */
	public DcSceneDTO getSceneJoinItemsById(long sceneId) {
		
		DcSceneDTO dto = dcSceneService.getSceneById(sceneId) ;
		if(dto == null){
			return null ;
		}
		List<Long> ids = dcSceneDetailService.getItemsByScene(sceneId, 50) ;
		if(!CollectionUtils.isEmpty(ids)){
			List<DcItemDTO> itemList = dcItemService.getItemsByIds(ids) ;
			dto.setItemList(itemList);
		}
				
		return dto ;
	}
	
	public DcSceneQIndexFacade getQIndexFacadeNoCache(long sceneId){
		
		DcSceneDTO dto = getSceneJoinItemsById(sceneId) ;
		if(dto == null){
			return null ;
		}
		DcSceneQIndexFacade facade = new DcSceneQIndexFacade(dto) ;
		
		if(CollectionUtils.isNotEmpty(dto.getCategoryIdList())){
			Map<Long,DcCategoryDTO> catMappings  = new HashMap<Long,DcCategoryDTO>() ;
			for(Long catId : dto.getCategoryIdList()){
				DcCategoryDTO cat = dcCategoryService.getCategoryById(catId) ;
				catMappings.put(catId, cat) ;
			}
			facade.setCatMappings(catMappings);
		}
		
		return facade ;
	}
	
	public DcSceneQIndexFacade getQIndexFacade(long sceneId){
		DcSceneQIndexFacade facade = dcQIndexCache.getSceneCache(sceneId) ;
		if(facade != null){
			return facade ;
		}
		
		facade = getQIndexFacadeNoCache(sceneId) ;
		
		if(facade == null){
			return null ;
		}
		
		dcQIndexCache.setSceneCache(sceneId, facade) ;
		return facade ;
	}
	
	public QueryResult<DcItemDTO> getItemsWithPagination(long sceneId , Pagination pagination){
		QueryResult<Long> idQuery = dcSceneDetailService.getItemsBySceneWithPagination(sceneId, pagination) ;
		List<DcItemDTO> dtoList = null ;
		if(CollectionUtils.isNotEmpty(idQuery.getItems())){
			dtoList = dcItemService.getItemsByIds(idQuery.getItems()) ;
		}else{
			dtoList = new ArrayList<DcItemDTO>() ;
		}
		return new QueryResult<DcItemDTO>(dtoList, pagination, idQuery.getTotalRecords()) ;
		
	}

}
