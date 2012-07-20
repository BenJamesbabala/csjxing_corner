package com.doucome.corner.biz.zhe.service;

import java.util.List;

import com.doucome.corner.biz.zhe.model.TaobaokeItemFacade;
import com.doucome.corner.biz.zhe.model.TaobaokeShopFacade;

/**
 * 品牌
 * @author shenjia.caosj 2012-6-28
 *
 */
public interface DdzTaobaokeService {

	/**
	 * 查询店铺
	 * @param shopIds
	 * @param outCode
	 * @return
	 */
	List<TaobaokeShopFacade> conventShops(List<String> shopIdList , String outCode) ;
	
	/**
	 * 转换店铺
	 * @param ShopId
	 * @param outCode
	 * @return
	 */
	TaobaokeShopFacade conventShop(String ShopId , String outCode) ;
	
	/**
	 * 转换商品
	 * @param itemId
	 * @param outCode
	 * @return
	 */
	TaobaokeItemFacade conventItem(String itemId , String outCode);
	
	/**
	 * 查询淘宝商品
	 * @return
	 */
	TaobaokeItemFacade getTaobaoItem(String itemId) ;
	
	
	/**
	 * 批量转换推荐商品
	 * @param favList
	 * @return
	 */
	List<TaobaokeItemFacade> getFavoriteItems(String itemId , int count) ;
	
}
