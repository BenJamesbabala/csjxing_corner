package com.doucome.corner.biz.dcome.business;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IntegerUtils;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.utils.DateUtils;
import com.doucome.corner.biz.dal.condition.dcome.DcIntegralCondition;
import com.doucome.corner.biz.dcome.enums.DcIntegralSourceEnums;
import com.doucome.corner.biz.dcome.enums.DcItemGenWayEnums;
import com.doucome.corner.biz.dcome.exception.IntegralNotEnoughException;
import com.doucome.corner.biz.dcome.model.DcIntegralDesc;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.model.DcPromotionItemDTO;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.model.DcUserIntegralDetailDTO;
import com.doucome.corner.biz.dcome.model.param.DcAppInvitationModel;
import com.doucome.corner.biz.dcome.model.param.DcAuctionBidModel;
import com.doucome.corner.biz.dcome.model.param.DcExchangeItemModel;
import com.doucome.corner.biz.dcome.model.param.DcFollowQzoneModel;
import com.doucome.corner.biz.dcome.model.param.DcGameAwardModel;
import com.doucome.corner.biz.dcome.model.param.DcPubPromItemModel;
import com.doucome.corner.biz.dcome.model.param.DcRebateModel;
import com.doucome.corner.biz.dcome.model.param.DcShareModel;
import com.doucome.corner.biz.dcome.model.param.DcStealStarModel;
import com.doucome.corner.biz.dcome.model.param.DcUserGuideModel;
import com.doucome.corner.biz.dcome.model.util.PromotionIntegralUtils;
import com.doucome.corner.biz.dcome.service.DcMessageService;
import com.doucome.corner.biz.dcome.service.DcPromotionItemService;
import com.doucome.corner.biz.dcome.service.DcUserIntegralDetailService;
import com.doucome.corner.biz.dcome.service.DcUserService;
import com.doucome.corner.biz.dcome.utils.DcItemUtils;

/**
 * �û����ֲ���
 * @author langben 2012-8-27
 *	@deprecated replce with {@link DcUserIntegralOperateBO}
 */
public class DcUserIntegralBO {
	@Autowired
	private DcUserService dcUserService ;
	@Autowired
	private DcMessageService dcMessageService;
	@Autowired
	private DcUserIntegralDetailService dcUserIntegralDetailService;
	@Autowired
	private DcPromotionItemService dcPromotionItemService;
	
	private static final Log logger = LogFactory.getLog(DcUserIntegralBO.class);
	
	/**
	 * ÿ��ǩ�����ӻ���(����ǩ��ÿ������10�֣��������50��)
	 * @param userId
	 * @deprecated
	 */
//	public int doDailyCheckin(long userId, String userNick, int checkInCount) {
////		int integralCount = DcIntegralSourceEnums.DAILY_CHECKIN.getAwardIntegral(checkInCount);
////		DcUserIntegralDetailDTO detail = new DcUserIntegralDetailDTO() ;
////		detail.setUserId(userId) ;
////		detail.setIntegralCount(integralCount) ;
////		detail.setSource(DcIntegralSourceEnums.DAILY_CHECKIN);
////		DcIntegralDesc integralDesc = new DcIntegralDesc();
////		integralDesc.setToObjName(userNick);
////		detail.setIntegralDesc(integralDesc);
////		
////		_doWithIntegral(detail) ;
////		return integralCount ;
//		return 0 ;
//	}
	
	/**
	 * 
	 * @param userId
	 * @param integralCount
	 * @return
	 * @deprecated
	 */
	public int doWinnerGame(long userId , int integralCount) {
//		DcUserIntegralDetailDTO detail = new DcUserIntegralDetailDTO() ;
//		detail.setUserId(userId) ;
//		detail.setIntegralCount(integralCount) ;
//		detail.setSource(DcIntegralSourceEnums.WINNER_GANE);
//		_doWithIntegral(detail) ;
//		return integralCount ;
		return 0 ;
	}
	
