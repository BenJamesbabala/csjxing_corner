package com.doucome.corner.biz.zhe.vo;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.core.constant.DecimalConstant;
import com.doucome.corner.biz.core.enums.TaobaoPicEnums;
import com.doucome.corner.biz.core.model.AbstractModel;
import com.doucome.corner.biz.core.utils.DecimalUtils;
import com.doucome.corner.biz.dal.dataobject.DdzRecommendDO;
import com.doucome.corner.biz.zhe.rule.DdzEatDiscountRule;
import com.doucome.corner.biz.zhe.rule.DdzEatDiscountRule.UserCommission;
import com.doucome.corner.biz.zhe.utils.TaobaoPicUtils;

public class BuyingRecommendVO extends AbstractModel {
	
	

	public BuyingRecommendVO(DdzRecommendDO recommend, String alipayId) {
		super();
		this.recommend = recommend;
		this.alipayId = alipayId;
	}


	private DdzRecommendDO recommend ;
	
	private String alipayId ;

	public DdzRecommendDO getRecommend() {
		return recommend;
	}

	public void setRecommend(DdzRecommendDO recommend) {
		this.recommend = recommend;
	}

	public String getAlipayId() {
		return alipayId;
	}

	public void setAlipayId(String alipayId) {
		this.alipayId = alipayId;
	}
	
	public String getItemPicUrl(String type){
		return TaobaoPicUtils.findPic(this.recommend.getItemPicUrl(), TaobaoPicEnums.toTaobaoPicEnums(type)) ;
	}
	
	
	/**
     * ��ʾ���û���Ӷ��
     */
    private BigDecimal userCommission ;
    
    /**
     * ��ʾ���û���Ӷ�����
     */
    private BigDecimal userCommissionRate ;

	public BigDecimal getUserCommission() {
		return userCommission;
	}

	public void setUserCommission(BigDecimal userCommission) {
		this.userCommission = userCommission;
	}

	public BigDecimal getUserCommissionRate() {
		return userCommissionRate;
	}

	public void setUserCommissionRate(BigDecimal userCommissionRate) {
		this.userCommissionRate = userCommissionRate;
	}
    
	 /**
     * ����ʵ����ʾ��Ӷ���Ӷ�����
     */
    public void calcFacadeCommissions(DdzEatDiscountRule rule){
    	
    	//���ڲ�ѯ���Ľ����  7000 ��ʾ70% ������Ҫ�ȳ�100����X100
    	BigDecimal _userCommissionRate = this.recommend.getCommissionRate() ;
    	BigDecimal _userCommission = this.recommend.getCommission() ;
    	_userCommissionRate = DecimalUtils.divide(_userCommissionRate,new BigDecimal(100));
    	BigDecimal _price = this.recommend.getItemPrice() ;
    	
    	UserCommission userCommission = DdzEatDiscountRule.calcUserCommissions(rule, _userCommission, _userCommissionRate, _price) ;
    	
    	this.userCommission = userCommission.getCommission() ;
    	this.userCommissionRate = userCommission.getCommissionRate().multiply(DecimalConstant.HUNDRED) ;
    }
    
    public String getPrivateAlipay(){
    	return StringUtils.substring(this.alipayId, 0, 2)  + "****" ;
    }
}
