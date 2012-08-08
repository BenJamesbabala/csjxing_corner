package com.doucome.corner.biz.dal.dcome.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.dal.condition.dcome.DcCategorySearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcCategoryDO;
import com.doucome.corner.biz.dal.dcome.DcCategoryDAO;

public class IBatisDcCategoryDAO extends SqlMapClientDaoSupport implements DcCategoryDAO {

	@Override
	public Long insertCategory(DcCategoryDO category) {
		return (Long)getSqlMapClientTemplate().insert("DcCategory.insertCategory" , category) ;
	}
	
	@Override
	public int updateCategoryById(DcCategoryDO cat) {
		return getSqlMapClientTemplate().update("DcCategory.updateCategoryById" , cat) ;
	}

	@Override
	public DcCategoryDO queryCategoryById(long categoryId) {
		return (DcCategoryDO)getSqlMapClientTemplate().queryForObject("DcCategory.queryCategoryById" , categoryId) ;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DcCategoryDO> queryCategoriesByIds(List<Long> ids) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("ids", ids) ;
		return getSqlMapClientTemplate().queryForList("DcCategory.queryCategoriesByIds" , condition) ;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DcCategoryDO> queryCategoriesByLevel(String categoryLevel) {
		return getSqlMapClientTemplate().queryForList("DcCategory.queryCategoriesByLevel" , categoryLevel) ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DcCategoryDO> queryChildCategories(long parentCategoryId) {
		return getSqlMapClientTemplate().queryForList("DcCategory.queryChildCategories" , parentCategoryId) ;
	}

	@Override
	public int deleteCategoryById(long categoryId) {
		return getSqlMapClientTemplate().delete("DcCategory.deleteCategoryById" , categoryId) ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DcCategoryDO> queryCategoriesWithPagination(
			DcCategorySearchCondition searchCondition, int start, int size) {
		Map<String,Object> condition  = searchCondition.toMap() ;
		condition.put("start", start - 1) ;
		condition.put("size", size) ;
		return getSqlMapClientTemplate().queryForList("DcCategory.queryCategoriesWithPagination" , condition) ;
	}

	@Override
	public int countCategoriesWithPagination(
			DcCategorySearchCondition searchCondition) {
		Map<String,Object> condition  = searchCondition.toMap() ;
		return NumberUtils.integerToInt((Integer)getSqlMapClientTemplate().queryForObject("DcCategory.countCategoriesWithPagination" , condition)) ;
	}

	

	

}
