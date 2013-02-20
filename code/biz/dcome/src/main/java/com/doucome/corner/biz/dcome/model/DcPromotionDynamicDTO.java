package com.doucome.corner.biz.dcome.model;

import java.math.BigDecimal;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 
 * @author langben 2012-8-29
 *
 */
public class DcPromotionDynamicDTO extends AbstractModel {
	
    private static final long serialVersionUID = -1934452656954031102L;

    public static DcPromotionDynamicDTO newStealRateDynamic(
			Long promotionId , Long promItemId , Long userId , int integralCount){
		DcPromotionDynamicDTO dto = new DcPromotionDynamicDTO() ;
		dto.setIntegralCount(integralCount) ;
		dto.setPromItemId(promItemId) ;
		dto.setPromotionId(promotionId) ;
		dto.setType(DynamicTypeEnums.steal_rate) ;
		dto.setUserId(userId) ;
		
		return dto ;
	}
	
	public static DcPromotionDynamicDTO newPubItemDynamic(
			Long promotionId , Long promItemId , Long itemId , String itemTitle , BigDecimal itemPrice , Long userId , int integralCount ){
		DcPromotionDynamicDTO dto = new DcPromotionDynamicDTO() ;
		dto.setIntegralCount(integralCount) ;
		dto.setPromItemId(promItemId) ;
		dto.setPromotionId(promotionId) ;
		dto.setItemId(itemId) ;
		dto.setItemPrice(itemPrice) ;
		dto.setItemTitle(itemTitle) ;		
		dto.setType(DynamicTypeEnums.pub_item) ;
		dto.setUserId(userId) ;
		
		return dto ;
	}
	
	public static enum DynamicTypeEnums {
		
		/**
		 * 发布商品参加活动
		 */
		pub_item , 
		
		/**
		 * 抢积分
		 */
		steal_rate , 
		
		/**
		 * 每日签到
		 */
		daily_checkin , 
		
		/**
		 * 购物返利
		 */
		buy_rebate
		;
	} 

	/**
	 * 商品ID
	 */
	private Long itemId ;
	
	/**
	 * 活动Id
	 */
	private Long promItemId ;
	
	/**
	 * 活动编号
	 */
	private Long promotionId ;
	
	/**
	 * 商品名
	 */
	private String itemTitle ;
	
	/**
	 * 价格
	 */
	private BigDecimal itemPrice ;
	
	/**
	 * 用户ID
	 */
	private Long userId ;
	
	/**
	 * 昵称
	 */
	private String userNick ;
	
	/**
	 * 动态类型
	 */
	private DynamicTypeEnums type ;
	
	/**
	 * 积分
	 */
	private int integralCount ;
	
	public int getIntegralCount() {
		return integralCount;
	}

	public void setIntegralCount(int integralCount) {
		this.integralCount = integralCount;
	}

	public DynamicTypeEnums getType() {
		return type;
	}

	public void setType(DynamicTypeEnums type) {
		this.type = type;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public Long getPromItemId() {
		return promItemId;
	}

	public void setPromItemId(Long promItemId) {
		this.promItemId = promItemId;
	}

	public Long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}
	
}
