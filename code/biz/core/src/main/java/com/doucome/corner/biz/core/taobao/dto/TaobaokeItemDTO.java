package com.doucome.corner.biz.core.taobao.dto;

import java.math.BigDecimal;

import com.doucome.corner.biz.core.model.AbstractModel;
import com.doucome.corner.biz.core.utils.ReflectUtils;
import com.taobao.api.domain.TaobaokeItem;

/**
 * �Ա���Item Data Transfer DO
 * 
 * @author shenjia.caosj 2012-2-24
 * 
 */
public class TaobaokeItemDTO extends AbstractModel {
	
	public TaobaokeItemDTO(TaobaokeItem item){
		ReflectUtils.reflectTo(item, this) ;
		//�Ա���Ӷ�����500 ���� 5%
		//this.commissionRate = DecimalUtils.divide(this.commissionRate , DecimalConstant.HUNDRED) ;
	}
	
	public TaobaokeItemDTO(){
		
	}

	/**
	 * �Ա���Ӷ����ʣ����磺12.34����12.34% ,���磺12.34
	 */
	private BigDecimal commissionRate;

	/**
	 * �Ա�����Ʒid(ע�⣺iid���ڼ�������������num_iid����) ,���磺 234
	 * 
	 */
	private String iid;

	/**
	 * �Ա�����Ʒ����id , ���磺 123
	 */
	private Long numIid;

	/**
	 * ��Ʒtitle ��������
	 */
	private String title;
	
	/**
	 * ��Ʒtitle����
	 */
	private String titleHighlight ;

	/**
	 * �����ǳ� , ���磺jayzhou
	 */
	private String nick;

	/**
	 * ͼƬurl ,
	 * ���磺http://img01.taobaocdn.com/bao/uploaded/i1/T1GM8KXkheXXXz9q_b_093149
	 * .jpg
	 */
	private String picUrl;

	/**
	 * ��Ʒ�۸� , ���磺12.15
	 */
	private BigDecimal price;

	/**
	 * 
	 */
	private String keywordClickUrl;

	/**
	 * �ƹ���url
	 * ,���磺http://s.click.taobao.com/.....&p=mm_15999136_0_0&n=19&u=12001469
	 */
	private String clickUrl;

	/**
	 * �Ա���Ӷ�� ,���磺12.15
	 */
	private BigDecimal commission;

	/**
	 * �ۼƳɽ���.ע�����ص�������30�����ۼ��ƹ��� ,���磺123
	 */
	private String commissionNum;

	/**
	 * �ۼ���֧��Ӷ����,���磺12.15
	 */
	private BigDecimal commissionVolume;

	/**
	 * ��Ʒ���ڵ��̵��ƹ���url
	 * ,���磺http://s.click.taobao.com/.....&p=mm_15999136_0_0&n=19&u=12001469
	 */
	private String shopClickUrl;

	/**
	 * �������õȼ� , ���磺12
	 */
	private Long sellerCreditScore;

	/**
	 * ��Ʒ���ڵ� ,���磺����
	 */
	private String itemLocation;

	/**
	 * 30���ڽ�����,���磺20
	 */
	private Long volume;

	public BigDecimal getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(BigDecimal commissionRate) {
		this.commissionRate = commissionRate;
	}

	public String getIid() {
		return iid;
	}

	public void setIid(String iid) {
		this.iid = iid;
	}

	public Long getNumIid() {
		return numIid;
	}

	public void setNumIid(Long numIid) {
		this.numIid = numIid;
	}

	public String getTitle() {
		return title;
	}

	public String getTitleHighlight() {
		return titleHighlight;
	}

	public void setTitleHighlight(String titleHighlight) {
		this.titleHighlight = titleHighlight;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getClickUrl() {
		return clickUrl;
	}

	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	public String getCommissionNum() {
		return commissionNum;
	}

	public void setCommissionNum(String commissionNum) {
		this.commissionNum = commissionNum;
	}

	public BigDecimal getCommissionVolume() {
		return commissionVolume;
	}

	public void setCommissionVolume(BigDecimal commissionVolume) {
		this.commissionVolume = commissionVolume;
	}

	public String getShopClickUrl() {
		return shopClickUrl;
	}

	public void setShopClickUrl(String shopClickUrl) {
		this.shopClickUrl = shopClickUrl;
	}

	public Long getSellerCreditScore() {
		return sellerCreditScore;
	}

	public void setSellerCreditScore(Long sellerCreditScore) {
		this.sellerCreditScore = sellerCreditScore;
	}

	public String getItemLocation() {
		return itemLocation;
	}

	public void setItemLocation(String itemLocation) {
		this.itemLocation = itemLocation;
	}

	public Long getVolume() {
		return volume;
	}

	public void setVolume(Long volume) {
		this.volume = volume;
	}

	public String getKeywordClickUrl() {
		return keywordClickUrl;
	}

	public void setKeywordClickUrl(String keywordClickUrl) {
		this.keywordClickUrl = keywordClickUrl;
	}

	
}
