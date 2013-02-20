package com.doucome.corner.biz.dal.dcome;

import com.doucome.corner.biz.dal.dataobject.dcome.DcRateDetailDO;

/**
 * 投票详情
 * @author langben 2012-8-10
 *
 */
public interface DcRateDetailDAO {

	/**
	 * 增加一条投票
	 * @param rate
	 */
	Long insertRate(DcRateDetailDO rate) ;
	
	/**
	 * 根据用户ID和商品ID查询投票
	 * @param userId
	 * @param itemId
	 * @return
	 */
	DcRateDetailDO queryRateByItemAndRateuser(long itemId , long rateUserId) ;
	
}
