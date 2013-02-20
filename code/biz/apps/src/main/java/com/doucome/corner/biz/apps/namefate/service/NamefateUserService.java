package com.doucome.corner.biz.apps.namefate.service;

import com.doucome.corner.biz.apps.exception.DuplicateKeyException;
import com.doucome.corner.biz.apps.namefate.model.NamefateUserDTO;
import com.doucome.corner.biz.dal.namefate.dataobject.NamefateUserDO;


public interface NamefateUserService {

	/**
	 * 
	 * @param dcUserDO
	 * @return
	 */
    long insertUser(NamefateUserDO user) throws DuplicateKeyException;
    /**
     * 
     * @param userId
     * @return
     */
    NamefateUserDTO getUser(Long userId);
    
    /**
     * 
     * @param externalId
     * @param externalPf
     * @return
     */
    NamefateUserDTO getUserByExternalId(String externalId);
    
    /**
     * 更新用户登陆信息。当前主要是lastLoginTime和userNick.
     * @param user
     * @return
     */
    int updateLoginInfo(NamefateUserDO user);
    /**
     * 
     * @param userId
     * @return
     */
    int updateFollowQzone(Long userId);
    
    /**
     * 
     * @param userId
     * @return
     */
    int incrUnreadMsgCount(Long userId) ;

    /**
     * 更新第几位
     * @param userId
     * @param index
     */
    int updateNewGuide(long userId , long newValue) ;
}
