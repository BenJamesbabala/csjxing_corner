package com.doucome.corner.biz.dcome.service;

import com.doucome.corner.biz.dal.dataobject.dcome.DcLoveDetailDO;

public interface DcLoveDetailService {
	
	/**
	 * ����ϲ����¼
	 * @param itemId
	 * @param userId
	 * @return
	 */
	Long createDetail(long itemId, long userId) ;
	
	/**
	 * �����û�ID����ƷID��ѯϲ��
	 * @param userId
	 * @param itemId
	 * @return
	 */
	DcLoveDetailDO getDetailByUserAndItem(long userId , long itemId) ;
	
	
}
