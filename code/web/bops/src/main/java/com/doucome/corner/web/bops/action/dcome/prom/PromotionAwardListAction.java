package com.doucome.corner.web.bops.action.dcome.prom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionAwardCondition;
import com.doucome.corner.biz.dcome.business.DcItemBO;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.model.DcPromotionAwardDTO;
import com.doucome.corner.biz.dcome.service.DcPromotionAwardService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 活动中奖记录
 * @author langben 2012-10-17
 *
 */
public class PromotionAwardListAction extends BopsBasicAction  implements ModelDriven<DcPromotionAwardCondition>{

	private DcPromotionAwardCondition condition = new DcPromotionAwardCondition() ;
	
	@Autowired
	private DcPromotionAwardService dcPromotionAwardService ;
	
	@Autowired
	private DcItemBO dcItemBO ;
	
	private QueryResult<DcPromotionAwardDTO> awardQuery ;
	
	private Map<Long , DcItemDTO> itemMap ;
	
	private int page ;
	
	@Override
	public String execute() throws Exception {
		this.awardQuery = dcPromotionAwardService.getAwardsWithPagination(condition, new Pagination(page)) ;
		
		List<Long> idList = getIds(awardQuery.getItems()) ;
		
		this.itemMap = dcItemBO.getItemsMapByIds(idList) ;
		
		return SUCCESS ;
	}
	
	private List<Long> getIds(List<DcPromotionAwardDTO> awardList){
		List<Long> idList = new ArrayList<Long>() ;
		
		if(CollectionUtils.isNotEmpty(awardList)){
			for(DcPromotionAwardDTO a : awardList){
				idList.add(a.getItemId()) ;
			}
		}
		
		return idList ;
	}
	
	@Override
	public DcPromotionAwardCondition getModel() {
		return condition ;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public QueryResult<DcPromotionAwardDTO> getAwardQuery() {
		return awardQuery;
	}

	public Map<Long, DcItemDTO> getItemMap() {
		return itemMap;
	}

}
