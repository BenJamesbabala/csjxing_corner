package com.doucome.corner.web.bops.action.zhe;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.DdzJfbSettleRecordSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzJfbSettleRecordDO;
import com.doucome.corner.biz.zhe.service.DdzJfbSettleRecordService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * @author langben 2012-12-23
 *
 */
@SuppressWarnings("serial")
public class JfbSettleRecordListAction extends BopsBasicAction implements ModelDriven<DdzJfbSettleRecordSearchCondition> {

	private DdzJfbSettleRecordSearchCondition condition = new DdzJfbSettleRecordSearchCondition() ;
	
	@Autowired
	private DdzJfbSettleRecordService ddzJfbSettleRecordService ;
	
	private int page = 1;
	
	private QueryResult<DdzJfbSettleRecordDO> queryResult ;
	
	@Override
	public String execute() throws Exception {
		
		queryResult = ddzJfbSettleRecordService.getRecordsWithPagination(condition, new Pagination(page)) ;
		
		return SUCCESS ;
	}

	@Override
	public DdzJfbSettleRecordSearchCondition getModel() {
		return condition ;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public QueryResult<DdzJfbSettleRecordDO> getQueryResult() {
		return queryResult;
	}

	public int getPage() {
		return page;
	}
	
}
