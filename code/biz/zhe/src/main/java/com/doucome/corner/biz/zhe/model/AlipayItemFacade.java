package com.doucome.corner.biz.zhe.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

public final class AlipayItemFacade {
	private List<String> ids = new ArrayList<String>();
	private String account;
	private BigDecimal amount;
	
	public String getIds() {
		if (ids.size() == 0) {
			return "";
		}
		StringBuilder builder = new StringBuilder(ids.get(0));
		for (int i = 1; i < ids.size(); i++) {
			builder.append(",").append(ids.get(i));
		}
		return builder.toString();
	}
	public void addIds(String id) {
		this.ids.add(id);
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public BigDecimal getAmount() {
		return this.amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
