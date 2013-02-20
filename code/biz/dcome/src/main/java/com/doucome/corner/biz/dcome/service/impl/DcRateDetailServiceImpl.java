package com.doucome.corner.biz.dcome.service.impl;

import org.springframework.dao.DuplicateKeyException;

import com.doucome.corner.biz.dal.dataobject.dcome.DcRateDetailDO;
import com.doucome.corner.biz.dal.dcome.DcRateDetailDAO;
import com.doucome.corner.biz.dcome.exception.DuplicateOperateException;
import com.doucome.corner.biz.dcome.service.DcRateDetailService;

public class DcRateDetailServiceImpl implements DcRateDetailService{

	private DcRateDetailDAO dcRateDetailDAO ;
	
	@Override
	public Long createRate(DcRateDetailDO rate) throws DuplicateOperateException {
		try {
			return dcRateDetailDAO.insertRate(rate) ;
		}catch(DuplicateKeyException e){
			throw new DuplicateOperateException(e.getMessage()) ;
		}
	}

}
