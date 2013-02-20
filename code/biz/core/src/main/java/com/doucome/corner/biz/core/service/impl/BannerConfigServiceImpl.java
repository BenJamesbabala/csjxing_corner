package com.doucome.corner.biz.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.cache.BannerConfigCache;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.service.BannerConfigService;
import com.doucome.corner.biz.dal.BannerConfigDAO;
import com.doucome.corner.biz.dal.condition.BannerConfigSearchCondition;
import com.doucome.corner.biz.dal.dataobject.BannerConfigDO;

public class BannerConfigServiceImpl implements BannerConfigService {

	@Autowired
	private BannerConfigCache bannerConfigCache ;
	
	@Autowired
	private BannerConfigDAO bannerConfigDAO ;
	
	@Override
	public BannerConfigDO getConfigByBannerId(String bannerId) {
		
		BannerConfigDO config = bannerConfigCache.getCache(bannerId) ;
		
		if(config == null){
			config = bannerConfigDAO.queryConfigsByBannerId(bannerId) ;
			bannerConfigCache.setCache(config) ;
		}
		
		return config ;
	}

	@Override
	public int updateConfigByBannerId(BannerConfigDO config) {
		//²»²Ù×÷»º´æ
		int effectCount =  bannerConfigDAO.updateConfigByBannerId(config) ;
		triggerCacheModified(config.getBannerId()) ;
		return effectCount ;
	}

	@Override
	public QueryResult<BannerConfigDO> getConfigsWithPagination(
			BannerConfigSearchCondition searchCondition, Pagination pagination) {
		int totalRecords = bannerConfigDAO.countConfigsWithPagination(searchCondition) ;
        if (totalRecords <= 0) {
            return new QueryResult<BannerConfigDO>(new ArrayList<BannerConfigDO>(), pagination, totalRecords);
        }
        List<BannerConfigDO> items = bannerConfigDAO.queryConfigsWithPagination(searchCondition, pagination.getStart(), pagination.getSize());
        return new QueryResult<BannerConfigDO>(items, pagination, totalRecords);
	}

	@Override
	public int updateConfigStatusByBannerId(String bannerId, String status) {
		int effectCount =  bannerConfigDAO.updateConfigStatusByBannerId(bannerId,status) ;
		triggerCacheModified(bannerId) ;
		return effectCount ;
	}
	
	@Override
	public int updateBindEventById(String bannerId, String bindEvent,
			String bindEventFunction) {
		int effectCount = bannerConfigDAO.updateBindEventById(bannerId, bindEvent, bindEventFunction) ;
		triggerCacheModified(bannerId) ;
		return effectCount ;
	}
	
	private void triggerCacheModified(String bannerId){
		bannerConfigCache.remove(bannerId) ;
    }



}
