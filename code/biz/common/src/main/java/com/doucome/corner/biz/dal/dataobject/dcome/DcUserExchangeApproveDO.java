package com.doucome.corner.biz.dal.dataobject.dcome;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 用户积分兑换申请
 * @author langben 2013-1-11
 *
 */
@SuppressWarnings("serial")
public class DcUserExchangeApproveDO extends AbstractModel {

	private Long id ;
	
	private Long userId ;
	
	/**
	 * 用户昵称
	 */
	private String userNick ;
	
	/**
	 * 兑换类型
	 */
	private String source ;
	
	/**
	 * 消耗积分
	 */
	private Integer consumeIntegralCount ;
	
	/**
	 * 兑换商品ID
	 */
	private Long exItemId ;
	
	/**
	 * 兑换商品的类型
	 */
	private String exItemType ;
	
	/**
	 * 兑换商品数量
	 */
	private Integer exItemNum = 1;
	
	/**
	 * 充值手机
	 */
	private String delMobile ;
	
	/**
	 * 充值支付宝
	 */
	private String delAlipay ;
	
	/**
	 * 充值QQ
	 */
	private String delQq ;
	
	/**
	 * 收货信息
	 */
	private String delAddr ;
	
	/**
	 * 收货姓名
	 */
	private String delName ;
	
	/**
	 * 颜色|尺寸 等
	 */
	private String userSku ;
	
	/**
	 * 用户备注
	 */
	private String userMemo ;
	
	/**
	 * 唯一验证码，序列号
	 */
	private String checkCode ;
	
	/**
	 * 系统对用户的备注
	 */
	private String memo ;
	
	/**
	 * 状态
	 */
	private String status ;
	
	/**
	 * 结算ID
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
