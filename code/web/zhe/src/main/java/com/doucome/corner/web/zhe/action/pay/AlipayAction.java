package com.doucome.corner.web.zhe.action.pay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.enums.AlipayStatusEnum;
import com.doucome.corner.biz.core.enums.SettleStatusEnums;
import com.doucome.corner.biz.dal.dataobject.AlipayItemDO;
import com.doucome.corner.biz.zhe.service.AlipayService;
import com.doucome.corner.biz.zhe.utils.EncryptUtils;
import com.doucome.corner.web.zhe.action.DdzBasicAction;
import com.doucome.corner.web.zhe.model.JsonModel;

@SuppressWarnings("serial")
public class AlipayAction extends DdzBasicAction {
	@Autowired
	AlipayService alipayService;
	
	private String userName;
	
	private String reportIds;
	
	private String actualPayReportIds;
	
	private String alipayBatchNO;
	
	private String successRecordIds;
	
	private JsonModel<List<AlipayItemDO>> payItems = new JsonModel<List<AlipayItemDO>>();
	
	private int count = -1;
	
	private static final Log log = LogFactory.getLog(AlipayAction.class);
	
	/**
	 * 
	 * @return
	 */
	public String doGetTaokeReportSettleDatas() {
		//设置默认数据
		payItems.setCode(JsonModel.CODE_FAIL);
		payItems.setData(null);
		
		String temp = EncryptUtils.md5Encrypt("ZhAng187@test.com");
		if (!temp.equalsIgnoreCase(userName)) {
			payItems.setCode(JsonModel.CODE_AUTH_FAIL);
			return ERROR;
		}

		List<AlipayItemDO> payDatas = alipayService.getAlipayItems();
		log.error("try to settle: " + getTaokeReportIds(payDatas));
		if (payDatas != null) {
			payItems.setCode(JsonModel.CODE_SUCCESS);
			payItems.setData(payDatas);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public String doUpdateAlipayProgressing() {
		payItems.setCode(JsonModel.CODE_SUCCESS);
		payItems.setData(null);
		
		log.error(String.format("taoke report settle [%s], success [%s] ",
				       reportIds.toString(), actualPayReportIds.toString()));
		
		String temp = EncryptUtils.md5Encrypt("ZhAng187@test.com");
		if (!temp.equalsIgnoreCase(userName)) {
			payItems.setCode(JsonModel.CODE_AUTH_FAIL);
			payItems.setDetail("authentication failed");
			return ERROR;
		}
		List<String> tempIds = convert(reportIds);
		List<String> tempActualIds = convert(actualPayReportIds);
		if (tempIds.size() < tempActualIds.size()) {
			payItems.setDetail(String.format("expect pay to %d person, but actual pay to %d person",
					  tempIds.size(), tempActualIds.size()));
			payItems.setCode(JsonModel.CODE_FAIL);
			return ERROR;
		}
		int count = alipayService.updateTaokeReportSettleStatus(tempIds, tempActualIds, SettleStatusEnums.SETTLE_PENDING);
		
		if (count != tempIds.size()) {
			payItems.setCode(JsonModel.CODE_FAIL_UNEXPECTED);
			payItems.setDetail(String.format("expect update %d recodes, but update %d records",
					             tempIds.size(), count));
		}
		return SUCCESS;
	}
	
	private List<String> convert(String ids) {
		List<String> result = new ArrayList<String>();
		if (StringUtils.isEmpty(ids)) {
			return result; 
		}
		String[] temps = ids.split(",");
		for(String temp : temps) {
			if (StringUtils.isNotEmpty(temp)) {
				result.add(temp);
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @return
	 */
	public String doUpdateTaokeReportAlipayResult() {
		String temp = EncryptUtils.md5Encrypt("ZhAng187@test.com");
		if (!temp.equalsIgnoreCase(userName)) {
			
			return ERROR;
		}
		if (StringUtils.isNotEmpty(successRecordIds)) {
			count = alipayService.updateTaokeReportAlipayResult(Arrays.asList(successRecordIds.split(",")),
					  AlipayStatusEnum.SUCCESS, alipayBatchNO);
			if (count == -1) {
				
			}
		}
//		if (StringUtils.isNotEmpty(failRecordIds)) {
//			count = alipayService.updateTaokeReportAlipayResult(Arrays.asList(failRecordIds.split(",")),
//					AlipayStatusEnum.FAIL, alipayBatchNO);
//			if (count == -1) {
//				
//			}
//		}
		
		return SUCCESS;
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

	public void setAlipayBatchNO(String alipayBatchNO) {
		this.alipayBatchNO = alipayBatchNO;
	}

	public void setSuccessRecordIds(String successRecordIds) {
		this.successRecordIds = successRecordIds;
	}

	public int getCount() {
		return count;
	}

	public void setReportIds(String reportIds) {
		this.reportIds = reportIds;
	}

	public void setActualPayReportIds(String actualPayReportIds) {
		this.actualPayReportIds = actualPayReportIds;
	}
}
