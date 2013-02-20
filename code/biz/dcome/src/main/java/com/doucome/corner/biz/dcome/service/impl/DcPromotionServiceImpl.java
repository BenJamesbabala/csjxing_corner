package com.doucome.corner.biz.dcome.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionCondition;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionDO;
import com.doucome.corner.biz.dal.dcome.DcPromotionDAO;
import com.doucome.corner.biz.dcome.cache.DcPromotionCache;
import com.doucome.corner.biz.dcome.model.DcPromotionDTO;
import com.doucome.corner.biz.dcome.service.DcPromotionService;

public class DcPromotionServiceImpl implements DcPromotionService {

	@Autowired
	private DcPromotionDAO dcPromotionDAO ;
	
	@Autowired
	private DcPromotionCache dcPromotionCache ;
	
	@Override
	public Long createPromotion(DcPromotionDO promotion) {
		return dcPromotionDAO.insertPromotion(promotion) ;
	}
	
	@Override
	public DcPromotionDTO getCurPromotion() {
		DcPromotionDTO currentPromotion = dcPromotionCache.getCurrentPromotion() ;
		if(currentPromotion != null){
			return currentPromotion ;
		}
		DcPromotionSearchCondition condition = new DcPromotionSearchCondition();
		condition.setSysTime(new Date());
		List<DcPromotionDTO> promItems = getPromotionsNoPagination(condition, new Pagination(1, 1));
		if (CollectionUtils.isEmpty(promItems)) {
			return null ;
		}
		currentPromotion = promItems.iterator().next() ;
		dcPromotionCache.setCurrentPromotion(currentPromotion) ;
		return currentPromotion ;
	}
	
	@Override
	public DcPromotionDTO getPromotionById(Long promotionId) {
		
		DcPromotionDTO dto = dcPromotionCache.get(promotionId) ;
		if(dto == null){
			DcPromotionDO promotion = dcPromotionDAO.queryPromotionById(promotionId) ;
			if(promotion == null){
				return null ;
			}
			dto = new DcPromotionDTO(promotion) ;
			dcPromotionCache.set(dto) ;
		}
		return dto ;
	}
	
	@Override
	public DcPromotionDTO getPromotionByDate(Date date) {
		DcPromotionDO promotion = dcPromotionDAO.queryPromotionByDate(date);
		if (promotion == null) {
			return null;
		}
		return new DcPromotionDTO(promotion);
	}
	
	@Override
	public QueryResult<DcPromotionDTO> getPromotionsWithPage(DcPromotionCondition condition, int pageNum) {
		Pagination pagination = new Pagination(pageNum);
		int count = dcPromotionDAO.countPromotions(condition);
		if (count == 0) {
			return new QueryResult<DcPromotionDTO>(new ArrayList<DcPromotionDTO>(), pagination, count);
		}
		List<DcPromotionDO> promotions =
			dcPromotionDAO.getPromotionsWithPage(condition, pagination.getStart() - 1, pagination.getSize());
		List<DcPromotionDTO> result = new ArrayList<DcPromotionDTO>();
		for (DcPromotionDO temp: promotions) {
			result.add(new DcPromotionDTO(temp));
		}
		return new QueryResult<DcPromotionDTO>(result, pagination, count);
	}
	
	@Override
	public List<DcPromotionDTO> getPromotionsNoPagination(
			DcPromotionSearchCondition searchCondition, Pagination pagination) {
		List<DcPromotionDO> doList = dcPromotionDAO.queryPromotionsWithPagination(searchCondition, pagination.getStart()	, pagination.getSize()) ;
		List<DcPromotionDTO> dtoList = new ArrayList<DcPromotionDTO>() ;
		if(CollectionUtils.isNotEmpty(doList)){
			for(DcPromotionDO pro : doList) {
				dtoList.add(new DcPromotionDTO(pro)) ;
			}
		}
		return dtoList ;
	}

    @Override
    public int updatePromotion(DcPromotionDTO promotion) {
        if (promotion == null || promotion.getPromotion() == null || promotion.getPromotion().getId() == null) {
            return 0;
        }
        int result = dcPromotionDAO.updatePromotion(promotion.getPromotion());
        dcPromotionCache.remove(promotion.getId());
        return result;
    }
	
}
