package com.doucome.corner.biz.dal.namefate.dataobject;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * ����Ե��ƥ��
 * @author langben 2013-1-31
 *
 */
@SuppressWarnings("serial")
public class NamefateTrickDO extends AbstractModel {

	private Long id ;
	
	/**
	 * ������UserId
	 */
	private Long userId ;
	/**
	 * ������UserNick
	 */
	private String userNick ;
	
	/**
	 * ׽Ū��UserId
	 */
	private Long trickUserId ;
	/**
	 * ׽Ū��UserNick
	 */
	private String trickUserNick ;
	/**
	 * ׽Ū������д������
	 */
	private String trickInputName ;
	/**
	 * ׽Ū������д��TA������
	 */
	private String trickInputTaName ;
	
	private Date gmtCreate ;
	
	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
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
	public String getUserNick() {
		return userNick;
	}
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	public Long getTrickUserId() {
		return trickUserId;
	}
	public void setTrickUserId(Long trickUserId) {
		this.trickUserId = trickUserId;
	}
	public String getTrickUserNick() {
		return trickUserNick;
	}
	public void setTrickUserNick(String trickUserNick) {
		this.trickUserNick = trickUserNick;
	}
	public String getTrickInputName() {
		return trickInputName;
	}
	public void setTrickInputName(String trickInputName) {
		this.trickInputName = trickInputName;
	}
	public String getTrickInputTaName() {
		return trickInputTaName;
	}
	public void setTrickInputTaName(String trickInputTaName) {
		this.trickInputTaName = trickInputTaName;
	}
	
	
}
