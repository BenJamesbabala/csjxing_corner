package com.doucome.corner.biz.dcome.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
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
		dcCategoryCache.remove(cat.getId()) ;
		return dcCategoryDAO.updateCategoryById(cat) ;
	}

	@Override
	public DcCategoryDTO getCategoryById(long categoryId) {
		DcCategoryDO cat = dcCategoryCache.get(categoryId) ;
		if(cat == null){
			cat = dcCategoryDAO.queryCategoryById(categoryId) ;
			if(cat == null){
				return null ;
			}
			dcCategoryCache.set(cat);
		}
				
		return new DcCategoryDTO(cat) ;
	}
	
	@Override
	public List<DcCategoryDTO> getCategoriesByIds(List<Long> ids){
		List<DcCategoryDO> catList = dcCategoryDAO.queryCategoriesByIds(ids) ;
		List<DcCategoryDTO> dtoList = new ArrayList<DcCategoryDTO>() ;
		if(CollectionUtils.isNotEmpty(catList)){
			for(DcCategoryDO cat : catList){
				dtoList.add(new DcCategoryDTO(cat)) ;
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
		dcCategoryCache.remove(categoryId) ;
		return dcCategoryDAO.deleteCategoryById(categoryId) ;
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

	

	

	
}
