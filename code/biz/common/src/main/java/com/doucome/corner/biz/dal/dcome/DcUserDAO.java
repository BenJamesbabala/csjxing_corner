package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.dataobject.dcome.DcUserDO;

/**
 * 类DcUserDAO.java的实现描述：豆蔻用户信息DAO
 * 
 * @author ib 2012-7-28 下午09:55:16
 */
public interface DcUserDAO {

    public long insertUser(DcUserDO dcUserDO);

    public int updateUser(DcUserDO dcUserDO);

    public int updateLastLoginTime(long userId);

    public DcUserDO queryUser(long userId);
    /**
     * 查询用户
     * @param userIds
     * @return
     */
    List<DcUserDO> queryUsers(List<Long> userIds);

    public DcUserDO queryUserByExternalId(String externalId, String externalPf);
}