	/**
	 * ��������
	 * @param userId
	 * @deprecated
	 */
	public int doPubPromotionItem(DcPubPromItemModel pubModel) {
//		int integralCount = DcIntegralSourceEnums.PUB_PROMOTION_ITEM.getAwardIntegral();
//		DcUserIntegralDetailDTO detail = new DcUserIntegralDetailDTO() ;
//		detail.setUserId(pubModel.getUserId()) ;
//		detail.setIntegralCount(integralCount) ;
//		detail.setSource(DcIntegralSourceEnums.PUB_PROMOTION_ITEM) ;
//		DcIntegralDesc integralDesc = new DcIntegralDesc();
//		integralDesc.setToObjName(pubModel.getUserNick());
//		integralDesc.setFromObjId(pubModel.getPromItemId());
//		detail.setIntegralDesc(integralDesc);
//		
//		try {
//			_doWithIntegral(detail) ;
//			return integralCount;
//		} catch (Exception e) {
//			logger.error(e);
//			return 0;
//		}
		return 0 ;
	}
	
//	/**
//	 * 
//	 * @param rankInfo
//	 * @return
//	 */
//	public int doAwardPromActiveUser(UserRankInfo rankInfo) {
//		int integral = DcIntegralSourceEnums.ACTIVE_USER_AWARD.getAwardIntegral();
//		DcUserIntegralDetailDTO detail = new DcUserIntegralDetailDTO() ;
//		detail.setUserId(rankInfo.getUserId()) ;
//		detail.setIntegralCount(integral) ;
//		detail.setSource(DcIntegralSourceEnums.ACTIVE_USER_AWARD);
//		DcIntegralDesc integralDesc = new DcIntegralDesc();
//		integralDesc.setToObjName(rankInfo.getUserNick());
//		detail.setIntegralDesc(integralDesc);
//		
//		try {
//			_doWithIntegral(detail) ;
//			return integral;
//		} catch (Exception e) {
//			logger.error(e);
//			return 0;
//		}
//	}
	
	/**
	 * ���û�ע��
	 * @param userId
	 */
//	public int doUserRegister(long userId, String userNick) {
//		int integralCount = DcIntegralSourceEnums.USER_REGISTER.getAwardIntegral();
//		DcUserIntegralDetailDTO detail = new DcUserIntegralDetailDTO() ;
//		detail.setUserId(userId) ;
//		detail.setIntegralCount(integralCount) ;
//		detail.setSource(DcIntegralSourceEnums.USER_REGISTER) ;
//		DcIntegralDesc integralDesc = new DcIntegralDesc();
//		integralDesc.setToObjName(userNick);
//		detail.setIntegralDesc(integralDesc);
//		
//		try {
//			_doWithIntegral(detail) ;
//			return integralCount;
//		} catch (Exception e) {
//			logger.error(e);
//			return -1;
//		}
//	}
//	
	/**
	 * ���Ʊ���ѱ��˵�Ʊ�ӵ��Լ�����Ʒ��.
	 * ���������û���Ϣ.˭����˭���ǣ�˭���Ǳ���
	 * @param userId
	 * @throws throws IntegralNotEnoughException  ���ֲ���
	 */
	public int doStealPromRate(DcStealStarModel stealModel) throws IntegralNotEnoughException  {
//		int integralCount = DcIntegralSourceEnums.STEAL_PROMO_RATE.getAwardIntegral();
//		DcUserIntegralDetailDTO detail = new DcUserIntegralDetailDTO() ;
//		detail.setUserId(stealModel.getToUserId()) ;
//		detail.setIntegralCount(integralCount) ;
//		detail.setSource(DcIntegralSourceEnums.STEAL_PROMO_RATE) ;
//		
//		DcIntegralDesc integralDesc = new DcIntegralDesc();
//		DcPromotionItemDTO promItem =
//			dcPromotionItemService.getPromotionItemById(stealModel.getFromPromItemId());
//		integralDesc.setFromObjId(promItem.getUserId());
//		integralDesc.setFromObjName(promItem.getUserNick());
//		integralDesc.setToObjId(stealModel.getToUserId());
//		integralDesc.setToObjName(stealModel.getToUserNick());
//		integralDesc.setOtherInfo(String.valueOf(stealModel.getFromPromItemId()));
//		
//		detail.setIntegralDesc(integralDesc);
//		_doWithIntegral(detail);
//		//�����û������Ǽ�¼.
//		doBeStealedUserDetail(detail);
//		
//		return integralCount;
		return 0 ;
	}
	
