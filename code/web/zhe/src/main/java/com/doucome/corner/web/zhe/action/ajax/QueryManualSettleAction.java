package com.doucome.corner.web.zhe.action.ajax;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.dal.dataobject.DdzUserDO;
import com.doucome.corner.biz.zhe.bo.DdzTaokeReportSettleBO;
import com.doucome.corner.biz.zhe.utils.DdzTaokeReportUtils;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.zhe.action.DdzBasicAction;

@SuppressWarnings("serial")
public class QueryManualSettleAction extends DdzBasicAction{
	
	private static final Log log = LogFactory.getLog(QueryManualSettleAction.class) ;
	
	private JsonModel<Result> json = new JsonModel<Result>();

	@Autowired
	private DdzTaokeReportSettleBO ddzTaokeReportSettleBO ;
	
	@Override
	public String execute() throws Exception {
		
		DdzUserDO user = ddzAuthz.getUser() ;
		
		if(user == null){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("ddz.settle.manual.user.required") ;
			return SUCCESS ;
		}
		
		String alipayId = user.getAlipayId() ;
		if(StringUtils.isBlank(alipayId)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("ddz.settle.manual.alipay.required") ;
			return SUCCESS ;
		}
		try {
			List<DdzTaokeReportDO> doList = ddzTaokeReportSettleBO.getManualCombineSettlesByUser(alipayId) ;
			int size = CollectionUtils.size(doList) ;
			Result res = new Result() ;
			res.setCount(size) ;
			res.setAmount(DdzTaokeReportUtils.calcTotalSettleFee(doList)) ;
			json.setCode(JsonModel.CODE_SUCCESS) ;
			json.setData(res) ;
		}catch (Exception e){
			log.error(e.getMessage() , e) ;
			json.setCode(JsonModel.CODE_FAIL) ;
		}
		return SUCCESS ;
	}
	
	public static class Result {
		
		private int count ;
		
		private BigDecimal amount ;

		public BigDecimal getAmount() {
			return amount;
		}

		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}		
	}

	public JsonModel<Result> getJson() {
		return json;
	}
	
	
}
