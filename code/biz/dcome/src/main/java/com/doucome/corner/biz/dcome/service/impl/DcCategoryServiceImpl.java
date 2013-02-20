package com.doucome.corner.biz.dcome.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcCategorySearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcCategoryDO;
import com.doucome.corner.biz.dal.dcome.DcCategoryDAO;
import com.doucome.corner.biz.dcome.cache.DcCategoryCache;
import com.doucome.corner.biz.dcome.enums.DcCategoryLevelEnums;
import com.doucome.corner.biz.dcome.model.DcCategoryDTO;
import com.doucome.corner.biz.dcome.service.DcCategoryService;

public class DcCategoryServiceImpl implements DcCategoryService {

	@Autowired
	private DcCategoryDAO dcCategoryDAO ;
	
	@Autowired
	private DcCategoryCache dcCategoryCache ;
	
	@Override
	public Long createCategory(DcCategoryDO category) {
		return dcCategoryDAO.insertCategory(category) ;
	}
	
	@Override
	public int updateCategoryById(DcCategoryDO cat) {
		if(cat == null || cat.getId() == null){
			throw new IllegalArgumentException("catId cant be null.") ;
		}
		
		int effectCount = dcCategoryDAO.updateCategoryById(cat) ;
		
		triggerCacheModified(cat.getId()) ;
		
		return effectCount ;
	}

	@Override
	public DcCategoryDTO getCategoryById(long categoryId) {
		DcCategoryDTO dto = dcCategoryCache.get(categoryId) ;
		if(dto == null){
			DcCategoryDO catDO = dcCategoryDAO.queryCategoryById(categoryId) ;
			if(catDO == null){
				return null ;
			}
			dto = new DcCategoryDTO(catDO) ; 
			dcCategoryCache.set(dto);
		}
				
		return dto ;
	}
	
	@Override
	public List<DcCategoryDTO> getCategoriesByIds(List<Long> idList){
		
		Map<Long,DcCategoryDTO> catMap = dcCategoryCache.getCacheMap(idList) ;
		
		List<DcCategoryDTO> dtoList = new ArrayList<DcCategoryDTO>() ;
		//Step 1 :从缓存中取
		if(MapUtils.isNotEmpty(catMap)) {
			for(Iterator<Long> i = idList.iterator() ;i.hasNext() ;){
				Long id = i.next() ;
				DcCategoryDTO dto = catMap.get(id) ;
				if(dto != null){ //缓存中存在
					dtoList.add(catMap.get(id)) ;
					i.remove() ;
				}
			}
		}
		//Step 2 : 缓存中不存在的继续从数据库中取
		if(CollectionUtils.isNotEmpty(idList)) { 
			List<DcCategoryDO> catList = dcCategoryDAO.queryCategoriesByIds(idList) ;
			if(CollectionUtils.isNotEmpty(catList)){
		        for(DcCategoryDO item : catList){
		        	DcCategoryDTO dto = new DcCategoryDTO(item) ; 
		        	dtoList.add(dto) ; 
		        	dcCategoryCache.set(dto) ; //加到缓存
		        }
	        }
		}
        
        return dtoList ;
		
		
	}
	
	@Override
	public List<DcCategoryDTO> getCategoriesByLevel(DcCategoryLevelEnums categoryLevel) {
		List<DcCategoryDO> catList = dcCategoryDAO.queryCategoriesByLevel(categoryLevel.getValue()) ;
		List<DcCategoryDTO> dtoList = new ArrayList<DcCategoryDTO>() ;
		if(CollectionUtils.isNotEmpty(catList)){
			for(DcCategoryDO cat : catList){
				dtoList.add(new DcCategoryDTO(cat)) ;
			}
		}
		return dtoList ;
	}


	@Override
	public List<DcCategoryDTO> getChildCategories(long parentCategoryId) {
		List<DcCategoryDO> catList = dcCategoryDAO.queryChildCategories(parentCategoryId) ;
		List<DcCategoryDTO> dtoList = new ArrayList<DcCategoryDTO>() ;
		if(CollectionUtils.isEmpty(catList)){
			for(DcCategoryDO cat : catList){
				dtoList.add(new DcCategoryDTO(cat)) ;
			}
		}
		return dtoList ;
	}
	
	@Override
	public QueryResult<DcCategoryDTO> getCategoriesWithPagination(DcCategorySearchCondition searchCondition, Pagination pagination) {
		int totalRecords = dcCategoryDAO.countCategoriesWithPagination(searchCondition) ;
        if (totalRecords <= 0) {
            return new QueryResult<DcCategoryDTO>(new ArrayList<DcCategoryDTO>(), pagination, totalRecords);
        }
        List<DcCategoryDO> catList = dcCategoryDAO.queryCategoriesWithPagination(searchCondition, pagination.getStart(), pagination.getSize());
        
        List<DcCategoryDTO> dtoList = new ArrayList<DcCategoryDTO>() ;
        if(!CollectionUtils.isEmpty(catList)){
			for(DcCategoryDO cat : catList){
				dtoList.add(new DcCategoryDTO(cat)) ;
			}
		}
        
        return new QueryResult<DcCategoryDTO>(dtoList, pagination, totalRecords);
	}

	@Override
	public int removeCategoryById(long categoryId) {
		int effectCount = dcCategoryDAO.deleteCategoryById(categoryId) ;
		triggerCacheModified(categoryId) ;
		return effectCount ;
	}
	
	@Override
	public List<DcCategoryDTO> getCategories(DcCategorySearchCondition searchCondition) {
		
		List<DcCategoryDO> catList = dcCategoryDAO.queryCategoriesWithPagination(searchCondition, 1 , 100);
        
        List<DcCategoryDTO> dtoList = new ArrayList<DcCategoryDTO>() ;
        if(!CollectionUtils.isEmpty(catList)){
			for(DcCategoryDO cat : catList){
				dtoList.add(new DcCategoryDTO(cat)) ;
			}
		}
        return dtoList ;
	}

	
	private void triggerCacheModified(Long catId) {
		dcCategoryCache.remove(catId) ;
	}
	

	
}
