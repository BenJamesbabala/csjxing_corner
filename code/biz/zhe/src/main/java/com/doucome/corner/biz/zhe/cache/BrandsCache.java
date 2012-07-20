package com.doucome.corner.biz.zhe.cache;

import java.util.List;

import com.doucome.corner.biz.zhe.model.TaobaokeShopFacade;

/**
 * Ê×Ò³Æ·ÅÆ»º´æ
 * @author shenjia.caosj 2012-6-28
 *
 */
public interface BrandsCache {

	List<TaobaokeShopFacade> getItems() ;
	
	void setCache(List<TaobaokeShopFacade> items) ;
	
	void clear() ;
}
