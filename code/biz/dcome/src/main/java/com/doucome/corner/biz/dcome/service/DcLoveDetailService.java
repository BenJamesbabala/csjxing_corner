package com.doucome.corner.biz.dcome.service;

import com.doucome.corner.biz.dal.dataobject.dcome.DcLoveDetailDO;

public interface DcLoveDetailService {
	
	/**
	 * 创建喜欢记录
	 * @param itemId
	 * @param userId
	 * @return
	 */
	Long createDetail(long itemId, long userId) ;
	
	/**
	 * 根据用户ID和商品ID查询喜欢
	 * @param userId
	 * @param itemId
	 * @return
	 */
	DcLoveDetailDO getDetailByUserAndItem(long userId , long itemId) ;
	
	
}
