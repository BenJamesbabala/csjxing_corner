package com.doucome.corner.biz.dal.dcome;

import com.doucome.corner.biz.dal.dataobject.dcome.DcLoveDetailDO;

/**
 * 喜欢+1 详细
 * @author langben 2012-7-22
 *
 */
public interface DcLoveDetailDAO {

	/**
	 * 增加一条喜欢+1
	 * @param loveDetail
	 */
	Long insertDetail(DcLoveDetailDO loveDetail) ;
	
	/**
	 * 根据用户ID和商品ID查询喜欢
	 * @param userId
	 * @param itemId
	 * @return
	 */
	DcLoveDetailDO queryDetailByUserAndItem(long userId , long itemId) ;
}
