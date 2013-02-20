package com.doucome.corner.biz.dal.dcome;

import com.doucome.corner.biz.dal.dataobject.dcome.DcLoveDetailDO;

/**
 * ϲ��+1 ��ϸ
 * @author langben 2012-7-22
 *
 */
public interface DcLoveDetailDAO {

	/**
	 * ����һ��ϲ��+1
	 * @param loveDetail
	 */
	Long insertDetail(DcLoveDetailDO loveDetail) ;
	
	/**
	 * �����û�ID����ƷID��ѯϲ��
	 * @param userId
	 * @param itemId
	 * @return
	 */
	DcLoveDetailDO queryDetailByUserAndItem(long userId , long itemId) ;
}
