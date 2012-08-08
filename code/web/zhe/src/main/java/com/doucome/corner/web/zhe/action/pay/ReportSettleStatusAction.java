package com.doucome.corner.web.zhe.action.pay;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.enums.SettleStatusEnums;
import com.doucome.corner.biz.core.utils.UidCreateUtil;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;
import com.doucome.corner.biz.zhe.model.SettleResult;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportSettleService;
import com.doucome.corner.biz.zhe.utils.EncryptUtils;
import com.doucome.corner.biz.zhe.utils.SettleUtils;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.zhe.action.DdzBasicAction;

@SuppressWarnings("serial")
public class ReportSettleStatusAction extends DdzBasicAction {

	private String userName;
	
	private String settleIds;
	
	private String actualSettleIds;
	
	private JsonModel<SettleResult> result = new JsonModel<SettleResult>();
	
	@Autowired
	DdzTaokeReportSettleService taokeReportSettleService;
	
	private static final Log log = LogFactory.getLog(ReportSettleStatusAction.class);
	
	private static final Log settleLog = LogFactory.getLog("report-settle-log");
	
	@Override
	public String execute() {
		result.setCode(JsonModel.CODE_SUCCESS);
		result.setData(null);
		
		log.error(String.format("----update [%s] settle status, success [%s] ", settleIds, actualSettleIds));
		settleLog.error(String.format("----update [%s] settle status, success [%s] ", settleIds, actualSettleIds));
		
		String temp = EncryptUtils.md5Encrypt("ZhAng187@test.com");
		if (!temp.equalsIgnoreCase(userName)) {
			result.setCode(JsonModel.CODE_AUTH_FAIL);
			result.setDetail("authentication failed");
			log.error("authentication failed");
			settleLog.error("authentication failed");
			return ERROR;
		}
		StringBuilder message = new StringBuilder();
		List<Long> allIds = SettleUtils.split(settleIds, ",");
		List<Long> succSettleIds = SettleUtils.split(actualSettleIds, ",");
		List<Long> cancelSettleIds = SettleUtils.difference(allIds, succSettleIds);
		List<Long> unExpectedDatas = SettleUtils.difference(succSettleIds, allIds);
		
		if (unExpectedDatas.size() != 0) {
			log.error("----unexcept settle ids: " + unExpectedDatas.toString());
			settleLog.error("----unexcept settle ids: " + unExpectedDatas.toString());
			message.append("\n there are some unexpected data").append(unExpectedDatas.toString());
			succSettleIds.removeAll(unExpectedDatas);
		}
		
		List<DdzTaokeReportSettleDO> settleDOs = SettleUtils.build(succSettleIds);
		for (DdzTaokeReportSettleDO settleDO : settleDOs) {
			settleDO.setSettleStatus(SettleStatusEnums.SETTLE_PENDING.getValue());
		}
		List<DdzTaokeReportSettleDO> cancelDOs = SettleUtils.build(cancelSettleIds);
		for (DdzTaokeReportSettleDO settleDO : cancelDOs) {
			settleDO.setSettleStatus(SettleStatusEnums.SETTLE_CANCEL.getValue());
		}
		settleDOs.addAll(cancelDOs);
		
		if (settleDOs.size() == 0) {
			message.append("\n there is no settle report to update");
			log.error(message.toString());
			settleLog.error(message.toString());
			
			return SUCCESS;
		}
		
		String batchNO = UidCreateUtil.createUid(settleDOs.get(0).getId().toString(), 15);
		for (DdzTaokeReportSettleDO settleDO : settleDOs) {
			settleDO.setSettleBatchno(batchNO);
		}
		
		log.error("----update settle report status: " + settleDOs.toString());
		settleLog.error("----update settle report status: " + settleDOs.toString());
		
		SettleResult settleResult = taokeReportSettleService.updateSettleStatus(settleDOs);
		settleResult.setBatchNO(batchNO);
		
		log.error("----update settle report result:" + settleResult);
		settleLog.error("----update settle report result:" + settleResult);
		
		result.setData(settleResult);
		
		if (settleResult.isSucc()) {
			if (settleResult.getUpdateSettleCount() != allIds.size()) {
				message.append("\n").append("expect update ").append(allIds.size())
				  .append(" recodes, but update ").append(settleResult.getUpdateSettleCount())
				  .append(" records");
			}
		} else {
			message.append("\n").append(settleResult.getMessage());
		}
		if (message.length() > 0) {
			log.error(message.toString());
			settleLog.error(message.toString());

			result.setCode(JsonModel.CODE_FAIL);
			result.setDetail(message.toString());
		}
		return SUCCESS;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setSettleIds(String settleIds) {
		this.settleIds = settleIds;
	}

	public void setActualSettleIds(String actualSettleIds) {
		this.actualSettleIds = actualSettleIds;
	}

	public JsonModel<SettleResult> getResult() {
		return result;
	}
}
