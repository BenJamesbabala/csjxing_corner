package com.doucome.corner.biz.zhe.service;

import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.taobao.dto.TaobaoItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.zhe.condition.EatDiscountCondition;
import com.doucome.corner.biz.zhe.model.TaobaokeItemFacade;

/**
 * ��ʾ����Ա��Ǯ��Ե�һ�����ۿ�
 * @author shenjia.caosj 2012-3-8
 *
 */
public interface DdzEatDiscountService {

	/**
	 * ת���ۿ�
	 * @param item
	 * @return
	 */
	TaobaokeItemFacade eatDiscount(TaobaokeItemDTO item , EatDiscountCondition condition) ;
	
	/**
	 * ת���ۿ�
	 * @param item
	 * @return
	 */
	TaobaokeItemFacade eatDiscount(TaobaoItemDTO item , EatDiscountCondition condition) ;
	
	//TaobaokeItemFacade eatDiscount(TaobaokeR)
	
	/**
	 * ����ת���ۿ�
	 * @param items
	 * @return
	 */
	QueryResult<TaobaokeItemFacade> batchEatDiscount(QueryResult<TaobaokeItemDTO> items , EatDiscountCondition condition) ;
	
	
}
