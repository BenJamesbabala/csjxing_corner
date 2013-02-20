package com.doucome.corner.biz.apps.namefate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.apps.exception.DuplicateKeyException;
import com.doucome.corner.biz.apps.namefate.model.NamefateUserDTO;
import com.doucome.corner.biz.apps.namefate.service.NamefateUserService;
import com.doucome.corner.biz.dal.namefate.NamefateUserDAO;
import com.doucome.corner.biz.dal.namefate.dataobject.NamefateUserDO;

public class NamefateUserServiceImpl implements NamefateUserService {

	@Autowired
	private NamefateUserDAO namefateUserDAO ;
	
	@Override
	public long insertUser(NamefateUserDO user) throws DuplicateKeyException {
		return namefateUserDAO.insertUser(user) ;
	}

	@Override
	public NamefateUserDTO getUser(Long userId) {	
		NamefateUserDO user = namefateUserDAO.queryUser(userId) ;
		if(user == null){
			return null ;
		}
		return new NamefateUserDTO(user) ;
	}

	@Override
	public NamefateUserDTO getUserByExternalId(String externalId) {
		NamefateUserDO user = namefateUserDAO.queryUserByExternalId(externalId) ;
		if(user == null){
			return null ;
		}
		return new NamefateUserDTO(user) ;
	}

	@Override
	public int updateLoginInfo(NamefateUserDO user) {
		return namefateUserDAO.updateLoginInfo(user) ;
	}

	@Override
	public int updateFollowQzone(Long userId) {
		return namefateUserDAO.updateFollowQzone(userId) ;
	}

	@Override
	public int incrUnreadMsgCount(Long userId) {
		return namefateUserDAO.incrUnreadMsgCount(userId) ;
	}

	@Override
	public int updateNewGuide(long userId, long newValue) {
		return namefateUserDAO.updateNewGuide(userId, newValue) ;
	}

}
