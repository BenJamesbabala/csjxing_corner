package com.doucome.corner.biz.zhe.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.exception.TaobaoRemoteException;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.service.taobao.TaobaoRecommandDecorator;
import com.doucome.corner.biz.core.taobao.dto.TaobaoItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaoPromotionDisplayDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaoPromotionInItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.zhe.condition.EatDiscountCondition;
import com.doucome.corner.biz.zhe.model.TaobaokeItemFacade;
import com.doucome.corner.biz.zhe.rule.DdzEatDiscountRule;
import com.doucome.corner.biz.zhe.service.DdzEatDiscountService;


public class DdzEatDiscountServiceImpl implements DdzEatDiscountService {
		
	private static final Log log = LogFactory.getLog(DdzEatDiscountServiceImpl.class) ;
	
	@Autowired
	private TaobaoRecommandDecorator taobaoRecommandDecorator ;
	
	@Autowired
	private DdzEatDiscountRule ddzEatDiscountRule ;

	@Override
	public TaobaokeItemFacade eatDiscount(TaobaokeItemDTO item  , EatDiscountCondition condition) {
		if(item == null){
			return null ;
		}
		
		TaobaokeItemFacade newItem = new TaobaokeItemFacade(item) ;
		
		//查促销
		if(condition.isNeedPromotionPrice()){
			BigDecimal promotionPrice = getPromotionPrice(newItem.getNumIid()) ;
			newItem.setPromotionPrice(promotionPrice) ;
		}
		
		newItem.calcFacadeCommissions(ddzEatDiscountRule) ;
		return newItem ;
	}
	
	@Override
	public TaobaokeItemFacade eatDiscount(TaobaoItemDTO item, EatDiscountCondition condition) {
		if(item == null){
			return null ;
		}
		
		TaobaokeItemFacade newItem = new TaobaokeItemFacade(item) ;
		
		//查促销
		if(condition.isNeedPromotionPrice()){
			BigDecimal promotionPrice = getPromotionPrice(newItem.getNumIid()) ;
			newItem.setPromotionPrice(promotionPrice) ;
		}
		newItem.calcFacadeCommissions(ddzEatDiscountRule) ;
		return newItem ;
	}
	
	private BigDecimal getPromotionPrice(Long numIid){
		BigDecimal promotionPrice = null ;
		try{
			TaobaoPromotionDisplayDTO promotionDTO = taobaoRecommandDecorator.getPromotionUmp(numIid, null) ;
			if(promotionDTO != null && promotionDTO.getPromotionInItem() != null){
				TaobaoPromotionInItemDTO promotionInItem = promotionDTO.getPromotionInItem() ;
				if(promotionInItem != null){
					promotionPrice = promotionInItem.getItemPromoPrice() ;
				}
				
			}
		}catch(TaobaoRemoteException e){
			log.error(e.getMessage() , e) ;
		}
		return promotionPrice ;
	}
	
	@Override
	public QueryResult<TaobaokeItemFacade> batchEatDiscount(QueryResult<TaobaokeItemDTO> items ,  EatDiscountCondition condition) {
		if(items == null){
			return null ;
		}
		List<TaobaokeItemFacade> facadeList = new ArrayList<TaobaokeItemFacade>() ;
		if(CollectionUtils.isNotEmpty(items.getItems())){
			for(TaobaokeItemDTO i : items.getItems()){
				if(i == null){
					continue ;
				}
				TaobaokeItemFacade f = eatDiscount(i , condition) ;
				facadeList.add(f) ;
			}
		}
		return new QueryResult<TaobaokeItemFacade>(facadeList , items.getPagination() , items.getTotalRecords()) ;
	}

	



}
