package com.doucome.corner.biz.dcome.service;

import java.util.List;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.dal.condition.dcome.DcIntegralCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserIntegralDetailDO;
import com.doucome.corner.biz.dcome.enums.DcIntegralSourceEnums;
import com.doucome.corner.biz.dcome.enums.DcYesOrNoEnum;
import com.doucome.corner.biz.dcome.model.DcUserIntegralDetailDTO;

/**
 * 积分明细.此服务更应该叫做系统消息明细服务.
 * @author langben 2012-8-27
 *
 */
public interface DcUserIntegralDetailService {

	/**
	 * 新增积分记录
	 * @param detail
	 * @return
	 */
	Long createDetail(DcUserIntegralDetailDO detail) ;
	/**
	 * 获取用户最新的积分列表.特殊值 userId：null: 应用消息, 1l:应用通知;
	 * @param userId 用户id,1L为应用通知.
	 * @return
	 */
	List<DcUserIntegralDetailDTO> getUserIntegralDetails(Long userId, Pagination page);
	/**
	 * 获取最新的积分列表
	 * @param page
	 * @return
	 */
	//List<DcUserIntegralDetailDTO> getIntegralDetails(Pagination page);
	/**
	 * 获取最新的明细消息.
	 * @param source 积分类别,null表示取所有类别的数据.
	 * @param page
	 * @return
	 */
	List<DcUserIntegralDetailDTO> getLatestIntegralDetails(DcIntegralSourceEnums source, Pagination page);
	/**
	 * 注意，userId比
	 * @param userId
	 * @param source
	 * @return
	 */
	List<DcUserIntegralDetailDTO> getIntegralDetails(DcIntegralCondition condition, Pagination page);
	/**
	 * 
	 * @param condition
	 * @return
	 */
	Integer countIntegralDetails(DcIntegralCondition condition);
	/**
	 * 
	 * @param auctionId
	 * @return
	 */
	List<DcUserIntegralDetailDTO> getAuctionBidList(Long auctionId);
	/**
	 * 
	 * @param id
	 * @param readStatus
	 * @return
	 */
	int updateReadStatus(Long id, DcYesOrNoEnum readStatus);
}
