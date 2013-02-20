package com.doucome.corner.biz.dcome.service;

import com.doucome.corner.biz.dal.dataobject.dcome.DcRateDetailDO;
import com.doucome.corner.biz.dcome.exception.DuplicateOperateException;

public interface DcRateDetailService {

	/**
	 * 增加一条投票
	 * @param rate
	 * @throws DuplicateRateException 重复投票
	 */
	Long createRate(DcRateDetailDO rate) throws DuplicateOperateException ;
	
	
}
