package com.doucome.corner.biz.dcome.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dal.dataobject.dcome.DcGameRuleDO;
import com.doucome.corner.biz.dal.dcome.DcGameRuleDAO;
import com.doucome.corner.biz.dcome.enums.DcGameEnum;
import com.doucome.corner.biz.dcome.model.DcGameRuleDTO;
import com.doucome.corner.biz.dcome.service.DcGameRuleService;

/**
 * 
 * @author ze2200
 *
 */
public class DcGameRuleServiceImpl implements DcGameRuleService {
	@Autowired
	private DcGameRuleDAO dcGameRuleDAO;
	
	@Override
	public Long createGameRule(DcGameRuleDO gameRuleDO) {
		return dcGameRuleDAO.insertGameRule(gameRuleDO);
	}
	
	@Override
	public DcGameRuleDTO getGameRule(Long id) {
		DcGameRuleDO gameRule = dcGameRuleDAO.getGameRule(id);
		if (gameRule == null) {
			return null;
		}
		return new DcGameRuleDTO(gameRule);
	}

	@Override
	public DcGameRuleDTO getUserGameRule(Long userId, DcGameEnum game) {
		DcGameRuleDO gameRule = dcGameRuleDAO.getUserGameRule(userId, game.getType(), game.getTimeId());
		if (gameRule == null) {
			return null;
		}
		return new DcGameRuleDTO(gameRule);
	}

	@Override
	public int incClickCount(Long id, int count) {
		return dcGameRuleDAO.incClickCount(id, count);
	}

	public int incUserClickCount(Long userId, DcGameEnum game, int count) {
		return dcGameRuleDAO.incUserClickCount(userId, game.getType(), game.getTimeId(), count);
	}
	
	@Override
	public int incPlayCount(Long id, int count) {
		return dcGameRuleDAO.incPlayCount(id, count);
	}
	
}
