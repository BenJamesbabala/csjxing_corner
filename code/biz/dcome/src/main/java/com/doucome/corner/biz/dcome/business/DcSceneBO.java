package com.doucome.corner.biz.dcome.business;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.biz.dcome.service.DcSceneDetailService;

/**
 * ³¡¾°
 * @author langben 2012-7-24
 *
 */
public class DcSceneBO {
	
	@Autowired
	private DcSceneDetailService dcSceneDetailService ;
	
	@Autowired
	private DcItemService dcItemService ;
	
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
