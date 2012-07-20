package com.doucome.corner.biz.zhe.cache;

import java.util.List;

import com.doucome.corner.biz.zhe.model.TaobaokeReportFacade;

public interface BuyingRecommendItemCache {

	void setCache(List<TaobaokeReportFacade> items) ;
	
	List<TaobaokeReportFacade> getItems() ;
	
	void clear() ;
}
