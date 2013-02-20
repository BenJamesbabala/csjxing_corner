package com.doucome.corner.biz.dcome.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionAwardCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionAwardDO;
import com.doucome.corner.biz.dal.dcome.DcPromotionAwardDAO;
import com.doucome.corner.biz.dcome.enums.DcPromotionAwardReviewStatusEnums;
import com.doucome.corner.biz.dcome.enums.DcPromotionAwardSendStatusEnums;
import com.doucome.corner.biz.dcome.enums.DcShareStatusEnum;
import com.doucome.corner.biz.dcome.model.DcPromotionAwardDTO;
import com.doucome.corner.biz.dcome.service.DcPromotionAwardService;

public class DcPromotionAwardServiceImpl implements DcPromotionAwardService {

	@Autowired
	private DcPromotionAwardDAO dcPromotionAwardDAO ;
	
	@Override
	public long createAward(DcPromotionAwardDO delivery) {
		return dcPromotionAwardDAO.insertAward(delivery);
	}

	@Override
	public List<DcPromotionAwardDTO> getAwardByUserId(long userId) {
		List<DcPromotionAwardDTO> dtoList = new ArrayList<DcPromotionAwardDTO>() ;
		List<DcPromotionAwardDO> awards = dcPromotionAwardDAO.queryAwardByUserId(userId) ;
		if(CollectionUtils.isEmpty(awards)){
			return dtoList;
		}
		for(DcPromotionAwardDO promotionAward : awards){
			dtoList.add(new DcPromotionAwardDTO(promotionAward)) ; 
		}
		return dtoList;
	}
	
	@Override
	public List<DcPromotionAwardDTO> getAwardsByPromId(Long promotionId) {
		List<DcPromotionAwardDTO> dtoList = new ArrayList<DcPromotionAwardDTO>() ;
		if (!IDUtils.isCorrect(promotionId)) {
			return dtoList ;
		}
		List<DcPromotionAwardDO> list = dcPromotionAwardDAO.getAwardsByPromId(promotionId);
		if(CollectionUtils.isEmpty(list)){
			return dtoList ;
		}
		for(DcPromotionAwardDO promotionAward : list){
			dtoList.add(new DcPromotionAwardDTO(promotionAward)) ; 
		}
		return dtoList ;
	}
	
	@Override
	public List<DcPromotionAwardDTO> getAwardsNoPagination(DcPromotionAwardCondition searchCondition, Pagination pagination) {
		List<DcPromotionAwardDTO> dtoList = new ArrayList<DcPromotionAwardDTO>() ;
		List<DcPromotionAwardDO> list = dcPromotionAwardDAO.queryAwardsWithPagination(searchCondition, pagination.getStart(), pagination.getSize()) ;
		if(CollectionUtils.isEmpty(list)){
			return dtoList ;
		}
		for(DcPromotionAwardDO promotionAward : list){
			dtoList.add(new DcPromotionAwardDTO(promotionAward)) ; 
		}
		return dtoList ;
	}
	
	@Override
	public QueryResult<DcPromotionAwardDTO> getAwardsWithPagination(
			DcPromotionAwardCondition searchCondition, Pagination pagination) {
		
		int totalRecords = dcPromotionAwardDAO.countAwardsWithPagination(searchCondition) ;
        if (totalRecords <= 0) {
            return new QueryResult<DcPromotionAwardDTO>(new ArrayList<DcPromotionAwardDTO>(), pagination, totalRecords);
        }
		
		List<DcPromotionAwardDTO> dtoList = new ArrayList<DcPromotionAwardDTO>() ;
		List<DcPromotionAwardDO> list = dcPromotionAwardDAO.queryAwardsWithPagination(searchCondition, pagination.getStart(), pagination.getSize()) ;
		if(CollectionUtils.isNotEmpty(list)) {
			for(DcPromotionAwardDO promotionAward : list){
				dtoList.add(new DcPromotionAwardDTO(promotionAward)) ; 
			}
		}
		return new QueryResult<DcPromotionAwardDTO>(dtoList, pagination, totalRecords);
	}

	@Override
	public int updateReviewStatusById(Long awardId, DcPromotionAwardReviewStatusEnums status) {
		return dcPromotionAwardDAO.updateReviewStatusById(awardId, status.getStatus()) ;
	}
	
	@Override
	public int updateSendStatusById(Long awardId,DcPromotionAwardSendStatusEnums status) {
		return dcPromotionAwardDAO.updateSendStatusById(awardId, status.getStatus()) ;
	}

	@Override
	public DcPromotionAwardDTO getAwardById(long awardId) {
		DcPromotionAwardDO promotionAward = dcPromotionAwardDAO.queryAwardById(awardId) ;
		if(promotionAward == null){
			return null ;
		}
		return new DcPromotionAwardDTO(promotionAward) ;
	}
	
	@Override
	public int updateShareStatus(Long awardId, Long userId, DcShareStatusEnum shareStatus) {
		if (IDUtils.isNotCorrect(awardId) || shareStatus == null) {
			return 0;
		}
		return dcPromotionAwardDAO.updateShareStatus(awardId, userId, shareStatus.getValue());
	}

	@Override
	public int updateAwardAddrInfo(DcPromotionAwardDTO awardDTO) {
		DcPromotionAwardDO awardDO = new DcPromotionAwardDO();
		awardDO.setId(awardDTO.getId());
		awardDO.setUserId(awardDTO.getUserId());
		awardDO.setItemSize(awardDTO.getItemSize());
		awardDO.setItemColor(awardDTO.getItemColor());
		awardDO.setItemType(awardDTO.getItemType());
		awardDO.setDelName(awardDTO.getDelName());
		awardDO.setDelMobile(awardDTO.getDelMobile());
		awardDO.setDelAddr(awardDTO.getDelAddr());
		awardDO.setDelOther(awardDTO.getDelOther());
		awardDO.setDelZipcode(awardDTO.getDelZipcode());
		return dcPromotionAwardDAO.updateAwardAddrInfo(awardDO);
	}
}
