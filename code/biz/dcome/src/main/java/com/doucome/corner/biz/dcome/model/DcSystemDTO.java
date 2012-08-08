package com.doucome.corner.biz.dcome.model;

import java.util.Date;

import com.doucome.corner.biz.dal.dataobject.dcome.DcSystemDO;

public class DcSystemDTO {

	private DcSystemDO system ;
	
	public DcSystemDTO(DcSystemDO system){
		if(system == null){
			system = new DcSystemDO() ;
		}
		this.system = system ;
	}
	
	public Long getId() {
		return system.getId();
	}

	public String getName() {
		return system.getName();
	}


	public Date getGmtCreate() {
		return system.getGmtCreate();
	}

	
	public Date getGmtModified() {
		return system.getGmtModified();
	}

}
