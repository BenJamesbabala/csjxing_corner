package com.doucome.corner.web.bops.action.dcome.qq.hs;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.hs.HsTopicCondition;
import com.doucome.corner.biz.dcome.model.star.HsTopicDTO;
import com.doucome.corner.biz.dcome.service.horoscope.HsTopicService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 星话题列表
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class HsTopicListAction extends BopsBasicAction implements ModelDriven<HsTopicCondition> {

	private HsTopicCondition condition =  new HsTopicCondition() ;
	
	private QueryResult<HsTopicDTO> hsTopicQuery ;
	
	private int page = 1;
	
	@Autowired
	private HsTopicService hsTopicService;
	
	@Override
	public String execute() throws Exception {
		hsTopicQuery = hsTopicService.getHsTopicsPage(condition, new Pagination(page));
		return SUCCESS ;
	}
	
	@Override
	public HsTopicCondition getModel() {
		return condition ;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public QueryResult<HsTopicDTO> getHsTopicQuery() {
		return hsTopicQuery;
	}

}
