package com.doucome.corner.web.namefate.action.ajax;

import java.net.URLDecoder;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.apps.namefate.business.NamefateUserBO;
import com.doucome.corner.biz.apps.namefate.enums.NamefateUserGuideEnums;
import com.doucome.corner.biz.apps.namefate.model.NamefateUserDTO;
import com.doucome.corner.biz.apps.namefate.service.NamefateTrickService;
import com.doucome.corner.biz.apps.namefate.service.NamefateUserService;
import com.doucome.corner.biz.apps.namefate.utils.NamefateUserUtils;
import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.dal.namefate.dataobject.NamefateTrickDO;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.namefate.action.NamefateBasicAction;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 测测你是哪种歪脖子
 * @author langben 2013-1-7
 *
 */
@SuppressWarnings("serial")
public class NamefateTrickAjaxAction extends NamefateBasicAction implements ModelDriven<NamefateTrickDO>{

	private JsonModel<Long> json = new JsonModel<Long>() ;
	
	private NamefateTrickDO trick = new NamefateTrickDO() ;
	
	private String extId ;
	
	@Autowired
	private NamefateTrickService namefateTrickService ;
	
	@Autowired
	private NamefateUserService namefateUserService ;
	
	@Autowired
	private NamefateUserBO namefateUserBO ;
	
	@Override
	public String execute() throws Exception {
				
		NamefateUserDTO user = getUser() ;
		
		Long userId = NamefateUserUtils.getUserId(user);
		String userNick = NamefateUserUtils.getUserNick(user) ;
		
		if(StringUtils.isNotBlank(extId)){
			NamefateUserDTO extUserDTO = namefateUserService.getUserByExternalId(extId) ;
			if(extUserDTO != null){
				trick.setUserId(extUserDTO.getUserId()) ;
				trick.setUserNick(extUserDTO.getUserNick()) ;
			}	
			trick.setTrickUserId(userId) ;
			trick.setTrickUserNick(userNick) ;
		} else {
			trick.setUserId(userId) ;
			trick.setUserNick(userNick) ;
			trick.setTrickUserId(userId) ;
			trick.setTrickUserNick(userNick) ;
		}
				
		if(IDUtils.isNotCorrect(trick.getUserId())) {
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("namefate.trick.user.required") ;
			return SUCCESS ;
		}
		
		if(StringUtils.isBlank(trick.getTrickInputName())) {
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("namefate.trick.ipt.name.required") ;
			return SUCCESS ;
		}
		
		if(StringUtils.isBlank(trick.getTrickInputTaName())) {
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("namefate.trick.ipt.taname.required") ;
			return SUCCESS ;
		}
		
		trick.setTrickInputName(URLDecoder.decode(trick.getTrickInputName(), "UTF-8")) ;
		trick.setTrickInputTaName(URLDecoder.decode(trick.getTrickInputTaName(), "UTF-8")) ;
		
		//创建记录。
		Long creatId = namefateTrickService.createTrick(trick) ;
		//未读记录数
		namefateUserService.incrUnreadMsgCount(trick.getUserId()) ;
		
		if(user != null && !user.isGuideDone(NamefateUserGuideEnums.NAMEFATE_TEST.getGuideStr())) {
			namefateUserBO.updateGuideDone(user, NamefateUserGuideEnums.NAMEFATE_TEST) ;
		}
		
		json.setCode(JsonModel.CODE_SUCCESS) ;
		json.setData(creatId) ;
		
		return SUCCESS ;
	}

	public JsonModel<Long> getJson() {
		return json;
	}

	@Override
	public NamefateTrickDO getModel() {
		return trick ;
	}

	public void setExtId(String extId) {
		this.extId = extId;
	}
	
}
