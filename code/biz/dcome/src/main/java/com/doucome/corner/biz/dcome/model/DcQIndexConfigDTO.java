package com.doucome.corner.biz.dcome.model;

import java.util.Date;

import com.doucome.corner.biz.dal.dataobject.dcome.DcQIndexConfigDO;
import com.doucome.corner.biz.dal.model.AbstractModel;

@SuppressWarnings("serial")
public class DcQIndexConfigDTO extends AbstractModel {

	private DcQIndexConfigDO config ;
	
	public DcQIndexConfigDTO(DcQIndexConfigDO config){
		if(config == null){
			config = new DcQIndexConfigDO() ;
			return ;
		}
		this.config = config ;
	}
	
	public Long getId() {
		return config.getId();
	}

	
	public Long getSystemId() {
		return config.getSystemId();
	}

	public Date getGmtCreate() {
		return config.getGmtCreate() ;
	}



	public Date getGmtModified() {
		return config.getGmtModified() ;
	}

	public Long getSceneId() {
		return config.getSceneId();
	}

	
	public String getPubStatus() {
		return config.getPubStatus();
	}

}
