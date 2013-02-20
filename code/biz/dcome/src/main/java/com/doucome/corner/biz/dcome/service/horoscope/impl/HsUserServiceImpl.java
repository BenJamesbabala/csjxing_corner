package com.doucome.corner.biz.dcome.service.horoscope.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.dal.horoscope.HsUserDAO;
import com.doucome.corner.biz.dal.horoscope.dataobject.HsUserDO;
import com.doucome.corner.biz.dcome.exception.DcDuplicateKeyException;
import com.doucome.corner.biz.dcome.model.star.HsUserDTO;
import com.doucome.corner.biz.dcome.service.horoscope.HsUserService;

public class HsUserServiceImpl implements HsUserService {

	@Autowired
	private HsUserDAO hsUserDAO;
	
	@Override
	public long insertUser(HsUserDTO stUserDTO) throws DcDuplicateKeyException {
		HsUserDO userDO = stUserDTO.toDO();
		try {
			return hsUserDAO.insertUser(userDO);
		} catch (DuplicateKeyException e) {
			throw new DcDuplicateKeyException(e.getMessage(), e);
		}
	}
	
	@Override
	public HsUserDTO getUser(Long userId) {
		if (IDUtils.isNotCorrect(userId)) {
			return null;
		}
		HsUserDO userDO = hsUserDAO.queryUser(userId);
		if (userDO == null) {
			return null;
		}
		return new HsUserDTO(userDO);
	}

	@Override
	public List<HsUserDTO> queryUsers(List<Long> userIds) {
		List<HsUserDTO> results = new ArrayList<HsUserDTO>();
		List<HsUserDO> users = hsUserDAO.queryUsers(userIds);
		if (CollectionUtils.isNotEmpty(users)) {
			for (HsUserDO userDO: users) {
				results.add(new HsUserDTO(userDO));
			}
		}
		return results;
	}

	@Override
	public HsUserDTO queryUserByExternalId(String externalId) {
		HsUserDO userDO = hsUserDAO.queryUserByExternalId(externalId);
		if (userDO == null) {
			return null;
		}
		return new HsUserDTO(userDO);
	}
	
	@Override
	public int updateLoginInfo(HsUserDTO user) {
		return hsUserDAO.updateLoginInfo(user.toDO());
	}
	
	@Override
	public int updateFollowQzone(Long userId) {
		return hsUserDAO.updateFollowQzone(userId);
	}
	
	@Override
	public int updateHsId(Long userId, Integer hsId) {
		return hsUserDAO.updateHsId(userId, hsId);
	}
}
