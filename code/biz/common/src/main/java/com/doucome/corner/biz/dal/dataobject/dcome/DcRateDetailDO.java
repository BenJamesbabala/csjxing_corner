package com.doucome.corner.biz.dal.dataobject.dcome;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 投票详情
 * @author langben 2012-8-10
 *
 */
public class DcRateDetailDO extends AbstractModel {

	private Long id ;
	
	/**
	 * 商品对应的USerId
	 */
	private Long userId ;
	/**
	 * 商品ID
	 */
	private Long itemId ;
	/**
	 * 投票用户ID
	 */
	private Long rateUserId ;
	
	/**
	 * 投票用户昵称
	 */
	private String rateUserNick ;
	
	/**
	 * 状态
	 */
	private String status ;
	
	private Date gmtCreate ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getRateUserId() {
		return rateUserId;
	}

	public void setRateUserId(Long rateUserId) {
		this.rateUserId = rateUserId;
	}

	public String getRateUserNick() {
		return rateUserNick;
	}

	public void setRateUserNick(String rateUserNick) {
		this.rateUserNick = rateUserNick;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	
	
}
