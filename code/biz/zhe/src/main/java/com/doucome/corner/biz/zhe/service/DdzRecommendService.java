package com.doucome.corner.biz.zhe.service;

import java.util.List;

import com.doucome.corner.biz.zhe.model.TaobaokeReportFacade;
import com.doucome.corner.biz.zhe.model.TaobaokeShopFacade;

/**
 * 推荐
 * @author shenjia.caosj 2012-5-22
 *
 */
public interface DdzRecommendService {
	
	/**
	 * 首页品牌
	 * @param shopIdList
	 * @param outCode
	 * @return
	 */
	List<TaobaokeShopFacade> getIndexBrands() ;

	
	/**
	 * 别人正在买
	 * @param count
	 * @return
	 */
	List<TaobaokeReportFacade> getBuyingItems(int count) ;
	
}
