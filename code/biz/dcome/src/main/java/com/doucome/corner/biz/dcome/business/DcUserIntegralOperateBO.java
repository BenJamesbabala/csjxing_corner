package com.doucome.corner.biz.dcome.business;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.core.utils.JacksonHelper;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcExchangeItemDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserExchangeApproveDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserIntegralRecordDO;
import com.doucome.corner.biz.dcome.enums.DcExchangeApproveStatusEnums;
import com.doucome.corner.biz.dcome.enums.DcIntegralSourceEnums;
import com.doucome.corner.biz.dcome.enums.DcUserIntegralInOutTypeEnums;
import com.doucome.corner.biz.dcome.exception.IntegralNotEnoughException;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.model.param.DcShareModel;
import com.doucome.corner.biz.dcome.service.DcUserIntegralRecordService;
import com.doucome.corner.biz.dcome.service.DcUserService;
import com.doucome.corner.biz.dcome.utils.DcUserIntegralDescUtils;

/**
 * 用户积分操作
 * @author langben 2013-1-8
 *
 */
public class DcUserIntegralOperateBO {

	private static final Log log = LogFactory.getLog(DcUserIntegralOperateBO.class);
	
	@Autowired
	private DcUserService dcUserService ;
	
	@Autowired
	private DcUserIntegralRecordService dcUserIntegralRecordService ;
	
	/**
	 * 返利增加积分
	 * @param userId
	 * @param integralCount
	 * @param report
	 */
	public int doBuyRebate(DcUserDTO user , int integralCount , DdzTaokeReportDO report) throws IntegralNotEnoughException {
		if(user == null || integralCount <= 0 || report == null){
			throw new IllegalArgumentException("args user | integralCount " + integralCount + "| report not correct .") ;
		}

		DcUserIntegralRecordDO record = new DcUserIntegralRecordDO() ;
		record.setInOutType(DcUserIntegralInOutTypeEnums.INCOME.getValue()) ;
		record.setIntegralCount(integralCount) ;
		record.setTaokeReportId(report.getId()) ;
		record.setSource(DcIntegralSourceEnums.BUY_REBATE.getValue()) ;
		record.setUserId(user.getUserId()) ;
		record.setIntegralDesc(JacksonHelper.toJSON(DcUserIntegralDescUtils.buildTaokeReportDesc(user , report , DcIntegralSourceEnums.BUY_REBATE))) ;
		
		return this._doWithIntegral(record) ;
	}
	
	/**
	 * 积分兑换
	 * @param userId
	 * @param integralCount
	 * @param exItem
	 * @param approve
	 * @throws IntegralNotEnoughException
	 */
	public int doExchange(DcUserDTO user , int integralCount , DcExchangeItemDO exItem , DcUserExchangeApproveDO approve) throws IntegralNotEnoughException {
		if(user == null || integralCount <= 0 || exItem == null || IDUtils.isNotCorrect(approve.getId())){
			throw new IllegalArgumentException("args user | integralCount " + integralCount + "| exItem | approve not correct .") ;
		}
		
		if(integralCount <= 0) {
			return 0 ;
		}
		
		DcUserIntegralRecordDO record = new DcUserIntegralRecordDO() ;
		record.setInOutType(DcUserIntegralInOutTypeEnums.OUTLAY.getValue()) ;
		record.setIntegralCount(Math.abs(integralCount)) ;
		record.setSource(DcIntegralSourceEnums.EXCHANGE_ITEM.getValue()) ;
		record.setUserId(user.getUserId()) ;
		record.setExchangeApproveId(approve.getId()) ;
		record.setSettleId(approve.getSettleId()) ;
		record.setStatus(DcExchangeApproveStatusEnums.APPROVED_PENDING.getValue()) ;
		record.setIntegralDesc(JacksonHelper.toJSON(DcUserIntegralDescUtils.buildExchangeDesc(user , exItem, approve))) ;
		return this._doWithIntegral(record) ;
	}
	
	/**
	 * 豆蔻商品兑换
	 * @param userId
	 * @param integralCount
	 * @param exItem
	 * @param exNum
	 * @param awardId
	 * @return
	 */
	public int doDcItemExchange(DcUserDTO user , int integralCount , DcExchangeItemDO exItem , int exNum , Long awardId) {
		if( user == null || integralCount <= 0 || exItem == null ){
			throw new IllegalArgumentException("args user == null | integralCount " + integralCount + "| exItem not correct .") ;
		}
		
		DcUserIntegralRecordDO record = new DcUserIntegralRecordDO() ;
		record.setInOutType(DcUserIntegralInOutTypeEnums.OUTLAY.getValue()) ;
		record.setIntegralCount(Math.abs(integralCount)) ;
		record.setSource(DcIntegralSourceEnums.EXCHANGE_ITEM.getValue()) ;
		record.setUserId(user.getUserId()) ;
		record.setIntegralDesc(JacksonHelper.toJSON(DcUserIntegralDescUtils.buildDcItemExchangeDesc(user , exItem, exNum , awardId))) ;
		return this._doWithIntegral(record) ;
	}
	
