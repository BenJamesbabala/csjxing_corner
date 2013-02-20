package com.doucome.corner.biz.dcome.utils;

import java.util.HashMap;
import java.util.Map;

import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcExchangeItemDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserExchangeApproveDO;
import com.doucome.corner.biz.dcome.enums.DcIntegralSourceEnums;
import com.doucome.corner.biz.dcome.enums.DcUserIntegralDescKeyEnums;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.model.param.DcShareModel;

/**
 * 积分描述
 * @author langben 2013-1-16
 *
 */
public class DcUserIntegralDescUtils {
	
	/**
	 * 淘客报表
	 * @param report
	 * @return
	 */
	public static Map<String,String> buildTaokeReportDesc(DcUserDTO user , DdzTaokeReportDO report , DcIntegralSourceEnums source){
		Map<String,String> map = new HashMap<String,String>() ;
		map.put(DcUserIntegralDescKeyEnums.USER_NICK.getValue(), user.getNick()) ;
		map.put(DcUserIntegralDescKeyEnums.ITEM_TITLE.getValue(), report.getItemTitle()) ;
		map.put(DcUserIntegralDescKeyEnums.NUM_IID.getValue(), String.valueOf(report.getNumIid())) ;
		map.put(DcUserIntegralDescKeyEnums.DESC_SOURCE.getValue(),source.getValue()) ;
		map.put(DcUserIntegralDescKeyEnums.TAOKE_REPORT_ID.getValue(), String.valueOf(report.getId())) ;
		map.put(DcUserIntegralDescKeyEnums.TAOKE_TRADE_ID.getValue(), String.valueOf(report.getTradeId())) ;
		return map ;
	}
	
	/**
	 * 积分兑换
	 * @param exItem
	 * @param approve
	 * @return
	 */
	public static Map<String,String> buildExchangeDesc(DcUserDTO user , DcExchangeItemDO exItem , DcUserExchangeApproveDO approve) {
		Map<String,String> map = new HashMap<String,String>() ;
		map.put(DcUserIntegralDescKeyEnums.USER_NICK.getValue(), user.getNick()) ;
		map.put(DcUserIntegralDescKeyEnums.ITEM_TITLE.getValue(), exItem.getItemTitle()) ;
		map.put(DcUserIntegralDescKeyEnums.EX_ITEM_ID.getValue(), String.valueOf(exItem.getId())) ;
		map.put(DcUserIntegralDescKeyEnums.EX_ITEM_INTEGRAL.getValue(), String.valueOf(exItem.getExIntegral())) ;
		map.put(DcUserIntegralDescKeyEnums.DC_ITEM_ID.getValue(), exItem.getItemId() == null ? null : String.valueOf(exItem.getItemId())) ;
		map.put(DcUserIntegralDescKeyEnums.EX_ITEM_NUM.getValue(), String.valueOf(approve.getExItemNum())) ;
		map.put(DcUserIntegralDescKeyEnums.SETTLE_ID.getValue(), String.valueOf(approve.getSettleId())) ;
		map.put(DcUserIntegralDescKeyEnums.DESC_SOURCE.getValue()	, DcIntegralSourceEnums.EXCHANGE_ITEM.getValue()) ;
		return map ;
	}
	
	/**
	 * 积分兑换
	 * @param exItem
	 * @param approve
	 * @return
	 */
	public static Map<String,String> buildDcItemExchangeDesc(DcUserDTO user , DcExchangeItemDO exItem ,  int exNum , Long awardId ) {
		Map<String,String> map = new HashMap<String,String>() ;
		map.put(DcUserIntegralDescKeyEnums.USER_NICK.getValue(), user.getNick()) ;
		map.put(DcUserIntegralDescKeyEnums.ITEM_TITLE.getValue(), exItem.getItemTitle()) ;
		map.put(DcUserIntegralDescKeyEnums.EX_ITEM_ID.getValue(), String.valueOf(exItem.getId())) ;
		map.put(DcUserIntegralDescKeyEnums.EX_ITEM_INTEGRAL.getValue(), String.valueOf(exItem.getExIntegral())) ;
		map.put(DcUserIntegralDescKeyEnums.DC_ITEM_ID.getValue(), exItem.getItemId() == null ? null : String.valueOf(exItem.getItemId())) ;
		map.put(DcUserIntegralDescKeyEnums.EX_ITEM_NUM.getValue(), String.valueOf(exNum)) ;
		map.put(DcUserIntegralDescKeyEnums.AWARD_ID.getValue(), String.valueOf(awardId)) ;
		map.put(DcUserIntegralDescKeyEnums.DESC_SOURCE.getValue()	, DcIntegralSourceEnums.EXCHANGE_ITEM.getValue()) ;
		return map ;
	}
	
