package com.doucome.corner.web.dcome.action.frame.q.ajax;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.common.utils.ObjectUtils;
import com.doucome.corner.biz.core.utils.UUIDUtils;
import com.doucome.corner.biz.core.utils.ValidateUtil;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserExchangeApproveDO;
import com.doucome.corner.biz.dcome.business.DcUserExchangeBO;
import com.doucome.corner.biz.dcome.enums.DcExchangeApproveStatusEnums;
import com.doucome.corner.biz.dcome.enums.DcExchangeTypeEnums;
import com.doucome.corner.biz.dcome.exception.IntegralNotEnoughException;
import com.doucome.corner.biz.dcome.model.DcExchangeItemDTO;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.service.DcExchangeItemService;
import com.doucome.corner.biz.dcome.service.DcUserService;
import com.doucome.corner.biz.dcome.utils.DcUserUtils;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.DComeBasicAction;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 兑换申请
 * @author langben 2013-1-12
 *
 */
@SuppressWarnings("serial")
public class DcUserExchangeApproveAjaxAction extends DComeBasicAction implements ModelDriven<DcUserExchangeApproveDO>{

	private DcUserExchangeApproveDO approve = new DcUserExchangeApproveDO() ;
	
	private JsonModel<Boolean> json = new JsonModel<Boolean>() ;
	
	@Autowired
	private DcExchangeItemService dcExchangeItemService ;
	
	@Autowired
	private DcUserExchangeBO dcUserExchangeBO ;
	
	@Autowired
	private DcUserService dcUserService ;
		
	@Override
	public String execute() throws Exception {
		
		DcUserDTO user = getUser() ;
		String userNick = user.getNick() ;
		Long userId = user.getUserId() ;
		
		//String 属性 trim 
		ObjectUtils.trimStringProperties(approve) ;
		
		if(IDUtils.isNotCorrect(userId)) {
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.exchange.user.notLogin") ;
			return SUCCESS ;
		}
		
		if(IDUtils.isNotCorrect(approve.getExItemId())){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.exchange.exItemId.required") ;
			return SUCCESS ;
		}
		
		//兑换份数
		Integer exItemNum = approve.getExItemNum() ;
		if(IDUtils.isNotCorrect(exItemNum)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.exchange.exItemNum.error") ;
			return SUCCESS ;
		}		
		
		int canExCount = user.getCanExchangeCount() ;
		if(canExCount <= 0){//本月到达兑换次数上限
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.exchange.count.monthLimit") ;
			return SUCCESS ;
		}
		
		DcExchangeItemDTO exItem = dcExchangeItemService.getExchangeItem(approve.getExItemId()) ;
		if(exItem == null || DcExchangeTypeEnums.get(exItem.getExType()) != DcExchangeTypeEnums.DEFAULT_EXCHANGE){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.exchange.exType.error") ;
			return SUCCESS ;
		}
		
		//需要的积分 = 份数 X 积分
		int requireIntegral = exItemNum * exItem.getExIntegral() ;
		if(requireIntegral > user.getIntegralCount()){
			//积分不足
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.exchange.integral.limit") ;
			return SUCCESS ;
		}
				
		String reqFields = exItem.getRequireFields() ;
		boolean isAlipayModified = false ;
		if(StringUtils.contains(reqFields, "ALIPAY")) {
			if(!ValidateUtil.checkIsAlipay(approve.getDelAlipay())) {
				//非支付宝
				json.setCode(JsonModel.CODE_ILL_ARGS) ;
				json.setDetail("dcome.exchange.alipay.notCorrect") ;
				return SUCCESS ;
			}
			
			String oriAlipay = user.getAlipayAccount() ;
			String newAlipay = approve.getDelAlipay() ;
			if(!StringUtils.equals(oriAlipay , newAlipay)){
				isAlipayModified = true ;
			}
		} else if(StringUtils.contains(reqFields, "QQ")) {
			if(!ValidateUtil.checkIsQQ(approve.getDelQq())){
				//非QQ
				json.setCode(JsonModel.CODE_ILL_ARGS) ;
				json.setDetail("dcome.exchange.qq.notCorrect") ;
				return SUCCESS ;
			}
		} else if(StringUtils.contains(reqFields, "MOBILE")) {
			if(!ValidateUtil.checkIsMobile(approve.getDelMobile())){
				//非QQ
				json.setCode(JsonModel.CODE_ILL_ARGS) ;
				json.setDetail("dcome.exchange.mobile.notCorrect") ;
				return SUCCESS ;
			}
		}
				
		//插入一条数据
		approve.setCheckCode(UUIDUtils.random20Num()) ;
		approve.setConsumeIntegralCount(requireIntegral) ;
		approve.setUserId(userId) ;
		approve.setUserNick(userNick) ;
		approve.setExItemType(exItem.getItemType()) ;
		approve.setStatus(DcExchangeApproveStatusEnums.APPROVED_PENDING.getValue()) ;
		
		try {
			dcUserExchangeBO.exchange(user, approve, exItem) ;
			
			//更新本月兑换次数
			Map<String,String> exMap = user.getExtendDescMap() ;
			exMap = DcUserUtils.incrExchangeCount(exMap) ;
			dcUserService.updateExtendDescByUser(userId, exMap) ;
			if(isAlipayModified){
				dcUserService.updateAlipayAccountByUser(userId, approve.getDelAlipay()) ;
			}
			json.setCode(JsonModel.CODE_SUCCESS) ;
		} catch (IntegralNotEnoughException e){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.exchange.integral.limit") ;
			return SUCCESS ;
		}
			
		
		return SUCCESS ;
	}

	@Override
	public DcUserExchangeApproveDO getModel() {
		return approve ;
	}

	public JsonModel<Boolean> getJson() {
		return json;
	}

}