	/**
	 * 维权退款
	 * @param userId
	 * @param integralCount
	 * @param report
	 */
	public int doDcRefund(DcUserDTO user , int integralCount , DdzTaokeReportDO report){
		if(user == null || integralCount <= 0 || report == null ){
			throw new IllegalArgumentException("args user | integralCount " + integralCount + "| report not correct .") ;
		}
		
		DcUserIntegralRecordDO record = new DcUserIntegralRecordDO() ;
		record.setInOutType(DcUserIntegralInOutTypeEnums.OUTLAY.getValue()) ;
		record.setIntegralCount(integralCount) ;
		record.setSource(DcIntegralSourceEnums.REFUND.getValue()) ;
		record.setUserId(user.getUserId()) ;
		record.setTaokeReportId(report.getId()) ;
		record.setIntegralDesc(JacksonHelper.toJSON(DcUserIntegralDescUtils.buildTaokeReportDesc(user , report , DcIntegralSourceEnums.REFUND))) ;
		return this._doWithIntegral(record) ;
		
	}
	
	
	/**
	 * 新用户注册
	 * @param userId
	 * @param userNick
	 */
	public int doUserRegister(DcUserDTO user) {
		if(user == null){
			throw new IllegalArgumentException("args user not correct .") ;
		}
		int integralCount = DcIntegralSourceEnums.USER_REGISTER.getAwardIntegral() ;
		if(integralCount <= 0){
			return 0 ;
		}
		DcUserIntegralRecordDO record = new DcUserIntegralRecordDO() ;
		record.setInOutType(DcUserIntegralInOutTypeEnums.INCOME.getValue()) ;
		record.setIntegralCount(integralCount) ;
		record.setSource(DcIntegralSourceEnums.USER_REGISTER.getValue()) ;
		record.setUserId(user.getUserId()) ;
		record.setIntegralDesc(JacksonHelper.toJSON(DcUserIntegralDescUtils.buildUserRegisterDesc(user))) ;
		return this._doWithIntegral(record) ;
	}
	
	/**
	 * 每日签到
	 * @param userId
	 * @param userNick
	 * @param continueDays 连续签到天数
	 */
	public int doDailyCheckin(DcUserDTO user , int continueDays) {
		if(user == null){
			throw new IllegalArgumentException("args user not correct .") ;
		}
		
		int integralCount = DcIntegralSourceEnums.DAILY_CHECKIN.getAwardIntegral() ;
		if(integralCount <= 0){
			return 0 ;
		}
		
		DcUserIntegralRecordDO record = new DcUserIntegralRecordDO() ;
		record.setInOutType(DcUserIntegralInOutTypeEnums.INCOME.getValue()) ;
		record.setIntegralCount(integralCount) ;
		record.setSource(DcIntegralSourceEnums.DAILY_CHECKIN.getValue()) ;
		record.setUserId(user.getUserId()) ;
		record.setIntegralDesc(JacksonHelper.toJSON(DcUserIntegralDescUtils.buildDailyCheckinDesc(user, continueDays))) ;
		return this._doWithIntegral(record) ;
	}
	
	/**
	 * 关注认证空间
	 * @param userId
	 * @param userNick
	 */
	public int doFollowQzone(DcUserDTO user , String qzoneName) {
		if(user == null){
			throw new IllegalArgumentException("args user not correct .") ;
		}
		int integralCount = DcIntegralSourceEnums.FOLLOW_QZONE.getAwardIntegral() ;
		if(integralCount <= 0){
			return 0 ;
		}
		DcUserIntegralRecordDO record = new DcUserIntegralRecordDO() ;
		record.setInOutType(DcUserIntegralInOutTypeEnums.INCOME.getValue()) ;
		record.setIntegralCount(integralCount) ;
		record.setSource(DcIntegralSourceEnums.FOLLOW_QZONE.getValue()) ;
		record.setUserId(user.getUserId()) ;
		record.setIntegralDesc(JacksonHelper.toJSON(DcUserIntegralDescUtils.buildFollowQzoneDesc(user , qzoneName))) ;
		return this._doWithIntegral(record) ;
	}
	
