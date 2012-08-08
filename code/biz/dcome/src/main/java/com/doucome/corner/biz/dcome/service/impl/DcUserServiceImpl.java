package com.doucome.corner.biz.dcome.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dal.dataobject.dcome.DcUserDO;
import com.doucome.corner.biz.dal.dcome.DcUserDAO;
import com.doucome.corner.biz.dcome.service.DcUserService;

/**
 * @author ib 2012-7-28 ÏÂÎç11:50:22
 */
public class DcUserServiceImpl implements DcUserService {
    @Autowired
    private DcUserDAO dcUserDAO;

    @Override
    public long insertUser(DcUserDO dcUserDO) {
        if (dcUserDO != null) {
            return dcUserDAO.insertUser(dcUserDO);
        }
        return 0;
    }

    @Override
    public DcUserDO queryUser(long userId) {
        if (userId > 0) {
            return dcUserDAO.queryUser(userId);
        }
        return null;
    }
    
    @Override
    public List<DcUserDO> queryUsers(List<Long> userIds) {
    	if (userIds == null || userIds.size() == 0) {
    		return new ArrayList<DcUserDO>();
    	}
    	return dcUserDAO.queryUsers(userIds);
    }

    @Override
    public DcUserDO queryUserByExternalId(String externalId, String externalPf) {
        if (StringUtils.isNotBlank(externalId) && StringUtils.isNotBlank(externalPf)) {
            return dcUserDAO.queryUserByExternalId(externalId, externalPf);
        }
        return null;
    }

    @Override
    public int updateLastLoginTime(long userId) {
        if (userId > 0) {
            return dcUserDAO.updateLastLoginTime(userId);
        }
        return 0;
    }

    @Override
    public int updateUser(DcUserDO dcUserDO) {
        if (dcUserDO != null && dcUserDO.getUserId() > 0) {
            return dcUserDAO.updateUser(dcUserDO);
        }
        return 0;
    }

}
