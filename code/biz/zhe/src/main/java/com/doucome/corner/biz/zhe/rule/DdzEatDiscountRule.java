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
 * �ۿ۹������е�rate���ǣ����磺1234.00����12.34% 
 * @author shenjia.caosj 2012-4-8
 *
 */
public class DdzEatDiscountRule extends AbstractModel implements InitializingBean {
	
	private Resource configLocation ;
	
	private List<Rule> ruleList = new ArrayList<Rule>() ;
	
	private static Rule defaultRule = new Rule(new BigDecimal("0") , new BigDecimal("0") , new BigDecimal("0")) ;
	
	/**
	 * �Ա�Ĭ�ϵ��ۿ�
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
	 * @param commssion ԭʼӶ��  �� 12.50����λ Ԫ����ȷ��С�����2λ��
	 * @param commissionRate ԭʼӶ�����  ��  ��10.20����ʾ10.20%����ȷ��С�����2λ��
	 * @param price �۸�
	 * @return UserCommission
	 */
	public static UserCommission calcUserCommissions(DdzEatDiscountRule rule , BigDecimal commission , BigDecimal commissionRate , BigDecimal price){
		UserCommission uc = new UserCommission() ;
		Rule r = rule.getRule(commission) ;
    	//
    	BigDecimal eatRate = r.getEatUserRate() ;
    	//�Ա�Ĭ�ϵ�10%�ۿ�
    	BigDecimal taobaoDefaultRate = DdzEatDiscountRule.TAOBAO_DEFAULT_RATE ;
    	
    	//�ܵ�Ӷ�����
    	BigDecimal totalRate = DecimalConstant.HUNDRED ;
    	
    	//�û���Ӷ����� = �ܱ��� - �Ա���Ĭ�ϱ��� - ����۳Ե��ı���
    	BigDecimal userRate = DecimalUtils.substract(totalRate, taobaoDefaultRate) ;
    	userRate = DecimalUtils.substract(userRate , eatRate) ;
    	BigDecimal userRatePercent = DecimalUtils.divide(userRate, DecimalConstant.HUNDRED) ;
    	BigDecimal userCommissionRate = DecimalUtils.multiply(commissionRate,userRatePercent) ;
    	
    	//���þ��ȣ�С�������λ
    	if(userCommissionRate != null){
    		userCommissionRate = userCommissionRate.setScale(1, RoundingMode.DOWN) ;
    	}
    	
    	
    	//Ӷ��
    	BigDecimal userCommissionRatePercent = DecimalUtils.divide(userCommissionRate , DecimalConstant.HUNDRED) ; 
    	BigDecimal userCommission = DecimalUtils.multiply(price , userCommissionRatePercent) ;
		
    	if(userCommission != null){
    		uc.setCommission(userCommission.setScale(2,RoundingMode.DOWN)) ;
    		if(userCommission.compareTo(commission) == 1){
	    		uc.setCommission(commission) ; //��ֹ������ֵ���ڷ�����Ӷ��
	    	}
    	}
    	if(userCommissionRate != null){
	    	uc.setCommissionRate(userCommissionRate.setScale(2,RoundingMode.DOWN)) ;
	    	
    	}
		return uc ;
	}

	/**
	 * ����Ӷ��ȡ��Ӧ�Ĺ���
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
