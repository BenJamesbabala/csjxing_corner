package com.doucome.corner.web.wryneck.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.model.wryneck.WryneckUserDTO;
import com.doucome.corner.biz.dcome.service.wryneck.WryneckUserService;
import com.doucome.corner.web.common.action.BasicAction;
import com.doucome.corner.web.wryneck.context.WryneckAuthzContext;
import com.doucome.corner.web.wryneck.context.WryneckAuthzContextHolder;

/**
 * HsBasicAction
 * 
 */
@SuppressWarnings("serial")
public class WryneckBasicAction extends BasicAction {
	@Autowired
	private WryneckUserService wryneckUserService;
	
	private boolean isUserInit;
	
	private WryneckUserDTO user;
	
	public WryneckUserDTO getUser() {
		//保证当前线程内只读取一次数据库.
		if (!isUserInit) {
			user = wryneckUserService.getUser(getUserId());
			isUserInit = true;
		}
		return user;
	}
	
	public Long getUserId() {
		WryneckAuthzContext context = WryneckAuthzContextHolder.getContext();
		return context.getUserId();
	}
	
	public String getUserNick() {
		WryneckAuthzContext context = WryneckAuthzContextHolder.getContext();
		return context.getUserNick();
	}
	
	public String getOpenId() {
		WryneckAuthzContext context = WryneckAuthzContextHolder.getContext();
		return context.getOpenId();
	}
	
	public String getOpenKey() {
		WryneckAuthzContext context = WryneckAuthzContextHolder.getContext();
		return context.getOpenKey();
	}
	
	public String getPf() {
		WryneckAuthzContext context = WryneckAuthzContextHolder.getContext();
		return context.getPf();
	}
}
