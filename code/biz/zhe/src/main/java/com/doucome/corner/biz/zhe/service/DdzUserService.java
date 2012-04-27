package com.doucome.corner.biz.zhe.service;

import com.doucome.corner.biz.dal.dataobject.DdzUserDO;

public interface DdzUserService {

	/**
	 * 新建一个User
	 * @param searchLog
	 * @return
	 */
	int createUser(DdzUserDO user) ;
	
	/**
	 * 根据uid查询
	 * @param uid
	 * @return
	 */
	DdzUserDO getByUid(String uid) ;
	
	/**
	 * 根据loginId查询
	 * @param loginId
	 * @return
	 */
	DdzUserDO getByLoginId(String loginId) ;
	
	/**
	 * 根据loginId和password查询
	 * @param loginId
	 * @return
	 */
	DdzUserDO getByLoginIdAndPassword(String loginId , String md5Password) ;
	
}
