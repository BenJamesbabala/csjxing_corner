package com.doucome.corner.task.zhe.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.task.zhe.model.SyncReportRunResult.SettleReportResult;
import com.doucome.corner.task.zhe.syncReport.SyncReportTaskService;
import com.doucome.corner.web.common.action.BasicAction;

@SuppressWarnings("serial")
public class CreateSettleReportAction extends BasicAction {
	private static final Log log = LogFactory.getLog(CreateSettleReportAction.class);
	
	@Autowired
	private SyncReportTaskService syncReportTaskService;
	
	private SettleReportResult settleResult = new SettleReportResult();
	
	@Override
	public String execute() {
		try {
			settleResult = syncReportTaskService.createSettleReport();
			if (settleResult.getTotalCount() == -1) {
				return ERROR;
			}
		} catch(Exception e) {
			log.error("create settle report failed", e);
			settleResult = new SettleReportResult(-1, -1);
			return ERROR;
		}
		return SUCCESS;
	}

	public SettleReportResult getSettleResult() {
		return settleResult;
	}
}
