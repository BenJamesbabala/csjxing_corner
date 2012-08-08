package com.doucome.corner.biz.dal;

import com.doucome.corner.biz.dal.dataobject.AlipayItemDO;

public interface AlipayDao {
	/**
	 * 记录打款.
	 * @param payDetail .
	 * @return .
	 */
	Long logPayDetail(AlipayItemDO payDetail);
	
	/**
	 * 记录打款.
	 * @param payDetail .
	 * @return .
	 */
	Long batchLogPayDetail(AlipayItemDO[] payDetail);
	/**
	 * 更新打款结果.
	 * @param resultDetail
	 * @return
	 */
	boolean updatePayResult(AlipayItemDO resultDetail);
	/**
	 * 更新打款结果.
	 * @param resultDetail
	 * @return
	 */
	boolean batchUpdatePayResult(AlipayItemDO[] resultDetails);
}
