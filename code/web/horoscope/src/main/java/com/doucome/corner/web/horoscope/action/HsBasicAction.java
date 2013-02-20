package com.doucome.corner.web.horoscope.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.model.star.HsUserDTO;
import com.doucome.corner.biz.dcome.service.horoscope.HsUserService;
import com.doucome.corner.web.common.action.BasicAction;
import com.doucome.corner.web.horoscope.context.HsAuthzContext;
import com.doucome.corner.web.horoscope.context.HsAuthzContextHolder;

/**
 * HsBasicAction
 * 
 */
@SuppressWarnings("serial")
public class HsBasicAction extends BasicAction {
	@Autowired
	private HsUserService hsUserService;
	
	private boolean isUserInit;
	
	private HsUserDTO user;
	
	public HsUserDTO getUser() {
		//保证当前线程内只读取一次数据库.
		if (!isUserInit) {
			user = hsUserService.getUser(getUserId());
			isUserInit = true;
		}
		return user;
	}
	
	public Long getUserId() {
		HsAuthzContext context = HsAuthzContextHolder.getContext();
		return context.getUserId();
	}
	
	public String getUserNick() {
		HsAuthzContext context = HsAuthzContextHolder.getContext();
		return context.getUserNick();
	}
	
	public String getOpenId() {
		HsAuthzContext context = HsAuthzContextHolder.getContext();
		return context.getOpenId();
	}
	
	public String getOpenKey() {
		HsAuthzContext context = HsAuthzContextHolder.getContext();
		return context.getOpenKey();
	}
	
	public String getPf() {
		HsAuthzContext context = HsAuthzContextHolder.getContext();
		return context.getPf();
	}
}
