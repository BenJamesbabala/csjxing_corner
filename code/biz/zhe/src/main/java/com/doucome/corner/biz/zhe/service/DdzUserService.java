package com.doucome.corner.biz.zhe.service;

import com.doucome.corner.biz.dal.dataobject.DdzUserDO;

public interface DdzUserService {

    /**
     * �½�һ��User
     * 
     * @param searchLog
     * @return
     */
    int createUser(DdzUserDO user);

    /**
     * �����û���Ϣ
     * 
     * @param user
     */
    void updateUser(DdzUserDO user);
    
    /**
     * ��������½ʱ��
     * @param uid
     */
    void updateLastLoginTime(String uid);

    /**
     * ����uid��ѯ
     * 
     * @param uid
     * @return
     */
    DdzUserDO getByUid(String uid);

    /**
     * ����loginId��ѯ
     * 
     * @param loginId
     * @return
     */
    DdzUserDO getByLoginId(String loginId);

    /**
     * ����loginId��password��ѯ
     * 
     * @param loginId
     * @return
     */
    DdzUserDO getByLoginIdAndPassword(String loginId, String md5Password);
    
    int updateAlipayIdByLoginId(String loginId, String alipayId) ;
    
    int incrModificationCount(String loginId) ;

}
