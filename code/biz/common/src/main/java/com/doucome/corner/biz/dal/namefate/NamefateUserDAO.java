package com.doucome.corner.biz.dal.namefate;

import org.springframework.dao.DuplicateKeyException;

import com.doucome.corner.biz.dal.namefate.dataobject.NamefateUserDO;

public interface NamefateUserDAO {

	/**
	 * �½��û�
	 * 
	 * @param dcUserDO
	 * @return
	 */
	Long insertUser(NamefateUserDO user) throws DuplicateKeyException;

	/**
	 * ����ID��ѯ�û�
	 * 
	 * @param userId
	 * @return
	 */
	NamefateUserDO queryUser(Long userId);

	/**
	 * 
	 * @param externalId
	 * @param externalPf
	 * @return
	 */
	NamefateUserDO queryUserByExternalId(String externalId);

	/**
	 * 
	 * @param userId
	 * @return
	 */
	int updateLoginInfo(NamefateUserDO user);
	
	/**
	 * 
	 * @param userId
	 * @param newValue
	 * @return
	 */
	int updateNewGuide(long userId, long newValue) ;

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
}
