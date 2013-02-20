package com.doucome.corner.web.bops.action.zhe;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.zhe.model.DdzTaokeReportDTO;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.bops.model.JsonModel;

/**
 * 
 * @author langben 2012-4-22
 *
 */
@SuppressWarnings("serial")
public class TaokeReportAjaxAction extends BopsBasicAction{

	private JsonModel<List<DdzTaokeReportDTO>> json = new JsonModel<List<DdzTaokeReportDTO>>();
	
	/**
	 * 批量修改的Report id
	 */
	private String settleId ;
	
	
	@Autowired
	private DdzTaokeReportService ddzTaokeReportService ;
	
	@Override
	public String execute() throws Exception {
		
		if(StringUtils.isBlank(settleId) || !StringUtils.isNumeric(settleId)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("input settleId is blank or not formated .") ;
			return SUCCESS ;
		}
	
		List<DdzTaokeReportDO> reportResult = ddzTaokeReportService.getReportsBySettleId(Integer.valueOf(settleId)) ;
		List<DdzTaokeReportDTO> facadeList = new ArrayList<DdzTaokeReportDTO>() ;
		for(DdzTaokeReportDO report : reportResult){
			facadeList.add(new DdzTaokeReportDTO(report)) ;
		}
		json.setCode(JsonModel.CODE_SUCCESS) ;
		json.setData(facadeList) ;
		
		return SUCCESS ;
	}

	
	public void setSettleId(String settleId) {
		this.settleId = settleId;
	}

	public JsonModel<List<DdzTaokeReportDTO>> getJson() {
		return json;
	}
	
	
}
