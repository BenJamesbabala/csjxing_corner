package com.doucome.corner.web.bops.action.dcome.qq;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.business.DcItemBO;
import com.doucome.corner.biz.dcome.enums.DcItemGenWayEnums;
import com.doucome.corner.biz.dcome.exception.DcDuplicateKeyException;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * @author ze2200
 * 
 */
@SuppressWarnings("serial")
public class DcPgcItemAction extends BopsBasicAction implements ModelDriven<DcItemDTO> {
	
	private static final Log logger = LogFactory.getLog(DcPgcItemAction.class);
	
	private DcItemDTO dcItemDTO = new DcItemDTO(null);
	
	private Long itemId;
	
	private List<String> picUrlList ;
	
	@Autowired
	private DcItemBO dcItemBO ;
	
	@Autowired
	private DcItemService dcItemService;

	@Override
	public String execute() {
		if (dcItemDTO.getId() == null) {
			itemId = getDcItemId(dcItemDTO.getNativeId());
			dcItemDTO.setId(itemId);
		}
		
		try {
			if (dcItemDTO.getId() == null) {
				itemId = dcItemBO.createPgcItem(dcItemDTO, picUrlList) ;
			} else {
				itemId = dcItemDTO.getId();
				dcItemBO.updateItem(dcItemDTO, picUrlList);
			}
		} catch (DcDuplicateKeyException e) {
			itemId = getDcItemId(dcItemDTO.getNativeId());
			if (itemId == null) {
				return BOPS_ERROR;
			}
		} catch (Exception e) {
			logger.error(e);
			return BOPS_ERROR;
		}
		return SUCCESS;
	}
	
	private Long getDcItemId(String externalId) {
		List<DcItemDTO> items = dcItemService.getItemsByExtlId(externalId);
		for (DcItemDTO temp: items) {
			if (DcItemGenWayEnums.PROFESSIONAL.getValue().equals(temp.getGenWay())) {
				return temp.getId();
			}
		}
		return null;
	}
	
	@Override
	public DcItemDTO getModel() {
		return this.dcItemDTO;
	}

	public void setDcItemDTO(DcItemDTO dcItemDTO) {
		this.dcItemDTO = dcItemDTO;
	}
	
	public Long getItemId() {
		return this.itemId;
	}

	public void setPicUrlList(List<String> picUrlList) {
		this.picUrlList = picUrlList;
	}


	
}
