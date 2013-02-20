package com.doucome.corner.biz.dcome.service;

import com.doucome.corner.biz.dal.dataobject.dcome.DcLoveDetailDO;
import com.doucome.corner.biz.dcome.exception.DuplicateOperateException;

public interface DcLoveDetailService {
	
	/**
	 * 创建喜欢记录
	 * @param itemId
	 * @param userId
	 * @return
	 * @throws DuplicateOperateException 重复添加喜欢
	 */
	Long createDetail(long itemId, long userId) throws DuplicateOperateException ;
	
	/**
	 * 根据用户ID和商品ID查询喜欢
	 * @param userId
	 * @param itemId
	 * @return
	 */
	DcLoveDetailDO getDetailByUserAndItem(long userId , long itemId) ;
	
	
}
