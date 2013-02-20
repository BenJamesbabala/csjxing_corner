package com.doucome.corner.biz.dcome.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserIntegralDetailDO;
import com.doucome.corner.biz.dcome.cache.DcMessageCache;
import com.doucome.corner.biz.dcome.enums.DcIntegralSourceEnums;
import com.doucome.corner.biz.dcome.enums.DcYesOrNoEnum;
import com.doucome.corner.biz.dcome.model.DcMessageDTO;
import com.doucome.corner.biz.dcome.model.DcUserIntegralDetailDTO;
import com.doucome.corner.biz.dcome.service.DcMessageService;
import com.doucome.corner.biz.dcome.service.DcUserIntegralDetailService;
import com.doucome.corner.biz.dcome.utils.DcConvertUtils;

/**
 * 
 * @author ze2200
 */
public class DcMessageServiceImpl implements DcMessageService {
	/**
	 * Ӧ����Ϣid.
	 */
	private static final Long APP_MESSAGE_ID = 0l;
	/**
	 * Ӧ��֪ͨid.
	 */
	private static final Long APP_NOTIFY_ID = 1l;
	/**
	 * ���־�����Ϣid
	 */
	private static final Long AUCTION_MESSAGE_ID = 2l;
	/**
	 * �û���Ϣ������������.
	 */
	private static final int USER_MESSAGE_LIMIT = 10;
	/**
	 * Ӧ����Ϣ������������.
	 */
	private static final int APP_MESSAGE_LIMIT = 20;
	/**
	 * С����Ϣ������������.
	 */
	private static final int APP_NOTIFY_LIMIT = 50;
	
	private static final int AUCTION_MESSAGE_LIMIT = 100;
	@Autowired
	private DcMessageCache dcMessageCache;
	@Autowired
	private DcUserIntegralDetailService	dcUserIntegralDetailService;
	
	@Override
	public Long addIntegralMessage(DcUserIntegralDetailDTO integralDetail, boolean cacheForApp) {
		if (integralDetail == null || !IDUtils.isCorrect(integralDetail.getUserId())) {
			return 0l;
		}
		Long id = dcUserIntegralDetailService.createDetail(integralDetail.getUserIntegralDetailDO());
		DcMessageDTO tempMessage = DcConvertUtils.convertToDcMessage(integralDetail);
		//�����û���Ϣ.
		cacheUserMessage(tempMessage);
		if (cacheForApp) {
			//����ΪӦ����Ϣ
			cacheAppMessage(tempMessage);
		}
		if (integralDetail.getSourceEnum() == DcIntegralSourceEnums.AUCTION_BID) {
			cacheAuctionBidMessage(tempMessage);
		}
		return id;
	}
	
	@Override
	public boolean addAppGrepevine(String message, DcIntegralSourceEnums type) {
		if (StringUtils.isEmpty(message) || type == null) {
			return false;
		}
		DcUserIntegralDetailDTO temp = new DcUserIntegralDetailDTO();
		temp.setUserId(APP_NOTIFY_ID);
		temp.setSource(type);
		temp.setIntegralCount(0);
		temp.setIntegralDesc(message);
		long id = dcUserIntegralDetailService.createDetail(temp.getUserIntegralDetailDO());
		temp.setId(id);
		DcMessageDTO messageDTO = DcConvertUtils.convertToDcMessage(temp);
		cacheAppGrapevine(messageDTO);
		return true;
	}
	
	@Override
	public List<DcMessageDTO> getUserMessages(Long userId, int pageNum) {
		if (!IDUtils.isCorrect(userId) || pageNum <= 0) {
			return new ArrayList<DcMessageDTO>();
		}
		List<DcMessageDTO> messages = null;
		//��һҳ����Ĭ�ϴӻ����ȡ
		if (pageNum == 1) {
			messages = dcMessageCache.getMessages(userId);
		}
		//������㹻����Ϣ��ֱ�ӷ���
		if (messages != null && messages.size() > 5) {
			return messages;
		}
		messages = messages == null? new ArrayList<DcMessageDTO>(): messages;
		List<DcUserIntegralDetailDTO> integrals =
			dcUserIntegralDetailService.getUserIntegralDetails(userId, new Pagination(pageNum, USER_MESSAGE_LIMIT));
		List<DcMessageDTO> tempMessages = convert(integrals);
		messages.addAll(tempMessages);
		//�����һҳ����
		if (CollectionUtils.isNotEmpty(messages) && pageNum == 1) {
			dcMessageCache.cacheMessagesTenMinute(userId, messages);
		}
		return messages;
	}
	
