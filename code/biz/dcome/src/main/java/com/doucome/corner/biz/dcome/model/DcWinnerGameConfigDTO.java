package com.doucome.corner.biz.dcome.model;

import java.util.Date;

import com.doucome.corner.biz.dal.dataobject.dcome.DcWinnerGameConfigDO;
import com.doucome.corner.biz.dal.model.AbstractModel;

public class DcWinnerGameConfigDTO extends AbstractModel {

	private DcWinnerGameConfigDO config ;
	
	public DcWinnerGameConfigDTO(DcWinnerGameConfigDO config){
		if(config == null){
			config = new DcWinnerGameConfigDO() ;
		}
		this.config = config ;
	}
	
	public Long getId() {
		return config.getId();
	}

	public String getCardName() {
		return config.getCardName();
	}
	
	public Integer getProbability() {
		return config.getProbability();
	}

	public Integer getScore() {
		return config.getScore();
	}

	public Date getGmtCreate() {
		return config.getGmtCreate();
	}

	public Date getGmtModified() {
		return config.getGmtModified();
	}

}
