package com.doucome.corner.biz.zhe.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.doucome.corner.biz.core.service.taobao.TaobaokeServiceDecorator;
import com.doucome.corner.biz.core.taobao.dto.TaobaoFavoriteItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.core.taobao.fields.TaobaokeFields;
import com.doucome.corner.biz.core.utils.NumberUtils;
import com.doucome.corner.biz.zhe.condition.EatDiscountCondition;
import com.doucome.corner.biz.zhe.model.TaobaokeItemFacade;
import com.doucome.corner.biz.zhe.rule.DdzEatDiscountRule;
import com.doucome.corner.biz.zhe.service.DdzEatDiscountService;
import com.doucome.corner.biz.zhe.service.DdzTaobaoRecommendService;

public class DdzTaobaoRecommendServiceImpl implements DdzTaobaoRecommendService{

	private static final BigDecimal ZERO = new BigDecimal("0.00") ;
	
	@Autowired
	private TaobaokeServiceDecorator taobaokeServiceDecorator ;
	
	@Autowired
	private DdzEatDiscountService    ddzEatDiscountService;
	
	@Autowired
	private DdzEatDiscountRule ddzEatDiscountRule ;
	
	@Override
	public List<TaobaokeItemFacade> batchConventer(List<TaobaoFavoriteItemDTO> favoList , String outCode) {
		if(CollectionUtils.isEmpty(favoList)){
			return null ;
		}
		String[] itemIds = new String[favoList.size()] ; 
		Map<String, BigDecimal> promotionPriceMapping = new HashMap<String,BigDecimal>() ;
		for(int i = 0  ;i < favoList.size() ;i ++){
			String itemId = String.valueOf(favoList.get(i) .getItemId()) ; 
			itemIds[i] = itemId ;
			promotionPriceMapping.put(itemId, favoList.get(i) .getPromotionPrice()) ;
		}
		
		List<TaobaokeItemDTO> items = taobaokeServiceDecorator.conventItems(itemIds, outCode, TaobaokeFields.taoke_item_fields ) ;
		
		if(CollectionUtils.isEmpty(items)){
			return new ArrayList<TaobaokeItemFacade>() ;
		}
		EatDiscountCondition condition = new EatDiscountCondition() ;
		condition.setNeedPromotionPrice(false) ;
		List<TaobaokeItemFacade> returnList = new ArrayList<TaobaokeItemFacade>() ;
		for(TaobaokeItemDTO item : items){
			//
			TaobaokeItemFacade facade = ddzEatDiscountService.eatDiscount(item, condition) ;
			if(facade == null){
				continue ;
			}
			//ц╩спуш©ш
			if(NumberUtils.isLessEqual(facade.getCommissionRate(), ZERO)) {
				continue ;
			}
			facade.setPromotionPrice(promotionPriceMapping.get(item.getNumIid())) ;
			facade.calcFacadeCommissions(ddzEatDiscountRule) ;
			returnList.add(facade) ;
		}
		
		
		return returnList ;
	}
	
}
