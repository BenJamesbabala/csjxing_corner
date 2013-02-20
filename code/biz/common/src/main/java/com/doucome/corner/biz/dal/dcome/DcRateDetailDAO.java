package com.doucome.corner.biz.dal.dcome;

import com.doucome.corner.biz.dal.dataobject.dcome.DcRateDetailDO;

/**
 * ͶƱ����
 * @author langben 2012-8-10
 *
 */
public interface DcRateDetailDAO {

	/**
	 * ����һ��ͶƱ
	 * @param rate
	 */
	Long insertRate(DcRateDetailDO rate) ;
	
	/**
	 * �����û�ID����ƷID��ѯͶƱ
	 * @param userId
	 * @param itemId
	 * @return
	 */
	DcRateDetailDO queryRateByItemAndRateuser(long itemId , long rateUserId) ;
	
}