	@Override
	public List<DcMessageDTO> getAppMessages(int pageNum) {
		List<DcMessageDTO> messages = dcMessageCache.getMessages(APP_MESSAGE_ID);
		if (CollectionUtils.isNotEmpty(messages)) {
			return messages;
		}
		
		messages = new ArrayList<DcMessageDTO>();
		int tempNum = pageNum;
		//��ΪҪ�Ƴ�С����Ϣ���ݣ����Դ˴���Ҫѭ��ȡ����ֹӦ����Ϣ����̫��
		for (int i = 0; i < 3; i++) {
			List<DcUserIntegralDetailDTO> integrals =
				dcUserIntegralDetailService.getUserIntegralDetails(null, new Pagination(tempNum, APP_MESSAGE_LIMIT));
			List<DcMessageDTO> tempMessages = convert(integrals);
			removeGrapevineMessage(tempMessages);
			messages.addAll(tempMessages);
			if (messages.size() > 10) {
				break;
			}
			tempNum++;
		}
		//�����һҳ����
		if (CollectionUtils.isNotEmpty(messages) && pageNum == 1) {
			dcMessageCache.cacheMessagesOneHour(APP_MESSAGE_ID, messages);
		}
		return messages;
	}

	private void removeGrapevineMessage(List<DcMessageDTO> messages) {
		for(int i = messages.size() - 1; i >= 0; i--) {
			if (APP_NOTIFY_ID.equals(messages.get(i).getUserId())
				|| DcIntegralSourceEnums.APP_NOTIFICATION.getValue().equals(messages.get(i).getActivity())
				|| DcIntegralSourceEnums.APP_WARN.getValue().equals(messages.get(i).getActivity())) {
				messages.remove(i);
			}
		}
	}
	
	@Override
	public List<DcMessageDTO> getAppGrepevines() {
		List<DcMessageDTO> messages = dcMessageCache.getMessages(APP_NOTIFY_ID);
		if (!CollectionUtils.isEmpty(messages)) {
			return messages;
		}
		Pagination page = new Pagination(1, APP_NOTIFY_LIMIT);
		List<DcUserIntegralDetailDTO> integrals = dcUserIntegralDetailService.getUserIntegralDetails(APP_NOTIFY_ID, page);
		messages = convert(integrals);
		if (CollectionUtils.isNotEmpty(integrals)) {
			dcMessageCache.cacheMessagesOneHour(APP_NOTIFY_ID, messages);
		}
		return messages;
	}
	
	@Override
	public List<DcMessageDTO> getAuctionMessage(Long auctionId) {
		List<DcMessageDTO> messages = dcMessageCache.getMessages(AUCTION_MESSAGE_ID);
		if (!CollectionUtils.isEmpty(messages)) {
			//���ǵ�ǰ���ڽ��еľ��ĵľ��ļ�¼.
	        if (messages.get(0).getRelateObjId().equals(auctionId)) {
				messages = null;
			}
		}
		if (!CollectionUtils.isEmpty(messages)) {
			return messages;
		}
		
		List<DcUserIntegralDetailDTO> integrals = dcUserIntegralDetailService.getAuctionBidList(auctionId);
		messages = convert(integrals);
		if (CollectionUtils.isNotEmpty(integrals)) {
			dcMessageCache.cacheMessagesOneHour(AUCTION_MESSAGE_ID, messages);
		}
		return messages;
	}
	
