package com.doucome.corner.biz.dcome.model;

import java.util.Date;

import com.doucome.corner.biz.dal.dataobject.dcome.DcGameRuleDO;
import com.doucome.corner.biz.dal.model.AbstractModel;
import com.doucome.corner.biz.dcome.enums.DcGameEnum;

public class DcGameRuleDTO extends AbstractModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4234887286691377280L;

	private DcGameRuleDO gameRuleDO;
	
	private int canPlayCount;
	
	private DcGameEnum game;
	
	public DcGameRuleDTO() {
		gameRuleDO = new DcGameRuleDO();
	}
	
	public DcGameRuleDTO(DcGameRuleDO gameRuleDO) {
		this.gameRuleDO = gameRuleDO;
		this.canPlayCount = canPlayCount();
		game = DcGameEnum.getInstance(getType());
	}
	
	private int canPlayCount() {
		Integer exchange = getExchangeAmount();
		if (exchange == null || exchange == 0 || getTodayPlayCount() >= getPlayLimit()) {
			return 0;
		}
		int temp = getTodayClickCount() / getExchangeAmount() - getTodayPlayCount();
		if (temp < 0) {
			temp = 0;
		}
		return temp;
	}
	
	public DcGameRuleDO getGameRule() {
		return this.gameRuleDO;
	}
	
	public Long getId() {
		return gameRuleDO.getId();
	}
	
	public void setId(Long id) {
		gameRuleDO.setId(id);
	}

	public Long getUserId() {
		return gameRuleDO.getUserId();
	}

	public String getType() {
		return gameRuleDO.getType();
	}
	
	public String getTimeId() {
		return gameRuleDO.getTimeId();
	}

	public String getStatus() {
		return gameRuleDO.getStatus();
	}

	public Integer getPlayLimit() {
		return gameRuleDO.getPlayLimit();
	}

	public Integer getExchangeAmount() {
		return gameRuleDO.getExchangeAmount();
	}
	
	public int getCanPlayCount() {
		return this.canPlayCount;
	}
	
	public Integer getTodayClickCount() {
		if (game == null) {
			return gameRuleDO.getTodayClickCount(); 
		}
		//创建数据的时候，免费送了用户一次游戏机会.
		int result = gameRuleDO.getTodayClickCount() - game.getExchangeAmount();
		return result < 0? 0: result;
	}
	
	public void setTodayClickCount(int todayClickCount) {
		gameRuleDO.setTodayClickCount(todayClickCount);
	}
	
	public Integer getTodayPlayCount() {
		return gameRuleDO.getTodayPlayCount();
	}
	
	public void setTodayPlayCount(int playCount) {
		gameRuleDO.setTodayPlayCount(playCount);
	}
	
	public Date getGmtModified() {
		return gameRuleDO.getGmtModified();
	}

	public Date getGmtCreate() {
		return gameRuleDO.getGmtCreate();
	}
	
	/**
	 * 是否能玩游戏.
	 * @return
	 */
	public boolean canPlayGame() {
		return getCanPlayCount() > 0 && getTodayPlayCount() < getPlayLimit();
	}
	
	/**
	 * 是否超过了游戏次数限制.
	 * @return
	 */
	public boolean isPlayCountOutOfLimit() {
		return getTodayPlayCount() >= getPlayLimit();
	}
}
