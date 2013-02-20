package com.doucome.corner.biz.zhe.model;

import java.math.BigDecimal;

import com.doucome.corner.biz.common.utils.ReflectUtils;
import com.doucome.corner.biz.core.constant.DecimalConstant;
import com.doucome.corner.biz.core.enums.TaobaoPicEnums;
import com.doucome.corner.biz.core.taobao.dto.TaobaoItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.core.taobao.enums.TaobaoSellerStartCreditEnums;
import com.doucome.corner.biz.core.utils.TaobaoPicUtils;
import com.doucome.corner.biz.dal.model.AbstractModel;
import com.doucome.corner.biz.zhe.rule.DdzEatDiscountRule;
import com.doucome.corner.biz.zhe.rule.DdzEatDiscountRule.InternalCommission;
import com.doucome.corner.biz.zhe.utils.DdzJfbConvertUtils;

/**
 * չʾ���ⲿ��Item����
 * 
 * @author langben 2012-3-8
 */
public class TaobaokeItemFacade extends AbstractModel {
	
	private InternalCommission internalUserCommission ;
//		
//	public TaobaokeItemFacade(TaobaokeItemDTO item){
//		if(item != null){
//			ReflectUtils.reflectTo(item, this) ;
//			sumPicUrl = TaobaoPicUtils.findPic(picUrl, TaobaoPicEnums.SUM) ;
//		}
//	}
	
	public TaobaokeItemFacade(TaobaokeItemDTO item,DdzEatDiscountRule rule,boolean isMall){
		if(item != null){
			ReflectUtils.reflectTo(item, this) ;
			sumPicUrl = TaobaoPicUtils.findPic(picUrl, TaobaoPicEnums.SUM) ;
		}
		
		this.isMall = isMall ;

		calcFacadeCommissions(rule) ;
	}
	
