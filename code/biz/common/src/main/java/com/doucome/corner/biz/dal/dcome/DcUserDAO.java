package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;

import com.doucome.corner.biz.dal.dataobject.dcome.DcUserDO;

/**
 * ��DcUserDAO.java��ʵ����������ޢ�û���ϢDAO
 * 
 * @author ib 2012-7-28 ����09:55:16
 */
public interface DcUserDAO {

	/**
	 * �½��û�
	 * @param dcUserDO
	 * @return
	 */
    long insertUser(DcUserDO dcUserDO);
    /**
     * 
     * @param userId
     * @return
     */
    int deleteUser(Long userId);

    /**
     * 
     * @param dcUserDO
     * @return
     */
    int updateUser(DcUserDO dcUserDO);

    /**
     * ����½ʱ��
     * @param userId
     * @return
     */
    int updateLastLoginTime(long userId);
    
    /**
     * ��������½��ʱ���trace��Ϣ
     * @param userId
     * @param trace
     * @return
     */
    int updateLastLoginTimeAndTrace(long userId, String trace);
    
    /**
     * ���ǩ��ʱ��
     * @param userId
     * @return
     */
    int updateLastCheckinTime(long userId, int checkInCount) ;

    /**
     * ����ID��ѯ�û�
     * @param userId
     * @return
     */
    DcUserDO queryUser(Long userId);
    /**
     * ��ѯ�û�
     * @param userIds
     * @return
     */
    List<DcUserDO> queryUsers(List<Long> userIds);

    /**
     * 
     * @param externalId
     * @param externalPf
     * @return
     */
    DcUserDO queryUserByExternalId(String externalId, String externalPf);
    
    /**
     * ���ӻ���
     * @param userId
     * @param count
     */
    int incrIntegralByUser(long userId , int count) ;
    
    /**
     * ������
     * @param userId
     * @param count
     * @return
     */
    int decrIntegralByUser(long userId , int count) ;
    
    /**
     * ����δ����Ϣ 
     * @param userId
     * @param count
     * @return
     */
    int incrUnreadMsgCountByUser(long userId , int count) ;
    
    /**
     * ��0δ����Ϣ 
     * @param userId
     * @param count
     * @return
     */
    int clearUnreadMsgCountByUser(long userId) ;

    /**
     * ������������ֵ
     * @param userId
     * @param newValue
     * @return
     */
	int updateNewGuide(long userId, long newValue);
	/**
	 * ����û����շ���.
	 * @param userId
	 * @return
	 */
	Integer updateDailyShare(Long userId);
	/**
	 * ��ע�ռ�
	 * @param userId
	 * @return
	 */
	Integer updateFollowQzone(Long userId);
	/**
     * ���Ӳʵ�
     * @param userId
     * @param count
     */
	int incrGoldEggByUser(long userId, int count);

	/**
     * ���ٲʵ�
     * @param userId
     * @param count
     */
	int decrGoldEggByUser(long userId, int count);

	/**
	 * ������ֶ���
	 * @param userId
	 * @param integralCount
	 * @return
	 */
	int unfrozenIntegralByUser(long userId , Integer integralCount) ;

	/**
	 * �������
	 * @param userId
	 * @param integralCount
	 * @return
	 */
	int frozenIntegralByUser(long userId , Integer integralCount) ;

	/**
	 * 
	 * @param userId
	 * @param integralCount
	 * @return
	 */
	int descFrozenIntegralByUser(long userId, Integer integralCount);

	/**
	 * �޸��ǳ�
	 * @param userId
	 * @param nick
	 * @return
	 */
	int updateNick(Long userId, String nick) throws DuplicateKeyException ;
	
	/**
	 * �ϻ����÷�
	 * @param userId
	 * @param score
	 * @return
	 */
	int incrWinnerScoreByUser(long userId , int score) ;
	
	/**
	 * ���ϻ����÷ֱ�ɻ���
	 * @param userId
	 * @return
	 */
	int updateWinnerScoreToIntegralByUser(long userId) ;

	/**
	 * ����֧�����˺�
	 * @param userId
	 * @return
	 */
	int updateAlipayAccountByUser(long userId , String alipayAccount) ;
	
	/**
	 * ����EXTEND_DESC
	 * @param userId
	 * @param extendDesc
	 * @return
	 */
	int updateExtendDescByUser(long userId , String extendDesc);
	/**
	 * 
	 * @param users
	 * @return
	 */
	int batchUpdateUserLevel(List<DcUserDO> users);
}
