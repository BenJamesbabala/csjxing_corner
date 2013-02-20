package com.doucome.corner.biz.dal;

import java.util.List;

import com.doucome.corner.biz.dal.condition.BannerConfigSearchCondition;
import com.doucome.corner.biz.dal.dataobject.BannerConfigDO;

/**
 * Banner Config
 * @author langben 2012-9-27
 *
 */
public interface BannerConfigDAO {

	/**
	 * 查询配置
	 * @return
	 */
	BannerConfigDO queryConfigsByBannerId(String bannerId) ;
	
	/**
	 * 更新banner
	 * @param bannerType
	 * @return
	 */
	int updateConfigByBannerId(BannerConfigDO config) ;
	
	/**
	 * count banner配置
	 * @param condition
	 * @return
	 */
	int countConfigsWithPagination(BannerConfigSearchCondition condition) ;
	
	/**
	 * 查询banner配置
	 * @param condition
	 * @param pagination
	 * @return
	 */
	List<BannerConfigDO> queryConfigsWithPagination(BannerConfigSearchCondition searchCondition , int start, int size) ;

	/**
	 * 
	 * @param bannerId
	 * @param status
	 * @return
	 */
	int updateConfigStatusByBannerId(String bannerId, String status);
	
	/**
	 * banner绑定事件
	 * @param bannerId
	 * @param bindEvent
	 * @param bindEventFunction
	 * @return
	 */
	int updateBindEventById(String bannerId , String bindEvent , String bindEventFunction) ;
}
