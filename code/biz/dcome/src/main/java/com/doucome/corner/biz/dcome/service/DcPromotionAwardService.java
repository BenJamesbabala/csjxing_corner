package com.doucome.corner.biz.dcome.service;

import java.util.List;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionAwardCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionAwardDO;
import com.doucome.corner.biz.dcome.enums.DcPromotionAwardReviewStatusEnums;
import com.doucome.corner.biz.dcome.enums.DcPromotionAwardSendStatusEnums;
import com.doucome.corner.biz.dcome.enums.DcShareStatusEnum;
import com.doucome.corner.biz.dcome.model.DcPromotionAwardDTO;

/**
 * �û��ջ���Ϣ
 * @author langben 2012-8-10
 *
 */
public interface DcPromotionAwardService {
	
	/**
	 * �½��ջ���Ϣ
	 * @param delivery
	 * @return
	 */
	long createAward(DcPromotionAwardDO award);
	
	/**
	 * ����ID��ѯ
	 * @param awardId
	 * @return
	 */
	DcPromotionAwardDTO getAwardById(long awardId) ;
	
	/**
	 * ��ȡ�û����н����.
	 * 
	 * @param userId
	 * @return
	 */
	List<DcPromotionAwardDTO> getAwardByUserId(long userId) ;
	/**
	 * ��ȡ��Ŀ������.
	 * @param promotionId �id.
	 * @return
	 */
	List<DcPromotionAwardDTO> getAwardsByPromId(Long promotionId);
	/**
	 * 
	 * @param searchCondition
	 * @param start
	 * @param size
	 * @return
	 */
	List<DcPromotionAwardDTO> getAwardsNoPagination(DcPromotionAwardCondition condition , Pagination pagination ) ;
	
	/**
	 * ��ȡ�н���¼
	 * @param searchCondition
	 * @param pagination
	 * @return
	 */
	QueryResult<DcPromotionAwardDTO> getAwardsWithPagination(DcPromotionAwardCondition searchCondition , Pagination pagination ) ;
	
	/**
	 * ����ID����reviewStatus
	 * @param promotionId
	 * @param status
	 * @return
	 */
	int updateReviewStatusById(Long awardId, DcPromotionAwardReviewStatusEnums status) ;
	
	/**
	 * ����ID����sendStatus
	 * @param promotionId
	 * @param status
	 * @return
	 */
	int updateSendStatusById(Long awardId, DcPromotionAwardSendStatusEnums status) ;
	
	/**
	 * �����콱����״̬.
	 * @param awardId
	 * @param userId
	 * @param shareStatus
	 * @return
	 */
	int updateShareStatus(Long awardId, Long userId, DcShareStatusEnum shareStatus);
	/**
	 * �����콱����Ϣ.
	 * @param awardDTO
	 * @return
	 */
	int updateAwardAddrInfo(DcPromotionAwardDTO awardDTO);
}
