package com.doucome.corner.web.zhe.model;

/**
 * 支付宝验证接口
 * @author langben 2012-4-27
 *
 */
public class AlipayAcctStatusCheck {

	private String nick ;
	
	private String acctstatus ;
	
	private String acctType ;
	
	private String stat ;

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getAcctstatus() {
		return acctstatus;
	}

	public void setAcctstatus(String acctstatus) {
		this.acctstatus = acctstatus;
	}

	public String getAcctType() {
		return acctType;
	}

	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}
	
	
}
