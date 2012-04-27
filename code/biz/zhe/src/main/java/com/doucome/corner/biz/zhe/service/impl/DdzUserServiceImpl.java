package com.doucome.corner.biz.zhe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dal.DdzUserDAO;
import com.doucome.corner.biz.dal.dataobject.DdzUserDO;
import com.doucome.corner.biz.zhe.service.DdzUserService;

public class DdzUserServiceImpl implements DdzUserService {

	@Autowired
	private DdzUserDAO ddzUserDAO ;
		
	@Override
	public int createUser(DdzUserDO user) {
		return ddzUserDAO.insertUser(user) ;
	}

	@Override
	public DdzUserDO getByUid(String uid) {
		return ddzUserDAO.queryByUid(uid) ;
	}

	@Override
	public DdzUserDO getByLoginId(String loginId) {
		return ddzUserDAO.queryByLoginId(loginId) ;
	}

	@Override
	public DdzUserDO getByLoginIdAndPassword(String loginId, String md5Password) {
		return ddzUserDAO.queryByLoginIdAndPassword(loginId, md5Password) ;
	}

}
