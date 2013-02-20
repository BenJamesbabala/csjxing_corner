package com.doucome.corner.biz.dcome.service;

import com.doucome.corner.biz.dal.dataobject.dcome.DcGameRuleDO;
import com.doucome.corner.biz.dcome.enums.DcGameEnum;
import com.doucome.corner.biz.dcome.model.DcGameRuleDTO;

public interface DcGameRuleService {
	/**
	 * 
	 * @param gameRuleDO
	 * @return
	 */
	Long createGameRule(DcGameRuleDO gameRuleDO);
	/**
	 * 
	 * @param id
	 * @return
	 */
	DcGameRuleDTO getGameRule(Long id);
	/**
	 * 
	 * @param userId
	 * @param game
	 * @return
	 */
	DcGameRuleDTO getUserGameRule(Long userId, DcGameEnum game);
	/**
	 * ����id����todayClickCount,todayPlayCount.�����в�������.
	 * @param id
	 * @param awardClickCount
	 * @return
	 */
	//int updateGameDataByID(DcGameRuleDO gameRule);
	/**
	 * ����userId, game����todayClickCount,todayPlayCount.�����в�������.
	 * @param gameRule
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
	 * @param count
	 * @return
	 */
	int incUserClickCount(Long userId, DcGameEnum game, int count);
	/**
	 * 
	 * @param count
	 * @return
	 */
	int incPlayCount(Long id, int count);
}
