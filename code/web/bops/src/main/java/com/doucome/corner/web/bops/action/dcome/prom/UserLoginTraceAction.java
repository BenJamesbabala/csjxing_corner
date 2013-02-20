package com.doucome.corner.web.bops.action.dcome.prom;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcUserLoginTraceCondition;
import com.doucome.corner.biz.dcome.model.DcUserLoginTraceDTO;
import com.doucome.corner.biz.dcome.service.DcUserLoginTraceService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * @author langben 2012-9-12
 *
 */
@SuppressWarnings("serial")
public class UserLoginTraceAction extends BopsBasicAction implements ModelDriven<DcUserLoginTraceCondition>{

	private DcUserLoginTraceCondition condition = new DcUserLoginTraceCondition() ;
	
	@Autowired
	private DcUserLoginTraceService dcUserLoginTraceService ;
	
	private QueryResult<DcUserLoginTraceDTO> traceQuery ; 
	
	private int page ;
	
	@Override
	public String execute() throws Exception {
		
		traceQuery = dcUserLoginTraceService.getTraceWithPagination(condition, new Pagination(page)) ;
		
		return super.execute();
	}
	
	@Override
	public DcUserLoginTraceCondition getModel() {
		return condition ;
	}

	public QueryResult<DcUserLoginTraceDTO> getTraceQuery() {
		return traceQuery;
	}

	public void setCondition(DcUserLoginTraceCondition condition) {
		this.condition = condition;
	}

	public void setPage(int page) {
		this.page = page;
	}

	
}
