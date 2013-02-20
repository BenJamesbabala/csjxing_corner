package com.doucome.corner.biz.dcome.model.wryneck;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;
import com.doucome.corner.biz.dal.wryneck.dataobject.WryneckTestDO;

@SuppressWarnings("serial")
public class WryneckTestDTO extends AbstractModel {

	private WryneckTestDO wryneckTestDO ;
	
	public WryneckTestDTO(WryneckTestDO wryneckTestDO) {
		if(wryneckTestDO == null){
			wryneckTestDO = new WryneckTestDO() ;
		}
		this.wryneckTestDO = wryneckTestDO ;
		
	}
	
	public WryneckTestDO getWryneckTestDO() {
		return wryneckTestDO;
	}

	public Long getId() {
		return wryneckTestDO.getId();
	}

	public String getName() {
		return wryneckTestDO.getName();
	}

	public String getShowText() {
		return wryneckTestDO.getShowText();
	}

	public Date getGmtCreate() {
		return wryneckTestDO.getGmtCreate();
	}

}
