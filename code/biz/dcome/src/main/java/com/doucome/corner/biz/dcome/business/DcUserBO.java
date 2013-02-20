package com.doucome.corner.biz.dcome.business;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.utils.DateUtils;
import com.doucome.corner.biz.dal.condition.dcome.DcIntegralCondition;
import com.doucome.corner.biz.dcome.enums.DcIntegralSourceEnums;
import com.doucome.corner.biz.dcome.enums.DcShareObjectEnum;
import com.doucome.corner.biz.dcome.enums.DcUserGuideEnum;
import com.doucome.corner.biz.dcome.exception.DuplicateOperateException;
import com.doucome.corner.biz.dcome.model.DcIntegralDesc;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.model.DcUserIntegralDetailDTO;
import com.doucome.corner.biz.dcome.model.param.DcFollowQzoneModel;
import com.doucome.corner.biz.dcome.model.param.DcUserGuideModel;
import com.doucome.corner.biz.dcome.service.DcUserIntegralDetailService;
import com.doucome.corner.biz.dcome.service.DcUserService;

/**
 * 
 * @author langben 2012-8-28
 *
 */
public class DcUserBO {
	@Autowired
	private DcUserService dcUserService;
	@Autowired
	private DcUserIntegralOperateBO dcUserIntegralOperateBO ;
	@Autowired
	private DcUserIntegralDetailService dcUserIntegralDetailService;
	
	/**
	 * 每日签到
	 * @param userId
	 * @throws DuplicateOperateException 重复签到
	 */
	public int dailyCheckin(long userId, String userNick) throws DuplicateOperateException {
		DcUserDTO user = dcUserService.getUser(userId) ; 
		if(user != null){
			Date gmtLastCheckin = user.getGmtLastCheckin() ; 
			int checkInCount = user.getCheckInCount();
			if(gmtLastCheckin != null){
				Date date = new Date() ;
				if(DateUtils.isSameDay(gmtLastCheckin, date)){
					throw new DuplicateOperateException() ;
				}
				
				if (DateUtils.isYesterday(gmtLastCheckin)) {
					checkInCount++;
				}else {
					checkInCount = 1;
				}
			}else {
				checkInCount = 1;
			}
			//更新积分
			int integralCount = dcUserIntegralOperateBO.doDailyCheckin(user , checkInCount) ;
			//更新签到时间
			dcUserService.updateLastCheckinTime(userId, checkInCount) ;
			
			return integralCount ;
		}
		return 0 ;
	}
	
	/**
	 * 关注空间
	 * @param dcUser
	 * @return
	 */
	public int followQzone(DcUserDTO dcUser) {
		Date now = new Date();
		if (DateUtils.isSameDay(dcUser.getGmtFollowQzone(), now)) {
			return 0;
		}
		DcFollowQzoneModel model = new DcFollowQzoneModel();
		model.setUserId(dcUser.getUserId());
		model.setUserNick(dcUser.getNick());
		model.setQzoneName("doucome");
		int count = dcUserService.updateFollowQzone(dcUser.getUserId());
		if (count > 0) {
			dcUserIntegralOperateBO.doFollowQzone(dcUser, model.getQzoneName()) ;
		}
		return 0;
	}
	
	/**
	 * 获取用户今日可挣取的积分
	 * @param user
	 * @return
	 */
	public Map<String, Integer> getUserUndrawIntegral(DcUserDTO user) {
		int integralCount = 0;
		int taskCount = 0;
		//关注空间.
		if (user.getGmtFollowQzone() == null) {
			integralCount += DcIntegralSourceEnums.FOLLOW_QZONE.getAwardIntegral();
			taskCount++;
		}
		//签到.
		if (!DateUtils.isToday(user.getGmtLastCheckin())) {
			if (DateUtils.isYesterday(user.getGmtLastCheckin())) {
				//连续签到
				integralCount += DcIntegralSourceEnums.DAILY_CHECKIN.getAwardIntegral(user.getCheckInCount() + 1);
			} else {
				integralCount += DcIntegralSourceEnums.DAILY_CHECKIN.getAwardIntegral();
			}
			taskCount++;
		}
		integralCount += 30; //分享30 + 邀请 10,前端没有给分享活动的按钮，暂时去掉分享活动的10积分
		taskCount += 3;  //任务：分享3 + 邀请1,签到赠送积分,连续签到有更多奖励哦
		//分享商品，分享活动，分享应用
		Date start = DateUtils.trimDate(new Date(), Calendar.HOUR_OF_DAY);
		DcIntegralCondition condition = new DcIntegralCondition();
		condition.setUserId(user.getUserId());
		condition.addSource(DcIntegralSourceEnums.SHARE.getValue());
		condition.addSource(DcIntegralSourceEnums.SEND_INVITE.getValue());
		condition.setGmtCreateStart(getCurValidShareStartTime());
		List<DcUserIntegralDetailDTO> integralDetails = dcUserIntegralDetailService.getIntegralDetails(condition, new Pagination(1, 20));
		for (DcUserIntegralDetailDTO temp: integralDetails) {
			if (temp.getSourceEnum() == DcIntegralSourceEnums.SHARE) {
				DcIntegralDesc desc = temp.getIntegralDesc();
				DcShareObjectEnum shareObj = DcShareObjectEnum.toEnum(desc.getFromObjName());
				if (shareObj == null) {
					continue;
				}
				if (temp.getGmtCreate().after(shareObj.getCurValidShareStartTime())) {
					integralCount -= shareObj.getAwardIntegral();
					taskCount--;
				}
			} else {
				DcIntegralSourceEnums source = temp.getSourceEnum();
				if (temp.getGmtCreate().after(start)) {
					integralCount -= source.getAwardIntegral();
					taskCount--;
				}
			}
		}
		Map<String, Integer> result = new HashMap<String, Integer>();
		result.put("integral", integralCount);
		result.put("taskCount", taskCount);
		return result;
	}
	
