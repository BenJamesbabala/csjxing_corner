package com.doucome.corner.biz.dal.wryneck;

import org.springframework.dao.DuplicateKeyException;

import com.doucome.corner.biz.dal.wryneck.dataobject.WryneckUserDO;

public interface WryneckUserDAO {

	/**
	 * 新建用户
	 * 
	 * @param dcUserDO
	 * @return
	 */
	Long insertUser(WryneckUserDO user) throws DuplicateKeyException;

	/**
	 * 根据ID查询用户
	 * 
	 * @param userId
	 * @return
	 */
	WryneckUserDO queryUser(Long userId);

	/**
	 * 
	 * @param externalId
	 * @param externalPf
	 * @return
	 */
	WryneckUserDO queryUserByExternalId(String externalId);

	/**
	 * 
	 * @param userId
	 * @return
	 */
	int updateLoginInfo(WryneckUserDO user);

	/**
	 * 
	 * @param userId
	 * @return
	 */
	int updateFollowQzone(Long userId);

	/**
	 * 更新测试结果
	 * @param userId
	 * @param result
	 * @return
	 */
	int updateTestResult(Long userId, String result);

}