	@Override
	public Long sendMessageToUser(Long userId, String message) {
		DcUserIntegralDetailDO detail = new DcUserIntegralDetailDO();
		detail.setUserId(userId);
		detail.setSource(DcIntegralSourceEnums.APP_NOTIFICATION.getValue());
		detail.setIntegralCount(0);
		detail.setIntegralDesc(message);
		detail.setReadStatus(DcYesOrNoEnum.NO.getValue());
		return dcUserIntegralDetailService.createDetail(detail);
	}
	
	private List<DcMessageDTO> convert(List<DcUserIntegralDetailDTO>  integrals) {
		List<DcMessageDTO> messages = new ArrayList<DcMessageDTO>();
		if (CollectionUtils.isNotEmpty(integrals)) {
			for (DcUserIntegralDetailDTO temp: integrals) {
				DcMessageDTO dynamicDTO = DcConvertUtils.convertToDcMessage(temp);
				messages.add(dynamicDTO);
			}
		}
		return messages;
	}
	
	/**
	 * ����̶���������Ϣ
	 * @param id
	 * @param message
	 * @param num
	 * @return
	 */
	private boolean cacheUserMessage(DcMessageDTO message) {
		List<DcMessageDTO> userMessages = dcMessageCache.getMessages(message.getUserId());
		if (userMessages == null) {
			userMessages = new ArrayList<DcMessageDTO>();
		}
		userMessages.add(0, message);
		//�̶�����num����Ϣ.
		for (int i = userMessages.size() - 1; i > USER_MESSAGE_LIMIT; i--) {
			userMessages.remove(i);
		}
		return dcMessageCache.cacheMessagesTenMinute(message.getUserId(), userMessages);
	}
	
	/**
	 * ����ΪӦ����Ϣ������˵��ڲ��û���Ϣ.
	 * @param message
	 * @return
	 */
	private boolean cacheAppMessage(DcMessageDTO message) {
		List<DcMessageDTO> userMessages = dcMessageCache.getMessages(APP_MESSAGE_ID);
		if (userMessages == null) {
			userMessages = new ArrayList<DcMessageDTO>();
		}
		userMessages.add(0, message);
		//�̶�����num����Ϣ.
		for (int i = userMessages.size() - 1; i > APP_MESSAGE_LIMIT; i--) {
			userMessages.remove(i);
		}
		return dcMessageCache.cacheMessagesOneHour(APP_MESSAGE_ID, userMessages);
	}
	
	/**
	 * ����С����Ϣ
	 * @param message
	 * @return
	 */
	private boolean cacheAppGrapevine(DcMessageDTO message) {
		List<DcMessageDTO> userMessages = dcMessageCache.getMessages(APP_NOTIFY_ID);
		if (userMessages == null) {
			userMessages = new ArrayList<DcMessageDTO>();
		}
		userMessages.add(0, message);
		for (int i = userMessages.size() - 1; i > APP_NOTIFY_LIMIT; i--) {
			userMessages.remove(i);
		}
		return dcMessageCache.cacheMessagesOneHour(APP_NOTIFY_ID, userMessages);
	}
	
	/**
	 * ������־�����Ϣ.
	 * @param message
	 * @return
	 */
	private boolean cacheAuctionBidMessage(DcMessageDTO message) {
		Long curAcutionId = message.getRelateObjId();
		List<DcMessageDTO> auctionMessages = dcMessageCache.getMessages(AUCTION_MESSAGE_ID);
		if (auctionMessages == null) {
			auctionMessages = new ArrayList<DcMessageDTO>();
		}
		if (auctionMessages.size() > 0) {
			//�жϻ���ľ�����Ϣ�Ƿ��ǵ�ǰ���ڽ��еľ��ĵ���Ϣ
			if (!auctionMessages.get(0).equals(curAcutionId)) {
				auctionMessages = new ArrayList<DcMessageDTO>();
			}
		}
		auctionMessages.add(message);
		while(auctionMessages.size() > AUCTION_MESSAGE_LIMIT) {
			auctionMessages.remove(0);
		}
		return dcMessageCache.cacheMessagesOneHour(AUCTION_MESSAGE_ID, auctionMessages);
	}
}
