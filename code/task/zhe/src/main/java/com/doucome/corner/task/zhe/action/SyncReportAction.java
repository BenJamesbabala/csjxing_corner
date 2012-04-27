package com.doucome.corner.task.zhe.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.taobao.model.TaobaokeDate;
import com.doucome.corner.task.zhe.syncReport.SyncReportTaskService;
import com.doucome.corner.task.zhe.utils.TaskUtils;
import com.doucome.corner.web.common.action.BasicAction;

@SuppressWarnings("serial")
public class SyncReportAction extends BasicAction {
	
	private static final Log log = LogFactory.getLog(SyncReportAction.class) ;
	
	private static final String DATE_TYPE_DAY = "d" ; 
	private static final String DATE_TYPE_MONTH = "m" ;
	
	private Map<String,String> runoutResultMap = new HashMap<String, String>() ;
	
	private String dateStr ;
	
	//m = 月|默认d = 日
	private String dateType = DATE_TYPE_DAY ;
	
	@Autowired
	private SyncReportTaskService syncReportTaskService ;

	@Override
	public String execute() throws Exception {
		String runoutResult = null ;
		if(StringUtils.isBlank(dateStr)){
			runoutResult = "input dateStr is empty ." ;
			runoutResultMap.put("", runoutResult) ;
		} else {
			if(StringUtils.equals(DATE_TYPE_DAY, dateType)){
				DateFormat f = new SimpleDateFormat("yyyyMMdd") ;
				try {
					Date date =  f.parse(dateStr) ;
					runoutResult = syncReportTaskService.syncDailyReport(new TaobaokeDate(date)) ;
				}catch(ParseException e ){
					runoutResult = "input dateStr format not correct." ;
				}
				runoutResultMap.put(dateStr , runoutResult) ;
				//syncReportTaskService.syncDailyReport() ;
			}else if(StringUtils.equals(DATE_TYPE_MONTH, dateType)){
				DateFormat f = new SimpleDateFormat("yyyyMM") ;
				try {
					 //同步一个月的数据
					Date date =  f.parse(dateStr) ;
					Calendar c = Calendar.getInstance() ;
					c.setTime(date) ;
					int currMonth = c.get(Calendar.MONTH) ;
					c.set(Calendar.DATE, 1) ;
					Calendar daydayBefore = Calendar.getInstance() ;
					daydayBefore.setTime(TaskUtils.getLastLateDate()) ;
					
					while(true){
						Date toSyncDate = c.getTime() ;
						TaobaokeDate taokeDate = new TaobaokeDate(toSyncDate) ;
						try {
							runoutResult = syncReportTaskService.syncDailyReport(taokeDate) ;
							runoutResultMap.put(taokeDate.getDateFormat() , runoutResult) ;
						}catch(Exception e){
							log.error(e.getMessage() , e) ;
							runoutResultMap.put(taokeDate.getDateFormat(), "exception : " + e.getMessage()) ;
						}
						c.add(Calendar.DATE, 1) ;
						if(c.get(Calendar.MONTH) != currMonth){
							break ;//一个月的数据同步完成
						}
						if(c.after(daydayBefore)){
							break ; //大于昨天
						}
					}
				}catch(ParseException e ){
					runoutResult = "input dateStr format not correct." ;
					runoutResultMap.put(dateStr , runoutResult) ;
				}
			}else{
				runoutResult = "input dateType format not correct." ;
				runoutResultMap.put(dateStr , runoutResult) ;
			}
		}
		return SUCCESS ;
	}
	
	



	public Map<String, String> getRunoutResultMap() {
		return runoutResultMap;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
	
	
}
