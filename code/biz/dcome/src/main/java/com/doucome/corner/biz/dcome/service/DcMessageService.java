package com.doucome.corner.biz.dcome.service;

import java.util.List;

import com.doucome.corner.biz.dcome.enums.DcIntegralSourceEnums;
import com.doucome.corner.biz.dcome.model.DcMessageDTO;
import com.doucome.corner.biz.dcome.model.DcUserIntegralDetailDTO;

/**
 * �˽ӿ�Ӧ�ú�DcUserIntegralDetailService�ϲ�
 * @author ze2200
 *
 */
public interface DcMessageService {
	/**
	 * ����û����ֶ�̬.
	 * @param integralDetail 
	 * @param cacheForApp �Ƿ񻺴�ΪӦ����Ϣ.��Ҫ���һ�����������˶�����Ϣ������.
	 * @return
	 */
	Long addIntegralMessage(DcUserIntegralDetailDTO integralDetail, boolean cacheForApp);
	/**
	 * ���С����Ϣ,��ʱ֧��NOTI��WARN����.
	 * @param message
	 * @param type ��ʱ֧��NOTI��WARN���ͣ�����������Ҫ�ͻ���֧��.
	 * @return
	 */
	boolean addAppGrepevine(String message, DcIntegralSourceEnums type);
	/**
	 * ��ȡ�û���Ϣ.
	 * @param userId
	 * @return
	 */
	List<DcMessageDTO> getUserMessages(Long userId, int pageNum);
	/**
	 * ��ȡӦ����Ϣ.
	 * @param page
	 * @return
	 */
	List<DcMessageDTO> getAppMessages(int pageNum);
	/**
	 * ��ȡϵͳ֪ͨ.
	 * @return
	 */
	List<DcMessageDTO> getAppGrepevines();
	/**
	 * ��ȡ������Ϣ.
	 * @param autionId
	 * @return
	 */
	List<DcMessageDTO> getAuctionMessage(Long autionId);
	/**
	 * ���û�������Ϣ.
	 * @param userId
	 * @param message
	 * @return
	 */
	Long sendMessageToUser(Long userId, String message);
}
