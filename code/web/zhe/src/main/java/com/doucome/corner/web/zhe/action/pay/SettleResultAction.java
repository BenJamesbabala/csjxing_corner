package com.doucome.corner.web.zhe.action.pay;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.enums.AlipayStatusEnum;
import com.doucome.corner.biz.core.enums.SettleStatusEnums;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;
import com.doucome.corner.biz.zhe.model.SettleResult;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportSettleService;
import com.doucome.corner.biz.zhe.utils.EncryptUtils;
import com.doucome.corner.biz.zhe.utils.SettleUtils;
import com.doucome.corner.web.zhe.action.DdzBasicAction;
import com.doucome.corner.web.zhe.model.JsonModel;

@SuppressWarnings("serial")
public class SettleResultAction extends DdzBasicAction {

	private String userName;
	
	private String settleBatchNO;
	
	private String actualSettleIds;
	
	private String alipayBatchNO;
	
	private JsonModel<String> message = new JsonModel<String>();
	
	private static final Log log = LogFactory.getLog(SettleResultAction.class);
	
	private static final Log settleLog = LogFactory.getLog("report-settle-log");
	
	@Autowired
	private DdzTaokeReportSettleService ddzTaokeReportSettleService;
	
	@Override
	public String execute() {
		message.setCode(JsonModel.CODE_SUCCESS);
		log.error(String.format("----update settle report alipay batchno [%s] by batchNO [%s], ids [%s]",
			    alipayBatchNO, settleBatchNO, actualSettleIds));
		settleLog.error(String.format("----update settle report alipay batchno [%s] by batchNO [%s], ids [%s]",
			    alipayBatchNO, settleBatchNO, actualSettleIds));
		String temp = EncryptUtils.md5Encrypt("ZhAng187@test.com");
		if (!temp.equalsIgnoreCase(userName)) {
			message.setCode(JsonModel.CODE_AUTH_FAIL);
			message.setDetail("authentication failed");
			log.error("authentication failed");
			settleLog.error("authentication failed");
			return ERROR;
		}

		
		if (StringUtils.isEmpty(actualSettleIds)) {
			message.setCode(JsonModel.CODE_NO_DATA);
			message.setDetail("there is no settle report to update");
			return ERROR;
		}
		
		List<Long> ids = SettleUtils.split(actualSettleIds, ",");
		List<DdzTaokeReportSettleDO> settleDOs = SettleUtils.build(ids);
		for (DdzTaokeReportSettleDO settllDO : settleDOs) {
			settllDO.setSettleBatchno(settleBatchNO);
			settllDO.setAlipayBatchno(alipayBatchNO);
			settllDO.setAlipayStatus(AlipayStatusEnum.SUCCESS.getValue());
			settllDO.setSettleStatus(SettleStatusEnums.SETTLE_SUCCESS.getValue());
		}
		
		int count = ddzTaokeReportSettleService.updateAlipayResult(settleDOs);
		SettleResult settleResult = ddzTaokeReportSettleService.updateSettleStatus(settleDOs);
		
		log.error(String.format("----[%d] settle report updated, update settle status [%s]",
				    count, settleResult.toString()));
		settleLog.error(String.format("----[%d] settle report updated, update settle status [%s]",
				          count, settleResult.toString()));
		
		if (count == -1) {
			message.setCode(JsonModel.CODE_FAIL);
			message.setDetail("update settle report alipay batchno and status fail");
		}
		if (!settleResult.isSucc()) {
			message.setCode(JsonModel.CODE_FAIL);
			message.setDetail(message.getDetail() + "\n" + settleResult.getMessage());
		}
		return SUCCESS;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setSettleBatchNO(String batchNO) {
		this.settleBatchNO = batchNO;
	}

	public void setAlipayBatchNO(String alipayBatchNO) {
		this.alipayBatchNO = alipayBatchNO;
	}

	public JsonModel<String> getMessage() {
		return message;
	}
	
	public void setActualSettleIds(String actualSettleIds) {
		this.actualSettleIds = actualSettleIds;
	}
}
