package com.doucome.corner.web.dcome.model;

import java.math.BigDecimal;

import com.doucome.corner.biz.core.constant.DecimalConstant;
import com.doucome.corner.biz.core.taobao.config.SettleConfigMgr;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.dcome.utils.DcTbCommissionUtils;
import com.doucome.corner.biz.dcome.utils.DcTbCommissionUtils.InternalCommissions;

public class TaobaokeItemFacade {
	
	TaobaokeItemDTO taobaokeItem ;
	
	/**
	 * 用户可以获得的积分对应的比率
	 */
	private BigDecimal userIntegralRate ;
	
	/**
	 * 用户可以获得的积分
	 */
	private int userIntegral ;
	
	public TaobaokeItemFacade(TaobaokeItemDTO taobaokeItem) {
		if(taobaokeItem == null){
			taobaokeItem = new TaobaokeItemDTO() ;
		} else {
			this.taobaokeItem = taobaokeItem ;
			InternalCommissions internalCommission = DcTbCommissionUtils.calcFacadeCommissions(getPrice(), getCommission(), SettleConfigMgr.get(SettleConfigMgr.DCOME)) ;
			if(internalCommission != null){
				this.userIntegral =  internalCommission.getUserIntegral() ;
				this.userIntegralRate = internalCommission.getUserIntegralRate() ;
			}
		}
	}
	
	/**
	 * 用户获得的积分
	 * @return
	 */
	public int getUserIntegral(){
		return userIntegral ;
	}
	
	public BigDecimal getUserIntegralByMoney(){
		int integral = getUserIntegral() ;
    	BigDecimal money = new BigDecimal(integral).divide(DecimalConstant.HUNDRED , 4, BigDecimal.ROUND_HALF_EVEN) ;
    	return money ;
	}
	
	/**
	 * 用户积分比例
	 * @return
	 */
	public BigDecimal getUserIntegralRate() {
		return userIntegralRate ;
	}
	
	public BigDecimal getUserIntegralRateX100(){
		
		if( userIntegralRate == null ){
			return DecimalConstant.ZERO ;
		}
		
		return userIntegralRate.multiply(DecimalConstant.HUNDRED) ;
	}
	
	/**
	 * ------------------------------------------------------------
	 */
	
	public BigDecimal getCommissionRate() {
		return taobaokeItem.getCommissionRate();
	}

	public String getIid() {
		return taobaokeItem.getIid();
	}

	public Long getNumIid() {
		return taobaokeItem.getNumIid();
	}

	public String getTitle() {
		return taobaokeItem.getTitle();
	}

	public String getTitleHighlight() {
		return taobaokeItem.getTitleHighlight();
	}

	public String getNick() {
		return taobaokeItem.getNick();
	}

	public String getPicUrl() {
		return taobaokeItem.getPicUrl();
	}

	public BigDecimal getPrice() {
		return taobaokeItem.getPrice();
	}

	public String getClickUrl() {
		return taobaokeItem.getClickUrl();
	}

	public BigDecimal getCommission() {
		return taobaokeItem.getCommission();
	}

	public String getCommissionNum() {
		return taobaokeItem.getCommissionNum();
	}

	public BigDecimal getCommissionVolume() {
		return taobaokeItem.getCommissionVolume();
	}

	public String getShopClickUrl() {
		return taobaokeItem.getShopClickUrl();
	}

	public Long getSellerCreditScore() {
		return taobaokeItem.getSellerCreditScore();
	}

	public String getItemLocation() {
		return taobaokeItem.getItemLocation();
	}

	public Long getVolume() {
		return taobaokeItem.getVolume();
	}

	public String getKeywordClickUrl() {
		return taobaokeItem.getKeywordClickUrl();
	}
	
}
