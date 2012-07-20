package com.doucome.corner.biz.core.taobao.dto;

import java.math.BigDecimal;

import com.doucome.corner.biz.core.model.AbstractModel;
import com.doucome.corner.biz.core.utils.ReflectUtils;
import com.taobao.api.domain.TaobaokeItem;

/**
 * 淘宝客Item Data Transfer DO
 * 
 * @author shenjia.caosj 2012-2-24
 * 
 */
public class TaobaokeItemDTO extends AbstractModel {
	
	public TaobaokeItemDTO(TaobaokeItem item){
		ReflectUtils.reflectTo(item, this) ;
		//淘宝的佣金比率500 代表 5%
		//this.commissionRate = DecimalUtils.divide(this.commissionRate , DecimalConstant.HUNDRED) ;
	}
	
	public TaobaokeItemDTO(){
		
	}

	/**
	 * 淘宝客佣金比率，比如：12.34代表12.34% ,比如：12.34
	 */
	private BigDecimal commissionRate;

	/**
	 * 淘宝客商品id(注意：iid近期即将废弃，请用num_iid参数) ,比如： 234
	 * 
	 */
	private String iid;

	/**
	 * 淘宝客商品数字id , 比如： 123
	 */
	private Long numIid;

	/**
	 * 商品title 宝贝名称
	 */
	private String title;
	
	/**
	 * 商品title高亮
	 */
	private String titleHighlight ;

	/**
	 * 卖家昵称 , 比如：jayzhou
	 */
	private String nick;

	/**
	 * 图片url ,
	 * 比如：http://img01.taobaocdn.com/bao/uploaded/i1/T1GM8KXkheXXXz9q_b_093149
	 * .jpg
	 */
	private String picUrl;

	/**
	 * 商品价格 , 比如：12.15
	 */
	private BigDecimal price;

	/**
	 * 
	 */
	private String keywordClickUrl;

	/**
	 * 推广点击url
	 * ,比如：http://s.click.taobao.com/.....&p=mm_15999136_0_0&n=19&u=12001469
	 */
	private String clickUrl;

	/**
	 * 淘宝客佣金 ,比如：12.15
	 */
	private BigDecimal commission;

	/**
	 * 累计成交量.注：返回的数据是30天内累计推广量 ,比如：123
	 */
	private String commissionNum;

	/**
	 * 累计总支出佣金量,比如：12.15
	 */
	private BigDecimal commissionVolume;

	/**
	 * 商品所在店铺的推广点击url
	 * ,比如：http://s.click.taobao.com/.....&p=mm_15999136_0_0&n=19&u=12001469
	 */
	private String shopClickUrl;

	/**
	 * 卖家信用等级 , 比如：12
	 */
	private Long sellerCreditScore;

	/**
	 * 商品所在地 ,比如：北京
	 */
	private String itemLocation;

	/**
	 * 30天内交易量,比如：20
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
