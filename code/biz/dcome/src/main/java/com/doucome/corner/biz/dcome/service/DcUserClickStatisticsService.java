package com.doucome.corner.biz.dcome.service;

import com.doucome.corner.biz.dal.dataobject.dcome.DcUserClickStatisticsDO;
import com.doucome.corner.biz.dcome.enums.DcUserClickStatisticsEnums;

/**
 * 用户点击统计
 * @author langben 2012-9-15
 *
 */
public interface DcUserClickStatisticsService {

	/**
	 * 
	 * @param clickStatistics
	 * @return
	 */
	long createClickStatistics(DcUserClickStatisticsDO clickStatistics) ;
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	DcUserClickStatisticsDO getClickStatisticsByUser(Long userId, DcUserClickStatisticsEnums clickType) ;
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	int incrClickStatisticsByUser(Long userId, DcUserClickStatisticsEnums clickType) ;
}
