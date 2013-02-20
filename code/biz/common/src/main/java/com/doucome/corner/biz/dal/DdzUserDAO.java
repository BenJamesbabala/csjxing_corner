package com.doucome.corner.biz.dal;

import com.doucome.corner.biz.dal.dataobject.DdzUserDO;

/**
 * ������û�
 * 
 * @author langben 2012-3-30
 */
public interface DdzUserDAO {

    /**
     * �½�һ��User
     * 
     * @param searchLog
     * @return
     */
    int insertUser(DdzUserDO user);

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
    DdzUserDO queryByUid(String uid);

    /**
     * ����loginId��ѯ
     * 
     * @param loginId
     * @return
     */
    DdzUserDO queryByLoginId(String loginId);

    /**
     * ����loginId��password��ѯ
     * 
     * @param loginId
     * @return
     */
    DdzUserDO queryByLoginIdAndPassword(String loginId, String md5Password);
    
    /**
     * ����֧����
     * @param loginId
     * @param alipayId
     * @return
     */
    int updateAlipayIdByLoginId(String loginId , String alipayId) ;

	int incrModificationCount(String loginId);
}
