package com.doucome.corner.biz.dcome.service.wryneck;

import com.doucome.corner.biz.dal.wryneck.dataobject.WryneckUserDO;
import com.doucome.corner.biz.dcome.exception.DcDuplicateKeyException;
import com.doucome.corner.biz.dcome.model.wryneck.WryneckUserDTO;

/**
 * ��DcUserService.java��ʵ����������ޢ�û�service
 * 
 * @author ib 2012-7-28 ����11:49:27
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
     * �����û���½��Ϣ����ǰ��Ҫ��lastLoginTime��userNick.
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
