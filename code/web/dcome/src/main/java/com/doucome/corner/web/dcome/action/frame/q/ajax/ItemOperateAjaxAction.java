package com.doucome.corner.web.dcome.action.frame.q.ajax;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.business.DcLoveBO;
import com.doucome.corner.biz.dcome.exception.DuplicateLoveException;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.DComeBasicAction;

/**
 * ²Ù×÷ITEM
 * @author shenjia.caosj 2012-7-28
 *
 */
@SuppressWarnings("serial")
public class ItemOperateAjaxAction extends DComeBasicAction{
	
	private static final Log log = LogFactory.getLog(ItemOperateAjaxAction.class) ;
	
	private Long itemId ;
		
	private JsonModel<Boolean> json = new JsonModel<Boolean>() ;
	
	@Autowired
	private DcLoveBO dcLoveBO ;
	
	public String addLove(){
		
		Long userId = dcAuthz.getUserId() ;
		
		String errMsg = this.validate(itemId , userId);
		if(StringUtils.isNotBlank(errMsg)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail(errMsg) ;
			return SUCCESS ;
		}
		try {
			dcLoveBO.addLove(userId, itemId) ;
		}catch(DuplicateLoveException e){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.addLove.itemId.duplicate") ;
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
	
	private String validate(Long itemId , Long userId){
		if(itemId == null){
			return "dcome.addLove.itemId.required" ;
		}
		if(userId == null){
			return "dcome.addLove.userId.required" ;
		}
		return null ;
	}

	public JsonModel<Boolean> getJson() {
		return json;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	
}
