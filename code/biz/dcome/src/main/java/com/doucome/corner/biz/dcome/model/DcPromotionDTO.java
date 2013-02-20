package com.doucome.corner.biz.dcome.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;



import com.doucome.corner.biz.core.utils.DateUtils;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionDO;
import com.doucome.corner.biz.dal.model.AbstractModel;
import com.doucome.corner.biz.dcome.prom.PromotionRule;
import com.doucome.corner.web.common.model.ResultModel;

public class DcPromotionDTO extends AbstractModel implements PromotionRule {

	private static final long serialVersionUID = 3021927595969023843L;
	
	private DcPromotionDO promotion ;
	
	public DcPromotionDTO() {
		this.promotion = new DcPromotionDO();
		
	}
	
	public DcPromotionDTO(DcPromotionDO promotion){
		if(promotion == null){
			promotion = new DcPromotionDO() ;
		}
		this.promotion = promotion;
	}
	
	/**
	 * 根据开始结束时间判断状态
	 * @return -1(未开始)| 1（结束）|0进行中
	 */
	public int getTimeStatus(){
		Date start = getStartTime() ;
		Date end = getEndTime() ;
		if(start == null || end == null){
			return -1 ;
		}
		Date date = new Date() ;
		if(date.before(start)){
			return -1 ;
		}
		if(date.after(end)){
			return 1 ;
		}
		if(DateUtils.isBetween(start, end, date)){
			return 0 ;
		}
		return -1 ;
	}
	
	/**--------------------------------------------------------------------------**/
	
	public String getPromType() {
		return promotion.getPromType();
	}

	public void setPromType(String promType) {
		promotion.setPromType(promType) ;
	}

	public Long getId() {
		return promotion.getId();
	}


	public String getStatus() {
		return promotion.getStatus();
	}

	public BigDecimal getLimitTopPrice() {
		return promotion.getLimitTopPrice();
	}

	public Date getStartTime() {
		return promotion.getStartTime();
	}

	public Date getEndTime() {
		return promotion.getEndTime();
	}

	public Date getGmtCreate() {
		return promotion.getGmtCreate();
	}

	public Date getGmtModified() {
		return promotion.getGmtModified() ;
	}

	public DcPromotionDO getPromotion() {
		return this.promotion;
	}
	
	@Override
	public ResultModel<Boolean> checkSelf() {
		ResultModel<Boolean> checkResult = new ResultModel<Boolean>();
		Date date = Calendar.getInstance().getTime();
		if (date.after(getEndTime())) {
			checkResult.setFail(ResultModel.CODE_FAIL, ResultModel.DETAIL_PROM_ENDED);
		} else if (date.before(getStartTime())) {
			checkResult.setFail(ResultModel.CODE_FAIL, ResultModel.DETAIL_PROM_NOT_START);
		} else {
			checkResult.setSuccess(ResultModel.CODE_SUCCESS, true);
		}
		return checkResult;
	}
	
	@Override
	public ResultModel<Boolean> checkItem(DcItemDTO dcItem) {
		ResultModel<Boolean> checkResult = new ResultModel<Boolean>();
		BigDecimal itemPrice = dcItem.getItemPromPrice();
		if (itemPrice == null) {
			itemPrice = dcItem.getItemPrice();
		}
		if (getLimitTopPrice().compareTo(itemPrice) < 0) {
			checkResult.setFail(ResultModel.CODE_FAIL, ResultModel.DETAIL_ITEMPRICE_MORE);
		} else {
			checkResult.setSuccess(ResultModel.CODE_SUCCESS, true);
		}
		return checkResult;
	}
	
	/**
	 * 活动是否在进行中.
	 * @return
	 */
	public boolean isOnGoing() {
		Date cur = new Date();
		return cur.before(promotion.getEndTime()) && cur.after(promotion.getStartTime());
	}
}
