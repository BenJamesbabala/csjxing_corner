package com.doucome.corner.biz.dcome.business;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.dal.dataobject.dcome.DcGameRuleDO;
import com.doucome.corner.biz.dcome.enums.DcGameAwardEnum;
import com.doucome.corner.biz.dcome.enums.DcGameEnum;
import com.doucome.corner.biz.dcome.model.DcGameRuleDTO;
import com.doucome.corner.biz.dcome.model.param.DcGameAwardModel;
import com.doucome.corner.biz.dcome.res.DcGameAwardPool;
import com.doucome.corner.biz.dcome.service.DcGameRuleService;
import com.doucome.corner.biz.dcome.service.DcPromotionItemService;
import com.doucome.corner.biz.dcome.service.DcUserService;
import com.doucome.corner.web.common.model.ResultModel;

/**
 * 
 * @author ze2200
 *
 */
public class DcGameBO {
	@Autowired
	private DcGameRuleService dcGameRuleService;
	@Autowired
	private DcUserService dcUserService;
	@Autowired
	private DcPromotionItemService dcPromotionItemService;
	
	private static final Log logger = LogFactory.getLog(DcGameBO.class);
	
	/**
	 * 创建用户今日游戏数据
	 * @param userId
	 * @param game
	 * @return
	 */
	public DcGameRuleDTO createUserGameRule(Long userId, DcGameEnum game) {
		if (IDUtils.isNotCorrect(userId) || game == null || game == DcGameEnum.UNKNOWN) {
			return null;
		}
		DcGameRuleDTO gameRule = getDefaultGameRule(userId, game);
		Long id = dcGameRuleService.createGameRule(gameRule.getGameRule());
		gameRule.setId(id);
		return gameRule;
	}
	
	/**
	 * 创建用户今日游戏数据,默认送一次游戏机会.
	 * @param userId
	 * @param game
	 * @return
	 */
	public DcGameRuleDTO createUserGameRuleWith1GameChance(Long userId, DcGameEnum game) {
		if (IDUtils.isNotCorrect(userId) || game == null || game == DcGameEnum.UNKNOWN) {
			return null;
		}
		DcGameRuleDTO gameRule = getDefaultGameRule(userId, game);
		//默认送一次游戏机会
		gameRule.setTodayClickCount(game.getExchangeAmount());
		Long id = dcGameRuleService.createGameRule(gameRule.getGameRule());
		gameRule.setId(id);
		return gameRule;
	}
	
	/**
	 * 获取用户今日游戏数据.没有会创建一条有一次游戏机会的记录.
	 * @param userId
	 * @param game
	 * @return
	 */
	public DcGameRuleDTO getUserTodayGameRule(Long userId, DcGameEnum game) {
		if (IDUtils.isNotCorrect(userId) || game == null || game == DcGameEnum.UNKNOWN) {
			return null;
		}
		try {
			DcGameRuleDTO gameRuleDTO = dcGameRuleService.getUserGameRule(userId, game);
			if (gameRuleDTO == null) {
				gameRuleDTO = createUserGameRuleWith1GameChance(userId, game);
			}
			return gameRuleDTO;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	/**
	 * 奖励一次游戏机会
	 * @param userId
	 * @param game
	 * @return
	 */
    public boolean award1GameChance(Long userId, DcGameEnum game) {
    	if (!IDUtils.isCorrect(userId) || game == null) {
    		return false;
    	}
    	try {
    	//送一次游戏机会
	    	int count = dcGameRuleService.incUserClickCount(userId, game, game.getExchangeAmount());
	    	if (count == 0) {
	    		createUserGameRuleWith1GameChance(userId, game);
	    	}
	    	return true;
    	} catch (Exception e) {
    		logger.error(e);
    		return false;
    	}
    }
    
    /**
     * 
     * @return
     */
    public boolean incTodayClickCount(Long userId, DcGameEnum game, int count) {
    	if (!IDUtils.isCorrect(userId) || game == null) {
    		return false;
    	}
    	try {
    		int amount = dcGameRuleService.incUserClickCount(userId, game, count);
    		if (amount == 0) {
    			createUserGameRule(userId, game);
    		}
	    	return true;
    	} catch (Exception e) {
    		logger.error(e);
    		return false;
    	}
    }
	
	/**
	 * 砸金蛋.
	 * @param user
	 * @return
	 */
    public ResultModel<DcGameAwardModel> smashEgg(Long userId, Long promItemId) {
    	ResultModel<DcGameAwardModel> result = new ResultModel<DcGameAwardModel>();
    	if (IDUtils.isNotCorrect(userId)|| IDUtils.isNotCorrect(promItemId)) {
    		result.setFail(ResultModel.CODE_FAIL, "invalid.param");
    		return result;
    	}
    	DcGameRuleDTO gameRule = dcGameRuleService.getUserGameRule(userId, DcGameEnum.GOLDEN_EGG);
    	if (gameRule == null) {
    		//砸蛋前肯定已经创建了gameRule
    		result.setFail(ResultModel.CODE_FAIL, "cant.play");
    		return result;
    	}
    	if (!gameRule.canPlayGame()) {
    		String detail = "cant.play";
    		if (gameRule.isPlayCountOutOfLimit()) {
    			detail = "play.limited";
    		}
    		result.setFail(ResultModel.CODE_FAIL, detail);
    		return result;
    	}
    	
    	int count = dcGameRuleService.incPlayCount(gameRule.getId(), 1);
    	if (count == 0) {
    		result.setFail(ResultModel.CODE_FAIL, "internal.error");
    		return result;
    	}
    	DcGameAwardEnum gameAward = DcGameAwardPool.POOL.getSmashEggAward();
    	DcGameAwardModel gameAwardDTO = new DcGameAwardModel();
    	gameAwardDTO.setAward(gameAward);
    	
    	if (gameAward == DcGameAwardEnum.INTEGRAL) {
    		dcUserService.incrIntegralByUser(userId, gameAward.getAmount());
    	} else if (gameAward == DcGameAwardEnum.WISH_STAR) {
    		dcPromotionItemService.incrRateCountById(promItemId);
    	}
    	
    	result.setSuccess(ResultModel.CODE_SUCCESS, gameAwardDTO);
    	return result;
    }
    
    /**
     * 
     * @param userId
     * @param game
     * @return
     */
    private DcGameRuleDTO getDefaultGameRule(Long userId, DcGameEnum game) {
    	DcGameRuleDO gameRule = new DcGameRuleDO();
    	gameRule.setUserId(userId);
    	gameRule.setType(game.getType());
    	gameRule.setTimeId(game.getTimeId());
    	gameRule.setStatus("R");
    	gameRule.setPlayLimit(game.getPlayLimit());
    	gameRule.setExchangeAmount(game.getExchangeAmount());
    	gameRule.setTodayPlayCount(0);
    	gameRule.setTodayClickCount(1);
    	return new DcGameRuleDTO(gameRule);
    }
}