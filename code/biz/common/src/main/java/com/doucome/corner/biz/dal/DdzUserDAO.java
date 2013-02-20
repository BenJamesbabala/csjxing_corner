package com.doucome.corner.biz.dal;

import com.doucome.corner.biz.dal.dataobject.DdzUserDO;

/**
 * 点点折用户
 * 
 * @author langben 2012-3-30
 */
public interface DdzUserDAO {

    /**
     * 新建一个User
     * 
     * @param searchLog
     * @return
     */
    int insertUser(DdzUserDO user);

    /**
     * 更新用户信息
     * 
     * @param user
     */
    void updateUser(DdzUserDO user);
    /**
     * 更新最后登陆时间
     * @param uid
     */
    void updateLastLoginTime(String uid);

    /**
     * 根据uid查询
     * 
     * @param uid
     * @return
     */
    DdzUserDO queryByUid(String uid);

    /**
     * 根据loginId查询
     * 
     * @param loginId
     * @return
     */
    DdzUserDO queryByLoginId(String loginId);

    /**
     * 根据loginId和password查询
     * 
     * @param loginId
     * @return
     */
    DdzUserDO queryByLoginIdAndPassword(String loginId, String md5Password);
    
    /**
     * 更新支付宝
     * @param loginId
     * @param alipayId
     * @return
     */
    int updateAlipayIdByLoginId(String loginId , String alipayId) ;

	int incrModificationCount(String loginId);
}
