package com.doucome.corner.web.dcome.action.frame.q;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.business.DcPromotionBO;
import com.doucome.corner.biz.dcome.model.DcPromotionDTO;
import com.doucome.corner.biz.dcome.model.DcPromotionItemDTO;
import com.doucome.corner.biz.dcome.model.DcPromotionSnapInfoDTO;

/**
 * 活动
 * @author langben 2012-11-02
 *
 */
@SuppressWarnings("serial")
public class PromotionPKAction extends QBasicAction{
	
	private String order ;

	private DcPromotionDTO promotion;
	/**
	 * 活动Item
	 */
	private List<DcPromotionItemDTO> topRankPromItems;
	
	private List<DcPromotionSnapInfoDTO> mostWishItems;
	@Autowired
	private DcPromotionBO dcPromotionBO;
	
	@Override
	public String execute() throws Exception {
		promotion = getPromotion();
		if (promotion != null) {
			topRankPromItems = dcPromotionBO.getPromotionTopRanks(promotion.getId(), 5);
			mostWishItems = dcPromotionBO.getMostWishItems(promotion.getId());
		} else {
			topRankPromItems = new ArrayList<DcPromotionItemDTO>();
			mostWishItems = new ArrayList<DcPromotionSnapInfoDTO>();
		}
		return SUCCESS ;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public List<DcPromotionItemDTO> getTopRankPromItems() {
		return topRankPromItems;
	}
	
	public List<DcPromotionSnapInfoDTO> getMostWishItems() {
		return this.mostWishItems;
	}
}
