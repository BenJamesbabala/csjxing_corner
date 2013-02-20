package com.doucome.corner.biz.dcome.service.horoscope;

import java.util.List;

import com.doucome.corner.biz.dcome.exception.DcDuplicateKeyException;
import com.doucome.corner.biz.dcome.model.star.HsUserDTO;

/**
 * ��DcUserService.java��ʵ����������ޢ�û�service
 * 
 * @author ib 2012-7-28 ����11:49:27
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
     * ��ȡ�û���Ϣ
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
     * �����û���½��Ϣ����ǰ��Ҫ��lastLoginTime��userNick.
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
