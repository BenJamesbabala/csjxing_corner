package com.doucome.corner.biz.core.taobao.dto;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 淘宝Item图片
 * @author shenjia.caosj 2012-8-1
 *
 */
public class TaobaoItemImg extends AbstractModel {

	/**
	 * Number 	否 	1645920 	商品图片的id，和商品相对应
	 */
	private Long id ;
	
	/**
	 * 图片地址
	 */
	private String url ;
	
	/**
	 * 位置
	 */
	private Long position ;
	
	/**
	 * 创建时间（格式：yyyy-MM-dd HH:mm:ss）
	 */
	private Date created ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getPosition() {
		return position;
	}

	public void setPosition(Long position) {
		this.position = position;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	
}
