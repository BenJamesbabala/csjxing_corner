package com.doucome.corner.biz.dcome.model;

import java.util.Date;

import com.doucome.corner.biz.dal.dataobject.dcome.DcUserIntegralDetailDO;
import com.doucome.corner.biz.dal.model.AbstractModel;
import com.doucome.corner.biz.dcome.enums.DcIntegralSourceEnums;

/**
 * 
 * @author ze2200
 *
 */
public class DcUserIntegralDetailDTO extends AbstractModel {
	
	private static final long serialVersionUID = -7374018608432531814L;
	/**
	 * 
	 */
	private DcUserIntegralDetailDO detailDO;
	/**
	 * 积分来源
	 */
	private DcIntegralSourceEnums source;
	/**
	 * 积分描述.
	 */
	private DcIntegralDesc desc;
	
	public DcUserIntegralDetailDTO() {
		this.detailDO = new DcUserIntegralDetailDO();
	}
	
	public DcUserIntegralDetailDTO(DcUserIntegralDetailDO integralDetailDO) {
		this.detailDO = integralDetailDO;
		this.source = DcIntegralSourceEnums.get(detailDO.getSource());
		this.desc = source.fromJson(detailDO.getIntegralDesc());
	}
	
	public DcUserIntegralDetailDO getUserIntegralDetailDO() {
		return this.detailDO;
	}
	
	public Long getId() {
		return detailDO.getId();
	}

	public void setId(Long id) {
		detailDO.setId(id);
	}

	public Long getUserId() {
		return detailDO.getUserId();
	}

	public void setUserId(Long userId) {
		detailDO.setUserId(userId);
	}

	public Integer getIntegralCount() {
		return detailDO.getIntegralCount();
	}

	public void setIntegralCount(Integer integralCount) {
		this.detailDO.setIntegralCount(integralCount);
	}
	
	public String getSource() {
		return source.getValue();
	}
	
	public DcIntegralSourceEnums getSourceEnum() {
		return source;
	}

	public void setSource(DcIntegralSourceEnums source) {
		this.source = source;
		detailDO.setSource(source.getValue());
	}

	public void setIntegralDesc(DcIntegralDesc integralDesc) {
		this.desc = integralDesc;
		if (source != null) {
			detailDO.setIntegralDesc(source.toJson(integralDesc));
		} else {
			detailDO.setIntegralDesc(null);
		}
	}
	
	public void setIntegralDesc(String integralDesc) {
		detailDO.setIntegralDesc(integralDesc);
		if (source != null) {
			this.desc = source.fromJson(integralDesc);
		}
	}
	
	public DcIntegralDesc getIntegralDesc() {
		return this.desc;
	}
	
	public String getIntegralDescStr() {
		return detailDO.getIntegralDesc();
	}
	
	public String getFromObjName() {
		return desc == null? "": desc.getFromObjName();
	}
	
	public Long getFromObjId() {
		if (desc != null) {
			Long result = desc.getFromObjId();
			return result != null? result: -1l;
		}
		return -1l;
	}
	
	public String getToObjName() {
		return desc == null? "": desc.getToObjName();
	}
	
	public Long getToObjId() {
		if (desc != null) {
			Long result = desc.getToObjId();
			return result != null? result: -1l;
		}
		return -1l;
	}
	
	public String getOtherInfo() {
		return desc == null? "": desc.getOtherInfo();
	}
	public Date getGmtCreate() {
		return detailDO.getGmtCreate();
	}

	public void setGmtCreate(Date gmtCreate) {
		this.detailDO.setGmtCreate(gmtCreate);
	}
}