	public TaobaokeItemFacade(TaobaokeItemDTO item,DdzEatDiscountRule rule){
		if(item != null){
			ReflectUtils.reflectTo(item, this) ;
			sumPicUrl = TaobaoPicUtils.findPic(picUrl, TaobaoPicEnums.SUM) ;
		}

		calcFacadeCommissions(rule) ;
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
     * ����ʵ����ʾ��Ӷ���Ӷ�����
     */
    private void calcFacadeCommissions(DdzEatDiscountRule rule){
    	
    	//���ڲ�ѯ���Ľ����  7000 ��ʾ70% ������Ҫ�ȳ�100����X100
    	BigDecimal _userCommissionRate = this.commissionRate.divide(DecimalConstant.HUNDRED) ;
    	BigDecimal _userCommission = this.commission ;
    	BigDecimal _price = this.promotionPrice == null ? this.price : this.promotionPrice ;
    	
    	InternalCommission userCommission = DdzEatDiscountRule.calcUserCommissions(rule, _userCommission, _userCommissionRate, _price , isMall) ;
    	
    	this.userCommission = userCommission.getCommission() ;
    	this.userCommissionRate = userCommission.getCommissionRate().multiply(DecimalConstant.HUNDRED) ;
    	
    	this.internalUserCommission = userCommission ;
    	
    }
	
	/**
	 * ��Ʒurl ,,http://item.taobao.com/item.htm?id=4947813209 	
	 */
	private String detailUrl ;
	
    /**
     * �Ա���Ӷ����ʣ����磺1234.00����12.34% ,���磺500.00 
     */
    private BigDecimal commissionRate;

    /**
     * �Ա�����Ʒid(ע�⣺iid���ڼ�������������num_iid����) ,���磺 234
     */
    private String     iid;

    /**
     * �Ա�����Ʒ����id , ���磺 123
     */
    private Long       numIid;

    /**
     * ��Ʒtitle ��������
     */
    private String     title;
    
    /**
     * �ؼ��ָ����ı�������
     */
    private String 	   titleHighlight ;

    /**
     * �����ǳ� , ���磺jayzhou
     */
    private String     nick;

    /**
     * ͼƬurl , ���磺http://img01.taobaocdn.com/bao/uploaded/i1/T1GM8KXkheXXXz9q_b_093149 .jpg
     */
    private String     picUrl;

    /**
     * ����ͼ
     */
    private String     sumPicUrl;

    /**
     * ��Ʒԭ�۸� , ���磺12.15
     */
    private BigDecimal price;
    
    /**
     * �����۸�
     */
    private BigDecimal promotionPrice ;

	/**
	 * 
	 */
    private String     keywordClickUrl;

    /**
     * �ƹ���url ,���磺http://s.click.taobao.com/.....&p=mm_15999136_0_0&n=19&u=12001469
     */
    private String     clickUrl;

    /**
     * �Ա���Ӷ�� ,���磺12.15(Ϊ�Ա���ѯ������Ӷ��۳�10%֮���Ӷ��)
     */
    private BigDecimal commission;

    /**
     * �ۼƳɽ���.ע�����ص�������30�����ۼ��ƹ��� ,���磺123
     */
    private String     commissionNum;

    /**
     * �ۼ���֧��Ӷ����,���磺12.15
     */
    private BigDecimal commissionVolume;

    /**
     * ��Ʒ���ڵ��̵��ƹ���url ,���磺http://s.click.taobao.com/.....&p=mm_15999136_0_0&n=19&u=12001469
     */
    private String     shopClickUrl;

    /**
     * �������õȼ� , ���磺12
     */
    private Long       sellerCreditScore;

    /**
     * ��Ʒ���ڵ� ,���磺����
     */
    private String     itemLocation;

    /**
     * 30���ڽ�����,���磺20
     */
    private Long       volume;

    /**
     * shortUrl
     */
    private String     clickUrlShorten ;
    
    /**
     * ��ʾ���û���Ӷ��
     */
    private BigDecimal userCommission ;
    
    private String desc;
    
    private String wapDesc;
    
    /**
     * ��ʾ���û���Ӷ�����
     */
    private BigDecimal userCommissionRate ;
    
    /**
     * �Ƿ��̳�
     */
    private boolean isMall ;
        
    public InternalCommission getInternalUserCommission() {
		return internalUserCommission;
	}
    
    public String getPic(String type){
    	return TaobaoPicUtils.findPic(this.picUrl, TaobaoPicEnums.toTaobaoPicEnums(type)) ;
    }
    
    public String getSellerCreditScorePic(){
    	int i = 0 ;
    	if(this.sellerCreditScore != null){
    		long l = this.sellerCreditScore ;
    		i = (int)l;
    	}
    	String pic = TaobaoSellerStartCreditEnums.fromCode(i).getPic();
    	return "http://pics.taobaocdn.com/newrank/" + pic + ".gif" ;
    }
    
    public int getUserJfb(){
    	BigDecimal userCommission = getUserCommission() ;
    	return DdzJfbConvertUtils.convertMoney2Jfb(userCommission) ;
    }
    
    public BigDecimal getUserJfbByMoney(){
    	int jfb = getUserJfb() ;
    	BigDecimal money = new BigDecimal(jfb).divide(DecimalConstant.HUNDRED , 4, BigDecimal.ROUND_HALF_EVEN) ;
    	return money ;
    }
    
    public BigDecimal getUserJfbRate() {
    	BigDecimal userJfbRate =  DdzJfbConvertUtils.convertJfbCommissionRate(getUserJfb() , getPrice()) ;
    	return userJfbRate.multiply(DecimalConstant.WAN) ;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getWapDesc() {
		return wapDesc;
	}

	public void setWapDesc(String wapDesc) {
		this.wapDesc = wapDesc;
	}

	public boolean isMall() {
		return isMall;
	}

	public void setMall(boolean isMall) {
		this.isMall = isMall;
	}

}
