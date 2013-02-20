package com.doucome.corner.biz.dcome.service.wryneck;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dal.wryneck.WryneckUserDAO;
import com.doucome.corner.biz.dal.wryneck.dataobject.WryneckUserDO;
import com.doucome.corner.biz.dcome.exception.DcDuplicateKeyException;
import com.doucome.corner.biz.dcome.model.wryneck.WryneckUserDTO;

public class WryneckUserServiceImpl implements WryneckUserService {

	@Autowired
	private WryneckUserDAO wryneckUserDAO ;
	
	@Override
	public long insertUser(WryneckUserDO user) throws DcDuplicateKeyException {
		return wryneckUserDAO.insertUser(user) ;
	}

	@Override
	public WryneckUserDTO getUser(Long userId) {	
		WryneckUserDO user = wryneckUserDAO.queryUser(userId) ;
		if(user == null){
			return null ;
		}
		return new WryneckUserDTO(user) ;
	}

	@Override
	public WryneckUserDTO queryUserByExternalId(String externalId) {
		WryneckUserDO user = wryneckUserDAO.queryUserByExternalId(externalId) ;
		if(user == null){
			return null ;
		}
		return new WryneckUserDTO(user) ;
	}

	@Override
	public int updateLoginInfo(WryneckUserDO user) {
		return wryneckUserDAO.updateLoginInfo(user) ;
	}

	@Override
	public int updateFollowQzone(Long userId) {
		return wryneckUserDAO.updateFollowQzone(userId) ;
	}

	@Override
	public int updateTestResult(Long userId, String result) {
		return wryneckUserDAO.updateTestResult(userId , result) ;
	}

}
