package com.doucome.corner.biz.dal.dcome;

import com.doucome.corner.biz.dal.dataobject.dcome.DcUserClickStatisticsDO;

/**
 * µã»÷Í³¼Æ
 * @author langben 2012-9-15
 *
 */
public interface DcUserClickStatisticsDAO {

	/**
	 * 
	 * @param clickStatistics
	 * @return
	 */
	long insertClickStatistics(DcUserClickStatisticsDO clickStatistics) ;
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	DcUserClickStatisticsDO queryClickStatisticsByUser(Long userId, String clickType) ;
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	int incrClickStatisticsByUser(Long userId, String clickType) ;
}
