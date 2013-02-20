package com.doucome.corner.web.dcome.action.frame.q.ajax;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.dcome.business.DcUserBO;
import com.doucome.corner.biz.dcome.exception.DuplicateOperateException;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.DComeBasicAction;

/**
 * �û�����
 * @author langben 2012-8-28
 *
 */
@SuppressWarnings("serial")
public class UserAjaxAction extends DComeBasicAction {

	private JsonModel<Integer> json = new JsonModel<Integer>() ; 
		
	@Autowired
	private DcUserBO dcUserBO ;
	
	/**
	 * ÿ��ǩ��
	 * @return
	 * @throws Exception
	 */
	public String dailyCheckin() throws Exception {
		
		
		Long userId = dcAuthz.getUserId() ;
		
		if(IDUtils.isNotCorrect(userId)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.userTask.userId.required") ;
			return SUCCESS ;
		}
		
		try {
			int integralCount = dcUserBO.dailyCheckin(userId, getPfNick()) ; 
			json.setCode(JsonModel.CODE_SUCCESS) ;
			json.setData(integralCount) ;
		}catch(DuplicateOperateException e){ //�ظ�ǩ��
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.userTask.dailyCheckin.duplicate") ;
			return SUCCESS ;
		}catch (Exception e) {
			json.setCode(JsonModel.CODE_FAIL) ;
			return SUCCESS ;
		}
						
		return SUCCESS ;
	}

	public JsonModel<Integer> getJson() {
		return json;
	}
	
}