	/**
	 * 新用户注册
	 * @param userNick
	 * @return
	 */
	public static Map<String,String> buildUserRegisterDesc(DcUserDTO user){
		Map<String,String> map = new HashMap<String,String>() ;
		map.put(DcUserIntegralDescKeyEnums.USER_NICK.getValue(), user.getNick()) ;
		map.put(DcUserIntegralDescKeyEnums.DESC_SOURCE.getValue()	, DcIntegralSourceEnums.USER_REGISTER.getValue()) ;
		return map ;
	}
	
	/**
	 * 邀请新用户
	 * @param userNick
	 * @param invitedUserId
	 * @param invitedUserNick
	 * @return
	 */
	public static Map<String,String> buildInviteNewUserDesc(DcUserDTO user , DcUserDO userInvited) {
		Map<String,String> map = new HashMap<String,String>() ;
		map.put(DcUserIntegralDescKeyEnums.USER_NICK.getValue(), user.getNick()) ;
		map.put(DcUserIntegralDescKeyEnums.INVITED_USER_ID.getValue(), String.valueOf(userInvited.getUserId())) ;
		map.put(DcUserIntegralDescKeyEnums.INVITED_USER_NICK.getValue(), userInvited.getNick()) ;
		map.put(DcUserIntegralDescKeyEnums.DESC_SOURCE.getValue()	, DcIntegralSourceEnums.INVITED_USER.getValue()) ;
		return map ;
	}
	
	/**
	 * 关注认证空间
	 * @param userNick
	 * @param qzoneName
	 * @return
	 */
	public static Map<String,String> buildFollowQzoneDesc(DcUserDTO user , String qzoneName) {
		Map<String,String> map = new HashMap<String,String>() ;
		map.put(DcUserIntegralDescKeyEnums.USER_NICK.getValue(), user.getNick()) ;
		map.put(DcUserIntegralDescKeyEnums.QZONE_NAME.getValue(), qzoneName) ;
		map.put(DcUserIntegralDescKeyEnums.DESC_SOURCE.getValue(), DcIntegralSourceEnums.FOLLOW_QZONE.getValue()) ;
		return map ;
	}
	
	/**
	 * 签到
	 * @param userNick
	 * @param continueDays 连续天数
	 * @return
	 */
	public static Map<String,String> buildDailyCheckinDesc(DcUserDTO user , int continueDays) {
		Map<String,String> map = new HashMap<String,String>() ;
		map.put(DcUserIntegralDescKeyEnums.USER_NICK.getValue(), user.getNick()) ;
		map.put(DcUserIntegralDescKeyEnums.CONTINUE_DAYS.getValue(), String.valueOf(continueDays)) ;
		map.put(DcUserIntegralDescKeyEnums.DESC_SOURCE.getValue(), DcIntegralSourceEnums.DAILY_CHECKIN.getValue()) ;
		return map ;
	}
	
	/**
	 * 分享
	 * @param userNick
	 * @param shareModel
	 * @return
	 */
	public static Map<String,String> buildShareDesc(DcUserDTO user , DcShareModel shareModel) {
		Map<String,String> map = new HashMap<String,String>() ;
		map.put(DcUserIntegralDescKeyEnums.USER_NICK.getValue(), user.getNick()) ;
		map.put(DcUserIntegralDescKeyEnums.SHARE_OBJECT_TYPE.getValue(), shareModel.getShareObject().getValue()) ;
		map.put(DcUserIntegralDescKeyEnums.SHARE_OBJECT_ID.getValue(), String.valueOf(shareModel.getShareObjectId())) ;
		map.put(DcUserIntegralDescKeyEnums.DESC_SOURCE.getValue(), DcIntegralSourceEnums.SHARE.getValue()) ;
		return map ;
	}
	
	/**
	 * 发送邀请
	 * @param userNick
	 * @param shareModel
	 * @return
	 */
	public static Map<String,String> buildSendInviteDesc(DcUserDTO user , String openIds) {
		Map<String,String> map = new HashMap<String,String>() ;
		map.put(DcUserIntegralDescKeyEnums.USER_NICK.getValue(), user.getNick()) ;
		map.put(DcUserIntegralDescKeyEnums.OPEN_IDS.getValue(), openIds) ;
		map.put(DcUserIntegralDescKeyEnums.DESC_SOURCE.getValue(), DcIntegralSourceEnums.SEND_INVITE.getValue()) ;
		return map ;
	}
}
