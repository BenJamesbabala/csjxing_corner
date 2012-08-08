package com.doucome.corner.web.zhe.action.pay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dal.dataobject.AlipayItemDO;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportSettleService;
import com.doucome.corner.biz.zhe.utils.EncryptUtils;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.zhe.action.DdzBasicAction;

@SuppressWarnings("serial")
public class ReportSettleAction extends DdzBasicAction {
	@Autowired
	DdzTaokeReportSettleService taokeReportSettleService;
	
	private String userName;
	
	private JsonModel<List<AlipayItemDO>> payItems = new JsonModel<List<AlipayItemDO>>();
	
	private static final Log log = LogFactory.getLog(ReportSettleAction.class);
	
	private static final Log settleLog = LogFactory.getLog("report-settle-log");
	
	@Override
	public String execute() {
		settleLog.error("----try to get settle report, auth: " + userName);
		//设置默认数据
		payItems.setData(null);
		payItems.setCode(JsonModel.CODE_SUCCESS);
		
		String temp = EncryptUtils.md5Encrypt("ZhAng187@test.com");
		if (!temp.equalsIgnoreCase(userName)) {
			payItems.setCode(JsonModel.CODE_AUTH_FAIL);
			payItems.setDetail("authentication failed");
			return ERROR;
		}

		List<AlipayItemDO> payDatas = taokeReportSettleService.getAlipayItems(20);
		log.error("try to settle: " + getTaokeReportIds(payDatas));
		settleLog.error("try to settle: " + getTaokeReportIds(payDatas));
		if (payDatas != null) {
			payItems.setData(payDatas);
			return SUCCESS;
		} else {
			payItems.setCode(JsonModel.CODE_FAIL);
			payItems.setDetail("Server Exception");
			return ERROR;
		}
		
	}

	private List<String> getTaokeReportIds(List<AlipayItemDO> items) {
		if (items == null) {
			return null;
		}
		List<String> reportIds = new ArrayList<String>();
		for (AlipayItemDO alipayItem: items) {
			reportIds.addAll(Arrays.asList(alipayItem.getIds().split(",")));
		}
		return reportIds;
	}
	
	public JsonModel<List<AlipayItemDO>> getPayItems() {
		return payItems;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
