package com.doucome.corner.biz.dal.dataobject.dcome;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * ͶƱ����
 * @author langben 2012-8-10
 *
 */
public class DcRateDetailDO extends AbstractModel {

	private Long id ;
	
	/**
	 * ��Ʒ��Ӧ��USerId
	 */
	private Long userId ;
	/**
	 * ��ƷID
	 */
	private Long itemId ;
	/**
	 * ͶƱ�û�ID
	 */
	private Long rateUserId ;
	
	/**
	 * ͶƱ�û��ǳ�
	 */
	private String rateUserNick ;
	
	/**
	 * ״̬
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
