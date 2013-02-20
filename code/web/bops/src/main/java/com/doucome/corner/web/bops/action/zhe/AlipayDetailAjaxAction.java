package com.doucome.corner.web.bops.action.zhe;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.enums.SettleStatusEnums;
import com.doucome.corner.biz.dal.dataobject.DdzAccountDO;
import com.doucome.corner.biz.zhe.service.DdzAccountService;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportSettleService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.bops.model.AlipayDetailModel;
import com.doucome.corner.web.bops.model.JsonModel;

/**
 * 根据支付宝账号获取详细信息
 * @author langben 2012-7-2
 *
 */
@SuppressWarnings("serial")
public class AlipayDetailAjaxAction extends  BopsBasicAction {

	@Autowired
	private DdzTaokeReportSettleService ddzTaokeReportSettleService ; 
	
	@Autowired
	private DdzAccountService ddzAccountService ;
	
	private String alipayId ;
	
	private JsonModel<AlipayDetailModel> json = new JsonModel<AlipayDetailModel>() ;
	
	@Override
	public String execute() throws Exception {
		
		AlipayDetailModel alipayModel = new AlipayDetailModel() ;
		try {
			DdzAccountDO account = ddzAccountService.queryAccountDOByAlipayId(alipayId) ;
			
			if(account != null) {
				alipayModel.setAlipayId(alipayId) ;
				
				alipayModel.setGmtCreate(account.getGmtCreate()) ;
				
				BigDecimal totalSettleFee = ddzTaokeReportSettleService.getTotalSettleFee(alipayId, 
						new String[]{SettleStatusEnums.SETTLE_SUCCESS.getValue() , SettleStatusEnums.SETTLE_CANCEL.getValue() , SettleStatusEnums.SETTLE_FAIL.getValue() , SettleStatusEnums.SETTLE_PENDING.getValue()}) ;
				
				int settleCount = ddzTaokeReportSettleService.countTotalSettle(alipayId, 
							new String[]{SettleStatusEnums.SETTLE_SUCCESS.getValue() , SettleStatusEnums.SETTLE_CANCEL.getValue() , SettleStatusEnums.SETTLE_FAIL.getValue() , SettleStatusEnums.SETTLE_PENDING.getValue()}) ;
				
				alipayModel.setTotalSettleFee(totalSettleFee) ;
				
				alipayModel.setSettleCount(settleCount) ;
				
			}
			json.setCode(JsonModel.CODE_SUCCESS) ;
			json.setData(alipayModel) ;
		}catch(Exception e){
			json.setCode(JsonModel.CODE_FAIL) ;
			json.setDetail(e.getMessage()) ;
		}
		
		return SUCCESS ;
	}

	public JsonModel<AlipayDetailModel> getJson() {
		return json;
	}

	public void setAlipayId(String alipayId) {
		this.alipayId = alipayId;
	}
	
}