	/**
	 * �����û����������.
	 * @param stealModel
	 * @param promItem
	 * @return
	 */
	private int doBeStealedUserDetail(DcUserIntegralDetailDTO detail) {
//		DcUserIntegralDetailDTO temp = new DcUserIntegralDetailDTO() ;
//		temp.setSource(DcIntegralSourceEnums.STEALED_PROMO_RATE) ;
//		temp.setUserId(detail.getIntegralDesc().getFromObjId()) ;
//		temp.setIntegralCount(0) ;
//		DcIntegralDesc integralDesc = new DcIntegralDesc();
//		integralDesc.setToObjId(detail.getIntegralDesc().getFromObjId());
//		integralDesc.setToObjName(detail.getIntegralDesc().getFromObjName());
//		integralDesc.setFromObjId(detail.getIntegralDesc().getToObjId());
//		integralDesc.setFromObjName(detail.getIntegralDesc().getToObjName());
//		temp.setIntegralDesc(integralDesc);
//		
//		dcMessageService.addIntegralMessage(temp, false);
		return 0;
	}
	
	/**
	 * ���ﷵ����
	 * @param userId
	 * @param integralCount
	 */
	public boolean doBuyRebate(DcRebateModel buyModel){
		DcUserIntegralDetailDTO detail = new DcUserIntegralDetailDTO() ;
		detail.setUserId(buyModel.getUserId()) ;
		detail.setIntegralCount(buyModel.getIntegralCount()) ;
		detail.setSource(DcIntegralSourceEnums.BUY_REBATE);
		DcIntegralDesc integralDesc = new DcIntegralDesc();
		integralDesc.setFromObjName(buyModel.getItemTitle());
		integralDesc.setFromObjId(buyModel.getItemId());
		integralDesc.setToObjId(buyModel.getUserId());
		integralDesc.setToObjName(buyModel.getUserNick());
		
		detail.setIntegralDesc(integralDesc);
		try {
			_doWithIntegral(detail);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
		doAwardItemOwnerWithBuy(buyModel);
		return true;
	}
	
	/**
	 * 
	 * @param rebateModel
	 * @return
	 */
	public int doAwardItemOwnerWithBuy(DcRebateModel rebateModel) {
		if (rebateModel.getItemOwnerUserId() == null
				|| rebateModel.isPgcOwner() || rebateModel.isUserSelf()) {
			return 0;
		}
		int integral = (int) (rebateModel.getIntegralCount() * 0.1);
		integral = integral > 20? 20: integral;
		DcUserIntegralDetailDTO integralDetail = new DcUserIntegralDetailDTO();
		integralDetail.setUserId(rebateModel.getItemOwnerUserId());
		integralDetail.setIntegralCount(integral);
		integralDetail.setSource(DcIntegralSourceEnums.BUY_UGC);
		DcIntegralDesc desc = new DcIntegralDesc();
		desc.setFromObjId(rebateModel.getItemId());
		desc.setFromObjName(rebateModel.getItemTitle());
		desc.setToObjId(rebateModel.getItemOwnerUserId());
		try {
			DcUserDTO user = dcUserService.getUser(rebateModel.getItemOwnerUserId());
			if (user != null) {
				desc.setToObjName(user.getNick());
			}
		} catch (Exception e) {
			
		}
		integralDetail.setIntegralDesc(desc);
		try {
			_doWithIntegral(integralDetail) ;
			return integral;
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}
	
	/**
	 * 
	 * @return
	 */
//	public Integer doAwardUgcAccept(DcItemDTO item) {
//		if (DcItemGenWayEnums.PROFESSIONAL.getValue().equals(item.getGenWay()) ||
//				DcItemUtils.PGC_ITEM_CREATOR.equals(item.getCreatorUserId())) {
//			return 0;
//		}
//		int integral = PromotionIntegralUtils.getIntegral(item);
//		integral = (int) (integral * 0.2);
//		integral = integral > 10 ? 10: integral;
//		DcUserIntegralDetailDTO integralDetail = new DcUserIntegralDetailDTO();
//		integralDetail.setUserId(item.getCreatorUserId());
//		integralDetail.setIntegralCount(integral);
//		integralDetail.setSource(DcIntegralSourceEnums.ACCEPT_UGC);
//		DcIntegralDesc desc = new DcIntegralDesc();
//		desc.setFromObjId(item.getId());
//		desc.setFromObjName(item.getItemTitle());
//		desc.setToObjId(item.getCreatorUserId());
//		desc.setToObjName(item.getCreatorNick());
//		integralDetail.setIntegralDesc(desc);
//		try {
//			_doWithIntegral(integralDetail) ;
//			return integral;
//		} catch (Exception e) {
//			logger.error(e);
//			return 0;
//		}
//	}
	
	/**
	 * ����.
	 * @param shareModel, ���ԣ�shareObject,�������.������. 
	 * @return
	 */
//	public int doShare(DcShareModel shareModel) {
//		DcUserIntegralDetailDTO detail = new DcUserIntegralDetailDTO();
//		int integralCount = shareModel.getShareObject().getAwardIntegral() ;
//		detail.setUserId(shareModel.getUserId());
//		detail.setIntegralCount(integralCount);
//		detail.setSource(DcIntegralSourceEnums.SHARE);
//		
//		DcIntegralDesc integralDesc = new DcIntegralDesc();
//		integralDesc.setFromObjId(shareModel.getShareObjectId());
//		integralDesc.setFromObjName(shareModel.getShareObject().getValue());
//		integralDesc.setToObjId(shareModel.getUserId());
//		integralDesc.setToObjName(shareModel.getUserNick());
//		integralDesc.setOtherInfo(shareModel.getOtherInfo());
//		detail.setIntegralDesc(integralDesc);
//		
//		try {
//			_doWithIntegral(detail) ;
//			return integralCount;
//		} catch (Exception e) {
//			logger.error(e);
//			return 0;
//		}
//	}
	
	/**
	 * �����ѷ���Ӧ������.
	 * @return
	 */
//	public int doInviteFriend(DcAppInvitationModel invitationModel) {
//		if (countTodayInegralSource(invitationModel.getUserId(), DcIntegralSourceEnums.INVITE_FRIEND) > 0) {
//			return 0;
//		}
//		int integralCount = DcIntegralSourceEnums.INVITE_FRIEND.getAwardIntegral() ;
////		if (isAwardInviteIntegral(invitationModel.getUserId())) {
////			integralCount = 0;
////		}
//		DcUserIntegralDetailDTO detail = new DcUserIntegralDetailDTO();
//		detail.setUserId(invitationModel.getUserId());
//		detail.setIntegralCount(integralCount);
//		detail.setSource(DcIntegralSourceEnums.INVITE_FRIEND);
//		DcIntegralDesc integralDesc = new DcIntegralDesc();
//		integralDesc.setToObjId(invitationModel.getUserId());
//		integralDesc.setToObjName(invitationModel.getUserNick());
//		integralDesc.setFromObjName(invitationModel.getInviteeOpenIds());
//		detail.setIntegralDesc(integralDesc);
//		
//		try {
//			_doWithIntegral(detail) ;
//			return integralCount;
//		} catch (Exception e) {
//			logger.error(e);
//			return 0;
//		}
//	}
	
	/**
	 * ������������7�����ϵ����룬�������7����û�гɹ�����һ���ˣ����ͻ���
	 * @param userId
	 * @return
	 */
//	private boolean isAwardInviteIntegral(Long userId) {
//		DcIntegralCondition condition = new DcIntegralCondition();
//		condition.setUserId(userId);
//		condition.addSource(DcIntegralSourceEnums.INVITE_FRIEND.getValue());
//		condition.addSource(DcIntegralSourceEnums.INVITED_USER.getValue());
//		List<DcUserIntegralDetailDTO> integralDetails =
//			dcUserIntegralDetailService.getIntegralDetails(condition, new Pagination(1, 20));
//		if (CollectionUtils.isEmpty(integralDetails)) {
//			return true;
//		}
//		int inviteCount = 0;
//		Date latestDate = null;
//		for (DcUserIntegralDetailDTO temp: integralDetails) {
//			if (temp.getSourceEnum() == DcIntegralSourceEnums.INVITE_FRIEND) {
//				inviteCount++;
//			} else if (temp.getSourceEnum() == DcIntegralSourceEnums.INVITED_USER) {
//				if (latestDate == null) {
//					latestDate = temp.getGmtCreate();
//				} else if (temp.getGmtCreate().compareTo(latestDate) > 0) {
//					latestDate = temp.getGmtCreate();
//				}
//			}
//		}
//		int days = DateUtils.getDiffInDays(new Date(), latestDate);
//		//�����������Ĵ���С��3�Σ������3�������������ѣ��ͻ���
//		return inviteCount < 3 || (latestDate != null && days < 3);
//	}
//	
	/**
	 * �ɹ�����������. ������Ӧ�����ж����������û��Ƿ������û�.
	 * @param userId ����������û�.
	 * @param invitedUserId �������û�id.
	 * @return
	 */
//	public int doInviteSucc(DcInviteModel inviteUser) {
//		DcUserIntegralDetailDTO detail = new DcUserIntegralDetailDTO();
//		int integralCount = DcIntegralSourceEnums.INVITED_USER.getAwardIntegral() ;
//		detail.setUserId(inviteUser.getUserId());
//		detail.setIntegralCount(integralCount);
//		detail.setSource(DcIntegralSourceEnums.INVITED_USER);
//		DcIntegralDesc integralDesc = new DcIntegralDesc();
//		integralDesc.setToObjId(inviteUser.getUserId());
//		integralDesc.setToObjName(inviteUser.getUserNick());
//		integralDesc.setFromObjName(inviteUser.getInvitedUserNick());
//		integralDesc.setFromObjId(inviteUser.getInvitedUserId());
//		detail.setIntegralDesc(integralDesc);
//		
//		try {
//			_doWithIntegral(detail) ;
//			return integralCount;
//		} catch (Exception e) {
//			logger.error(e);
//			return 0;
//		}
//	}
	
	/**
	 * ��Ϸ����
	 * @param gameAward
	 * @return
	 */
	public int doPKGameAward(DcGameAwardModel gameAward) {
		
		return 0;
	}
	
	/**
	 * ��ע�ռ�.
	 * @param followModel
	 * @return
	 */
//	public int doFollowQzone(DcFollowQzoneModel followModel) {
//		DcUserIntegralDetailDTO detail = new DcUserIntegralDetailDTO();
//		int integralCount = DcIntegralSourceEnums.FOLLOW_QZONE.getAwardIntegral() ;
//		detail.setUserId(followModel.getUserId());
//		detail.setIntegralCount(integralCount);
//		detail.setSource(DcIntegralSourceEnums.FOLLOW_QZONE);
//		
//		DcIntegralDesc integralDesc = new DcIntegralDesc();
//		integralDesc.setToObjId(followModel.getUserId());
//		integralDesc.setToObjName(followModel.getUserNick());
//		integralDesc.setFromObjName(followModel.getQzoneName());
//		detail.setIntegralDesc(integralDesc);
//		
//		try {
//			_doWithIntegral(detail) ;
//			return integralCount;
//		} catch (Exception e) {
//			logger.error(e);
//			return 0;
//		}
//	}
	
	/**
	 * ���־��ĳ���.
	 * @param auctionBid
	 * @return
	 */
	public DcUserIntegralDetailDTO doAuctionBid(DcAuctionBidModel bidModel) {
//		DcUserIntegralDetailDTO detail = new DcUserIntegralDetailDTO();
//		detail.setUserId(bidModel.getUserId());
//		detail.setIntegralCount(bidModel.getBidIntegral());
//		detail.setSource(DcIntegralSourceEnums.AUCTION_BID);
//		
//		DcIntegralDesc integralDesc = new DcIntegralDesc();
//		integralDesc.setToObjId(bidModel.getUserId());
//		integralDesc.setToObjName(bidModel.getUserNick());
//		integralDesc.setFromObjName(bidModel.getAuction());
//		integralDesc.setFromObjId(bidModel.getAuctionItemId());
//		detail.setIntegralDesc(integralDesc);
//		
//		try {
//			_doWithIntegral(detail) ;
//			return detail;
//		} catch (Exception e) {
//			logger.error(e);
//			return null;
//		}
		return null ;
	}
	
	/**
	 * log���ֶһ�.
	 * @param exchangeModel
	 * @return
	 */
	public int doExchangeItem(DcExchangeItemModel exchangeModel) {
		DcUserIntegralDetailDTO detail = new DcUserIntegralDetailDTO();
		detail.setUserId(exchangeModel.getUserId());
		detail.setIntegralCount(exchangeModel.getExIntegral() * -1);
		detail.setSource(DcIntegralSourceEnums.EXCHANGE_ITEM);
		
		DcIntegralDesc integralDesc = new DcIntegralDesc();
		integralDesc.setToObjId(exchangeModel.getUserId());
		integralDesc.setToObjName(exchangeModel.getUserNick());
		integralDesc.setFromObjName(exchangeModel.getExItemTitle());
		integralDesc.setFromObjId(exchangeModel.getExItemId());
		integralDesc.setOtherInfo(exchangeModel.getOtherInfo());
		detail.setIntegralDesc(integralDesc);
		
		try {
			_doWithIntegral(detail);
			return exchangeModel.getExIntegral();
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}
	
//	/**
//	 * 
//	 * @param ugcItem
//	 * @return
//	 */
//	public int doAwardUserGuide(DcUserGuideModel guideModel) {
//		if (guideModel.getGuideEnum().getDoneAward() == null
//				|| guideModel.getGuideEnum().getDoneAward() == DcIntegralSourceEnums.UNKNOWN) {
//			return 0;
//		}
//		int integral = guideModel.getGuideEnum().getDoneAward().getAwardIntegral();
//		DcUserIntegralDetailDTO detail = new DcUserIntegralDetailDTO();
//		detail.setUserId(guideModel.getUserId());
//		detail.setIntegralCount(integral);
//		detail.setSource(guideModel.getGuideEnum().getDoneAward());
//		
//		DcIntegralDesc integralDesc = new DcIntegralDesc();
//		integralDesc.setToObjId(guideModel.getUserId());
//		integralDesc.setToObjName(guideModel.getUserNick());
//		integralDesc.setFromObjId(guideModel.getGuideResultId());
//		integralDesc.setFromObjName(guideModel.getGuideResultName());
//		integralDesc.setOtherInfo(guideModel.getOtherInfo());
//		detail.setIntegralDesc(integralDesc);
//		
//		try {
//			_doWithIntegral(detail);
//			return integral;
//		} catch (Exception e) {
//			logger.error(e);
//			return 0;
//		}
//	}
	
	/**
	 * ͳ�ƽ��ջ�����Դ����.
	 * @param userId
	 * @param source
	 * @return
	 */
	public int countTodayInegralSource(Long userId, DcIntegralSourceEnums source) {
		DcIntegralCondition condition = new DcIntegralCondition();
		Date todayStart = DateUtils.trimDate(new Date(), Calendar.HOUR_OF_DAY);
		condition.setUserId(userId);
		condition.setSource(source.getValue());
		condition.setGmtCreateStart(todayStart);
		return dcUserIntegralDetailService.countIntegralDetails(condition);
	}
	
	/**
	 * �Ƿ����㹻�Ļ���
	 * @param userId
	 * @param integralCount
	 * @return
	 */
	public boolean hasEnoughIntegral(long userId , DcIntegralSourceEnums source){
		int integralCount = source.getAwardIntegral();
		if(integralCount >= 0){
			return true ;
		}
		DcUserDTO user = dcUserService.getUser(userId) ;
		int userIntegral = IntegerUtils.parseInt(user.getIntegralCount()) ;
		if(userIntegral >= Math.abs(integralCount)){
			return true ;
		}
		return false ;
	}
	
	private boolean _doWithIntegral(DcUserIntegralDetailDTO detail) throws IntegralNotEnoughException {
		if(detail == null){
			return false;
		}
		int effectCount = 0 ;
		int integralCount = IntegerUtils.parseInt(detail.getIntegralCount()) ;
		//���ĳɹ�ǰ�������û�����.
		if (detail.getSourceEnum() != DcIntegralSourceEnums.AUCTION_BID) {
			if(integralCount > 0){
				effectCount = dcUserService.incrIntegralByUser(detail.getUserId(), Math.abs(integralCount)) ;
			}else if(integralCount < 0){
				DcIntegralSourceEnums source = detail.getSourceEnum();
				if(!hasEnoughIntegral(detail.getUserId(), source)) {
					throw new IntegralNotEnoughException() ;
				}
				effectCount = dcUserService.decrIntegralByUser(detail.getUserId(), Math.abs(integralCount)) ;
			}
		}
		if( detail.getSourceEnum() != DcIntegralSourceEnums.WINNER_GANE) { 
			dcMessageService.addIntegralMessage(detail, true);
		}
		return true;
	}
}
