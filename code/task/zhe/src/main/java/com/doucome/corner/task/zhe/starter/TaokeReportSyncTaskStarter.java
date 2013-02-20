package com.doucome.corner.task.zhe.starter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.doucome.corner.biz.core.constant.LogConstant;
import com.doucome.corner.task.zhe.TaskService;

/**
 * 淘客报表同步启动类
 * @author ze2200
 *
 */
public class TaokeReportSyncTaskStarter extends TaskStarterEnv {
	
	private static final Log syncReportLog = LogFactory.getLog(LogConstant.task_syncReport_log) ;
	
	public TaokeReportSyncTaskStarter() {
		super("applicationContext.xml");
	}
	
	public static void main(String[] args) {
		syncReportLog.info("------taoke report sync start.");
		TaokeReportSyncTaskStarter starter = new TaokeReportSyncTaskStarter();
		Object o = starter.getBean("syncReportTaskService"); 
		TaskService syncReportTaskService = (TaskService) o; 
		syncReportTaskService.executeInternal();
		syncReportLog.info("------taoke report sync end.");
		//强制退出
		System.exit(0) ;
	}
}
