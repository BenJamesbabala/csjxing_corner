package com.doucome.corner.web.bops.action.zhe;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.DdzTaokeReportSettleSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ÌÔ±¦¿Í±¨±í
 * @author shenjia.caosj 2012-4-17
 *
 */
@SuppressWarnings("serial")
public class TaokeReportSettleAction extends BopsBasicAction implements ModelDriven<DdzTaokeReportSettleSearchCondition>{
	
	private int page = 1;
	
	private int size = 20 ;
	
	private DdzTaokeReportSettleSearchCondition  condition = new DdzTaokeReportSettleSearchCondition() ;

	private QueryResult<DdzTaokeReportSettleDO> settleResult ;
	
	@Autowired
	private DdzTaokeReportService ddzTaokeReportService ;
	
	@Override
	public String execute() throws Exception {
		
		Pagination pagination = new Pagination(page , size);
		
		settleResult = ddzTaokeReportService.getSettlesWithPagination(condition, pagination) ;
		
		page = settleResult.getPagination().getPage() ;
		
		return SUCCESS ;
	}

	@Override
	public DdzTaokeReportSettleSearchCondition getModel() {
		return condition ;
	}

	/**
	 * --------------------------------------------------------------------------
	 */
	public int getPage() {
		return page;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setPage(int page) {
		this.page = page;
	}

	

	public DdzTaokeReportSettleSearchCondition getCondition() {
		return condition;
	}

	public void setCondition(DdzTaokeReportSettleSearchCondition condition) {
		this.condition = condition;
	}

	public QueryResult<DdzTaokeReportSettleDO> getSettleResult() {
		return settleResult;
	}
	
	
}
