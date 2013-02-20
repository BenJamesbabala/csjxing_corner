package com.doucome.corner.biz.dal.horoscope;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;

import com.doucome.corner.biz.dal.horoscope.dataobject.HsUserDO;

/**
 * 星座贝贝
 * 
 * @author ib 2012-7-28 下午09:55:16
 */
public interface HsUserDAO {
	/**
	 * 新建用户
	 * @param dcUserDO
	 * @return
	 */
    Long insertUser(HsUserDO stUserDO) throws DuplicateKeyException;
    /**
     * 根据ID查询用户
     * @param userId
     * @return
     */
    HsUserDO queryUser(Long userId);
    /**
     * 查询用户
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
