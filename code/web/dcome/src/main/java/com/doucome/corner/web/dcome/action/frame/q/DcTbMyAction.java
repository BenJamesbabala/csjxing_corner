package com.doucome.corner.web.dcome.action.frame.q;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcUserIntegralRecordSearchCondition;
import com.doucome.corner.biz.dcome.model.DcUserIntegralRecordDTO;
import com.doucome.corner.biz.dcome.service.DcUserIntegralRecordService;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 用户中心
 * @author langben 2013-1-9
 *
 */
@SuppressWarnings("serial")
public class DcTbMyAction extends QBasicAction implements ModelDriven<DcUserIntegralRecordSearchCondition> {

	@Autowired
	private DcUserIntegralRecordService dcUserIntegralRecordService ;
	
	private int page ;
	
	private QueryResult<DcUserIntegralRecordDTO> queryResult ; 
	
	private DcUserIntegralRecordSearchCondition condition = new DcUserIntegralRecordSearchCondition() ;
	
	@Override
	public String execute() throws Exception {
		
		Long userId = getUserId() ;
		
		condition.setUserId(userId) ;
		
		queryResult = dcUserIntegralRecordService.getRecordsWithPagination(condition, new Pagination(page,10)) ;
		
		return SUCCESS ;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	@Override
	public DcUserIntegralRecordSearchCondition getModel() {
		return condition ;
	}

	public QueryResult<DcUserIntegralRecordDTO> getQueryResult() {
		return queryResult;
	}
	
}
