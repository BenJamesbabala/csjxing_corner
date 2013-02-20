package com.doucome.corner.web.bops.action.dcome.qq;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.enums.OutCodeEnums;
import com.doucome.corner.biz.core.utils.DateTool;
import com.doucome.corner.biz.core.utils.DecimalUtils;
import com.doucome.corner.biz.dal.condition.TaokeReportSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.service.DcUserService;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.bops.model.JsonModel;
/**
 * 
 * @author ze2200
 *
 */
public class BopsUserAjaxAction extends BopsBasicAction {
	
	private static final long serialVersionUID = 1L;

	JsonModel<Object> json = new JsonModel<Object>();
	
	private List<Long> userIds = new ArrayList<Long>();
	
	private Boolean includeComm = false;
	
	@Autowired
	private DcUserService dcUserService;
	@Autowired
	private DdzTaokeReportService ddzTaokeReportService;
	
	/**
	 * 
	 * @return
	 */
	public String queryUserInfo() {
		if (CollectionUtils.isEmpty(userIds)) {
			json.setFail(JsonModel.CODE_FAIL, "no.userId");
			return SUCCESS;
		}
		List<DcUserInfo> result = new ArrayList<BopsUserAjaxAction.DcUserInfo>();
		List<DcUserDTO> users = dcUserService.queryUsers(userIds);
		for (DcUserDTO user: users) {
			DcUserInfo temp = new DcUserInfo();
			temp.setUserId(user.getUserId());
			temp.setUserNick(user.getNick());
			temp.setIntegralCount(user.getIntegralCount());
			temp.setGmtCreate(user.getGmtCreate());
			result.add(temp);
		}
		if (includeComm) {
			if (result.size() > 0) {
				result.get(0).setCommission(DecimalUtils.format(getUserCommission(userIds.get(0)), "###,##0.00"));
			}
		}
		json.setSuccess(JsonModel.CODE_SUCCESS, result);
		return SUCCESS;
	}
	
	private BigDecimal getUserCommission(Long userId) {
		TaokeReportSearchCondition condition = new TaokeReportSearchCondition();
		condition.setDcUserId(userId);
		condition.setOutcodeType(OutCodeEnums.DOUCOME_PROMOTION.getName());
		List<DdzTaokeReportDO> taokeReports = ddzTaokeReportService.getReports(condition);
		BigDecimal result = new BigDecimal("0");
		for (DdzTaokeReportDO temp: taokeReports) {
			result = result.add(temp.getCommission());
		}
		return result;
	}
	
	public void setUserIds(String userIds) {
		List<String> tempIds = Arrays.asList(StringUtils.split(userIds, ","));
		if (CollectionUtils.isNotEmpty(tempIds)) {
			for (String temp: tempIds) {
				try {
					this.userIds.add(Long.valueOf(temp));
				} catch (Exception e) {
					
				}
			}
		}
	}

	public void setIncludeComm(Boolean includeComm) {
		this.includeComm = includeComm;
	}

	public JsonModel<Object> getJson() {
		return json;
	}
	
	public class DcUserInfo {
		
		private Long userId;
		
		private String userNick;
		
		private Integer integralCount;
		
		private String gmtCreateFmt;
		
		private String commission;
		
		public Long getUserId() {
			return userId;
		}
		
		public void setUserId(Long userId) {
			this.userId = userId;
		}
		
		public String getUserNick() {
			return userNick;
		}

		public void setUserNick(String userNick) {
			this.userNick = userNick;
		}

		public Integer getIntegralCount() {
			return integralCount;
		}

		public void setIntegralCount(Integer integralCount) {
			this.integralCount = integralCount;
		}

		public String getGmtCreateFmt() {
			return gmtCreateFmt;
		}

		public void setGmtCreate(Date gmtCreate) {
			this.gmtCreateFmt = DateTool.formatToDay(gmtCreate);
		}

		public String getCommission() {
			return commission;
		}

		public void setCommission(String commission) {
			this.commission = commission;
		}
	}
}
