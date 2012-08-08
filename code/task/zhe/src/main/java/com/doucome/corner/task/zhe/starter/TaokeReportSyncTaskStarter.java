package com.doucome.corner.task.zhe.starter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.doucome.corner.biz.core.constant.LogConstant;
import com.doucome.corner.task.zhe.syncReport.SyncReportTaskService;

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
		SyncReportTaskService syncReportTaskService = (SyncReportTaskService) starter.getBean("syncReportTaskService");
		syncReportTaskService.executeInternal();
		syncReportLog.info("------taoke report sync end.");
	}
}