	/**
	 * 成功邀请新朋友. 调用者应该先判断邀请来的用户是否是新用户.
	 * @param userId
	 * @param userNick
	 * @param inviteModel
	 */
	public int doInviteNewUser(DcUserDTO user , DcUserDO userInvited) {
		if(user == null || userInvited == null){
			throw new IllegalArgumentException("args user or userInvited not correct .") ;
		}
		
		int integralCount = DcIntegralSourceEnums.INVITED_USER.getAwardIntegral() ;
		if(integralCount <= 0){
			return 0 ;
		}
		
		DcUserIntegralRecordDO record = new DcUserIntegralRecordDO() ;
		record.setInOutType(DcUserIntegralInOutTypeEnums.INCOME.getValue()) ;
		record.setIntegralCount(integralCount) ;
		record.setSource(DcIntegralSourceEnums.INVITED_USER.getValue()) ;
		record.setUserId(user.getUserId()) ;
		record.setIntegralDesc(JacksonHelper.toJSON(DcUserIntegralDescUtils.buildInviteNewUserDesc(user, userInvited))) ;
		return this._doWithIntegral(record) ;
	}
	
	/**
	 * 分享
	 * @param userId
	 * @param userNick
	 * @param shareModel
	 * @return
	 */
	public int doShare(DcUserDTO user, DcShareModel shareModel) {
		if(user == null|| shareModel == null){
			throw new IllegalArgumentException("args user or shareModel not correct .") ;
		}
		
		int integralCount = DcIntegralSourceEnums.SHARE.getAwardIntegral() ;
		if(integralCount <= 0){
			return 0 ;
		}
		
		DcUserIntegralRecordDO record = new DcUserIntegralRecordDO() ;
		record.setInOutType(DcUserIntegralInOutTypeEnums.INCOME.getValue()) ;
		record.setIntegralCount(integralCount) ;
		record.setSource(DcIntegralSourceEnums.SHARE.getValue()) ;
		record.setUserId(user.getUserId()) ;
		record.setIntegralDesc(JacksonHelper.toJSON(DcUserIntegralDescUtils.buildShareDesc(user, shareModel))) ;
		return this._doWithIntegral(record) ;
	}
	
	/**
	 * 发送邀请
	 * @param userId
	 * @param userNick
	 * @param inviteOpenIds
	 * @return
	 */
	public int doSendInvite(DcUserDTO user , String inviteOpenIds) {
		if(user == null){
			throw new IllegalArgumentException("args user not correct .") ;
		}
		
		int integralCount = DcIntegralSourceEnums.SEND_INVITE.getAwardIntegral() ;
		if(integralCount <= 0){
			return 0 ;
		}
		
		DcUserIntegralRecordDO record = new DcUserIntegralRecordDO() ;
		record.setInOutType(DcUserIntegralInOutTypeEnums.INCOME.getValue()) ;
		record.setIntegralCount(integralCount) ;
		record.setSource(DcIntegralSourceEnums.SEND_INVITE.getValue()) ;
		record.setUserId(user.getUserId()) ;
		record.setIntegralDesc(JacksonHelper.toJSON(DcUserIntegralDescUtils.buildSendInviteDesc(user, inviteOpenIds))) ;
		return this._doWithIntegral(record) ;
	}
	
	@Transactional(rollbackFor=RuntimeException.class)
	private int _doWithIntegral(DcUserIntegralRecordDO record) throws IntegralNotEnoughException {
		if(record == null){
			throw new IllegalArgumentException("record cant be null .") ;
		}
		Long userId = record.getUserId() ;
		DcUserDTO user = dcUserService.getUser(userId) ;
		
		if(user == null){
			throw new IllegalArgumentException("cant find user userId : " + userId) ;
		}
		
		int userIntegral = NumberUtils.parseInt(user.getIntegralCount()) ;
		
		int integralCount = Math.abs(NumberUtils.parseInt(record.getIntegralCount())) ;
		
		//
		if(DcUserIntegralInOutTypeEnums.INCOME == DcUserIntegralInOutTypeEnums.get(record.getInOutType())) {
			//收入
			dcUserService.incrIntegralByUser(userId, integralCount) ;
			userIntegral = userIntegral + integralCount ;
		} else {
			//支出
			if(DcIntegralSourceEnums.get(record.getSource()) != DcIntegralSourceEnums.REFUND) {
				if(userIntegral < integralCount) {
					throw new IntegralNotEnoughException() ;
				}
			}
			dcUserService.decrIntegralByUser(userId, integralCount) ;
			userIntegral = userIntegral - integralCount ;
		}
		
		record.setIntegralBalance(userIntegral) ;
		
		dcUserIntegralRecordService.createRecord(record) ;
		
		return integralCount ;
	}
	
}
