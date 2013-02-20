package com.doucome.corner.web.zhe.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.service.taobao.TaobaokeServiceDecorator;

/**
 * 转向淘宝的商品URL
 * @author langben 2012-4-1
 *
 */
public class GoAction extends DdzBasicAction{

	private String id ;
	
	@Autowired
	private TaobaokeServiceDecorator taobaokeServiceDecorator ;
	
	@Override
	public String execute() throws Exception {
		
//		String alipayId = ddzAuthz.getAlipayId() ;
//		
//		taobaokeServiceDecorator.conventItem(id, outCode, fields)
		return SUCCESS ;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
