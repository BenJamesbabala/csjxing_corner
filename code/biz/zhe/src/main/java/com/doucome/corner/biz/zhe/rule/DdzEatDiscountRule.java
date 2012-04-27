package com.doucome.corner.biz.zhe.rule;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.digester.Digester;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import com.doucome.corner.biz.core.constant.DecimalConstant;
import com.doucome.corner.biz.core.model.AbstractModel;
import com.doucome.corner.biz.core.utils.DecimalUtils;

/**
 * 折扣规则，所有的rate都是，比如：1234.00代表12.34% 
 * @author shenjia.caosj 2012-4-8
 *
 */
public class DdzEatDiscountRule extends AbstractModel implements InitializingBean {
	
	private Resource configLocation ;
	
	private List<Rule> ruleList = new ArrayList<Rule>() ;
	
	private static Rule defaultRule = new Rule(new BigDecimal("0") , new BigDecimal("0") , new BigDecimal("0")) ;
	
	/**
	 * 淘宝默认的折扣
	 * @return
	 */
	public static final BigDecimal TAOBAO_DEFAULT_RATE = new BigDecimal("10") ;
	
	public void setConfigLocation(Resource configLocation) {
		this.configLocation = configLocation;
	}	

	@Override
	public void afterPropertiesSet() throws Exception {
		if(this.configLocation == null){
			throw new IllegalArgumentException("configLocation is null .") ;
		}		
		
		Digester digester = new Digester();

        digester.setValidating(false);
        digester.addObjectCreate("businesses", DdzEatDiscountRule.class);
        digester.addObjectCreate("rules/rule", Rule.class);
        digester.addSetProperties("rules/rule", "startCommission", "startCommission");
        digester.addSetProperties("rules/rule", "endCommission", "endCommission");
        digester.addSetProperties("rules/rule", "eatRate", "eatRate");
        digester.addSetNext("rules/rule", "addRule");
		
        //rule  =  (DdzEatDiscountRule)digester.parse(configLocation.getInputStream());
        //System.out.println(rule);
        
        
        ruleList.add(new Rule(new BigDecimal("5.00"), new BigDecimal("10.00"), new BigDecimal("5"))) ;
        ruleList.add(new Rule(new BigDecimal("10.00"), new BigDecimal("20.00"), new BigDecimal("6"))) ;
        ruleList.add(new Rule(new BigDecimal("20.00"), new BigDecimal("30.00"), new BigDecimal("7"))) ;
        ruleList.add(new Rule(new BigDecimal("30.00"), new BigDecimal("40.00"), new BigDecimal("8"))) ;
        ruleList.add(new Rule(new BigDecimal("40.00"), new BigDecimal("50.00"), new BigDecimal("9"))) ;
        ruleList.add(new Rule(new BigDecimal("50.00"), new BigDecimal("1000000.00"), new BigDecimal("10"))) ;
        
	}
	
	/**
	 * 
	 * @param commssion 原始佣金  如 12.50（单位 元，精确到小数点后2位）
	 * @param commissionRate 原始佣金比例  如  （10.20，表示10.20%，精确到小数点后2位）
	 * @param price 价格
	 * @return UserCommission
	 */
	public static UserCommission calcUserCommissions(DdzEatDiscountRule rule , BigDecimal commission , BigDecimal commissionRate , BigDecimal price){
		UserCommission uc = new UserCommission() ;
		Rule r = rule.getRule(commission) ;
    	//
    	BigDecimal eatRate = r.getEatUserRate() ;
    	//淘宝默认的10%折扣
    	BigDecimal taobaoDefaultRate = DdzEatDiscountRule.TAOBAO_DEFAULT_RATE ;
    	
    	//总的佣金比例
    	BigDecimal totalRate = DecimalConstant.HUNDRED ;
    	
    	//用户的佣金比例 = 总比例 - 淘宝的默认比例 - 点点折吃掉的比例
    	BigDecimal userRate = DecimalUtils.substract(totalRate, taobaoDefaultRate) ;
    	userRate = DecimalUtils.substract(userRate , eatRate) ;
    	BigDecimal userRatePercent = DecimalUtils.divide(userRate, DecimalConstant.HUNDRED) ;
    	BigDecimal userCommissionRate = DecimalUtils.multiply(commissionRate,userRatePercent) ;
    	
    	//设置精度，小数点后三位
    	if(userCommissionRate != null){
    		userCommissionRate = userCommissionRate.setScale(1, RoundingMode.DOWN) ;
    	}
    	
    	
    	//佣金
    	BigDecimal userCommissionRatePercent = DecimalUtils.divide(userCommissionRate , DecimalConstant.HUNDRED) ; 
    	BigDecimal userCommission = DecimalUtils.multiply(price , userCommissionRatePercent) ;
		
    	if(userCommission != null){
    		uc.setCommission(userCommission.setScale(2,RoundingMode.DOWN)) ;
    		if(userCommission.compareTo(commission) == 1){
	    		uc.setCommission(commission) ; //防止计算后的值大于返还的佣金
	    	}
    	}
    	if(userCommissionRate != null){
	    	uc.setCommissionRate(userCommissionRate.setScale(2,RoundingMode.DOWN)) ;
	    	
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
	
	public static class UserCommission extends AbstractModel {
		
		private BigDecimal commissionRate ;
		
		private BigDecimal commission ;

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
