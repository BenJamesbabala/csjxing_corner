package com.doucome.corner.biz.dcome.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.doucome.corner.biz.core.utils.JacksonHelper;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserIntegralRecordDO;
import com.doucome.corner.biz.dcome.model.util.DcUserIntegralDescAdapter;

public class DcUserIntegralRecordDTO {
	
	private static final Log log = LogFactory.getLog(DcUserIntegralRecordDTO.class) ;

	private DcUserIntegralRecordDO integralRecord ;
	
	private Map<String,String> integralDescMap ; 
	
	private DcUserIntegralDescAdapter descAdapter ;
	
	public DcUserIntegralRecordDTO(DcUserIntegralRecordDO integralRecord) {
		if(integralRecord == null){
			integralRecord = new DcUserIntegralRecordDO() ;
		}
		
		this.integralRecord = integralRecord ;
		
		if(StringUtils.isBlank(integralRecord.getIntegralDesc())){
			integralDescMap = new HashMap<String,String> ();
		} else {
			try {
				integralDescMap = JacksonHelper.fromJSON(integralRecord.getIntegralDesc(), Map.class) ;
			} catch (Exception e){
				log.error(e.getMessage()  , e) ;
			}
		}
		
		
	}
	
	public Map<String,String> getIntegralDescMap(){
		return integralDescMap ;
	}
	
	public DcUserIntegralDescAdapter getDescModel(){
		if(descAdapter == null){
			descAdapter = new DcUserIntegralDescAdapter(getIntegralDescMap()) ;
		}
		return descAdapter ;
	}
	
	/**
	 * ----------------------------------------------------
	 */
	
	public Long getId() {
		return integralRecord.getId();
	}

	public Long getUserId() {
		return integralRecord.getUserId();
	}

	public Integer getIntegralCount() {
		return integralRecord.getIntegralCount();
	}

	public String getInOutType() {
		return integralRecord.getInOutType();
	}

	public String getSource() {
		return integralRecord.getSource();
	}

	public String getIntegralDesc() {
		return integralRecord.getIntegralDesc();
	}

	public Date getGmtCreate() {
		return integralRecord.getGmtCreate();
	}

	public Date getGmtModified() {
		return integralRecord.getGmtModified();
	}

	public Integer getIntegralBalance() {
		return integralRecord.getIntegralBalance();
	}

	public Long getTaokeReportId() {
		return integralRecord.getTaokeReportId();
	}

	public Long getExchangeApproveId() {
		return integralRecord.getExchangeApproveId();
	}

	public String getStatus() {
		return integralRecord.getStatus();
	}

}
