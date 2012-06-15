package com.doucome.corner.biz.zhe.cache;

import java.util.List;

import com.doucome.corner.biz.core.cache.AbstractCacheSupport.InternalStoreItem;
import com.doucome.corner.biz.dal.dataobject.DdzRecommendDO;

public interface BuyingRecommendItemCache {

	void setCache(List<DdzRecommendDO> items) ;
	
	List<DdzRecommendDO> getItems() ;
	
	InternalStoreItem<List<DdzRecommendDO>> getInternalItems() ;
	
	void clear() ;
}
