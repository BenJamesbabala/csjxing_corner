package com.doucome.corner.biz.dcome.service.horoscope;

import java.util.List;

import com.doucome.corner.biz.dcome.exception.DcDuplicateKeyException;
import com.doucome.corner.biz.dcome.model.star.HsUserDTO;

/**
 * 类DcUserService.java的实现描述：豆蔻用户service
 * 
 * @author ib 2012-7-28 下午11:49:27
 */
public interface HsUserService {

	/**
	 * 
	 * @param dcUserDO
	 * @return
	 */
    long insertUser(HsUserDTO stUserDTO) throws DcDuplicateKeyException;
    /**
     * 
     * @param userId
     * @return
     */
    HsUserDTO getUser(Long userId);
    
    /**
     * 获取用户信息
     * @param userIds
     * @return
     */
    List<HsUserDTO> queryUsers(List<Long> userIds);
    /**
     * 
     * @param externalId
     * @param externalPf
     * @return
     */
    HsUserDTO queryUserByExternalId(String externalId);
    
    /**
     * 更新用户登陆信息。当前主要是lastLoginTime和userNick.
     * @param user
     * @return
     */
    int updateLoginInfo(HsUserDTO user);
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
    int updateHsId(Long userId, Integer hsId);
}
