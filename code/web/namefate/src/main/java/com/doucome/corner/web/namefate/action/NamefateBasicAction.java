package com.doucome.corner.web.namefate.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.apps.namefate.model.NamefateUserDTO;
import com.doucome.corner.biz.apps.namefate.service.NamefateUserService;
import com.doucome.corner.web.common.action.BasicAction;
import com.doucome.corner.web.namefate.action.context.NamefateAuthzContext;
import com.doucome.corner.web.namefate.action.context.NamefateAuthzContextHolder;

/**
 * HsBasicAction
 * 
 */
@SuppressWarnings("serial")
public class NamefateBasicAction extends BasicAction {
	@Autowired
	private NamefateUserService namefateUserService;
	
	private boolean isUserInit;
	
	private NamefateUserDTO user;
	
	public NamefateUserDTO getUser() {
		//保证当前线程内只读取一次数据库.
		if (!isUserInit) {
			user = namefateUserService.getUser(getUserId());
			isUserInit = true;
		}
		return user;
	}
	
	public Long getUserId() {
		NamefateAuthzContext context = NamefateAuthzContextHolder.getContext();
		return context.getUserId();
	}
	
	public String getUserNick() {
		NamefateAuthzContext context = NamefateAuthzContextHolder.getContext();
		return context.getUserNick();
	}
	
	public String getOpenId() {
		NamefateAuthzContext context = NamefateAuthzContextHolder.getContext();
		return context.getOpenId();
	}
	
	public String getOpenKey() {
		NamefateAuthzContext context = NamefateAuthzContextHolder.getContext();
		return context.getOpenKey();
	}
	
	public String getPf() {
		NamefateAuthzContext context = NamefateAuthzContextHolder.getContext();
		return context.getPf();
	}
}
