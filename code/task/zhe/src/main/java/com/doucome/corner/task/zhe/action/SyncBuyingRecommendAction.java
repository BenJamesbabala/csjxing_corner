package com.doucome.corner.task.zhe.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.zhe.model.RecommendBatchDate;
import com.doucome.corner.task.zhe.syncBuyingRecomm.SyncBuyingRecommendTaskService;
import com.doucome.corner.web.common.action.BasicAction;

@SuppressWarnings("serial")
public class SyncBuyingRecommendAction extends BasicAction {

	private static final Log log = LogFactory.getLog(SyncBuyingRecommendAction.class) ;
	
	private String dateStr ;
	
	private String runResult ;
	
	@Autowired
	private SyncBuyingRecommendTaskService syncBuyingRecommendTaskService ;
	
	@Override
	public String execute() throws Exception {
		
		DateFormat f = new SimpleDateFormat("yyyyMMdd") ;
		Date d = null ;
		if(StringUtils.isBlank(dateStr)){
			runResult = "input dateStr format err ." ;
			return SUCCESS ;
		}
		try {
			 d = f.parse(dateStr)  ;
		}catch(ParseException e){
			runResult = "input dateStr format err ." ;
			return SUCCESS ;
		}
		
		String s = syncBuyingRecommendTaskService.syncDailyRecommend(new RecommendBatchDate(d)) ;
		runResult = s ;
		return SUCCESS ;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public String getRunResult() {
		return runResult;
	}
	
	
}
