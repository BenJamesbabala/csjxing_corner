package com.doucome.corner.biz.dcome.utils;

import java.math.BigDecimal;

import com.doucome.corner.biz.core.constant.DecimalConstant;
import com.doucome.corner.biz.core.taobao.config.SettleConfig;


/**
 * 计算返利
 * @author langben 2013-1-1
 *
 */
public class DcTbCommissionUtils {
	
	public static final int INTEGRAL_EXCHANGE_RATE = 100 ;
	
	public static final BigDecimal DECI_INTEGRAL_EXCHANGE_RATE = new BigDecimal(INTEGRAL_EXCHANGE_RATE) ;
	
	/**
	 * 
	 */
	public static InternalCommissions calcFacadeCommissions(BigDecimal price , BigDecimal commission , SettleConfig settleConfig){
		
		if(price == null || commission == null || settleConfig == null){
			return new InternalCommissions() ;
		}
		
		/**
		 * 用户比例 = 100% - 淘宝税率 - 集分宝税率 - 豆蔻吃掉的比例
		 */
		BigDecimal userRate = DecimalConstant.HUNDRED.subtract(settleConfig.getJfbTaxRate()).subtract(settleConfig.getTaobaoTaxRate()).subtract(settleConfig.getEatCommissionRate()) ;
		
		BigDecimal userCommission = commission.multiply(userRate).divide(DecimalConstant.HUNDRED ,4, BigDecimal.ROUND_HALF_EVEN ) ;
		
		InternalCommissions ic = new InternalCommissions() ;
		ic.setUserIntegral(userCommission.multiply(DECI_INTEGRAL_EXCHANGE_RATE).intValue()) ;
		ic.setUserIntegralRate(userCommission.divide(price , 4 , BigDecimal.ROUND_HALF_EVEN)) ;
		
		return ic ;
	}
	
	public static class InternalCommissions {
		
		private int userIntegral = 0 ;
		
		private BigDecimal userIntegralRate = DecimalConstant.ZERO ;

		public int getUserIntegral() {
			return userIntegral;
		}

		public void setUserIntegral(int userIntegral) {
			this.userIntegral = userIntegral;
		}

		public BigDecimal getUserIntegralRate() {
			return userIntegralRate;
		}

		public void setUserIntegralRate(BigDecimal userIntegralRate) {
			this.userIntegralRate = userIntegralRate;
		}
		
		
	}
	
}
