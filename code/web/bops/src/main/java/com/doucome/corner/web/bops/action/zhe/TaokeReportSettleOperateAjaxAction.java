package com.doucome.corner.web.bops.action.zhe;

import java.net.URLDecoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportSettleService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.bops.model.JsonModel;

/**
 * ½áËã²Ù×÷
 * @author langben 2012-9-5
 *
 */
public class TaokeReportSettleOperateAjaxAction extends BopsBasicAction {
	
	private static final Log log = LogFactory.getLog(TaokeReportSettleOperateAjaxAction.class) ;

	private JsonModel<Boolean> json = new JsonModel<Boolean>();
	
	private Integer id ;
	
	private String memo ;
	
	@Autowired
	private DdzTaokeReportSettleService ddzTaokeReportSettleService ;
	
	public String addMemo() throws Exception {
		
		if(IDUtils.isNotCorrect(id)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("ddz.settle.addMemo.id.required") ;
			return SUCCESS ;
		}
		
		if(StringUtils.isBlank(memo)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("ddz.settle.addMemo.memo.required") ;
			return SUCCESS ;
		}
		try {
			String memoDec = URLDecoder.decode(memo , "UTF-8") ;
			ddzTaokeReportSettleService.updateMemoById(id, memoDec ) ;
			json.setCode(JsonModel.CODE_SUCCESS) ;
		}catch(Exception e){
			log.error(e.getMessage() , e) ;
			json.setCode(JsonModel.CODE_FAIL) ;
			json.setDetail(e.getMessage()) ;
		}
		
		
		return SUCCESS ;
	}

	public JsonModel<Boolean> getJson() {
		return json;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
}
