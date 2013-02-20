package com.doucome.corner.web.bops.action.dcome.prom;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.dcome.enums.DcPromotionAwardReviewStatusEnums;
import com.doucome.corner.biz.dcome.enums.DcPromotionAwardSendStatusEnums;
import com.doucome.corner.biz.dcome.model.DcPromotionAwardDTO;
import com.doucome.corner.biz.dcome.service.DcPromotionAwardService;
import com.doucome.corner.biz.dcome.service.DcUserService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.common.model.JsonModel;

public class PromotionAwardOperateAjaxAction extends BopsBasicAction {
	
	private static final Log log = LogFactory.getLog(PromotionAwardOperateAjaxAction.class) ;

	private JsonModel<Boolean> json = new JsonModel<Boolean>();
	
	private Long id ;
	
	private String status ;
	
	@Autowired
	private DcPromotionAwardService dcPromotionAwardService ;
	
	@Autowired
	private DcUserService dcUserService ;
	
	/**
	 * 更新审核状态
	 * @return
	 */
	public String updateReviewStatus(){
	
		if(IDUtils.isNotCorrect(id)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.promotion.award.id.required") ;
			return SUCCESS ;
		}
		
		DcPromotionAwardReviewStatusEnums statusEnums = DcPromotionAwardReviewStatusEnums.getInstance(status) ;
		
		if(statusEnums == DcPromotionAwardReviewStatusEnums.UNKNOWN){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.promotion.award.status.required") ;
			return SUCCESS ;
		}
		try {
			int effectCount = dcPromotionAwardService.updateReviewStatusById(id, statusEnums) ;
			if(effectCount > 0){
				//更新该会员的未读消息
				DcPromotionAwardDTO awardDO = dcPromotionAwardService.getAwardById(id) ;
				if(awardDO != null){
					Long userId = awardDO.getUserId() ;
					if(IDUtils.isCorrect(userId)){
						dcUserService.incrUnreadMsgCountByUser(userId, 1) ; //添加一条未读消息
					}
				}
			}
			json.setCode(JsonModel.CODE_SUCCESS) ;
		} catch(Exception e){
			log.error(e.getMessage() , e) ;
			json.setCode(JsonModel.CODE_FAIL) ;
			json.setDetail(e.getMessage()) ;
		}
		
		return SUCCESS ;
		
	}
	
	public String updateSendStatus(){
		if(IDUtils.isNotCorrect(id)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.promotion.award.id.required") ;
			return SUCCESS ;
		}
				
		DcPromotionAwardSendStatusEnums statusEnums = DcPromotionAwardSendStatusEnums.getInstance(status) ;
		
		if(statusEnums == DcPromotionAwardSendStatusEnums.UNKNOWN){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.promotion.award.status.required") ;
			return SUCCESS ;
		}
		
		try {
			int effectCount = dcPromotionAwardService.updateSendStatusById(id, statusEnums) ;
			if(effectCount > 0){
				
			}
			json.setCode(JsonModel.CODE_SUCCESS) ;
		} catch(Exception e){
			log.error(e.getMessage() , e) ;
			json.setCode(JsonModel.CODE_FAIL) ;
			json.setDetail(e.getMessage()) ;
		}
		
		
		return SUCCESS ;
	
	}

	public JsonModel<Boolean> getJson() {
		return json;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
