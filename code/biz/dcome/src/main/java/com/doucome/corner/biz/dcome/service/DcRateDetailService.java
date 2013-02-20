package com.doucome.corner.biz.dcome.service;

import com.doucome.corner.biz.dal.dataobject.dcome.DcRateDetailDO;
import com.doucome.corner.biz.dcome.exception.DuplicateOperateException;

public interface DcRateDetailService {

	/**
	 * ����һ��ͶƱ
	 * @param rate
	 * @throws DuplicateRateException �ظ�ͶƱ
	 */
	Long createRate(DcRateDetailDO rate) throws DuplicateOperateException ;
	
	
}
