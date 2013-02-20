package com.doucome.corner.web.dcome.action.frame.q;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionAwardCondition;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionItemSearchCondition;
import com.doucome.corner.biz.dcome.enums.DcPromotionAwardReviewStatusEnums;
import com.doucome.corner.biz.dcome.enums.DcPromotionTypeEnum;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.model.DcPromotionAwardDTO;
import com.doucome.corner.biz.dcome.model.DcPromotionDTO;
import com.doucome.corner.biz.dcome.model.DcPromotionItemDTO;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.biz.dcome.service.DcPromotionAwardService;

/**
 * QQ 空间首页
 * @author langben 2012-7-21
 *
 */
@SuppressWarnings("serial")
public class IndexAction extends QBasicAction {
	
	private DcPromotionDTO promotion ;
	
	private Map<Long,DcItemDTO> itemMap;
	
	/**
	 * 历史中奖
	 */
	private List<DcPromotionAwardDTO> awardHisList;
		
	@Autowired
	private DcItemService dcItemService ;
	
	@Autowired
	private DcPromotionAwardService dcPromotionAwardService ;
	
	@Override
	public String execute() throws Exception {
		
		promotion = getPromotion() ;
		
		if(promotion != null) {
			
			Long promotionId = promotion.getId() ; 
			
			//活动商品
			DcPromotionItemSearchCondition promotionCondition = new DcPromotionItemSearchCondition();
			promotionCondition.setPromotionId(promotionId);
			
			//历史获奖
			DcPromotionAwardCondition awardCondition = new DcPromotionAwardCondition() ;
			awardCondition.setPromotionType(DcPromotionTypeEnum.PK.getType()) ;
			awardCondition.setReviewStatus(DcPromotionAwardReviewStatusEnums.SUCCESS.getStatus());
			awardHisList = dcPromotionAwardService.getAwardsNoPagination(awardCondition, new Pagination(1 , 15));
		}
		
		//取出所有ID的商品
		List<Long> idList = mergeIds(null , awardHisList ) ;
		
		DcPromotionItemDTO myPromotionItem = getMyPromotionItem() ;
		if(myPromotionItem != null){
			idList.add(myPromotionItem.getItemId()) ;
		}
		
		List<DcItemDTO> itemList = dcItemService.getItemsByIds(idList) ;
		itemMap = new HashMap<Long,DcItemDTO>() ;
		for(DcItemDTO dto : itemList) {
			itemMap.put(dto.getId(), dto) ;
		}
		
		return SUCCESS ;
	}

	private List<Long> mergeIds(List<Long> pgcList ,List<DcPromotionAwardDTO> hisAwardList ,List<DcPromotionItemDTO>... allPromotionItemList) {
		List<Long> mergeIdList = new ArrayList<Long>() ;
		
		if(CollectionUtils.isNotEmpty(pgcList)){
			mergeIdList.addAll(pgcList) ;
		}
		
		for(List<DcPromotionItemDTO> promotionItemList : allPromotionItemList) {
			if(CollectionUtils.isNotEmpty(promotionItemList)){
				for(DcPromotionItemDTO pi : promotionItemList){
					mergeIdList.add(pi.getItemId()) ; 
				}
			}
		}
		
		if(CollectionUtils.isNotEmpty(hisAwardList)){
			for(DcPromotionAwardDTO pa : hisAwardList){
				mergeIdList.add(pa.getItemId()) ; 
			}
		}
		
		return mergeIdList ;
	}
	
	public Map<Long, DcItemDTO> getItemMap() {
		return itemMap;
	}

	public List<DcPromotionAwardDTO> getAwardHisList() {
		return awardHisList;
	}
}
