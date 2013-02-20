package com.doucome.corner.biz.dcome.service;

import java.util.List;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcCategorySearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcCategoryDO;
import com.doucome.corner.biz.dcome.enums.DcCategoryLevelEnums;
import com.doucome.corner.biz.dcome.model.DcCategoryDTO;
/**
 * ��ޢ��Ŀ
 * @author langben 2012-7-23
 *
 */
public interface DcCategoryService {

	/**
	 * ������Ŀ
	 * @param category
	 * @return
	 */
	Long createCategory(DcCategoryDO category) ;
	
	/**
	 * ������Ŀ
	 * @param categoryId
	 * @param name
	 * @return
	 */
	int updateCategoryById(DcCategoryDO cat);
	
	/**
	 * ����ID��ѯ��Ŀ
	 * @param id
	 * @return
	 */
	DcCategoryDTO getCategoryById(long categoryId) ;
	
	/**
	 * ����IDS��ѯ��Ŀ
	 * @param ids
	 * @return
	 */
	List<DcCategoryDTO> getCategoriesByIds(List<Long> ids);
	
	
	/**
	 * ������Ŀ�㼶��ѯ
	 * @param categoryLevel
	 * @return
	 */
	List<DcCategoryDTO> getCategoriesByLevel(DcCategoryLevelEnums categoryLevel) ;
	
	/**
	 * ��ѯ����Ŀ
	 * @param parentCategoryId
	 * @return
	 */
	List<DcCategoryDTO> getChildCategories(long parentCategoryId) ;
	
	/**
	 * ��ѯ��Ŀ
	 * @param searchCondition
	 * @return
	 */
	List<DcCategoryDTO> getCategories(DcCategorySearchCondition searchCondition) ;
	
	/**
	 * ��ҳ��ѯ��Ŀ
	 * @param searchCondition
	 * @param pagination
	 * @return
	 */
	QueryResult<DcCategoryDTO> getCategoriesWithPagination(DcCategorySearchCondition searchCondition , Pagination pagination) ;
	
	/**
	 * ɾ����Ŀ
	 * @param categoryId
	 * @return
	 */
	int removeCategoryById(long categoryId) ;
}
