package com.doucome.corner.biz.core.taobao.model;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 类目推广查询条件
 * @author langben 2012-3-21
 *
 */
public class TaokeCaturlCondition extends AbstractModel{

	private String q ;
	
	private Long cid ;
	
	private String outCode ;

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getOutCode() {
		return outCode;
	}

	public void setOutCode(String outCode) {
		this.outCode = outCode;
	}
	
	
}
