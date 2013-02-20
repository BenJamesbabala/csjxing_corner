package com.doucome.corner.web.bops.action.dcome.qq.hs;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.hs.HsDailyFateCondition;
import com.doucome.corner.biz.dcome.model.star.HsDailyFateDTO;
import com.doucome.corner.biz.dcome.service.horoscope.HsDailyFateService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 日运势列表
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class HsDailyFateListAction extends BopsBasicAction implements ModelDriven<HsDailyFateCondition> {

	private HsDailyFateCondition condition =  new HsDailyFateCondition() ;
	
	private QueryResult<HsDailyFateDTO> hsFateQuery ;
	
	private int page = 1;
	
	@Autowired
	private HsDailyFateService hsDailyFateService ;
	
	@Override
	public String execute() throws Exception {
		
		hsFateQuery = hsDailyFateService.getHsFatePage(condition, new Pagination(page)) ;
		
		return SUCCESS ;
	}
	
	@Override
	public HsDailyFateCondition getModel() {
		return condition ;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public QueryResult<HsDailyFateDTO> getHsFateQuery() {
		return hsFateQuery;
	}

}
