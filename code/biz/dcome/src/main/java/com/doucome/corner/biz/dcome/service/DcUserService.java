package com.doucome.corner.biz.dcome.service;

import java.util.List;

import com.doucome.corner.biz.dal.dataobject.dcome.DcUserDO;

/**
 * 类DcUserService.java的实现描述：豆蔻用户service
 * 
 * @author ib 2012-7-28 下午11:49:27
 */
public interface DcUserService {

    public long insertUser(DcUserDO dcUserDO);

    public int updateUser(DcUserDO dcUserDO);

    public int updateLastLoginTime(long userId);

    public DcUserDO queryUser(long userId);
    /**
     * 获取用户信息
     * @param userIds
     * @return
     */
    List<DcUserDO> queryUsers(List<Long> userIds);

    public DcUserDO queryUserByExternalId(String externalId, String externalPf);
}
