package com.doucome.corner.biz.dcome.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dal.dataobject.dcome.DcUserDO;
import com.doucome.corner.biz.dcome.service.DcUserService;

/**
 * 
 * @author ze2200
 *
 */
public class DcFakeUserBO implements InitializingBean {
	
	private String fakeUserIds;
	
	@Autowired
	private DcUserService dcUserService;
	
	private DcFakeUserSelector selector = DcFakeUserSelector.POLL_SELECTOR;
	
	/**
	 * 
	 * @return
	 */
	public DcUserDO getFakeUser() {
		return selector.nextUser();
	}
	
	public DcUserDO getFakeUser(Long userId) {
		if (userId == null) {
			return null;
		}
		return selector.nextUser(userId);
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		if (selector.getUsers() == null) {
			// TODO Auto-generated method stub
			List<String> tempIds = Arrays.asList(fakeUserIds.split(","));
			List<Long> tempUserIds = new ArrayList<Long>();
			for (String temp : tempIds) {
				tempUserIds.add(Long.valueOf(temp));
			}
			
			List<DcUserDO> fakeUserDOs = dcUserService.queryUsers(tempUserIds);
			selector.setUsers(fakeUserDOs);
		}
	}

	public void setFakeUserIds(String fakeUserIds) {
		this.fakeUserIds = fakeUserIds;
	}
}

enum DcFakeUserSelector {
	POLL_SELECTOR {
		@Override
		public int nextIndex() {
			synchronized (syncObject) {
				curIndex = ++curIndex % fakeUserDOs.size();
			}
			return curIndex;
		}
	};
	
	protected volatile int curIndex = 0;
	
	protected List<DcUserDO> fakeUserDOs;
	
	private static Object syncObject = new Object();
	
	public abstract int nextIndex();
	
	public void setUsers(List<DcUserDO> userDOs) {
		this.fakeUserDOs = userDOs;
	}
	
	public List<DcUserDO> getUsers() {
		return this.fakeUserDOs;
	}
	
	public DcUserDO nextUser() {
		int i = -1;
		while(i > fakeUserDOs.size() || i < 0) {
			i = nextIndex();
		}
		return fakeUserDOs.get(i);
	}
	
	public DcUserDO nextUser(Long userId) {
		if (userId == null || this.fakeUserDOs == null || this.fakeUserDOs.size() == 0) {
			return null;
		}
		for (DcUserDO temp: this.fakeUserDOs) {
			if (userId.equals(temp.getUserId())) {
				return temp;
			}
		}
		return null;
	}
}
