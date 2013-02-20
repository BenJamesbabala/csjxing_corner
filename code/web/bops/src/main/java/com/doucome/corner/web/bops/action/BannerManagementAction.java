package com.doucome.corner.web.bops.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.service.BannerConfigService;
import com.doucome.corner.biz.dal.condition.BannerConfigSearchCondition;
import com.doucome.corner.biz.dal.dataobject.BannerConfigDO;
import com.opensymphony.xwork2.ModelDriven;

/**
 * bannerÎ¬»¤
 * @author langben 2012-10-30
 *
 */
@SuppressWarnings("serial")
public class BannerManagementAction extends BopsBasicAction implements ModelDriven<BannerConfigSearchCondition>{

	private BannerConfigSearchCondition condition = new BannerConfigSearchCondition() ;
	
	private QueryResult<BannerConfigDO> queryResult ;
	
	private int page ;
	
	@Autowired
	private BannerConfigService bannerConfigService ;
	
	@Override
	public String execute() throws Exception {
		
		queryResult = bannerConfigService.getConfigsWithPagination(condition, new Pagination(page , 20)) ;
		
		return SUCCESS ;
	}

	@Override
	public BannerConfigSearchCondition getModel() {
		return condition ;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public QueryResult<BannerConfigDO> getQueryResult() {
		return queryResult;
	}

	
}