	/**
	 * 获取当前用户收集积分情况.
	 * 包括share_app,share_item,share_prom,checkin,follow_qzone,invite friend,invited friend
	 * @return
	 */
	public Map<String, Integer> getCurIntegralCollectStatus(DcUserDTO user) {
		Map<String, Integer> integralStatus = new HashMap<String, Integer>();
		integralStatus.put(DcIntegralSourceEnums.SEND_INVITE.getValue(), 0);
		integralStatus.put(DcIntegralSourceEnums.FOLLOW_QZONE.getValue(), 0);
		integralStatus.put(DcIntegralSourceEnums.DAILY_CHECKIN.getValue(), 0);
		integralStatus.put(DcShareObjectEnum.APP.getExactValue(), 0);
		integralStatus.put(DcShareObjectEnum.PROMOTION.getExactValue(), 0);
		integralStatus.put(DcShareObjectEnum.ITEM.getExactValue(), 0);
		if (user.getGmtFollowQzone() != null) {
			integralStatus.put(DcIntegralSourceEnums.FOLLOW_QZONE.getValue(), 1);
		}
		if (DateUtils.isToday(user.getGmtLastCheckin())) {
			integralStatus.put(DcIntegralSourceEnums.DAILY_CHECKIN.getValue(), 1);
		}
		//获取今日数据.
		Date start = DateUtils.trimDate(new Date(), Calendar.HOUR_OF_DAY);
		DcIntegralCondition condition = new DcIntegralCondition();
		condition.setUserId(user.getUserId());
		condition.addSource(DcIntegralSourceEnums.SHARE.getValue());
		condition.addSource(DcIntegralSourceEnums.SEND_INVITE.getValue());
		condition.setGmtCreateStart(getCurValidShareStartTime());
		List<DcUserIntegralDetailDTO> integralDetails = dcUserIntegralDetailService.getIntegralDetails(condition, new Pagination(1, 30));
		for (DcUserIntegralDetailDTO temp: integralDetails) {
			if (temp.getSourceEnum() == DcIntegralSourceEnums.SHARE) {
				DcIntegralDesc desc = temp.getIntegralDesc();
				DcShareObjectEnum shareObj = DcShareObjectEnum.toEnum(desc.getFromObjName());
				if (shareObj == null) {
					continue;
				}
				if (temp.getGmtCreate().after(shareObj.getCurValidShareStartTime())) {
					integralStatus.put(shareObj.getExactValue(), 1);
				}
			} else {
				DcIntegralSourceEnums source = temp.getSourceEnum();
				if (temp.getGmtCreate().after(start)) {
					integralStatus.put(source.getValue(), 1);
				}
			}
		}
		return integralStatus;
	}
	
	/**
	 * 查询新手引导完成情况.
	 * @param user
	 * @return
	 */
	public Map<String, Boolean> queryShowFLayer(DcUserDTO user) {
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		result.put(DcUserGuideEnum.UGC_LAYER.getGuideStr(), isShowUgcLayer(user));
		result.put(DcUserGuideEnum.NOTIFICATE.getGuideStr(), isShowIntegralDiscountLayer(user));
		return result;
	}
	
	/**
	 * 是否显示ugc浮层.
	 * @param user
	 * @return
	 */
	private boolean isShowUgcLayer(DcUserDTO user) {
		if (DateUtils.getDiffInDays(new Date(), user.getGmtCreate()) > 3) {
			boolean isDone = DcUserGuideEnum.UGC_LAYER.isDone(user.getNewGuide());
			if (!isDone) {
				dcUserService.updateNewGuide(user.getUserId(), DcUserGuideEnum.UGC_LAYER.getDoneValue(user.getNewGuide()));
			}
			return !isDone;
		}
		return false;
	}
	
	/**
	 * 是否显示积分折算浮层.
	 * @param user
	 * @return
	 */
	private boolean isShowIntegralDiscountLayer(DcUserDTO user) {
		//用户当前积分少于50
		if (user.getIntegralCount() < 50) {
			return false;
		}
		int days = DateUtils.getDiffInDays(new Date(), user.getGmtLastLogin());
		//15天没有登录
		if (days > 15) {
			return false;
		}
		return !DcUserGuideEnum.NOTIFICATE.isDone(user.getNewGuide());
	}
	
	/**
	 * 标识某个引导项完成.
	 * @param user
	 * @param guideEnum
	 * @return
	 */
	public int markGuideDone(DcUserDTO user, DcUserGuideModel guideModel) {
		
		int awrdIntegral = 0;
		if (guideModel.getGuideEnum().getDoneAward() != null &&
				guideModel.getGuideEnum().getDoneAward() != DcIntegralSourceEnums.UNKNOWN) {
			awrdIntegral = 0 ;//dcUserIntegralBO.doAwardUserGuide(guideModel);
		}
		long newValue = guideModel.getGuideEnum().getDoneValue(user.getNewGuide());
		int count = dcUserService.updateNewGuide(user.getUserId(), newValue);
		return count > 0? awrdIntegral: 0;
	}
	
	private Date getCurValidShareStartTime() {
		return DcShareObjectEnum.PROMOTION.getCurValidShareStartTime().before(DcShareObjectEnum.APP.getCurValidShareStartTime()) ?
				DcShareObjectEnum.PROMOTION.getCurValidShareStartTime() : DcShareObjectEnum.APP.getCurValidShareStartTime();
	}
}
