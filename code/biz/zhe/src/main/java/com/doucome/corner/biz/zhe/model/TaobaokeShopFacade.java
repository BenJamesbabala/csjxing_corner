package com.doucome.corner.biz.zhe.model;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.common.utils.ReflectUtils;
import com.doucome.corner.biz.core.model.URLModel;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeShopDTO;
import com.doucome.corner.biz.core.utils.HttpUrlHelper;
import com.doucome.corner.biz.dal.model.AbstractModel;
import com.doucome.corner.biz.zhe.rule.DdzEatDiscountRule;
import com.doucome.corner.biz.zhe.rule.DdzEatDiscountRule.InternalCommission;

public class TaobaokeShopFacade extends AbstractModel{
	
	private transient DdzEatDiscountRule rule ;
	
	private URLModel clickUrlModel ;
	
	private URLModel partnerClickUrlModel ;

	public TaobaokeShopFacade(TaobaokeShopDTO shop , DdzEatDiscountRule rule) {
		if(shop != null){
			ReflectUtils.reflectTo(shop, this) ;
			this.rule = rule ;
			this.clickUrlModel = HttpUrlHelper.parseURL(shop.getClickUrl()) ;
			__calcCommissions() ;
		}
	}
	
	private void __calcCommissions(){
		InternalCommission internalCommission = null ;
		if(partnerCommissionRate == null){
			internalCommission = DdzEatDiscountRule.calcUserCommission(rule ,  commissionRate) ;
		} else {
			internalCommission = DdzEatDiscountRule.calcUserCommission(rule ,  partnerCommissionRate) ;
		}
		this.userCommissionRate = internalCommission.getCommissionRate() ;
	}
	
	/**
	 * �Ƿ��Ǻ�������
	 */
	private boolean isPartner ;
	
	/**
	 * shopId
	 */
	private String sid ;
	
	/**
	 * �����û�id,1212 	
	 */
	private Long userId ;
	
	/**
	 * ��������
	 */
	private String shopTitle ;
	
	/**
	 * 	�����ƹ�URL , http://blog.open.taobao.com 
	 */
	private String clickUrl ;
	
	/**
	 * �Ա��͵���Ӷ����� , 12% 	
	 */
	private BigDecimal commissionRate ;
	
	/**
	 * �����ƹ����õȼ�,14 	
	 */
	private String sellerCredit ;
	
	/**
	 * �������� , B=�̳����� C=��ͨ����
	 */
	private String shopType ;
	
	/**
	 * 200 	�ۼ��ƹ���
	 */
	private String totalAuction ;
	
	/**
	 * ��������Ʒ������,100 	
	 */
	private Long auctionCount ;
	
	private BigDecimal userCommissionRate ;
		
	private String partnerClickUrl ;
	
	private BigDecimal partnerCommissionRate ;
	
	private String partnerShopTitle ;
	
	
	public void setBrandPartner(DdzBrandPartnerDTO partner){
		if(!StringUtils.equals(partner.getSid() , this.sid)){
			return ;
		}
		this.partnerClickUrl = partner.getClickUrl() ;
		this.partnerCommissionRate = partner.getCommissionRate() ;
		this.partnerShopTitle = partner.getShopTitle() ;
		this.partnerClickUrlModel = HttpUrlHelper.parseURL(this.partnerClickUrl) ;
		//�滻�û�����
		if(partnerClickUrlModel != null &&  clickUrlModel != null){
			Map<String,String> partParams = partnerClickUrlModel.getParams() ;
			Map<String,String> oriParams = clickUrlModel.getParams() ;
			String pid = oriParams.get("pid") ;
			String unid = oriParams.get("unid") ;
			if(StringUtils.isNotBlank(pid)){
				partParams.put("p", pid) ;
			}
			if(StringUtils.isNotBlank(unid)){
				partParams.put("unid", unid ) ;
			}
		}
		setPartner(true) ;
		
		__calcCommissions() ;//���¼���Ӷ��
	}
	
	/**
	 * �û���ʾ��title
	 * @return
	 */
	public String getUserShopTitle(){
		if(StringUtils.isNotBlank(partnerShopTitle)){
			return partnerShopTitle ;
		}
		return shopTitle ;
	}
	
	public String getUserClickUrl(){
		if(partnerClickUrlModel != null){
			return this.partnerClickUrlModel.toString() ;
		} else if(this.clickUrl != null) {
			return this.clickUrlModel.toString() ;
		}
		return null ;
	}
	
	public String getPartnerClickUrl() {
		return partnerClickUrl;
	}

	public void setPartnerClickUrl(String partnerClickUrl) {
		this.partnerClickUrl = partnerClickUrl;
	}

	public BigDecimal getPartnerCommissionRate() {
		return partnerCommissionRate;
	}

	public void setPartnerCommissionRate(BigDecimal partnerCommissionRate) {
		this.partnerCommissionRate = partnerCommissionRate;
	}

	public String getPartnerShopTitle() {
		return partnerShopTitle;
	}

	public void setPartnerShopTitle(String partnerShopTitle) {
		this.partnerShopTitle = partnerShopTitle;
	}

	/**********************************************************/

	public BigDecimal getUserCommissionRate() {
		return userCommissionRate;
	}

	public void setUserCommissionRate(BigDecimal userCommissionRate) {
		this.userCommissionRate = userCommissionRate;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getShopTitle() {
		return shopTitle;
	}

	public void setShopTitle(String shopTitle) {
		this.shopTitle = shopTitle;
	}

	public String getClickUrl() {
		return clickUrl;
	}

	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}

	public BigDecimal getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(BigDecimal commissionRate) {
		this.commissionRate = commissionRate;
	}

	public String getSellerCredit() {
		return sellerCredit;
	}

	public void setSellerCredit(String sellerCredit) {
		this.sellerCredit = sellerCredit;
	}

	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	public String getTotalAuction() {
		return totalAuction;
	}

	public void setTotalAuction(String totalAuction) {
		this.totalAuction = totalAuction;
	}

	public Long getAuctionCount() {
		return auctionCount;
	}

	public void setAuctionCount(Long auctionCount) {
		this.auctionCount = auctionCount;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public boolean isPartner() {
		return isPartner;
	}

	public void setPartner(boolean isPartner) {
		this.isPartner = isPartner;
	}
	
	
}
