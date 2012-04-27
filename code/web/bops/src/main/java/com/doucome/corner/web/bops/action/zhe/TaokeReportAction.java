package com.doucome.corner.web.bops.action.zhe;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.taobao.model.TaokeReportSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ÌÔ±¦¿Í±¨±í
 * @author shenjia.caosj 2012-4-17
 *
 */
@SuppressWarnings("serial")
public class TaokeReportAction extends BopsBasicAction implements ModelDriven<TaokeReportSearchCondition>{
	
	private int page = 1;
	
	private int size = 20 ;
	
	private TaokeReportSearchCondition  condition = new TaokeReportSearchCondition() ;

	private QueryResult<DdzTaokeReportDO> reportItems ;
	
	@Autowired
	private DdzTaokeReportService ddzTaokeReportService ;
	
	@Override
	public String execute() throws Exception {
		
		Pagination pagination = new Pagination(page , size);
		
		reportItems = ddzTaokeReportService.getReportsWithPagination(condition, pagination) ;
		
		page = reportItems.getPagination().getPage() ;
		
		return SUCCESS ;
	}

	@Override
	public TaokeReportSearchCondition getModel() {
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

	public TaokeReportSearchCondition getCondition() {
		return condition;
	}

	public void setCondition(TaokeReportSearchCondition condition) {
		this.condition = condition;
	}

	public QueryResult<DdzTaokeReportDO> getReportItems() {
		return reportItems;
	}
	
	
}
