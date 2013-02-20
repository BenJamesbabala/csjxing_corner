package com.doucome.corner.web.dcome.action.frame.q.ajax;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.model.DcPromotionAwardDTO;
import com.doucome.corner.biz.dcome.service.DcPromotionAwardService;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.frame.q.QBasicAction;

/**
 * 更新领奖信息.
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class DcAwardAddrAjaxAction extends QBasicAction {
	
	private DcPromotionAwardDTO awardDTO = new DcPromotionAwardDTO();
	
	private JsonModel<String> json = new JsonModel<String>();
	
	@Autowired
	private DcPromotionAwardService dcPromotionAwardService;
	
	@Override
	public String execute() {
		Long userId = getUserId();
		if (userId == null) {
			json.setFail(JsonModel.CODE_FAIL, "no.login");
			return SUCCESS;
		}
		awardDTO.setUserId(userId);
		int count = dcPromotionAwardService.updateAwardAddrInfo(awardDTO);
		if (count > 0) {
			json.setSuccess(JsonModel.CODE_SUCCESS, "success");
		} else {
			json.setFail(JsonModel.CODE_FAIL, "no.award");
		}
	    return SUCCESS;
	}
	
	public JsonModel<String> getJson() {
		return json;
	}

//	@Override
//	public DcPromotionAwardDTO getModel() {
//		return awardDTO;
//	}
	
	public void setId(Long Id) {
		awardDTO.setId(Id);
	}
	
	public void setItemSize(String itemSize) throws UnsupportedEncodingException {
		awardDTO.setItemSize(URLDecoder.decode(itemSize, "UTF-8"));
	}
	
	public void setItemColor(String itemColor) throws UnsupportedEncodingException {
		awardDTO.setItemColor(URLDecoder.decode(itemColor, "UTF-8"));
	}
	
	public void setDelName(String delName) throws UnsupportedEncodingException {
		awardDTO.setDelName(URLDecoder.decode(delName, "UTF-8"));
	}
	
	public void setDelMobile(String delMobile) throws UnsupportedEncodingException {
		awardDTO.setDelMobile(URLDecoder.decode(delMobile, "UTF-8"));
	}
	
	public void setDelAddr(String delAddr) throws UnsupportedEncodingException {
		awardDTO.setDelAddr(URLDecoder.decode(delAddr, "UTF-8"));
	}
	
	public void setDelOther(String delOther) throws UnsupportedEncodingException {
		awardDTO.setDelOther(URLDecoder.decode(delOther, "UTF-8"));
	}
	
	public void setDelZipcode(String delZipcode) throws UnsupportedEncodingException {
		awardDTO.setDelZipcode(URLDecoder.decode(delZipcode, "UTF-8"));
	}
}
