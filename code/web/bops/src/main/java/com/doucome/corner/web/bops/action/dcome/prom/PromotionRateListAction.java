package com.doucome.corner.web.bops.action.dcome.prom;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionRateDetailCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionRateDetailDO;
import com.doucome.corner.biz.dcome.service.DcPromotionRateDetailService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class PromotionRateListAction extends BopsBasicAction implements ModelDriven<DcPromotionRateDetailCondition> {
	
	private DcPromotionRateDetailCondition condition = new DcPromotionRateDetailCondition() ;
	
	@Autowired
	private DcPromotionRateDetailService dcPromotionRateDetailService ;

	private int page ;
	
	private QueryResult<DcPromotionRateDetailDO> rateQuery ;
	
	@Override
	public String execute() throws Exception {
		
		rateQuery = dcPromotionRateDetailService.getRateDetailsWithPagination(condition, new Pagination(page)) ;
		
		return SUCCESS ;
	}
	
	@Override
	public DcPromotionRateDetailCondition getModel() {
		return condition ;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public QueryResult<DcPromotionRateDetailDO> getRateQuery() {
		return rateQuery;
	}

}
