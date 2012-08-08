package com.doucome.corner.web.bops.action.dcome.qq;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.model.DcSystemDTO;
import com.doucome.corner.biz.dcome.service.DcSystemService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.bops.model.JsonModel;

/**
 * ²éÑ¯ÏµÍ³APP
 * @author shenjia.caosj 2012-7-26
 *
 */
@SuppressWarnings("serial")
public class QuerySystemsAjaxAction extends  BopsBasicAction {

	private JsonModel<List<DcSystemDTO>> json = new JsonModel<List<DcSystemDTO>>() ;
	
	@Autowired
	private DcSystemService dcSystemService  ;
	
	private Long systemId ;
	
	@Override
	public String execute() throws Exception {
		List<DcSystemDTO> systemList = null ;
		if(systemId == null){
			systemList = dcSystemService.getSystems() ;
		}else{
			systemList = new ArrayList<DcSystemDTO>() ;
			DcSystemDTO dto = dcSystemService.getSystemById(systemId) ;
			if(dto != null){
				systemList.add(dto) ;
			}
		}
		
		json.setCode(JsonModel.CODE_SUCCESS) ;
		json.setData(systemList) ;
		return SUCCESS ;
	}

	public JsonModel<List<DcSystemDTO>> getJson() {
		return json;
	}

	public void setSystemId(Long systemId) {
		this.systemId = systemId;
	}
 
}
