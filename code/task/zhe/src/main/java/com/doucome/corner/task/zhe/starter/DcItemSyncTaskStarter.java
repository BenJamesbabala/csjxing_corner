package com.doucome.corner.task.zhe.starter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.doucome.corner.biz.core.constant.LogConstant;
import com.doucome.corner.task.zhe.syncDcItem.SyncDcItemTaskService;

/**
 * 豆蔻商品同步启动类
 * @author ze2200
 *
 */
public class DcItemSyncTaskStarter extends TaskStarterEnv {
	
	private static final Log syncDcItemLog = LogFactory.getLog(LogConstant.task_syncDcItem_log) ;
	
	public DcItemSyncTaskStarter() {
		super("applicationContext.xml");
	}
	
	public static void main(String[] args) {
		syncDcItemLog.info("-------------- dc item sync start --------------- .") ;
		DcItemSyncTaskStarter starter = new DcItemSyncTaskStarter();
		SyncDcItemTaskService syncDcItemService = (SyncDcItemTaskService) starter.getBean("syncDcItemTaskService");
		syncDcItemService.executeInternal();
		syncDcItemLog.info("-------------- dc item sync end -----------------.");
		System.exit(0);
	}
}
