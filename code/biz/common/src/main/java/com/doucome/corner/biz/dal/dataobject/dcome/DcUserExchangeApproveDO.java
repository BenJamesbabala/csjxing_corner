package com.doucome.corner.biz.dal.dataobject.dcome;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * �û����ֶһ�����
 * @author langben 2013-1-11
 *
 */
@SuppressWarnings("serial")
public class DcUserExchangeApproveDO extends AbstractModel {

	private Long id ;
	
	private Long userId ;
	
	/**
	 * �û��ǳ�
	 */
	private String userNick ;
	
	/**
	 * �һ�����
	 */
	private String source ;
	
	/**
	 * ���Ļ���
	 */
	private Integer consumeIntegralCount ;
	
	/**
	 * �һ���ƷID
	 */
	private Long exItemId ;
	
	/**
	 * �һ���Ʒ������
	 */
	private String exItemType ;
	
	/**
	 * �һ���Ʒ����
	 */
	private Integer exItemNum = 1;
	
	/**
	 * ��ֵ�ֻ�
	 */
	private String delMobile ;
	
	/**
	 * ��ֵ֧����
	 */
	private String delAlipay ;
	
	/**
	 * ��ֵQQ
	 */
	private String delQq ;
	
	/**
	 * �ջ���Ϣ
	 */
	private String delAddr ;
	
	/**
	 * �ջ�����
	 */
	private String delName ;
	
	/**
	 * ��ɫ|�ߴ� ��
	 */
	private String userSku ;
	
	/**
	 * �û���ע
	 */
	private String userMemo ;
	
	/**
	 * Ψһ��֤�룬���к�
	 */
	private String checkCode ;
	
	/**
	 * ϵͳ���û��ı�ע
	 */
	private String memo ;
	
	/**
	 * ״̬
	 */
	private String status ;
	
	/**
	 * ����ID
	 */
	private Long settleId ;
	
	private Date gmtCreate ;
	
	private Date gmtModified ;

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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Integer getConsumeIntegralCount() {
		return consumeIntegralCount;
	}

	public void setConsumeIntegralCount(Integer consumeIntegralCount) {
		this.consumeIntegralCount = consumeIntegralCount;
	}

	public Long getExItemId() {
		return exItemId;
	}

	public void setExItemId(Long exItemId) {
		this.exItemId = exItemId;
	}

	public Integer getExItemNum() {
		return exItemNum;
	}

	public void setExItemNum(Integer exItemNum) {
		this.exItemNum = exItemNum;
	}

	public String getDelMobile() {
		return delMobile;
	}

	public void setDelMobile(String delMobile) {
		this.delMobile = delMobile;
	}

	public String getDelAlipay() {
		return delAlipay;
	}

	public void setDelAlipay(String delAlipay) {
		this.delAlipay = delAlipay;
	}

	public String getDelQq() {
		return delQq;
	}

	public void setDelQq(String delQq) {
		this.delQq = delQq;
	}

	public String getDelAddr() {
		return delAddr;
	}

	public void setDelAddr(String delAddr) {
		this.delAddr = delAddr;
	}

	public String getDelName() {
		return delName;
	}

	public void setDelName(String delName) {
		this.delName = delName;
	}

	public String getUserSku() {
		return userSku;
	}

	public void setUserSku(String userSku) {
		this.userSku = userSku;
	}

	public String getUserMemo() {
		return userMemo;
	}

	public void setUserMemo(String userMemo) {
		this.userMemo = userMemo;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public String getExItemType() {
		return exItemType;
	}

	public void setExItemType(String exItemType) {
		this.exItemType = exItemType;
	}

	public Long getSettleId() {
		return settleId;
	}

	public void setSettleId(Long settleId) {
		this.settleId = settleId;
	}
	
}
