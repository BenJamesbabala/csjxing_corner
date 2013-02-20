package com.doucome.corner.biz.zhe.rule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.constant.DecimalConstant;
import com.doucome.corner.biz.core.taobao.config.SettleConfig;
import com.doucome.corner.biz.core.taobao.config.SettleConfigMgr;
import com.doucome.corner.biz.core.utils.DecimalUtils;
import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 折扣规则，所有的rate都是，比如：1234.00代表12.34% 
 * @author langben 2012-4-8
 *
 */
public class DdzEatDiscountRule extends AbstractModel implements InitializingBean {
	
	private List<Rule> ruleList = new ArrayList<Rule>() ;
	
	private static Rule defaultRule = new Rule(new BigDecimal("0") , new BigDecimal("0") , new BigDecimal("0")) ;
	
	@Autowired
	SettleConfig ddzSettleConfig ;
	
	/**
	 * 淘宝默认的折扣
	 * @return
	 */
	public static final BigDecimal TAOBAO_DEFAULT_RATE = new BigDecimal("10") ;
	
	/**
	 * 天猫税率
	 */
	public static final BigDecimal TMALL_TAX_RATE = new BigDecimal("1") ;
	
	@Override
	public void afterPropertiesSet() throws Exception {	
        
        ruleList.add(new Rule(new BigDecimal("1.00"), new BigDecimal("10000000.00"), 
        		ddzSettleConfig.getEatCommissionRate())) ;
        
	}
	
	/**
	 * 
	 * @param commssion 原始佣金  如 12.50（单位 元，精确到小数点后2位）
	 * @param commissionRate 原始佣金比例  如  （10.20，表示10.20%，精确到小数点后2位）
	 * @param price 价格
	 * @return UserCommission
	 */
	public static InternalCommission calcUserCommission(DdzEatDiscountRule rule , BigDecimal commissionRate){
		
		InternalCommission uc = new InternalCommission() ;
		BigDecimal eatRate = SettleConfigMgr.get(SettleConfigMgr.DDZ).getEatCommissionRate() ;
		
    	//淘宝默认的10%折扣
    	BigDecimal taobaoDefaultRate = DdzEatDiscountRule.TAOBAO_DEFAULT_RATE ;
    	
    	//总的佣金比例
    	BigDecimal totalRate = DecimalConstant.HUNDRED ;
    	
    	BigDecimal userRate = DecimalUtils.substract(totalRate , eatRate) ;
    	userRate = DecimalUtils.substract(userRate, taobaoDefaultRate) ;
    	
    	
    	uc.setCommissionRate(DecimalUtils.divide(DecimalUtils.multiply(commissionRate, userRate),DecimalConstant.HUNDRED)) ;
    	
    	uc.setDdzCommissionRate(DecimalUtils.divide(DecimalUtils.multiply(commissionRate, eatRate),DecimalConstant.HUNDRED)) ;
    	
    	return uc ;
	}
	
	/**
	 * 
	 * @param commssion 原始佣金  如 12.50（单位 元，精确到小数点后2位）
	 * @param commissionRate 原始佣金比例  如  （10.20，表示10.20%，精确到小数点后2位）
	 * @param price 价格
	 * @return UserCommission
	 */
	public static InternalCommission calcUserCommissions(DdzEatDiscountRule rule , BigDecimal commission , BigDecimal commissionRate , BigDecimal price){
		return calcUserCommissions(rule, commission, commissionRate, price, false) ;
	}
	
