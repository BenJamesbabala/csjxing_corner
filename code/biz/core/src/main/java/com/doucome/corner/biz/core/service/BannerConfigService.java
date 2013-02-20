package com.doucome.corner.biz.core.service;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.BannerConfigSearchCondition;
import com.doucome.corner.biz.dal.dataobject.BannerConfigDO;

public interface BannerConfigService {

	/**
	 * 
	 * @return
	 */
	BannerConfigDO getConfigByBannerId(String bannerId) ;
	
	/**
	 * 
	 * @param config
	 * @return
	 */
	int updateConfigByBannerId(BannerConfigDO config) ;
	
	/**
	 * 更新状态
	 * @param bannerId
	 * @param status
	 * @return
	 */
	int updateConfigStatusByBannerId(String bannerId , String status) ;
	
	/**
	 * 查询banner配置
	 * @param condition
	 * @param pagination
	 * @return
	 */
	QueryResult<BannerConfigDO> getConfigsWithPagination(BannerConfigSearchCondition searchCondition , Pagination pagination) ;
	
	/**
	 * banner绑定事件
	 * @param bannerId
	 * @param bindEvent
	 * @param bindEventFunction
	 * @return
	 */
	int updateBindEventById(String bannerId , String bindEvent , String bindEventFunction) ;

}
