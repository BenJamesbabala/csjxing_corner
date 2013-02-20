package com.doucome.corner.biz.dcome.business;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.doucome.corner.biz.core.constant.DecimalConstant;
import com.doucome.corner.biz.core.enums.OutCodeEnums;
import com.doucome.corner.biz.core.enums.SettleStatusEnums;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserExchangeApproveDO;
import com.doucome.corner.biz.dcome.exception.IntegralNotEnoughException;
import com.doucome.corner.biz.dcome.model.DcExchangeItemDTO;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.service.DcExchangeItemService;
import com.doucome.corner.biz.dcome.service.DcUserExchangeApproveService;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportSettleService;

public class DcUserExchangeBO {

	@Autowired
	private DcExchangeItemService dcExchangeItemService ;
	
	@Autowired
	private DdzTaokeReportSettleService ddzTaokeReportSettleService ;
	
	@Autowired
	private DcUserIntegralOperateBO dcUserIntegralOperateBO ;
	
	@Autowired
	private DcUserExchangeApproveService dcUserExchangeApproveService ;
	
	@Transactional(rollbackFor=RuntimeException.class)
	public void exchange(DcUserDTO user , DcUserExchangeApproveDO approve , DcExchangeItemDTO exItem) throws IntegralNotEnoughException {
		
		int requireIntegral = approve.getConsumeIntegralCount() ;
		
		//集分宝的插入结算记录
		if(StringUtils.equals(exItem.getItemType(),"JFB")){
			DdzTaokeReportSettleDO settleDO = new DdzTaokeReportSettleDO() ;
			settleDO.setOutcodeType(OutCodeEnums.DOUCOME_USER_ID.getName()) ;
			settleDO.setSettleAlipay(approve.getDelAlipay()) ;
			settleDO.setSettleJfb(requireIntegral) ;
			settleDO.setSettleFee(new BigDecimal(requireIntegral).divide(DecimalConstant.HUNDRED)) ;
			settleDO.setSettleStatus(SettleStatusEnums.UNSETTLE.getValue()) ;
			long settleId = ddzTaokeReportSettleService.insertSettleReport(settleDO) ;
			approve.setSettleId(settleId) ;
		}
		
		//
		long id = dcUserExchangeApproveService.createExchangeApprove(approve) ;
			
		//消耗积分
		dcUserIntegralOperateBO.doExchange(user, requireIntegral, exItem.toDO() , approve) ;
	}
}
