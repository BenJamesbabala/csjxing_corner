package com.doucome.corner.biz.dcome.service;

import java.util.List;
import java.util.Map;

import com.doucome.corner.biz.dal.dataobject.dcome.DcUserDO;
import com.doucome.corner.biz.dcome.exception.DcDuplicateKeyException;
import com.doucome.corner.biz.dcome.model.DcUserDTO;

/**
 * ��DcUserService.java��ʵ����������ޢ�û�service
 * 
 * @author ib 2012-7-28 ����11:49:27
 */
public interface DcUserService {

	/**
	 * 
	 * @param dcUserDO
	 * @return
	 */
    long insertUser(DcUserDO dcUserDO);

    /**
     * 
     * @param dcUserDO
     * @return
     */
    int updateUser(DcUserDO dcUserDO);

    /**
     * 
     * @param userId
     * @return
     */
    int updateLastLoginTime(long userId);
    
    /**
     * 
     * @param userId
     * @return
     */
    int updateLastCheckinTime(long userId, int checkInCount) ; 
    
    /**
     * ��������½��ʱ���trace��Ϣ
     * @param userId
     * @param trace
     * @return
     */
    int updateLastLoginTimeAndTrace(long userId , String trace) ;

    /**
     * 
     * @param userId
     * @return
     */
    DcUserDTO getUser(Long userId);
    
    /**
     * ��ȡ�û���Ϣ
     * @param userIds
     * @return
     */
    List<DcUserDTO> queryUsers(List<Long> userIds);
    /**
     * 
     * @param externalId
     * @param externalPf
     * @return
     */
    /**
     * 
     * @param externalId
     * @param externalPf
     * @return
     */
    DcUserDTO queryUserByExternalId(String externalId, String externalPf);
    
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
     * ���Ӳʵ�
     * @param userId
     * @param count
     */
    int incrGoldEggByUser(long userId , int count) ;
    
    /**
     * ���ʵ�
     * @param userId
     * @param count
     * @return
     */
    int decrGoldEggByUser(long userId) ;
    
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
     * ���µڼ�λ
     * @param userId
     * @param index
     */
    int updateNewGuide(long userId , long newValue) ;
    /**
     * ����û�ÿ�շ���.
     * @param userId
     * @return
     */
    int markDailyShare(Long userId);
    
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
	 * ���ٶ������
	 * @param userId
	 * @param integralCount
	 * @return
	 */
	int descFrozenIntegralByUser(long userId , Integer integralCount) ;

    /**
     * ��ע�ռ�.
     * @param userId
     * @return
     */
	int updateFollowQzone(Long userId);
	
	/**
	 * �޸��ǳ�
	 * @param userId
	 * @param nick
	 * @return
	 * @throws DcDuplicateKeyException �ǳ��Ѵ���
	 */
	int updateNick(Long userId , String nick) throws DcDuplicateKeyException ;
	
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
	 * 
	 * @param userId
	 * @return
	 */
	int updateAlipayAccountByUser(long userId , String alipayAccount) ;

	/**
	 * 
	 * @param userId
	 * @param extendDesc
	 * @return
	 */
	int updateExtendDescByUser(long userId, Map<String,String> extendDescMap) ;
}
