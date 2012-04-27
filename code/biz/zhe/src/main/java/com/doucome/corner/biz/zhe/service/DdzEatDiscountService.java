package com.doucome.corner.biz.zhe.service;

import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.taobao.dto.TaobaoItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.zhe.condition.EatDiscountCondition;
import com.doucome.corner.biz.zhe.model.TaobaokeItemFacade;

/**
 * 显示给会员的钱会吃掉一部分折扣
 * @author shenjia.caosj 2012-3-8
 *
 */
public interface DdzEatDiscountService {

	/**
	 * 转换折扣
	 * @param item
	 * @return
	 */
	TaobaokeItemFacade eatDiscount(TaobaokeItemDTO item , EatDiscountCondition condition) ;
	
	/**
	 * 转换折扣
	 * @param item
	 * @return
	 */
	TaobaokeItemFacade eatDiscount(TaobaoItemDTO item , EatDiscountCondition condition) ;
	
	//TaobaokeItemFacade eatDiscount(TaobaokeR)
	
	/**
	 * 批量转换折扣
	 * @param items
	 * @return
	 */
	QueryResult<TaobaokeItemFacade> batchEatDiscount(QueryResult<TaobaokeItemDTO> items , EatDiscountCondition condition) ;
	
	
}
