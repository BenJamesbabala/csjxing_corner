package com.doucome.corner.web.bops.action.dcome.qq;

import org.springframework.beans.factory.annotation.Autowired;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.utils.DateUtils;
import com.doucome.corner.biz.dal.condition.dcome.DcItemSearchCondition;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 商品列表
 * @author langben 2012-7-24
 *
 */
@SuppressWarnings("serial")
public class DcItemListAction extends BopsBasicAction implements ModelDriven<DcItemSearchCondition> {

	private DcItemSearchCondition condition =  new DcItemSearchCondition() ;
	
	private QueryResult<DcItemDTO> itemQuery ;
	
	private int page = 1;
	
	@Autowired
	private DcItemService dcItemService ;
	
	@Override
	public String execute() throws Exception {
		if(StringUtils.isBlank(condition.getOrder())){
			condition.setOrder("recomm_type--desc") ;
		}
		condition.setGmtModifiedStart(DateUtils.setTime(condition.getGmtModifiedStart(), 0 , 0 , 0)) ;
		condition.setGmtModifiedEnd(DateUtils.setTime(condition.getGmtModifiedEnd(), 23 , 59 , 59)) ;
		condition.setGmtCreateStart(DateUtils.setTime(condition.getGmtCreateStart(), 0, 0, 0)) ;
		condition.setGmtCreateEnd(DateUtils.setTime(condition.getGmtCreateEnd(), 23 , 59 , 59)) ;
		itemQuery = dcItemService.getItemsWithPagination(condition, new Pagination(page)) ;
		
		return SUCCESS ;
	}
	
	@Override
	public DcItemSearchCondition getModel() {
		return condition ;
	}
	
	public DcItemSearchCondition getCondition() {
		return condition;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public QueryResult<DcItemDTO> getItemQuery() {
		return itemQuery;
	}

}
