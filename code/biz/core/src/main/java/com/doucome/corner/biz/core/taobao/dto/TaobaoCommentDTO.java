package com.doucome.corner.biz.core.taobao.dto;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * �Ա�����DTO.
 * @author ze2200
 *
 */
public class TaobaoCommentDTO extends AbstractModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ��Ʒ��Ϣ.
	 */
	private AuctionInfo auction;
	/**
	 * ��������
	 */
	private String content;
	/**
	 * �����û���Ϣ.
	 */
	private CommentUser user;
	/**
	 * ������Դ.
	 */
	private String source;

	public AuctionInfo getAuction() {
		return auction;
	}

	public void setAuction(AuctionInfo auction) {
		this.auction = auction;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public CommentUser getUser() {
		return user;
	}

	public void setUser(CommentUser user) {
		this.user = user;
	}
	
	public void setSouce(String source) {
		this.source = source;
	}
	
	public String getSource() {
		return this.source;
	}
	
	public String getUserNick() {
		return user == null ? null: user.getNick();
	}
}

class AuctionInfo extends AbstractModel {

	private static final long serialVersionUID = 1L;
	
	String aucNumId;

	public String getAucNumId() {
		return aucNumId;
	}

	public void setAucNumId(String aucNumId) {
		this.aucNumId = aucNumId;
	}
}

class CommentUser extends AbstractModel {
	
	private String userId;
	
	private String nick;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
}