package com.doucome.corner.task.zhe.syncReport.handler;

import java.util.List;

import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;

/**
 * 
 * @author langben 2012-9-6
 *
 */
public interface Handler {

	HandleResult handleReport(List<DdzTaokeReportDO> list) ;
	
}
