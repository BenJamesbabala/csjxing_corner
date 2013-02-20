package com.doucome.corner.biz.dal.condition.dcome;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.dal.model.OrderAndSortModel;
import com.doucome.corner.biz.dal.model.OrderAndSortModel.Sort;

/**
 * 
 * @author langben 2012-8-13
 *
 */
public class DcPromotionItemSearchCondition {
		
	/**
	 * ªÓ∂ØID
	 */
	private Long promotionId ;
	
	/**
	 * userId
	 */
	private Long userId ;
	
	/**
	 * 
	 */
	private List<Long> userIdList ;
	
	/**
	 * ≈≈–Ú
	 */
	private OrderEnum order = OrderEnum.auto ;
	
	/**
	 * 
	 */
	private int drawHour ;
	
	public Map<String,Object> toMap(){
    	Map<String,Object> condition = new HashMap<String,Object>() ;
		
    	condition.put("promotionId", promotionId) ;
    	condition.put("userId", userId) ;
    	condition.put("userIdList", userIdList) ;
    	    	
		OrderAndSortModel osm = new OrderAndSortModel(this.order.toString(), Sort.desc ) ;
		if(osm.isFormat()){
		
			condition.put("order", osm.getOrder()) ;
			condition.put("sort", osm.getSort()) ;
			condition.put("drawHour", drawHour) ;
			
		}
		return condition ;
    }
	
	public Long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public OrderEnum getOrder() {
		return order;
	}

	public void setOrder(OrderEnum order) {
		this.order = order;
	}

	public List<Long> getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(List<Long> userIdList) {
		this.userIdList = userIdList;
	}

	public static enum OrderEnum {
		rate_count , 
		gmt_modified , 
		gmt_create ,
		auto , 
		no_sort ,
		can_draw
		;
		
		public String toString(){
			return this.name().toString() ;
		}
		
		public static OrderEnum fromName(String name) {
			OrderEnum[] values = OrderEnum.values() ;
			for(OrderEnum o : values){
				if(StringUtils.equals(o.toString(), name)){
					return o ;
				}
			}
			return auto ;
		}
	}

	public int getDrawHour() {
		return drawHour;
	}

	public void setDrawHour(int drawHour) {
		this.drawHour = drawHour ;
	}
	
	
}
