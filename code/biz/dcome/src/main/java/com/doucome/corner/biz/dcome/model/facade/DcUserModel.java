package com.doucome.corner.biz.dcome.model.facade;

public class DcUserModel {

	private PfModel pfModel ;
	
	private Long userId ;
	
	private String nick ;

	public PfModel getPfModel() {
		return pfModel;
	}

	public void setPfModel(PfModel pfModel) {
		this.pfModel = pfModel;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	
}
