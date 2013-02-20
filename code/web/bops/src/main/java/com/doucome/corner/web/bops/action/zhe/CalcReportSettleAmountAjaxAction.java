package com.doucome.corner.web.bops.action.zhe;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.enums.SumTaokeReportTypeEnums;
import com.doucome.corner.biz.core.utils.DateUtils;
import com.doucome.corner.biz.dal.condition.TaokeReportSearchCondition;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.common.model.JsonModel;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ¼ÆËã×Ü½ð¶î
 * @author langben 2012-9-29
 *
 */
public class CalcReportSettleAmountAjaxAction extends BopsBasicAction implements ModelDriven<TaokeReportSearchCondition> {

	private TaokeReportSearchCondition condition = new TaokeReportSearchCondition();
	
	private JsonModel<BigDecimal> json = new JsonModel<BigDecimal>() ;
	
	private String sumType = SumTaokeReportTypeEnums.SETTLE_FEE.toString() ;
	
	@Autowired
	private DdzTaokeReportService ddzTaokeReportService ;
	
	@Override
	public String execute() throws Exception {
		
		try {
			condition.setGmtSettledStart(DateUtils.setTime(condition.getGmtSettledStart(), 0 , 0 , 0)) ;
			condition.setGmtSettledEnd(DateUtils.setTime(condition.getGmtSettledEnd(), 23 , 59 , 59)) ;
			condition.setGmtCreateStart(DateUtils.setTime(condition.getGmtCreateStart(), 0, 0, 0)) ;
			condition.setGmtCreateEnd(DateUtils.setTime(condition.getGmtCreateEnd(), 23 , 59 , 59)) ;
			SumTaokeReportTypeEnums ee = SumTaokeReportTypeEnums.get(sumType) ;
			
			if(ee == SumTaokeReportTypeEnums.UNKNOWN){
				ee = SumTaokeReportTypeEnums.COMMISSION ;
			}
			
			BigDecimal amount = ddzTaokeReportService.calcTaokeReportTotalSettleFee(condition , ee) ;
			
			json.setCode(JsonModel.CODE_SUCCESS) ;
			json.setData(amount) ;
			
		} catch(Exception e){
			json.setCode(JsonModel.CODE_FAIL) ;
		}
		return SUCCESS ;
	}
	
	public JsonModel<BigDecimal> getJson() {
		return json;
	}

	@Override
	public TaokeReportSearchCondition getModel() {
		return condition ;
	}

	public void setCondition(TaokeReportSearchCondition condition) {
		this.condition = condition;
	}

	public void setSumType(String sumType) {
		this.sumType = sumType;
	}

	
	
}
