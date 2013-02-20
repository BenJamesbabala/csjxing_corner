package com.doucome.corner.biz.dcome.service;

import java.util.List;

import com.doucome.corner.biz.dcome.enums.DcIntegralSourceEnums;
import com.doucome.corner.biz.dcome.model.DcMessageDTO;
import com.doucome.corner.biz.dcome.model.DcUserIntegralDetailDTO;

/**
 * 此接口应该和DcUserIntegralDetailService合并
 * @author ze2200
 *
 */
public interface DcMessageService {
	/**
	 * 添加用户积分动态.
	 * @param integralDetail 
	 * @param cacheForApp 是否缓存为应用消息.主要针对一个动作产生了多条消息的问题.
	 * @return
	 */
	Long addIntegralMessage(DcUserIntegralDetailDTO integralDetail, boolean cacheForApp);
	/**
	 * 添加小道消息,暂时支持NOTI和WARN类型.
	 * @param message
	 * @param type 暂时支持NOTI和WARN类型，新增类型需要客户端支持.
	 * @return
	 */
	boolean addAppGrepevine(String message, DcIntegralSourceEnums type);
	/**
	 * 获取用户消息.
	 * @param userId
	 * @return
	 */
	List<DcMessageDTO> getUserMessages(Long userId, int pageNum);
	/**
	 * 获取应用消息.
	 * @param page
	 * @return
	 */
	List<DcMessageDTO> getAppMessages(int pageNum);
	/**
	 * 获取系统通知.
	 * @return
	 */
	List<DcMessageDTO> getAppGrepevines();
	/**
	 * 获取竞拍消息.
	 * @param autionId
	 * @return
	 */
	List<DcMessageDTO> getAuctionMessage(Long autionId);
	/**
	 * 向用户发送消息.
	 * @param userId
	 * @param message
	 * @return
	 */
	Long sendMessageToUser(Long userId, String message);
}
