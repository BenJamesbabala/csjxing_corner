package com.doucome.corner.biz.dcome.service;

import java.util.List;
import java.util.Map;

import com.doucome.corner.biz.dal.dataobject.dcome.DcUserDO;
import com.doucome.corner.biz.dcome.exception.DcDuplicateKeyException;
import com.doucome.corner.biz.dcome.model.DcUserDTO;

/**
 * 类DcUserService.java的实现描述：豆蔻用户service
 * 
 * @author ib 2012-7-28 下午11:49:27
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
     * 更新最后登陆的时间和trace信息
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
     * 获取用户信息
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
     * 增加积分
     * @param userId
     * @param count
     */
    int incrIntegralByUser(long userId , int count) ;
    
    /**
     * 减积分
     * @param userId
     * @param count
     * @return
     */
    int decrIntegralByUser(long userId , int count) ;
    
    /**
     * 增加彩蛋
     * @param userId
     * @param count
     */
    int incrGoldEggByUser(long userId , int count) ;
    
    /**
     * 减彩蛋
     * @param userId
     * @param count
     * @return
     */
    int decrGoldEggByUser(long userId) ;
    
    /**
     * 增加未读消息 
     * @param userId
     * @param count
     * @return
     */
    int incrUnreadMsgCountByUser(long userId , int count) ;
    
    /**
     * 清0未读消息 
     * @param userId
     * @param count
     * @return
     */
    int clearUnreadMsgCountByUser(long userId) ;
    
    /**
     * 更新第几位
     * @param userId
     * @param index
     */
    int updateNewGuide(long userId , long newValue) ;
    /**
     * 标记用户每日分享.
     * @param userId
     * @return
     */
    int markDailyShare(Long userId);
    
    /**
	 * 解除积分冻结
	 * @param userId
	 * @param integralCount
	 * @return
	 */
	int unfrozenIntegralByUser(long userId , Integer integralCount) ;

	/**
	 * 冻结积分
	 * @param userId
	 * @param integralCount
	 * @return
	 */
	int frozenIntegralByUser(long userId , Integer integralCount) ;
	
	/**
	 * 减少冻结积分
	 * @param userId
	 * @param integralCount
	 * @return
	 */
	int descFrozenIntegralByUser(long userId , Integer integralCount) ;

    /**
     * 关注空间.
     * @param userId
     * @return
     */
	int updateFollowQzone(Long userId);
	
	/**
	 * 修改昵称
	 * @param userId
	 * @param nick
	 * @return
	 * @throws DcDuplicateKeyException 昵称已存在
	 */
	int updateNick(Long userId , String nick) throws DcDuplicateKeyException ;
	
	/**
	 * 老虎机得分
	 * @param userId
	 * @param score
	 * @return
	 */
	int incrWinnerScoreByUser(long userId , int score) ;
	
	/**
	 * 将老虎机得分变成积分
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
