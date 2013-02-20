package com.doucome.corner.web.bops.action.zhe;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.utils.DateUtils;
import com.doucome.corner.biz.dal.DdzSyncReportTaskDAO;
import com.doucome.corner.biz.dal.condition.DdzSyncReportTaskSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzSyncReportTaskDO;
import com.doucome.corner.web.bops.action.BopsBasicAction;


/**
 * 淘宝客报表任务
 * @author langben 2012-4-23
 *
 */
@SuppressWarnings("serial")
public class TaokeReportTaskAction extends BopsBasicAction {
	
	private static final Log log = LogFactory.getLog(TaokeReportTaskAction.class) ;

	/**
	 * 月 。格式 201204，默认当前月
	 */
	private String m ;
	
	/**
	 * 下月
	 */
	private Date next ;
	
	/**
	 * 上月
	 */
	private Date prev ;
	
	private Date current ;
	
	private SyncReportTaskFacade[][]  taskCalendar  = new SyncReportTaskFacade[6][7] ;
	
	@Autowired
	private DdzSyncReportTaskDAO ddzSyncReportTaskDAO ;
	
	@Override
	public String execute() throws Exception {
		DdzSyncReportTaskSearchCondition searchCondition = new DdzSyncReportTaskSearchCondition() ;
		DateFormat format = new SimpleDateFormat("yyyyMM") ;
		Date date = null ;
		try {
			if(StringUtils.isNotBlank(m)){
				date = format.parse(m) ;
			}
		}catch(Exception e){
			log.error(e.getMessage() , e) ;
		}
		if(date == null){
			date = new Date() ;
		}
		
		current = date ;
		Calendar calCurrent = Calendar.getInstance() ;
		calCurrent.setTime(current) ;
		
		Calendar cal = Calendar.getInstance() ;
		cal.setTime(date) ;
		cal.set(Calendar.DATE, 1) ;
		Date gmtStart = cal.getTime() ;
		gmtStart = DateUtils.setTime(gmtStart, 0, 0, 0) ;
		cal.add(Calendar.MONTH, 1) ;
		Date gmtEnd = cal.getTime() ;
		gmtEnd = DateUtils.setTime(gmtEnd, 0, 0, 0) ;
		
		searchCondition.setGmtReportStart(gmtStart) ;
		searchCondition.setGmtReportEnd(gmtEnd) ;
		
		List<DdzSyncReportTaskDO> taskList = ddzSyncReportTaskDAO.queryReportWithPagination(searchCondition, 1, 31) ;
	
		Calendar calFor = Calendar.getInstance() ;
		
		int currentMonth = calCurrent.get(Calendar.MONTH) ;
		calCurrent.set(Calendar.DATE, 1) ;
		
		while(true){ //初始化日历
			
			int weekOfMonth = calCurrent.get(Calendar.WEEK_OF_MONTH) ;
			int dayOfWeek = calCurrent.get(Calendar.DAY_OF_WEEK) ;
			
			taskCalendar[weekOfMonth-1][dayOfWeek-1] = new SyncReportTaskFacade() ;
			taskCalendar[weekOfMonth-1][dayOfWeek-1].date = calCurrent.getTime() ;
			
			calCurrent.add(Calendar.DATE, 1) ;
			
			if(calCurrent.get(Calendar.MONTH) != currentMonth){
				break ;
			}
			
		}
		
		
		for(int i = 0 ; i < taskList.size() ; i ++){
			DdzSyncReportTaskDO task = taskList.get(i) ;
			Date reportTime = task.getGmtReport() ;
			calFor.setTime(reportTime) ;
			int weekOfMonth = calFor.get(Calendar.WEEK_OF_MONTH) ;
			int dayOfWeek = calFor.get(Calendar.DAY_OF_WEEK) ;
			SyncReportTaskFacade facade = taskCalendar[weekOfMonth-1][dayOfWeek-1] ;
			if(facade != null){
				facade.task = task ;
			}
		}
		
		
		next = gmtEnd ;
		cal.setTime(gmtStart) ;
		cal.add(Calendar.MONTH, -1) ;
		prev = cal.getTime();
		
		return SUCCESS ;
	}

	public Date getNext() {
		return next;
	}

	public Date getPrev() {
		return prev;
	}

	public void setM(String m) {
		this.m = m;
	}

	public Date getCurrent() {
		return current;
	}

	public SyncReportTaskFacade[][] getTaskCalendar() {
		return taskCalendar;
	}
	
	
	public static class SyncReportTaskFacade {
		
		private DdzSyncReportTaskDO task ;
		
		private Date date ;

		public DdzSyncReportTaskDO getTask() {
			return task;
		}

		public void setTask(DdzSyncReportTaskDO task) {
			this.task = task;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}
		
		
	}
	
}
