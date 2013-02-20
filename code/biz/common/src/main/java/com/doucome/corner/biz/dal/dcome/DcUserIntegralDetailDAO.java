package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.condition.dcome.DcIntegralCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserIntegralDetailDO;

/**
 * ������ϸ��¼
 * @author langben 2012-8-27
 *
 */
public interface DcUserIntegralDetailDAO {

	/**
	 * �������ּ�¼
	 * @param detail
	 * @return
	 */
	Long insertDetail(DcUserIntegralDetailDO detail) ;
	
	/**
	 * �������ּ�¼
	 * @param detail
	 * @return
	 */
	Integer batchInsertDetail(List<DcUserIntegralDetailDO> details);
	
	/**
	 * ��ȡ�û����µĻ����б�.����ֵ userId��null: Ӧ����Ϣ, 1lӦ��֪ͨ;
	 * @param userId 
	 * @param start
	 * @param size
	 * @return
	 */
	List<DcUserIntegralDetailDO> getUserIntegralDetails(Long userId, int start, int size);
	/**
	 * 
	 * @param source
	 * @param start
	 * @param size
	 * @return
	 */
	List<DcUserIntegralDetailDO> getLatestIntegralDetails(String source, int start, int size);
	/**
	 * 
	 * @param condition
	 * @param start
	 * @param size
	 * @return
	 */
	List<DcUserIntegralDetailDO> getIntegralDetails(DcIntegralCondition condition, int start, int size);
	/**
	 * 
	 * @param condition
	 * @return
	 */
	Integer countIntegralDetails(DcIntegralCondition condition);
	/**
	 * 
	 * @param auctionId
	 * @param size
	 * @return
	 */
	List<DcUserIntegralDetailDO> getAuctionList(Long auctionId, int size);
	/**
	 * 
	 * @param id
	 * @param readStatus
	 * @return
	 */
	int updateReadStatus(Long id, String readStatus);
}
