package com.doucome.corner.biz.dcome.service.wryneck;

import com.doucome.corner.biz.dal.wryneck.dataobject.WryneckUserDO;
import com.doucome.corner.biz.dcome.exception.DcDuplicateKeyException;
import com.doucome.corner.biz.dcome.model.wryneck.WryneckUserDTO;

/**
 * 类DcUserService.java的实现描述：豆蔻用户service
 * 
 * @author ib 2012-7-28 下午11:49:27
 */
public interface WryneckUserService {

	/**
	 * 
	 * @param dcUserDO
	 * @return
	 */
    long insertUser(WryneckUserDO user) throws DcDuplicateKeyException;
    /**
     * 
     * @param userId
     * @return
     */
    WryneckUserDTO getUser(Long userId);
    
    /**
     * 
     * @param externalId
     * @param externalPf
     * @return
     */
    WryneckUserDTO queryUserByExternalId(String externalId);
    
    /**
     * 更新用户登陆信息。当前主要是lastLoginTime和userNick.
     * @param user
     * @return
     */
    int updateLoginInfo(WryneckUserDO user);
    /**
     * 
     * @param userId
     * @return
     */
    int updateFollowQzone(Long userId);
    
    int updateTestResult(Long userId , String result) ;
   
}
