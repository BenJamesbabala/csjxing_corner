package com.doucome.corner.web.dcome.action.frame.q.ajax;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.business.DcGameBO;
import com.doucome.corner.biz.dcome.enums.DcGameEnum;
import com.doucome.corner.biz.dcome.model.DcGameRuleDTO;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.frame.q.QBasicAction;

/**
 * 
 * @author ze2200
 *
 */
public class DcGameDataAjaxAction extends QBasicAction {
	
	private static final long serialVersionUID = -5626438995204282080L;
	
	private JsonModel<DcGameRuleDTO> json = new JsonModel<DcGameRuleDTO>();
	
	private String game;
	
	@Autowired
	private DcGameBO dcGameBO;
	
	
	public String execute() {
		Long userId = getUserId();
		DcGameEnum gameEnum = DcGameEnum.getInstance(game);
		if (gameEnum == DcGameEnum.UNKNOWN) {
			json.setFail(JsonModel.CODE_FAIL, "unknown.game");
			return SUCCESS;
		}
		DcGameRuleDTO gameRuleDTO = dcGameBO.getUserTodayGameRule(userId, gameEnum);
		json.setSuccess(JsonModel.CODE_SUCCESS, gameRuleDTO);
		return SUCCESS;
	}
	
	public JsonModel<DcGameRuleDTO> getJson() {
		return this.json;
	}
	
	public void setGame(String game) {
		this.game = game;
	}

}
