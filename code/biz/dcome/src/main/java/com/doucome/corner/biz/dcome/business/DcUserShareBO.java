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
import com.doucome.corner.biz.dcome.model.DcIntegralDesc;
import com.doucome.corner.biz.dcome.model.DcPromotionItemDTO;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.model.DcUserIntegralDetailDTO;
import com.doucome.corner.biz.dcome.model.param.DcShareModel;
import com.doucome.corner.biz.dcome.service.DcPromotionItemService;
import com.doucome.corner.biz.dcome.service.DcUserIntegralDetailService;
import com.doucome.corner.biz.dcome.service.DcUserService;
import com.doucome.corner.web.common.model.ResultModel;

/**
 * 用户参与的活动业务逻辑类.
 * @author ze2200
 *
 */
public class DcUserShareBO {
	@Autowired
	private DcPromotionItemService dcPromotionItemService;
	@Autowired
	private DcUserIntegralOperateBO dcUserIntegralOperateBO ;
	@Autowired
	private DcUserService dcUserService;
	@Autowired
	private DcUserIntegralDetailService dcUserIntegralDetailService;
	/**
	 * 分享活动.
	 * @return 分享获得积分.
	 */
	public ResultModel<Integer> sharePromotion(DcUserDTO user, DcPromotionItemDTO promItem) {
		ResultModel<Integer> result = new ResultModel<Integer>();
		if (promItem == null) {
			result.setFail(ResultModel.CODE_FAIL, "no.promitem");
			return result;
		}
		if (promItem.isShared()) {
			result.setFail(ResultModel.CODE_FAIL, "share.repeat");
			return result;
		}
		//标记分享
		dcPromotionItemService.markPromItemShared(promItem.getId());
		//增加用户积分.
		DcShareModel shareModel = new DcShareModel();
		shareModel.setUserId(user.getUserId());
		shareModel.setUserNick(user.getNick());
		shareModel.setShareObject(DcShareObjectEnum.PROMOTION);
		shareModel.setShareObjectId(promItem.getId());
		int integral = dcUserIntegralOperateBO.doShare(user , shareModel);
		result.setSuccess(ResultModel.CODE_SUCCESS, integral);
		return result;
	}
	
	/**
	 * 分享商品
	 * @return 分享获得积分.
	 */
	public int shareDcItem(DcUserDTO user, Long itemId) {
		int count = getTodayShareCount(user.getUserId(), DcShareObjectEnum.ITEM);
		if (count > 0) {
			return 0;
		}
		DcShareModel shareModel = new DcShareModel();
		shareModel.setUserId(user.getUserId());
		shareModel.setUserNick(user.getNick());
		shareModel.setShareObject(DcShareObjectEnum.ITEM);
		shareModel.setShareObjectId(itemId);
		
		return dcUserIntegralOperateBO.doShare(user , shareModel);
	}
	
	/**
	 * 分享app.
	 * @param userId
	 * @return
	 */
	public int shareApp(DcUserDTO user) {
		Date now = Calendar.getInstance().getTime();
		if (DateUtils.isSameDay(now, user.getGmtLastShare())) {
			return 0;
		}
		user.setGmtLastShare(now);
		dcUserService.markDailyShare(user.getUserId());
		DcShareModel shareModel = new DcShareModel();
		shareModel.setUserId(user.getUserId());
		shareModel.setUserNick(user.getNick());
		shareModel.setShareObject(DcShareObjectEnum.APP);
		return dcUserIntegralOperateBO.doShare(user , shareModel);
	}
	
	/**
	 * 
	 * @param user
	 * @param inviteeOpenIds
	 * @return
	 */
	public int sendFriendInvite(DcUserDTO user, String inviteeOpenIds) {
		return dcUserIntegralOperateBO.doSendInvite(user, inviteeOpenIds) ;
	}
	
	/**
	 * 判断用户已今日已分享几次.
	 * @param userId
	 * @param obj
	 * @return
	 */
	public int getTodayShareCount(Long userId, DcShareObjectEnum obj) {
		if  (obj == DcShareObjectEnum.AWARD) {
			return 0;
		}
		DcIntegralCondition condition = new DcIntegralCondition();
		condition.setUserId(userId);
		condition.setSource(DcIntegralSourceEnums.SHARE.getValue());
		condition.setGmtCreateStart(obj.getCurValidShareStartTime());
		List<DcUserIntegralDetailDTO> integralDetails =
			dcUserIntegralDetailService.getIntegralDetails(condition, new Pagination(1, 20));
		Date validSharrStartTime = obj.getCurValidShareStartTime();
		int count = 0;
		for (DcUserIntegralDetailDTO temp: integralDetails) {
			if (temp.getGmtCreate().after(validSharrStartTime)) {
				DcIntegralDesc desc = temp.getIntegralDesc();
				if (obj.getValue().equals(desc.getFromObjName())) {
					count++;
				}
			}
		}
		return count;
	}
	
	/**
	 * 获取今日分享完成情况.
	 * @return
	 */
	public Map<DcShareObjectEnum, Boolean> getTodayShareStatus(Long userId) {
		Map<DcShareObjectEnum, Boolean> result = new HashMap<DcShareObjectEnum, Boolean>();
		for (DcShareObjectEnum temp: DcShareObjectEnum.values()) {
			result.put(temp, false);
		}
		DcIntegralCondition condition = new DcIntegralCondition();
		condition.setUserId(userId);
		condition.setSource(DcIntegralSourceEnums.SHARE.getValue());
		condition.setGmtCreateStart(DcShareObjectEnum.PROMOTION.getCurValidShareStartTime());
		List<DcUserIntegralDetailDTO> integrals = dcUserIntegralDetailService.getIntegralDetails(condition, new Pagination(1, 20));
		for (DcUserIntegralDetailDTO temp: integrals) {
			DcIntegralDesc desc = temp.getIntegralDesc();
			DcShareObjectEnum shareObj = DcShareObjectEnum.toEnum(desc.getFromObjName());
			if (shareObj == null) {
				continue;
			}
			if (temp.getGmtCreate().after(shareObj.getCurValidShareStartTime())) {
				result.put(shareObj, true);
			}
		}
		return result;
	}
}