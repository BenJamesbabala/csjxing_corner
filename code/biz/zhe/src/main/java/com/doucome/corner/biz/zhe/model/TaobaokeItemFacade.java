package com.doucome.corner.biz.zhe.model;

import java.math.BigDecimal;

import com.doucome.corner.biz.core.constant.DecimalConstant;
import com.doucome.corner.biz.core.enums.TaobaoPicEnums;
import com.doucome.corner.biz.core.taobao.dto.TaobaoItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.core.utils.ReflectUtils;
import com.doucome.corner.biz.zhe.rule.DdzEatDiscountRule;
import com.doucome.corner.biz.zhe.rule.DdzEatDiscountRule.UserCommission;
import com.doucome.corner.biz.zhe.utils.TaobaoPicUtils;

/**
 * 展示给外部的Item对象
 * 
 * @author shenjia.caosj 2012-3-8
 */
public class TaobaokeItemFacade {
		
	public TaobaokeItemFacade(TaobaokeItemDTO item){
		if(item != null){
			ReflectUtils.reflectTo(item, this) ;
			sumPicUrl = TaobaoPicUtils.findPic(picUrl, TaobaoPicEnums.SUM) ;
		}
	}
	
	public TaobaokeItemFacade(TaobaoItemDTO item){
		if(item != null){
			ReflectUtils.reflectTo(item, this) ;
			sumPicUrl = TaobaoPicUtils.findPic(picUrl, TaobaoPicEnums.SUM) ;
			commissionRate = new BigDecimal("0.00") ;
			commission = new BigDecimal("0.00") ;
			commissionNum = "0" ;
		}
		
	}

	/**
	 * 商品url ,,http://item.taobao.com/item.htm?id=4947813209 	
	 */
	private String detailUrl ;
	
    /**
     * 淘宝客佣金比率，比如：1234.00代表12.34% ,比如：500.00 
     */
    private BigDecimal commissionRate;

    /**
     * 淘宝客商品id(注意：iid近期即将废弃，请用num_iid参数) ,比如： 234
     */
    private String     iid;

    /**
     * 淘宝客商品数字id , 比如： 123
     */
    private Long       numIid;

    /**
     * 商品title 宝贝名称
     */
    private String     title;
    
    /**
     * 关键字高亮的宝贝名称
     */
    private String 	   titleHighlight ;

    /**
     * 卖家昵称 , 比如：jayzhou
     */
    private String     nick;

    /**
     * 图片url , 比如：http://img01.taobaocdn.com/bao/uploaded/i1/T1GM8KXkheXXXz9q_b_093149 .jpg
     */
    private String     picUrl;

    /**
     * 索罗图
     */
    private String     sumPicUrl;

    /**
     * 商品原价格 , 比如：12.15
     */
    private BigDecimal price;
    
    /**
     * 促销价格
     */
    private BigDecimal promotionPrice ;

	/**
	 * 
	 */
    private String     keywordClickUrl;

    /**
     * 推广点击url ,比如：http://s.click.taobao.com/.....&p=mm_15999136_0_0&n=19&u=12001469
     */
    private String     clickUrl;

    /**
     * 淘宝客佣金 ,比如：12.15(为淘宝查询出来的佣金扣除10%之后的佣金)
     */
    private BigDecimal commission;

    /**
     * 累计成交量.注：返回的数据是30天内累计推广量 ,比如：123
     */
    private String     commissionNum;

    /**
     * 累计总支出佣金量,比如：12.15
     */
    private BigDecimal commissionVolume;

    /**
     * 商品所在店铺的推广点击url ,比如：http://s.click.taobao.com/.....&p=mm_15999136_0_0&n=19&u=12001469
     */
    private String     shopClickUrl;

    /**
     * 卖家信用等级 , 比如：12
     */
    private Long       sellerCreditScore;

    /**
     * 商品所在地 ,比如：北京
     */
    private String     itemLocation;

    /**
     * 30天内交易量,比如：20
     */
    private Long       volume;

    /**
     * shortUrl
     */
    private String     clickUrlShorten ;
    
    /**
     * 显示给用户的佣金
     */
    private BigDecimal userCommission ;
    
    /**
     * 显示给用户的佣金比例
     */
    private BigDecimal userCommissionRate ;
    
    /**
     * 计算实际显示的佣金和佣金比率
     */
    public void calcFacadeCommissions(DdzEatDiscountRule rule){
    	
    	//由于查询到的结果是  7000 表示70% ，所以要先除100，再X100
    	BigDecimal _userCommissionRate = this.commissionRate.divide(DecimalConstant.HUNDRED) ;
    	BigDecimal _userCommission = this.commission ;
    	BigDecimal _price = this.promotionPrice == null ? this.price : this.promotionPrice ;
    	
    	UserCommission userCommission = DdzEatDiscountRule.calcUserCommissions(rule, _userCommission, _userCommissionRate, _price) ;
    	
    	this.userCommission = userCommission.getCommission() ;
    	this.userCommissionRate = userCommission.getCommissionRate().multiply(DecimalConstant.HUNDRED) ;
    }
    
    public String getPic(String type){
    	return TaobaoPicUtils.findPic(this.picUrl, TaobaoPicEnums._210x210) ;
    }
    
    /**
     * ------------------------------------------------------------------------
     */
    
    public BigDecimal getShowPrice(){
    	if(promotionPrice != null){
    		return promotionPrice ;
    	}
    	return price ;
    }
    
    public String getTitleHighlight() {
		return titleHighlight;
	}

	public void setTitleHighlight(String titleHighlight) {
		this.titleHighlight = titleHighlight;
	}

	
    public String getClickUrlShorten() {
		return clickUrlShorten;
	}

	public void setClickUrlShorten(String clickUrlShorten) {
		this.clickUrlShorten = clickUrlShorten;
	}

	public BigDecimal getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(BigDecimal commissionRate) {
        this.commissionRate = commissionRate;
    }

    public String getSumPicUrl() {
        return sumPicUrl;
    }

    public void setSumPicUrl(String sumPicUrl) {
        this.sumPicUrl = sumPicUrl;
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

    public String getKeywordClickUrl() {
        return keywordClickUrl;
    }

    public void setKeywordClickUrl(String keywordClickUrl) {
        this.keywordClickUrl = keywordClickUrl;
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

	public BigDecimal getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(BigDecimal promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

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

	public String getDetailUrl() {
		return detailUrl;
	}

	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}
	
	

}