	/**
	 * 
	 * @param commssion 原始佣金  如 12.50（单位 元，精确到小数点后2位）
	 * @param commissionRate 原始佣金比例  如  （10.20，表示10.20%，精确到小数点后2位）
	 * @param price 价格
	 * @param isMall 是否商城
	 * @return UserCommission
	 */
	public static InternalCommission calcUserCommissions(DdzEatDiscountRule rule , BigDecimal commission , BigDecimal commissionRate , BigDecimal price , boolean isMall){
		InternalCommission uc = new InternalCommission() ;
		Rule r = rule.getRule(commission) ;
    	//
    	BigDecimal eatRate = r.getEatUserRate() ;
    	//淘宝默认的10%折扣
    	BigDecimal taobaoDefaultRate = DdzEatDiscountRule.TAOBAO_DEFAULT_RATE ;
    	
    	//总的佣金比例
    	BigDecimal totalRate = DecimalConstant.HUNDRED ;
    	
    	//用户的佣金比例 = 总比例 - 淘宝的默认比例 - 点点折吃掉的比例 - （商城税率）
    	BigDecimal userRate = DecimalUtils.substract(totalRate, taobaoDefaultRate) ;
    	if(isMall){
    		userRate = DecimalUtils.substract(userRate, DdzEatDiscountRule.TMALL_TAX_RATE) ;
    	}
    	userRate = DecimalUtils.substract(userRate , eatRate) ;
    	BigDecimal userRatePercent = DecimalUtils.divide(userRate, DecimalConstant.HUNDRED) ;
    	BigDecimal eatRatePercent = DecimalUtils.divide(eatRate, DecimalConstant.HUNDRED) ;
    	BigDecimal userCommissionRate = DecimalUtils.multiply(commissionRate,userRatePercent) ;
    	BigDecimal eatCommissionRate = DecimalUtils.multiply(commissionRate, eatRatePercent) ;
    	//设置精度，小数点后三位
    	if(userCommissionRate != null){
//    		userCommissionRate = userCommissionRate.setScale(1, RoundingMode.DOWN) ;
    	}
    	
    	
    	//佣金
    	BigDecimal userCommissionRatePercent = DecimalUtils.divide(userCommissionRate , DecimalConstant.HUNDRED) ; 
    	BigDecimal userCommission = DecimalUtils.multiply(price , userCommissionRatePercent) ;
		
    	if(userCommission != null){
    		uc.setCommission(userCommission) ;
    		if(userCommission.compareTo(commission) == 1){
	    		uc.setCommission(commission) ; //防止计算后的值大于返还的佣金
	    	}
    		uc.setDdzCommission(DecimalUtils.multiply(eatCommissionRate,price).divide(DecimalConstant.HUNDRED)) ;
    	}
    	if(userCommissionRate != null){
	    	uc.setCommissionRate(userCommissionRate) ;
	    	uc.setDdzCommissionRate(eatCommissionRate) ;
    	}
		return uc ;
	}

	/**
	 * 根据佣金取对应的规则
	 * @param commission
	 * @return
	 */
	public Rule getRule(BigDecimal commission){
		for(Rule rule : ruleList){
			BigDecimal start = rule.getStartCommission() ;
			BigDecimal end = rule.getEndCommission() ;
			if(DecimalUtils.greatEqualThan(commission ,start) && DecimalUtils.lessThan(commission , end)){
				return rule ;
			}
		}
		return defaultRule ;
	}
	
	public void addRule(Rule rule){
		this.ruleList.add(rule) ;
	}
	
	public static class InternalCommission extends AbstractModel {
		
		/**
		 * 返回给用户的佣金比率
		 */
		private BigDecimal commissionRate ;
		
		/**
		 * 返回给用户的佣金
		 */
		private BigDecimal commission ;
		
		/**
		 * 点点折的佣金
		 */
		private BigDecimal ddzCommission ;
		
		/**
		 * 点点折的佣金比率
		 */
		private BigDecimal ddzCommissionRate ;

		public BigDecimal getDdzCommission() {
			return ddzCommission;
		}

		public void setDdzCommission(BigDecimal ddzCommission) {
			this.ddzCommission = ddzCommission;
		}

		public BigDecimal getDdzCommissionRate() {
			return ddzCommissionRate;
		}

		public void setDdzCommissionRate(BigDecimal ddzCommissionRate) {
			this.ddzCommissionRate = ddzCommissionRate;
		}

		public BigDecimal getCommissionRate() {
			return commissionRate;
		}

		public void setCommissionRate(BigDecimal commissionRate) {
			this.commissionRate = commissionRate;
		}

		public BigDecimal getCommission() {
			return commission;
		}

		public void setCommission(BigDecimal commission) {
			this.commission = commission;
		}
		
		
	}


	public static class Rule extends AbstractModel {
				
		public Rule(BigDecimal startCommission, BigDecimal endCommission,BigDecimal eatUserRate) {
			super();
			this.startCommission = startCommission;
			this.endCommission = endCommission;
			this.eatUserRate = eatUserRate;
		}

		private BigDecimal startCommission;
		private BigDecimal endCommission;
		private BigDecimal eatUserRate;

		public BigDecimal getStartCommission() {
			return startCommission;
		}

		public void setStartCommission(BigDecimal startCommission) {
			this.startCommission = startCommission;
		}

		public BigDecimal getEndCommission() {
			return endCommission;
		}

		public void setEndCommission(BigDecimal endCommission) {
			this.endCommission = endCommission;
		}

		public BigDecimal getEatUserRate() {
			return eatUserRate;
		}

		public void setEatUserRate(BigDecimal eatUserRate) {
			this.eatUserRate = eatUserRate;
		}

	}
}
