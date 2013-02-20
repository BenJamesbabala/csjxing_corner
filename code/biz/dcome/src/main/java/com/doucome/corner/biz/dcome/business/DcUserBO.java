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
	 * ÿ��ǩ��
	 * @param userId
	 * @throws DuplicateOperateException �ظ�ǩ��
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
			//���»���
			int integralCount = dcUserIntegralOperateBO.doDailyCheckin(user , checkInCount) ;
			//����ǩ��ʱ��
			dcUserService.updateLastCheckinTime(userId, checkInCount) ;
			
			return integralCount ;
		}
		return 0 ;
	}
	
	/**
	 * ��ע�ռ�
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
	 * ��ȡ�û����տ���ȡ�Ļ���
	 * @param user
	 * @return
	 */
	public Map<String, Integer> getUserUndrawIntegral(DcUserDTO user) {
		int integralCount = 0;
		int taskCount = 0;
		//��ע�ռ�.
		if (user.getGmtFollowQzone() == null) {
			integralCount += DcIntegralSourceEnums.FOLLOW_QZONE.getAwardIntegral();
			taskCount++;
		}
		//ǩ��.
		if (!DateUtils.isToday(user.getGmtLastCheckin())) {
			if (DateUtils.isYesterday(user.getGmtLastCheckin())) {
				//����ǩ��
				integralCount += DcIntegralSourceEnums.DAILY_CHECKIN.getAwardIntegral(user.getCheckInCount() + 1);
			} else {
				integralCount += DcIntegralSourceEnums.DAILY_CHECKIN.getAwardIntegral();
			}
			taskCount++;
		}
		integralCount += 30; //����30 + ���� 10,ǰ��û�и������İ�ť����ʱȥ��������10����
		taskCount += 3;  //���񣺷���3 + ����1,ǩ�����ͻ���,����ǩ���и��ཱ��Ŷ
		//������Ʒ������������Ӧ��
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
	 * ��ȡ��ǰ�û��ռ��������.
	 * ����share_app,share_item,share_prom,checkin,follow_qzone,invite friend,invited friend
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
		//��ȡ��������.
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
	 * ��ѯ��������������.
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
	 * �Ƿ���ʾugc����.
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
	 * �Ƿ���ʾ�������㸡��.
	 * @param user
	 * @return
	 */
	private boolean isShowIntegralDiscountLayer(DcUserDTO user) {
		//�û���ǰ��������50
		if (user.getIntegralCount() < 50) {
			return false;
		}
		int days = DateUtils.getDiffInDays(new Date(), user.getGmtLastLogin());
		//15��û�е�¼
		if (days > 15) {
			return false;
		}
		return !DcUserGuideEnum.NOTIFICATE.isDone(user.getNewGuide());
	}
	
	/**
	 * ��ʶĳ�����������.
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
