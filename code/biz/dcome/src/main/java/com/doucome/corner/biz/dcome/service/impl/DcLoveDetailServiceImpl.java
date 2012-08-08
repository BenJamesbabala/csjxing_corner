package com.doucome.corner.biz.dcome.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dal.dataobject.dcome.DcLoveDetailDO;
import com.doucome.corner.biz.dal.dcome.DcLoveDetailDAO;
import com.doucome.corner.biz.dcome.service.DcLoveDetailService;

public class DcLoveDetailServiceImpl implements DcLoveDetailService{

	@Autowired
	private DcLoveDetailDAO dcLoveDetailDAO ;
	
	@Override
	public Long createDetail(long itemId, long userId) {
		DcLoveDetailDO loveDetail = new DcLoveDetailDO() ;
		loveDetail.setItemId(itemId);
		loveDetail.setUserId(userId) ;
		Long insertId = dcLoveDetailDAO.insertDetail(loveDetail) ;
		return insertId ;
	}

	@Override
	public DcLoveDetailDO getDetailByUserAndItem(long userId, long itemId) {
		return dcLoveDetailDAO.queryDetailByUserAndItem(userId, itemId) ;
	}

}
