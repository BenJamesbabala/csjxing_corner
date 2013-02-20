package com.doucome.corner.web.bops.action.zhe;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.core.enums.OutCodeEnums;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserDO;
import com.doucome.corner.biz.dcome.business.DcUserIntegralOperateBO;
import com.doucome.corner.biz.dcome.enums.DcUserIntegralInOutTypeEnums;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.model.DcUserIntegralRecordDTO;
import com.doucome.corner.biz.dcome.service.DcUserIntegralRecordService;
import com.doucome.corner.biz.dcome.service.DcUserService;
import com.doucome.corner.biz.zhe.enums.DdzRefundStatusEnums;
import com.doucome.corner.biz.zhe.service.DdzAccountService;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.bops.model.JsonModel;

/**
 * 用户维权打标
 * @author langben 2012-9-19
 *
 */
@SuppressWarnings("serial")
public class AccountRefundAjaxAction extends  BopsBasicAction{
	
	private static final Log log = LogFactory.getLog(AccountRefundAjaxAction.class) ;

	@Autowired
	private DdzTaokeReportService ddzTaokeReportService ;
	
	@Autowired
	private DdzAccountService ddzAccountService ;
	
	@Autowired
	private DcUserIntegralOperateBO dcUserIntegralOperateBO ;
	
	@Autowired
	private DcUserIntegralRecordService  dcUserIntegralRecordService ;
	
	@Autowired
	private DcUserService dcUserService ;
	
	private JsonModel<Boolean> json = new JsonModel<Boolean>() ;
	
	private String isRefund ;
	
	private Long reportId ;
	
	@Override
	public String execute() throws Exception {
		
		DdzRefundStatusEnums enums = DdzRefundStatusEnums.getInstance(isRefund) ;
		//现在不支持取消维权标记
		if(enums != DdzRefundStatusEnums.REFUND_TRUE || IDUtils.isNotCorrect(reportId)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("ddz.report.refund.unknown") ;
			return SUCCESS ;
		}
		
		try {
			
			DdzTaokeReportDO report = ddzTaokeReportService.getReportById(reportId) ;
			
			if(report == null){
				json.setCode(JsonModel.CODE_ILL_ARGS) ;
				json.setDetail("ddz.report.refund.report.notExists") ;
				return SUCCESS ;
			}
			
			DdzRefundStatusEnums refundStatusEnums = DdzRefundStatusEnums.getInstance(report.getRefundStatus()) ;
			
			if( refundStatusEnums == DdzRefundStatusEnums.REFUND_PAYBACK || refundStatusEnums == DdzRefundStatusEnums.REFUND_TRUE){
				json.setCode(JsonModel.CODE_ILL_ARGS) ;
				json.setDetail("ddz.report.refund.alreay") ;
				return SUCCESS ;
			}
			
			OutCodeEnums outCodeEnums = OutCodeEnums.toOutCodeEnums(report.getOutcodeType()) ;
			
			if(outCodeEnums == OutCodeEnums.DOUCOME_USER_ID) {
			
				Long reportId = report.getId() ;
				
				//充值记录
				DcUserIntegralRecordDTO chargeDTO = dcUserIntegralRecordService.getRecordByReportId(reportId) ;
				if(chargeDTO != null && DcUserIntegralInOutTypeEnums.get(chargeDTO.getInOutType()) == DcUserIntegralInOutTypeEnums.INCOME){
					int chargeIntegralCount = chargeDTO.getIntegralCount() ;
					Long userId = chargeDTO.getUserId() ;
					DcUserDTO user = dcUserService.getUser(userId) ;
					if(user == null){
						DcUserDO userMock = new DcUserDO() ;
						userMock.setUserId(userId) ;
						user = new DcUserDTO(userMock) ;
					}
					//扣除积分
					dcUserIntegralOperateBO.doDcRefund(user, chargeIntegralCount, report) ;
				}
				
				//豆蔻的UserId类型，直接扣除积分，订正为维权已还。
				int effectCount = ddzTaokeReportService.updateRefundById(reportId, DdzRefundStatusEnums.REFUND_PAYBACK) ;
			} else {
				int effectCount = ddzTaokeReportService.updateRefundById(reportId, enums) ;
				if(effectCount > 0){
					String alipayId = report.getSettleAlipay() ;
					if(StringUtils.isNotBlank(alipayId)) {
						ddzAccountService.incrRefundCountByAlipayId(alipayId, 1) ;
					}
				}
			}
			
			json.setCode(JsonModel.CODE_SUCCESS) ;
			
		} catch (Exception e){
			json.setCode(JsonModel.CODE_FAIL) ;
			json.setDetail(e.getMessage()) ;
		}
		
		return SUCCESS ;
	}

	public void setIsRefund(String isRefund) {
		this.isRefund = isRefund;
	}

	public JsonModel<Boolean> getJson() {
		return json;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}
	
	
}
