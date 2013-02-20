package com.doucome.corner.biz.zhe.service;

import com.doucome.corner.biz.dal.dataobject.DdzUserDO;

public interface DdzUserService {

    /**
     * 新建一个User
     * 
     * @param searchLog
     * @return
     */
    int createUser(DdzUserDO user);

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
    DdzUserDO getByUid(String uid);

    /**
     * 根据loginId查询
     * 
     * @param loginId
     * @return
     */
    DdzUserDO getByLoginId(String loginId);

    /**
     * 根据loginId和password查询
     * 
     * @param loginId
     * @return
     */
    DdzUserDO getByLoginIdAndPassword(String loginId, String md5Password);
    
    int updateAlipayIdByLoginId(String loginId, String alipayId) ;
    
    int incrModificationCount(String loginId) ;

}
