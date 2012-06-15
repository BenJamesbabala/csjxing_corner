package com.doucome.corner.web.zhe.action.ajax;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.enums.SettleStatusEnums;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.taobao.model.TaokeReportSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportService;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportSettleService;
import com.doucome.corner.web.zhe.action.DdzBasicAction;
import com.doucome.corner.web.zhe.model.JsonModel;

@SuppressWarnings("serial")
public class UserinfoAction extends DdzBasicAction {

	private JsonModel<InternalUserinfo> json = new JsonModel<InternalUserinfo>();
	
	@Autowired
	private DdzTaokeReportSettleService ddzTaokeReportSettleService ;
	
	@Autowired
	private DdzTaokeReportService ddzTaokeReportService ;
	
	@Override
	public String execute() throws Exception {
		String alipay = ddzAuthz.getAlipayId() ;
		
		if(StringUtils.isBlank(alipay)) {
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("alipay is blank .") ;
			return SUCCESS ;
		}
		
		json.setCode(JsonModel.CODE_SUCCESS) ;
		InternalUserinfo user = new InternalUserinfo() ;
		user.setAlipayId(alipay) ;
		
		BigDecimal total = ddzTaokeReportSettleService.getTotalSettleFee(alipay, new String[]{SettleStatusEnums.SETTLE_SUCCESS.getValue()}) ;
		user.setTotalCommission(total) ;
		
		//获取最近的5笔记录
		TaokeReportSearchCondition searchCondition = new TaokeReportSearchCondition() ;
		searchCondition.setSettleAlipay(alipay) ;
		searchCondition.setSettleStatusList(new String[]{SettleStatusEnums.SETTLE_SUCCESS.getValue(),SettleStatusEnums.UNSETTLE.getValue()}) ;
		QueryResult<DdzTaokeReportDO> result = ddzTaokeReportService.getReportsWithPagination(searchCondition, new Pagination(1, 5)) ;
		user.setRecentReportList(result.getItems()) ;
		json.setData(user) ;
		return SUCCESS ;
	}
	
	public static class InternalUserinfo {
		/**
		 * 支付宝账号
		 */
		private String alipayId ;
		/**
		 * 总佣金(共节省)
		 */
		private BigDecimal totalCommission ;
		
		/**
		 * 最近5笔返现记录
		 */
		private List<DdzTaokeReportDO> recentReportList ;
		
		public String getAlipayId() {
			return alipayId;
		}

		public void setAlipayId(String alipayId) {
			this.alipayId = alipayId;
		}

		public BigDecimal getTotalCommission() {
			return totalCommission;
		}

		public void setTotalCommission(BigDecimal totalCommission) {
			this.totalCommission = totalCommission;
		}

		public List<DdzTaokeReportDO> getRecentReportList() {
			return recentReportList;
		}

		public void setRecentReportList(List<DdzTaokeReportDO> recentReportList) {
			this.recentReportList = recentReportList;
		}
		
	}
	

	public JsonModel<InternalUserinfo> getJson() {
		return json;
	}
}
