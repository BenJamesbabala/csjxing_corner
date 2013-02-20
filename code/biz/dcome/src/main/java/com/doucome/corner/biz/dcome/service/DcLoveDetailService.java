package com.doucome.corner.biz.dcome.service;

import com.doucome.corner.biz.dal.dataobject.dcome.DcLoveDetailDO;
import com.doucome.corner.biz.dcome.exception.DuplicateOperateException;

public interface DcLoveDetailService {
	
	/**
	 * ����ϲ����¼
	 * @param itemId
	 * @param userId
	 * @return
	 * @throws DuplicateOperateException �ظ����ϲ��
	 */
	Long createDetail(long itemId, long userId) throws DuplicateOperateException ;
	
	/**
	 * �����û�ID����ƷID��ѯϲ��
	 * @param userId
	 * @param itemId
	 * @return
	 */
	DcLoveDetailDO getDetailByUserAndItem(long userId , long itemId) ;
	
	
}
