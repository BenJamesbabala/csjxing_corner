package com.doucome.corner.web.bops.action.dcome.qq.hs;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.hs.HsWeekFateCondition;
import com.doucome.corner.biz.dcome.model.star.HsWeekFateDTO;
import com.doucome.corner.biz.dcome.service.horoscope.HsWeekFateService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 周运势列表
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class HsWeekFateListAction extends BopsBasicAction implements ModelDriven<HsWeekFateCondition> {

	private HsWeekFateCondition condition =  new HsWeekFateCondition() ;
	
	private QueryResult<HsWeekFateDTO> hsFateQuery ;
	
	private int page = 1;
	
	@Autowired
	private HsWeekFateService hsWeekFateService;
	
	@Override
	public String execute() throws Exception {
		
		hsFateQuery = hsWeekFateService.getHsFatePage(condition, new Pagination(page)) ;
		
		return SUCCESS ;
	}
	
	@Override
	public HsWeekFateCondition getModel() {
		return condition ;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public QueryResult<HsWeekFateDTO> getHsFateQuery() {
		return hsFateQuery;
	}

}
