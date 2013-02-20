package com.doucome.corner.web.bops.action.dcome.prom;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionCondition;
import com.doucome.corner.biz.dcome.model.DcPromotionDTO;
import com.doucome.corner.biz.dcome.service.DcPromotionService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 活动列表
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class PromotionListAction extends BopsBasicAction implements ModelDriven<DcPromotionCondition> {
	
	private DcPromotionCondition condition = new DcPromotionCondition();
	
	private QueryResult<DcPromotionDTO> promotions;
	
	private int page;
	@Autowired
	private DcPromotionService dcPromotionService;
	
	public String execute() {
		this.promotions = dcPromotionService.getPromotionsWithPage(condition, page);
		return SUCCESS;
	}

	@Override
	public DcPromotionCondition getModel() {
		return this.condition;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public QueryResult<DcPromotionDTO> getPromotions() {
		return this.promotions;
	}
}
