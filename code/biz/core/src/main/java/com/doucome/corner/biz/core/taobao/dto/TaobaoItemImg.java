package com.doucome.corner.biz.core.taobao.dto;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * �Ա�ItemͼƬ
 * @author shenjia.caosj 2012-8-1
 *
 */
public class TaobaoItemImg extends AbstractModel {

	/**
	 * Number 	�� 	1645920 	��ƷͼƬ��id������Ʒ���Ӧ
	 */
	private Long id ;
	
	/**
	 * ͼƬ��ַ
	 */
	private String url ;
	
	/**
	 * λ��
	 */
	private Long position ;
	
	/**
	 * ����ʱ�䣨��ʽ��yyyy-MM-dd HH:mm:ss��
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
