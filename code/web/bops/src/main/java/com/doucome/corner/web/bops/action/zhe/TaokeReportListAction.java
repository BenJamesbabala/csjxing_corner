package com.doucome.corner.web.bops.action.zhe;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.utils.DateUtils;
import com.doucome.corner.biz.dal.condition.TaokeReportSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 淘客报表查询
 * @author langben 2012-8-13
 *
 */
@SuppressWarnings("serial")
public class TaokeReportListAction extends BopsBasicAction implements ModelDriven<TaokeReportSearchCondition>{

	private TaokeReportSearchCondition condition = new TaokeReportSearchCondition() ;
	
	@Autowired
	private DdzTaokeReportService ddzTaokeReportService ;
	
	private QueryResult<DdzTaokeReportDO> reportQuery ;
	
	private int page = 1 ;
	
	@Override
	public String execute() throws Exception {
		condition.setGmtSettledStart(DateUtils.setTime(condition.getGmtSettledStart(), 0 , 0 , 0)) ;
		condition.setGmtSettledEnd(DateUtils.setTime(condition.getGmtSettledEnd(), 23 , 59 , 59)) ;
		condition.setGmtCreateStart(DateUtils.setTime(condition.getGmtCreateStart(), 0, 0, 0)) ;
		condition.setGmtCreateEnd(DateUtils.setTime(condition.getGmtCreateEnd(), 23 , 59 , 59)) ;
		reportQuery = ddzTaokeReportService.getReportsWithPagination(condition, new Pagination(page)) ;
		
		return SUCCESS ;
	}

	@Override
	public TaokeReportSearchCondition getModel() {
		return condition ;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public QueryResult<DdzTaokeReportDO> getReportQuery() {
		return reportQuery;
	}
	
	
}
