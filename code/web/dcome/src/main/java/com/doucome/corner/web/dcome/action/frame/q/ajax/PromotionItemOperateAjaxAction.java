package com.doucome.corner.web.dcome.action.frame.q.ajax;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.dcome.business.DcPromotionBO;
import com.doucome.corner.biz.dcome.business.DcRateCtuBO;
import com.doucome.corner.biz.dcome.exception.DuplicateOperateException;
import com.doucome.corner.biz.dcome.exception.PromotionRateException;
import com.doucome.corner.biz.dcome.model.DcRiskDegreeDTO;
import com.doucome.corner.biz.dcome.model.facade.DcUserModel;
import com.doucome.corner.biz.dcome.model.facade.PromRateResult;
import com.doucome.corner.biz.dcome.model.util.DcPromotionItemUtils;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.DComeBasicAction;

/**
 * 活动Item
 * @author langben 2012-8-25
 *
 */
@SuppressWarnings("serial")
public class PromotionItemOperateAjaxAction extends DComeBasicAction {

	private static final Log log = LogFactory.getLog(PromotionItemOperateAjaxAction.class) ;
	
	private Long promItemId ; 
	
	private Long stealPromItemId ;
	
	private Long promotionId ;
	
	private JsonModel<PromRateResult> json = new JsonModel<PromRateResult>() ;
	
	@Autowired
	private DcPromotionBO dcPromotionBO ; 
	
	@Autowired
	private DcRateCtuBO dcRateCtuBO ;
	
	/**
	 * 投票
	 * @return
	 */
	public String addRate(){
		DcUserModel user = dcAuthz.getModel() ;
		
		String errMsg = this.validateRate(promItemId , user.getUserId() , promotionId);
		
		if(StringUtils.isNotBlank(errMsg)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail(errMsg) ;
			return SUCCESS ;
		}
		try {
			
			/**
			 * 看是否有违规
			 */
			DcRiskDegreeDTO riskDegree = dcRateCtuBO.checkSecurityDegree(getRequest(), user.getUserId(), promItemId) ;
			
			if(riskDegree.isNeedToBlock()){
				//
			}
			
			dcPromotionBO.addRate(user , promotionId , promItemId);
			//投票打点
			dcRateCtuBO.logRateEvent(getRequest(), user.getUserId(), promItemId, true) ;
		}catch(DuplicateOperateException e){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.addRate.itemId.duplicate") ;
			return SUCCESS ;
		}catch(Exception e){
			log.error(e.getMessage() , e) ;
			json.setCode(JsonModel.CODE_FAIL) ;
			json.setDetail("internal error .") ;
			return SUCCESS ;
		}
		
		json.setCode(JsonModel.CODE_SUCCESS) ;
		
		return SUCCESS ;
	}
	
	/**
	 * 领取自己当前时段的愿望值
	 * @return
	 */
	public String drawRate(){
		DcUserModel user = dcAuthz.getModel() ;
		String errMsg = this.validateDraw(user.getUserId() , promotionId ); 
		if(StringUtils.isNotBlank(errMsg)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail(errMsg) ;
			return SUCCESS ;
		}
		try {
			PromRateResult res = dcPromotionBO.drawMyRate(user, promotionId) ;
			res.setNextDrawRemainSeconds(DcPromotionItemUtils.getNextDrawRemainSeconds()) ;
			res.setSysTimeMillis(System.currentTimeMillis()) ;
			json.setData(res) ;
		}catch(PromotionRateException e){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail(e.getCode()) ;
			return SUCCESS ;
		}catch (Exception e){
			log.error(e.getMessage() , e) ;
			json.setCode(JsonModel.CODE_FAIL) ;
			json.setDetail("internal error .") ;
			return SUCCESS ;
		}
		
		json.setCode(JsonModel.CODE_SUCCESS) ;
		
		return SUCCESS ;
	}
	
	private String validateDraw(Long userId, Long promotionId) {
		if(IDUtils.isNotCorrect(userId)){
			return PromotionRateException.ERROR_PROMOTION_USER_REQUIRED ;
		}
		if(IDUtils.isNotCorrect(promotionId)){
			return PromotionRateException.ERROR_PROMOTION_REQUIRED ;
		}
		return null ;
	}

	/**
	 * 抢票数
	 * @return
	 */
	public String stealRate(){
		
		DcUserModel user = dcAuthz.getModel() ;
		
		String errMsg = this.validateSteal(promItemId , user.getUserId() , promotionId , stealPromItemId); 
		if(StringUtils.isNotBlank(errMsg)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail(errMsg) ;
			return SUCCESS ;
		}
		
		try {
			//抢票
			PromRateResult res = dcPromotionBO.stealRate(user, promotionId, promItemId, stealPromItemId) ;
			json.setData(res) ;
		}catch(DuplicateOperateException e){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.stealRate.itemId.duplicate") ;
			return SUCCESS ;
		}catch(PromotionRateException e){ //积分不够
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail(e.getCode()) ;
			return SUCCESS ;
		}catch(Exception e){
			log.error(e.getMessage() , e) ;
			json.setCode(JsonModel.CODE_FAIL) ;
			json.setDetail("internal error .") ;
			return SUCCESS ;
		}
		
		json.setCode(JsonModel.CODE_SUCCESS) ;
		
		
		
		return SUCCESS ;
	}
	
	private String validateSteal(Long myPromotionItemId, Long userId , Long promotionId , Long stealPromotionItemId) {
		if(IDUtils.isNotCorrect(myPromotionItemId)){
			return PromotionRateException.ERROR_PROMOTION_ITEM_REQUIRED ;
		}
		if(IDUtils.isNotCorrect(userId)){
			return PromotionRateException.ERROR_PROMOTION_USER_REQUIRED ;
		}
		if(IDUtils.isNotCorrect(promotionId)){
			return PromotionRateException.ERROR_PROMOTION_REQUIRED ;
		}
		if(IDUtils.isNotCorrect(stealPromotionItemId)){
			return PromotionRateException.ERROR_STEAL_ITEM_REQUIRED ;
		}
		return null ;
	}

	private String validateRate(Long myPromotionItemId , Long userId , Long promotionId){
		if(IDUtils.isNotCorrect(myPromotionItemId)){
			return "dcome.addRate.itemId.required" ;
		}
		if(IDUtils.isNotCorrect(userId)){
			return "dcome.addRate.userId.required" ;
		}
		if(IDUtils.isNotCorrect(promotionId)){
			return "dcome.addRate.promotionId.required" ;
		}
		return null ;
	}

	public JsonModel<PromRateResult> getJson() {
		return json;
	}

	public void setPromItemId(Long promItemId) {
		this.promItemId = promItemId;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	public void setStealPromItemId(Long stealPromItemId) {
		this.stealPromItemId = stealPromItemId;
	}
	
}
