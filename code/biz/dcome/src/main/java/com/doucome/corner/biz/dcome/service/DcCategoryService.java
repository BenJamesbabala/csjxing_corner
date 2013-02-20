package com.doucome.corner.biz.dcome.service;

import java.util.List;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcCategorySearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcCategoryDO;
import com.doucome.corner.biz.dcome.enums.DcCategoryLevelEnums;
import com.doucome.corner.biz.dcome.model.DcCategoryDTO;
/**
 * 豆蔻类目
 * @author langben 2012-7-23
 *
 */
public interface DcCategoryService {

	/**
	 * 新增类目
	 * @param category
	 * @return
	 */
	Long createCategory(DcCategoryDO category) ;
	
	/**
	 * 更新类目
	 * @param categoryId
	 * @param name
	 * @return
	 */
	int updateCategoryById(DcCategoryDO cat);
	
	/**
	 * 根据ID查询类目
	 * @param id
	 * @return
	 */
	DcCategoryDTO getCategoryById(long categoryId) ;
	
	/**
	 * 根据IDS查询类目
	 * @param ids
	 * @return
	 */
	List<DcCategoryDTO> getCategoriesByIds(List<Long> ids);
	
	
	/**
	 * 根据类目层级查询
	 * @param categoryLevel
	 * @return
	 */
	List<DcCategoryDTO> getCategoriesByLevel(DcCategoryLevelEnums categoryLevel) ;
	
	/**
	 * 查询子类目
	 * @param parentCategoryId
	 * @return
	 */
	List<DcCategoryDTO> getChildCategories(long parentCategoryId) ;
	
	/**
	 * 查询类目
	 * @param searchCondition
	 * @return
	 */
	List<DcCategoryDTO> getCategories(DcCategorySearchCondition searchCondition) ;
	
	/**
	 * 分页查询类目
	 * @param searchCondition
	 * @param pagination
	 * @return
	 */
	QueryResult<DcCategoryDTO> getCategoriesWithPagination(DcCategorySearchCondition searchCondition , Pagination pagination) ;
	
	/**
	 * 删除类目
	 * @param categoryId
	 * @return
	 */
	int removeCategoryById(long categoryId) ;
}
