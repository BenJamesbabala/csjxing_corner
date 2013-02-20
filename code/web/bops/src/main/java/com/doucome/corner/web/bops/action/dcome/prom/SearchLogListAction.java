package com.doucome.corner.web.bops.action.dcome.prom;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.utils.DateUtils;
import com.doucome.corner.biz.dal.condition.dcome.DcSearchLogCondition;
import com.doucome.corner.biz.dcome.model.DcSearchLogDTO;
import com.doucome.corner.biz.dcome.service.DcSearchLogService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * @author ze2200
 *
 */
public class SearchLogListAction extends BopsBasicAction implements ModelDriven<DcSearchLogCondition> {
	
	private static final long serialVersionUID = 1L;

	private DcSearchLogCondition condition = new DcSearchLogCondition();

	private int page;
	
	private QueryResult<DcSearchLogDTO> searchLogs;
	
	@Autowired
	private DcSearchLogService dcSearchLogService;
	@Override
	public String execute() throws Exception {
		page = page <= 0? 1: page;
		if (condition.getGmtStart() == null && condition.getGmtEnd() == null) {
			Date[] dates = DateUtils.getDayStartEnd(new Date());
			condition.setGmtStart(dates[0]);
			Calendar temp = Calendar.getInstance();
			temp.setTime(dates[0]);
			temp.add(Calendar.DAY_OF_YEAR, 1);
			condition.setGmtEnd(temp.getTime());
		}
		searchLogs = dcSearchLogService.querySearchLogsPage(condition, new Pagination(page));
		return SUCCESS;
	}

	@Override
	public DcSearchLogCondition getModel() {
		return condition;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public QueryResult<DcSearchLogDTO> getSearchLogs() {
		return searchLogs;
	}

}
