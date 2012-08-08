package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.dataobject.dcome.DcUserDO;

/**
 * ��DcUserDAO.java��ʵ����������ޢ�û���ϢDAO
 * 
 * @author ib 2012-7-28 ����09:55:16
 */
public interface DcUserDAO {

    public long insertUser(DcUserDO dcUserDO);

    public int updateUser(DcUserDO dcUserDO);

    public int updateLastLoginTime(long userId);

    public DcUserDO queryUser(long userId);
    /**
     * ��ѯ�û�
     * @param userIds
     * @return
     */
    List<DcUserDO> queryUsers(List<Long> userIds);

    public DcUserDO queryUserByExternalId(String externalId, String externalPf);
}
