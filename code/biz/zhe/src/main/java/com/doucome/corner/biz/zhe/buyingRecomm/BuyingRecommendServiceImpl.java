package com.doucome.corner.biz.zhe.buyingRecomm;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.doucome.corner.biz.core.cache.AbstractCacheSupport.InternalStoreItem;
import com.doucome.corner.biz.core.constant.LogConstant;
import com.doucome.corner.biz.core.enums.RecommendTypeEnums;
import com.doucome.corner.biz.dal.dataobject.DdzRecommendDO;
import com.doucome.corner.biz.zhe.cache.BuyingRecommendItemCache;
import com.doucome.corner.biz.zhe.cache.impl.BuyingRecommendItemCacheImpl;
import com.doucome.corner.biz.zhe.model.RecommendBatchDate;
import com.doucome.corner.biz.zhe.rule.DdzEatDiscountRule;
import com.doucome.corner.biz.zhe.service.DdzRecommendService;
import com.doucome.corner.biz.zhe.vo.BuyingRecommendVO;

public class BuyingRecommendServiceImpl implements BuyingRecommendService {
	
	private static final Log recommLog = LogFactory.getLog(LogConstant.recommend_log) ;
	
	private List<String> alipayList ;
	
	@Autowired
	private BuyingRecommendItemCache buyingRecommendItemCache ;
	
	@Autowired
	private DdzRecommendService ddzRecommendService ;
	
	@Autowired
	private DdzEatDiscountRule ddzEatDiscountRule ;
	
	@Override
	public List<BuyingRecommendVO> getBuyingItems(int count) {
		
		//从缓存查询
		InternalStoreItem<List<DdzRecommendDO>> internalItems = buyingRecommendItemCache.getInternalItems() ;
		
		//看是否过期
		if(BuyingRecommendItemCacheImpl.isCacheExpire(internalItems)){
			//从数据库加载新数据
			List<DdzRecommendDO> list = ddzRecommendService.getRecommends(new RecommendBatchDate(new Date()), RecommendTypeEnums.BUYING) ;
			if(!CollectionUtils.isEmpty(list)){
				buyingRecommendItemCache.setCache(list) ;
			} else {
				recommLog.error("recommend list is empty .") ;
			}
		}
		
		List<DdzRecommendDO> recommList = buyingRecommendItemCache.getItems() ;
		List<DdzRecommendDO> recommSubList = getRecommSubList(recommList , count) ;
		List<BuyingRecommendVO> voList = null ;
		if( recommSubList != null ){
			voList = new ArrayList<BuyingRecommendVO>() ;
	 		for(DdzRecommendDO recommItem : recommSubList){
				if(recommItem != null){
					String alipayId = getRandomAlipayId() ;
					BuyingRecommendVO vo = new BuyingRecommendVO(recommItem ,alipayId) ;
					//计算用户佣金
					vo.calcFacadeCommissions(ddzEatDiscountRule) ;
					voList.add(vo) ;
					
				}
			}
		}
		
 		return voList ;
	}
	
	private List<DdzRecommendDO> getRecommSubList(List<DdzRecommendDO> list , int count){
		if(list == null){
			return null ;
		}
		List<DdzRecommendDO> newList = new ArrayList<DdzRecommendDO>() ;
		
		int size = list.size() ;
		Set<Integer> set = new HashSet<Integer>() ;//防止重复
		
		for(int i=0 ;i<(count+10) ;i++){
			int r = RandomUtils.nextInt(size) ;
			set.add(r) ;
			if(set.size() >= count){
				break ;
			}
		}
	
		for(Integer i : set){
			DdzRecommendDO recomm = list.get(i) ;
			newList.add(recomm) ;
		}
		
		return newList ;
		
	}
	
	private String getRandomAlipayId(){
		int size = alipayList.size() ;
		int index = RandomUtils.nextInt(size) ;
		return alipayList.get(index) ;
	}
	
	public void setAlipayList(List<String> alipayList) {
		this.alipayList = alipayList;
	}
	
	

}
