package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.condition.dcome.DcCategorySearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcCategoryDO;

/**
 * 豆蔻类目
 * @author langben 2012-7-23
 *
 */
public interface DcCategoryDAO {

	/**
	 * 新增类目
	 * @param category
	 * @return
	 */
	Long insertCategory(DcCategoryDO category) ;
	
	/**
	 * 更新类目
	 * @param categoryId
	 * @param name
	 * @return
	 */
	int updateCategoryById(DcCategoryDO cat) ;
	
	/**
	 * 根据ID查询类目
	 * @param id
	 * @return
	 */
	DcCategoryDO queryCategoryById(long categoryId) ;
	
	/**
	 * 根据IDS查询类目
	 * @param ids
	 * @return
	 */
	List<DcCategoryDO> queryCategoriesByIds(List<Long> ids);
	
	/**
	 * 根据类目层级查询
	 * @param categoryLevel
	 * @return
	 */
	List<DcCategoryDO> queryCategoriesByLevel(String categoryLevel) ;
	
	/**
	 * 查询子类目
	 * @param parentCategoryId
	 * @return
	 */
	List<DcCategoryDO> queryChildCategories(long parentCategoryId) ;
	
	/**
	 * 分页查询类目
	 * @param searchCondition
	 * @param start
	 * @param size
	 * @return
	 */
	List<DcCategoryDO> queryCategoriesWithPagination(DcCategorySearchCondition searchCondition , int start , int size) ;
	
	/**
	 * 分页查询类目
	 * @param searchCondition
	 * @return
	 */
	int countCategoriesWithPagination(DcCategorySearchCondition searchCondition) ;
	
	/**
	 * 删除类目
	 * @param categoryId
	 * @return
	 */
	int deleteCategoryById(long categoryId) ;

	
}
