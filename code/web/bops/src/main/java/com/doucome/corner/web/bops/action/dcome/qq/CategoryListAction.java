package com.doucome.corner.web.bops.action.dcome.qq;

import org.springframework.beans.factory.annotation.Autowired;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcCategorySearchCondition;
import com.doucome.corner.biz.dcome.enums.DcCategoryLevelEnums;
import com.doucome.corner.biz.dcome.model.DcCategoryDTO;
import com.doucome.corner.biz.dcome.service.DcCategoryService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 类目列表
 * @author langben 2012-7-25
 *
 */
@SuppressWarnings("serial")
public class CategoryListAction extends BopsBasicAction implements ModelDriven<DcCategorySearchCondition>{

	private DcCategorySearchCondition condition = new DcCategorySearchCondition() ;
	
	@Autowired
	private DcCategoryService dcCategoryService ;
	
	private int page ;
	
	private QueryResult<DcCategoryDTO> categoryQuery ;
	

	@Override
	public String execute() throws Exception {
		if(StringUtils.isBlank(condition.getCategoryLevel())){
			condition.setCategoryLevel(DcCategoryLevelEnums.CATGORY.getValue()) ;
		}
		categoryQuery = dcCategoryService.getCategoriesWithPagination(condition, new Pagination(page)) ;
		
		return SUCCESS ;
	}
	
	@Override
	public DcCategorySearchCondition getModel() {
		return condition ;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public QueryResult<DcCategoryDTO> getCategoryQuery() {
		return categoryQuery;
	}


}
