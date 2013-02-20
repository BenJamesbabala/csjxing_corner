package com.doucome.corner.biz.zhe.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dal.DdzUserDAO;
import com.doucome.corner.biz.dal.dataobject.DdzUserDO;
import com.doucome.corner.biz.zhe.service.DdzUserService;

public class DdzUserServiceImpl implements DdzUserService {

    @Autowired
    private DdzUserDAO ddzUserDAO;

    @Override
    public int createUser(DdzUserDO user) {
        return ddzUserDAO.insertUser(user);
    }

    @Override
    public DdzUserDO getByUid(String uid) {
        return ddzUserDAO.queryByUid(uid);
    }

    @Override
    public DdzUserDO getByLoginId(String loginId) {
        return ddzUserDAO.queryByLoginId(loginId);
    }

    @Override
    public DdzUserDO getByLoginIdAndPassword(String loginId, String md5Password) {
        return ddzUserDAO.queryByLoginIdAndPassword(loginId, md5Password);
    }

    @Override
    public void updateUser(DdzUserDO user) {
        if (user == null || StringUtils.isBlank(user.getUid())) {
            return;
        }
        ddzUserDAO.updateUser(user);
    }

    @Override
    public void updateLastLoginTime(String uid) {
        if (StringUtils.isBlank(uid)) {
            return;
        }
        ddzUserDAO.updateLastLoginTime(uid);
    }

	@Override
	public int updateAlipayIdByLoginId(String loginId, String alipayId) {
		return ddzUserDAO.updateAlipayIdByLoginId(loginId, alipayId) ;
	}

	@Override
	public int incrModificationCount(String loginId) {
		return ddzUserDAO.incrModificationCount(loginId) ;
	}

}
