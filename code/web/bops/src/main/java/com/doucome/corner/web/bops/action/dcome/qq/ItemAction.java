package com.doucome.corner.web.bops.action.dcome.qq;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dal.dataobject.dcome.DcItemDO;
import com.doucome.corner.biz.dcome.business.DcImageUploadBO;
import com.doucome.corner.biz.dcome.enums.DcItemStatusEnum;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.biz.zhe.utils.DcStringUtils;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class ItemAction extends BopsBasicAction implements ModelDriven<DcItemDTO> {
	
	private static final Log logger = LogFactory.getLog(ItemAction.class);
	
	private DcItemDTO dcItemDTO = new DcItemDTO(null);
	
	private long itemId;
	
	@Autowired
	private DcItemService dcItemService;
	
	@Autowired
	private DcImageUploadBO dcImageUploadBO;
	
	@Override
	public String execute() {
		DcItemDO itemDO = dcItemDTO.getItem();
		String[] picUrls = uploadPic(dcItemDTO.getPicUrlList());
		itemDO.setPicUrls(DcStringUtils.concat(picUrls));
		itemDO.setStatus(DcItemStatusEnum.NORMAL.getValue());
		int count = 1;
		if (dcItemDTO.getId() == null) {
			itemId = dcItemService.createItem(itemDO);
		} else {
			itemId = dcItemDTO.getId();
			count = dcItemService.updateItem(itemDO);
		}
		if (count <= 0 || itemId <= 0L) {
			return BOPS_ERROR;
		}
		return SUCCESS;
	}
	
	private String[] uploadPic(List<String> picUrls) {
		if (picUrls == null || picUrls.size() == 0) {
			return new String[0];
		}
		List<String> result = new ArrayList<String>();
		for (String tempUrl: picUrls) {
			if (StringUtils.isNotBlank(tempUrl)) {
				if (tempUrl.indexOf("taobao") != -1) {
					try {
						String uploadUrl = dcImageUploadBO.uploadItemPictureFromUrl(tempUrl);
						result.add(uploadUrl);
					} catch (Exception e) {
						logger.error(e);
					}
				} else {
					result.add(tempUrl);
				}
			}
		}
		return (String[]) result.toArray(new String[result.size()]);
	}

	@Override
	public DcItemDTO getModel() {
		return this.dcItemDTO;
	}

	public void setDcItemDTO(DcItemDTO dcItemDTO) {
		this.dcItemDTO = dcItemDTO;
	}
	
	public long getItemId() {
		return this.itemId;
	}
}
