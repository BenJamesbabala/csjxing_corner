package com.doucome.corner.biz.zhe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dal.DdzDrawApproveDAO;
import com.doucome.corner.biz.dal.dataobject.DdzDrawApproveDO;
import com.doucome.corner.biz.zhe.service.DdzDrawApproveService;

public class DdzDrawApproveServiceImpl implements DdzDrawApproveService {

	@Autowired
	private DdzDrawApproveDAO ddzDrawApproveDAO ;
	
	/**
	 * 
	 * @param approve
	 * @return
	 */
	@Override
	public long createApprove(DdzDrawApproveDO approve) {
		return ddzDrawApproveDAO.insertApprove(approve) ;
	}
}
