package com.doucome.corner.biz.zhe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dal.DdzTransferUrlDAO;
import com.doucome.corner.biz.dal.dataobject.DdzTransferUrlDO;
import com.doucome.corner.biz.zhe.service.DdzTransferUrlService;

public class DdzTransferUrlServiceImpl implements DdzTransferUrlService{

	@Autowired
	private DdzTransferUrlDAO ddzTransferUrlDAO ;
	
	@Override
	public long createTransferUrl(DdzTransferUrlDO transferUrl) {
		return ddzTransferUrlDAO.insertTransferUrl(transferUrl) ;
	}

}
