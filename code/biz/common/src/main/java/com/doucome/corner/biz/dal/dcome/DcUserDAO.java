package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;

import com.doucome.corner.biz.dal.dataobject.dcome.DcUserDO;

/**
 * 类DcUserDAO.java的实现描述：豆蔻用户信息DAO
 * 
 * @author ib 2012-7-28 下午09:55:16
 */
public interface DcUserDAO {

	/**
	 * 新建用户
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
     * 最后登陆时间
     * @param userId
     * @return
     */
    int updateLastLoginTime(long userId);
    
    /**
     * 更新最后登陆的时间和trace信息
     * @param userId
     * @param trace
     * @return
     */
    int updateLastLoginTimeAndTrace(long userId, String trace);
    
    /**
     * 最后签到时间
     * @param userId
     * @return
     */
    int updateLastCheckinTime(long userId, int checkInCount) ;

    /**
     * 根据ID查询用户
     * @param userId
     * @return
     */
    DcUserDO queryUser(Long userId);
    /**
     * 查询用户
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
     * 更新新手引导值
     * @param userId
     * @param newValue
     * @return
     */
	int updateNewGuide(long userId, long newValue);
	/**
	 * 标记用户今日分享.
	 * @param userId
	 * @return
	 */
	Integer updateDailyShare(Long userId);
	/**
	 * 关注空间
	 * @param userId
	 * @return
	 */
	Integer updateFollowQzone(Long userId);
	/**
     * 增加彩蛋
     * @param userId
     * @param count
     */
	int incrGoldEggByUser(long userId, int count);

	/**
     * 减少彩蛋
     * @param userId
     * @param count
     */
	int decrGoldEggByUser(long userId, int count);

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
	 * 
	 * @param userId
	 * @param integralCount
	 * @return
	 */
	int descFrozenIntegralByUser(long userId, Integer integralCount);

	/**
	 * 修改昵称
	 * @param userId
	 * @param nick
	 * @return
	 */
	int updateNick(Long userId, String nick) throws DuplicateKeyException ;
	
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
	 * 更新支付宝账号
	 * @param userId
	 * @return
	 */
	int updateAlipayAccountByUser(long userId , String alipayAccount) ;
	
	/**
	 * 更新EXTEND_DESC
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
