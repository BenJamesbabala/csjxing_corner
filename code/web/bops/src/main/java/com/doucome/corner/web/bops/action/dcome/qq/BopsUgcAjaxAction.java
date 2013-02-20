package com.doucome.corner.web.bops.action.dcome.qq;


import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.business.DcItemBO;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.bops.model.JsonModel;
/**
 * 
 * @author ze2200
 *
 */
public class BopsUgcAjaxAction extends BopsBasicAction {
	
	private static final long serialVersionUID = 1L;

	JsonModel<String> json = new JsonModel<String>();
	
	private Long itemId;
	
	@Autowired
	private DcItemBO dcItemBO;
	
	/**
	 * 
	 * @return
	 */
	public String acceptUgcToPgc() {
		int integral = dcItemBO.acceptUgc(itemId);
		if (integral > 0) {
			json.setSuccess(JsonModel.CODE_SUCCESS, String.valueOf(integral));
		} else {
			json.setFail(JsonModel.CODE_FAIL, "");
		}
		return SUCCESS;
	}
	
	
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public JsonModel<String> getJson() {
		return json;
	}
}
