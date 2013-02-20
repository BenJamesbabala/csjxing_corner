package com.doucome.corner.web.dcome.action.frame.q.ajax;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.qq.QqQueryService;
import com.doucome.corner.biz.dcome.enums.DcLoginSourceEnums;
import com.doucome.corner.biz.dcome.model.facade.PfModel;
import com.doucome.corner.biz.dcome.model.facade.QQPfModel;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.frame.q.QBasicAction;

@SuppressWarnings("serial")
public class DcWeiboAjaxAction extends QBasicAction{

	private JsonModel<Boolean> json = new JsonModel<Boolean>();
	
	@Autowired
	private QqQueryService qqQueryService;
	
	/**
	 *  «∑Ò «Œ¢±°∑€Àø
	 * @return
	 */
	public String isWeiboFans() {
		PfModel pfm = dcAuthz.getPfModel() ;
		if (pfm == null) {
			json.setFail(JsonModel.CODE_FAIL, "invalid.user");
			return SUCCESS;
		}
		if(pfm != null && (pfm.getPf() == DcLoginSourceEnums.Pengyou || pfm.getPf() == DcLoginSourceEnums.Qzone)){
			QQPfModel qpfm = (QQPfModel) pfm ;
			boolean isFans = qqQueryService.isWeiboFans(pfm.getPf().getValue() , qpfm.getOpenId(), qpfm.getOpenKey()) ;
			json.setData(isFans) ;
		} else {
			json.setData(false) ;
		}
		
		json.setCode(JsonModel.CODE_SUCCESS) ;
		return SUCCESS;
	}

	public JsonModel<Boolean> getJson() {
		return json;
	}
	
}
