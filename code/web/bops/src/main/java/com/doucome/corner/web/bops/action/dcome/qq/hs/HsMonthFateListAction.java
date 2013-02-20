package com.doucome.corner.web.bops.action.dcome.qq.hs;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.hs.HsMonthFateCondition;
import com.doucome.corner.biz.dal.condition.dcome.hs.HsWeekFateCondition;
import com.doucome.corner.biz.dcome.model.star.HsMonthFateDTO;
import com.doucome.corner.biz.dcome.model.star.HsWeekFateDTO;
import com.doucome.corner.biz.dcome.service.horoscope.HsMonthFateService;
import com.doucome.corner.biz.dcome.service.horoscope.HsWeekFateService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 月运势列表
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class HsMonthFateListAction extends BopsBasicAction implements ModelDriven<HsMonthFateCondition> {

	private HsMonthFateCondition condition =  new HsMonthFateCondition() ;
	
	private QueryResult<HsMonthFateDTO> hsFateQuery ;
	
	private int page = 1;
	
	@Autowired
	private HsMonthFateService hsMonthFateService;
	
	@Override
	public String execute() throws Exception {
		
		hsFateQuery = hsMonthFateService.getHsFatePage(condition, new Pagination(page)) ;
		
		return SUCCESS ;
	}
	
	@Override
	public HsMonthFateCondition getModel() {
		return condition ;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public QueryResult<HsMonthFateDTO> getHsFateQuery() {
		return hsFateQuery;
	}

}
