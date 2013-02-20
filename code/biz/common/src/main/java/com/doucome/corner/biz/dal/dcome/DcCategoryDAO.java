package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.condition.dcome.DcCategorySearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcCategoryDO;

/**
 * ��ޢ��Ŀ
 * @author langben 2012-7-23
 *
 */
public interface DcCategoryDAO {

	/**
	 * ������Ŀ
	 * @param category
	 * @return
	 */
	Long insertCategory(DcCategoryDO category) ;
	
	/**
	 * ������Ŀ
	 * @param categoryId
	 * @param name
	 * @return
	 */
	int updateCategoryById(DcCategoryDO cat) ;
	
	/**
	 * ����ID��ѯ��Ŀ
	 * @param id
	 * @return
	 */
	DcCategoryDO queryCategoryById(long categoryId) ;
	
	/**
	 * ����IDS��ѯ��Ŀ
	 * @param ids
	 * @return
	 */
	List<DcCategoryDO> queryCategoriesByIds(List<Long> ids);
	
	/**
	 * ������Ŀ�㼶��ѯ
	 * @param categoryLevel
	 * @return
	 */
	List<DcCategoryDO> queryCategoriesByLevel(String categoryLevel) ;
	
	/**
	 * ��ѯ����Ŀ
	 * @param parentCategoryId
	 * @return
	 */
	List<DcCategoryDO> queryChildCategories(long parentCategoryId) ;
	
	/**
	 * ��ҳ��ѯ��Ŀ
	 * @param searchCondition
	 * @param start
	 * @param size
	 * @return
	 */
	List<DcCategoryDO> queryCategoriesWithPagination(DcCategorySearchCondition searchCondition , int start , int size) ;
	
	/**
	 * ��ҳ��ѯ��Ŀ
	 * @param searchCondition
	 * @return
	 */
	int countCategoriesWithPagination(DcCategorySearchCondition searchCondition) ;
	
	/**
	 * ɾ����Ŀ
	 * @param categoryId
	 * @return
	 */
	int deleteCategoryById(long categoryId) ;

	
}
