package com.doucome.corner.biz.core.service.taobao;

import java.util.List;

import com.doucome.corner.biz.core.exception.TaobaoRemoteException;
import com.doucome.corner.biz.core.taobao.dto.TaobaoFavoriteItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaoPromotionDisplayDTO;
import com.doucome.corner.biz.core.taobao.model.TaobaoRecommendItemCondition;

/**
 * 淘宝关联推荐
 * @author langben 2012-3-22
 *
 */
public interface TaobaoRecommandDecorator {

	/**
	 * 获得推荐商品
	 * @param condition
	 * @return
	 * @throws TaobaoRemoteException
	 */
	List<TaobaoFavoriteItemDTO> getRecommandItemsByItem(Long itemId , TaobaoRecommendItemCondition condition) throws TaobaoRemoteException ;
	
	/**
	 * 商品优惠详情查询 
	 * @param itemId 商品id 
	 * @param channelKey 渠道来源，第三方站点 可选 
	 * @return
	 */
	TaobaoPromotionDisplayDTO getPromotionUmp(Long itemId ,String channelKey) ;
	
	/**
	 * 获得推荐商品
	 * @param categoryId
	 * @param condition
	 * @return
	 */
	List<TaobaoFavoriteItemDTO> getRecommendItemsByCategory(Long categoryId , TaobaoRecommendItemCondition condition) ;
	
}
