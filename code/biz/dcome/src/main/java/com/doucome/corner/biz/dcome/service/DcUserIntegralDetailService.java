package com.doucome.corner.biz.dcome.service;

import java.util.List;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.dal.condition.dcome.DcIntegralCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserIntegralDetailDO;
import com.doucome.corner.biz.dcome.enums.DcIntegralSourceEnums;
import com.doucome.corner.biz.dcome.enums.DcYesOrNoEnum;
import com.doucome.corner.biz.dcome.model.DcUserIntegralDetailDTO;

/**
 * ������ϸ.�˷����Ӧ�ý���ϵͳ��Ϣ��ϸ����.
 * @author langben 2012-8-27
 *
 */
public interface DcUserIntegralDetailService {

	/**
	 * �������ּ�¼
	 * @param detail
	 * @return
	 */
	Long createDetail(DcUserIntegralDetailDO detail) ;
	/**
	 * ��ȡ�û����µĻ����б�.����ֵ userId��null: Ӧ����Ϣ, 1l:Ӧ��֪ͨ;
	 * @param userId �û�id,1LΪӦ��֪ͨ.
	 * @return
	 */
	List<DcUserIntegralDetailDTO> getUserIntegralDetails(Long userId, Pagination page);
	/**
	 * ��ȡ���µĻ����б�
	 * @param page
	 * @return
	 */
	//List<DcUserIntegralDetailDTO> getIntegralDetails(Pagination page);
	/**
	 * ��ȡ���µ���ϸ��Ϣ.
	 * @param source �������,null��ʾȡ������������.
	 * @param page
	 * @return
	 */
	List<DcUserIntegralDetailDTO> getLatestIntegralDetails(DcIntegralSourceEnums source, Pagination page);
	/**
	 * ע�⣬userId��
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
