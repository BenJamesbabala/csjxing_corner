package com.doucome.corner.biz.zhe.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.utils.DecimalUtils;
import com.doucome.corner.biz.dal.condition.TaokeReportSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.zhe.cache.BrandsCache;
import com.doucome.corner.biz.zhe.cache.BuyingRecommendItemCache;
import com.doucome.corner.biz.zhe.model.TaobaokeReportFacade;
import com.doucome.corner.biz.zhe.model.TaobaokeShopFacade;
import com.doucome.corner.biz.zhe.service.DdzRecommendService;
import com.doucome.corner.biz.zhe.service.DdzTaobaokeService;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportService;

public class DdzRecommendServiceImpl implements DdzRecommendService {
		
	@Autowired
	private DdzTaobaokeService ddzTaobaokeService ;
	
	@Autowired
	private BrandsCache brandsCache ;
	
	@Autowired
	private DdzTaokeReportService ddzTaokeReportService ;
	
	@Autowired
	private BuyingRecommendItemCache buyingRecommendItemCache ;
	
	private List<String> brandIdList ;

	public void setBrandIdList(List<String> brandIdList) {
		this.brandIdList = brandIdList;
	}

	@Override
	public List<TaobaokeShopFacade> getIndexBrands() {
		List<TaobaokeShopFacade> items = brandsCache.getItems() ;
		if(items != null){
			return items ;
		}
		items = ddzTaobaokeService.conventShops(brandIdList, null) ;
		brandsCache.setCache(items) ;
		return items ;
	}

	@Override
	public List<TaobaokeReportFacade> getBuyingItems(int count) {
		List<TaobaokeReportFacade> items = getRecentBuyingItems() ;
		int size = CollectionUtils.size(items) ;
		if(size <= count) {
			return items ;
		}
		
		Set<Integer> set = new HashSet<Integer>() ;
		int tickcount = 0 ;
		while(set.size() < count && tickcount < count*2){
			int ri = RandomUtils.nextInt(size-1) ;
			set.add(ri) ;
			tickcount ++ ;
		}
		
		List<TaobaokeReportFacade> facadeList = new ArrayList<TaobaokeReportFacade>() ; 
		
		for(Integer i : set){
			facadeList.add(items.get(i)) ;
		}
		
		return facadeList ;
	}
	
	private List<TaobaokeReportFacade> getRecentBuyingItems(){
		List<TaobaokeReportFacade> items = buyingRecommendItemCache.getItems() ;
		if(items != null){
			return items ;
		}
		items = new ArrayList<TaobaokeReportFacade>() ;
		TaokeReportSearchCondition searchCondition = new TaokeReportSearchCondition() ;  
		Calendar c = Calendar.getInstance() ;
		c.add(Calendar.DATE, -3) ;
		searchCondition.setGmtCreateStart(c.getTime()) ;
		//之前5天的成交
		QueryResult<DdzTaokeReportDO> result = ddzTaokeReportService.getReportsWithPagination(searchCondition, new Pagination(1,500)) ;
		BigDecimal jiao = new BigDecimal("0.1") ;//1角
		for(DdzTaokeReportDO r : result.getItems()){
			if(r != null && StringUtils.isNotBlank(r.getPicUrl()) && StringUtils.isNotBlank(r.getSettleAlipay())){
				TaobaokeReportFacade facade = new TaobaokeReportFacade(r) ;
				BigDecimal uc = facade.getUserCommission() ;
				if(DecimalUtils.greatThan(uc, jiao)){
					items.add(facade) ;
				}
			}
		}
		
		if(!CollectionUtils.isEmpty(items)){
			buyingRecommendItemCache.setCache(items) ;
		}
		
		return items ;
	}
}
