package com.doucome.corner.web.bops.action.dcome.qq;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcUserIntegralRecordSearchCondition;
import com.doucome.corner.biz.dcome.model.DcUserIntegralRecordDTO;
import com.doucome.corner.biz.dcome.service.DcUserIntegralRecordService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ¶Ò»»¼ÇÂ¼
 * @author langben 2013-1-13
 *
 */
@SuppressWarnings("serial")
public class UserIntegralRecordListAction  extends BopsBasicAction implements ModelDriven<DcUserIntegralRecordSearchCondition>{

	private DcUserIntegralRecordSearchCondition condition = new DcUserIntegralRecordSearchCondition() ;
	
	@Autowired
	private DcUserIntegralRecordService dcUserIntegralRecordService ;
	
	private int page ;
	
	private QueryResult<DcUserIntegralRecordDTO> queryResult ;

	@Override
	public String execute() throws Exception {
		
		queryResult = dcUserIntegralRecordService.getRecordsWithPagination(condition, new Pagination(page)) ;
		
		return SUCCESS ;
	}
	
	@Override
	public DcUserIntegralRecordSearchCondition getModel() {
		return condition ;
	}

	public QueryResult<DcUserIntegralRecordDTO> getQueryResult() {
		return queryResult;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
