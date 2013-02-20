package com.doucome.corner.biz.dcome.model;

import java.util.Date;

import com.doucome.corner.biz.dal.dataobject.dcome.DcUserExchangeApproveDO;
import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 用户积分兑换申请
 * @author langben 2013-1-11
 *
 */
@SuppressWarnings("serial")
public class DcUserExchangeApproveDTO extends AbstractModel {
	
	private DcUserExchangeApproveDO exchangeApprove ;
	
	public DcUserExchangeApproveDTO(DcUserExchangeApproveDO exchangeApprove) {
		if(exchangeApprove == null){
			exchangeApprove = new DcUserExchangeApproveDO() ;
		}
		this.exchangeApprove = exchangeApprove ;
	}


	public Long getId() {
		return exchangeApprove.getId();
	}

	public Long getUserId() {
		return exchangeApprove.getUserId();
	}

	public String getUserNick() {
		return exchangeApprove.getUserNick();
	}


	public String getSource() {
		return exchangeApprove.getSource();
	}

	public Integer getConsumeIntegralCount() {
		return exchangeApprove.getConsumeIntegralCount();
	}

	public Long getExItemId() {
		return exchangeApprove.getExItemId();
	}


	public Integer getExItemNum() {
		return exchangeApprove.getExItemNum();
	}
	
	public String getExItemType() {
		return exchangeApprove.getExItemType();
	}


	public String getDelMobile() {
		return exchangeApprove.getDelMobile();
	}


	public String getDelAlipay() {
		return exchangeApprove.getDelAlipay();
	}

	public String getDelQq() {
		return exchangeApprove.getDelQq();
	}

	public String getDelAddr() {
		return exchangeApprove.getDelAddr();
	}

	public String getDelName() {
		return exchangeApprove.getDelName();
	}

	public String getUserSku() {
		return exchangeApprove.getUserSku();
	}

	public String getUserMemo() {
		return exchangeApprove.getUserMemo();
	}

	public String getCheckCode() {
		return exchangeApprove.getCheckCode();
	}

	public String getMemo() {
		return exchangeApprove.getMemo();
	}

	public String getStatus() {
		return exchangeApprove.getStatus();
	}
	
	public Date getGmtCreate(){
		return exchangeApprove.getGmtCreate() ;
	}

	public Date getGmtModified(){
		return exchangeApprove.getGmtModified() ;
	}
	
}
