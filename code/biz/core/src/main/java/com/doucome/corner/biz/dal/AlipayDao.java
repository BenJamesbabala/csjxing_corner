package com.doucome.corner.biz.dal;

import com.doucome.corner.biz.dal.dataobject.AlipayItemDO;

public interface AlipayDao {
	/**
	 * ��¼���.
	 * @param payDetail .
	 * @return .
	 */
	Long logPayDetail(AlipayItemDO payDetail);
	
	/**
	 * ��¼���.
	 * @param payDetail .
	 * @return .
	 */
	Long batchLogPayDetail(AlipayItemDO[] payDetail);
	/**
	 * ���´����.
	 * @param resultDetail
	 * @return
	 */
	boolean updatePayResult(AlipayItemDO resultDetail);
	/**
	 * ���´����.
	 * @param resultDetail
	 * @return
	 */
	boolean batchUpdatePayResult(AlipayItemDO[] resultDetails);
}
