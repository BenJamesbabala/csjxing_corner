package com.doucome.corner.biz.dal.dcome;

import com.doucome.corner.biz.dal.dataobject.dcome.DcGameRuleDO;

/**
 * 活动规则dao
 * @author ze2200
 *
 */
public interface DcGameRuleDAO {
	/**
	 * 
	 * @param gameRule
	 * @return
	 */
	Long insertGameRule(DcGameRuleDO gameRule);
	/**
	 * 
	 * @param id
	 * @return
	 */
	DcGameRuleDO getGameRule(Long id);
	/**
	 * 
	 * @param userId
	 * @param type
	 * @return
	 */
	DcGameRuleDO getUserGameRule(Long userId, String type, String timeId);
	/**
	 * 
	 * @param id
	 * @param presentClickCount
	 * @return
	 */
	//int updateGameDataByID(DcGameRuleDO gameRule);
	/**
	 * 
	 * @param id
	 * @param presentClickCount
	 * @return
	 */
	//int updateGameDataByUserIdAndGame(DcGameRuleDO gameRule);
	/**
	 * 
	 * @param id
	 * @param count
	 * @return
	 */
	int incClickCount(Long id, int count);
	/**
	 * 
	 * @param userId
	 * @param game
	 * @param timeId
	 * @param count
	 * @return
	 */
	int incUserClickCount(Long userId, String gameType, String timeId, int count);
	/**
	 * 
	 * @param count
	 * @return
	 */
	int incPlayCount(Long id, int count);
}
