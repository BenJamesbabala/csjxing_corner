package com.doucome.corner.biz.dal.namefate.dataobject;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 姓名缘分匹配
 * @author langben 2013-1-31
 *
 */
@SuppressWarnings("serial")
public class NamefateTrickDO extends AbstractModel {

	private Long id ;
	
	/**
	 * 发布的UserId
	 */
	private Long userId ;
	/**
	 * 发布的UserNick
	 */
	private String userNick ;
	
	/**
	 * 捉弄的UserId
	 */
	private Long trickUserId ;
	/**
	 * 捉弄的UserNick
	 */
	private String trickUserNick ;
	/**
	 * 捉弄的人填写的名字
	 */
	private String trickInputName ;
	/**
	 * 捉弄的人填写的TA的名字
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
