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
 * �ۿ۹������е�rate���ǣ����磺1234.00����12.34% 
 * @author langben 2012-4-8
 *
 */
public class DdzEatDiscountRule extends AbstractModel implements InitializingBean {
	
	private List<Rule> ruleList = new ArrayList<Rule>() ;
	
	private static Rule defaultRule = new Rule(new BigDecimal("0") , new BigDecimal("0") , new BigDecimal("0")) ;
	
	@Autowired
	SettleConfig ddzSettleConfig ;
	
	/**
	 * �Ա�Ĭ�ϵ��ۿ�
	 * @return
	 */
	public static final BigDecimal TAOBAO_DEFAULT_RATE = new BigDecimal("10") ;
	
	/**
	 * ��è˰��
	 */
	public static final BigDecimal TMALL_TAX_RATE = new BigDecimal("1") ;
	
	@Override
	public void afterPropertiesSet() throws Exception {	
        
        ruleList.add(new Rule(new BigDecimal("1.00"), new BigDecimal("10000000.00"), 
        		ddzSettleConfig.getEatCommissionRate())) ;
        
	}
	
	/**
	 * 
	 * @param commssion ԭʼӶ��  �� 12.50����λ Ԫ����ȷ��С�����2λ��
	 * @param commissionRate ԭʼӶ�����  ��  ��10.20����ʾ10.20%����ȷ��С�����2λ��
	 * @param price �۸�
	 * @return UserCommission
	 */
	public static InternalCommission calcUserCommission(DdzEatDiscountRule rule , BigDecimal commissionRate){
		
		InternalCommission uc = new InternalCommission() ;
		BigDecimal eatRate = SettleConfigMgr.get(SettleConfigMgr.DDZ).getEatCommissionRate() ;
		
    	//�Ա�Ĭ�ϵ�10%�ۿ�
    	BigDecimal taobaoDefaultRate = DdzEatDiscountRule.TAOBAO_DEFAULT_RATE ;
    	
    	//�ܵ�Ӷ�����
    	BigDecimal totalRate = DecimalConstant.HUNDRED ;
    	
    	BigDecimal userRate = DecimalUtils.substract(totalRate , eatRate) ;
    	userRate = DecimalUtils.substract(userRate, taobaoDefaultRate) ;
    	
    	
    	uc.setCommissionRate(DecimalUtils.divide(DecimalUtils.multiply(commissionRate, userRate),DecimalConstant.HUNDRED)) ;
    	
    	uc.setDdzCommissionRate(DecimalUtils.divide(DecimalUtils.multiply(commissionRate, eatRate),DecimalConstant.HUNDRED)) ;
    	
    	return uc ;
	}
	
	/**
	 * 
	 * @param commssion ԭʼӶ��  �� 12.50����λ Ԫ����ȷ��С�����2λ��
	 * @param commissionRate ԭʼӶ�����  ��  ��10.20����ʾ10.20%����ȷ��С�����2λ��
	 * @param price �۸�
	 * @return UserCommission
	 */
	public static InternalCommission calcUserCommissions(DdzEatDiscountRule rule , BigDecimal commission , BigDecimal commissionRate , BigDecimal price){
		return calcUserCommissions(rule, commission, commissionRate, price, false) ;
	}
	
	/**
	 * 
	 * @param commssion ԭʼӶ��  �� 12.50����λ Ԫ����ȷ��С�����2λ��
	 * @param commissionRate ԭʼӶ�����  ��  ��10.20����ʾ10.20%����ȷ��С�����2λ��
	 * @param price �۸�
	 * @param isMall �Ƿ��̳�
	 * @return UserCommission
	 */
	public static InternalCommission calcUserCommissions(DdzEatDiscountRule rule , BigDecimal commission , BigDecimal commissionRate , BigDecimal price , boolean isMall){
		InternalCommission uc = new InternalCommission() ;
		Rule r = rule.getRule(commission) ;
    	//
    	BigDecimal eatRate = r.getEatUserRate() ;
    	//�Ա�Ĭ�ϵ�10%�ۿ�
    	BigDecimal taobaoDefaultRate = DdzEatDiscountRule.TAOBAO_DEFAULT_RATE ;
    	
    	//�ܵ�Ӷ�����
    	BigDecimal totalRate = DecimalConstant.HUNDRED ;
    	
    	//�û���Ӷ����� = �ܱ��� - �Ա���Ĭ�ϱ��� - ����۳Ե��ı��� - ���̳�˰�ʣ�
    	BigDecimal userRate = DecimalUtils.substract(totalRate, taobaoDefaultRate) ;
    	if(isMall){
    		userRate = DecimalUtils.substract(userRate, DdzEatDiscountRule.TMALL_TAX_RATE) ;
    	}
    	userRate = DecimalUtils.substract(userRate , eatRate) ;
    	BigDecimal userRatePercent = DecimalUtils.divide(userRate, DecimalConstant.HUNDRED) ;
    	BigDecimal eatRatePercent = DecimalUtils.divide(eatRate, DecimalConstant.HUNDRED) ;
    	BigDecimal userCommissionRate = DecimalUtils.multiply(commissionRate,userRatePercent) ;
    	BigDecimal eatCommissionRate = DecimalUtils.multiply(commissionRate, eatRatePercent) ;
    	//���þ��ȣ�С�������λ
    	if(userCommissionRate != null){
//    		userCommissionRate = userCommissionRate.setScale(1, RoundingMode.DOWN) ;
    	}
    	
    	
    	//Ӷ��
    	BigDecimal userCommissionRatePercent = DecimalUtils.divide(userCommissionRate , DecimalConstant.HUNDRED) ; 
    	BigDecimal userCommission = DecimalUtils.multiply(price , userCommissionRatePercent) ;
		
    	if(userCommission != null){
    		uc.setCommission(userCommission) ;
    		if(userCommission.compareTo(commission) == 1){
	    		uc.setCommission(commission) ; //��ֹ������ֵ���ڷ�����Ӷ��
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
	
	public static class InternalCommission extends AbstractModel {
		
		/**
		 * ���ظ��û���Ӷ�����
		 */
		private BigDecimal commissionRate ;
		
		/**
		 * ���ظ��û���Ӷ��
		 */
		private BigDecimal commission ;
		
		/**
		 * ����۵�Ӷ��
		 */
		private BigDecimal ddzCommission ;
		
		/**
		 * ����۵�Ӷ�����
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
