package com.doucome.corner.biz.dal.horoscope;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;

import com.doucome.corner.biz.dal.horoscope.dataobject.HsUserDO;

/**
 * ��������
 * 
 * @author ib 2012-7-28 ����09:55:16
 */
public interface HsUserDAO {
	/**
	 * �½��û�
	 * @param dcUserDO
	 * @return
	 */
    Long insertUser(HsUserDO stUserDO) throws DuplicateKeyException;
    /**
     * ����ID��ѯ�û�
     * @param userId
     * @return
     */
    HsUserDO queryUser(Long userId);
    /**
     * ��ѯ�û�
     * @param userIds
     * @return
     */
    List<HsUserDO> queryUsers(List<Long> userIds);
    /**
     * 
     * @param externalId
     * @param externalPf
     * @return
     */
    HsUserDO queryUserByExternalId(String externalId);
    /**
     * 
     * @param userId
     * @return
     */
    int updateLoginInfo(HsUserDO user);
    /**
     * 
     * @param userId
     * @return
     */
    int updateFollowQzone(Long userId);
    /**
     * 
     * @param userId
     * @param hsId
     * @return
     */
    Integer updateHsId(Long userId, Integer hsId);
}
