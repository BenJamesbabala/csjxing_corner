package com.doucome.corner.biz.dcome.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionRateDetailCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionRateDetailDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcRobOtherActivityDO;
import com.doucome.corner.biz.dal.dcome.DcPromotionRateDetailDAO;
import com.doucome.corner.biz.dcome.exception.DuplicateOperateException;
import com.doucome.corner.biz.dcome.service.DcPromotionRateDetailService;

public class DcPromotionRateDetailServiceImpl implements DcPromotionRateDetailService {

	@Autowired
	private DcPromotionRateDetailDAO dcPromotionRateDetailDAO ;
	
	@Override
	public Long createRateDetail(DcPromotionRateDetailDO rateDetail) throws DuplicateOperateException {
		if(rateDetail == null ||
				IDUtils.isNotCorrect(rateDetail.getRateUserId())){
			throw new IllegalArgumentException("rateDetail reguired params error .");
		}
		try {
			return dcPromotionRateDetailDAO.insertRateDetail(rateDetail) ;
		}catch(DuplicateKeyException e){
			throw new DuplicateOperateException(e.getMessage()) ;
		}
	}

	@Override
	public DcPromotionRateDetailDO getRateDetailByPromItemAndRateUser(Long promotionItemId,
			Long rateUserId) {
		return dcPromotionRateDetailDAO.queryRateDetailByPromItemAndRateUser(promotionItemId, rateUserId) ;
	}

	@Override
	public QueryResult<DcPromotionRateDetailDO> getRateDetailsWithPagination(DcPromotionRateDetailCondition searchCondition,
			Pagination pagination) {
		
		int totalRecords = dcPromotionRateDetailDAO.countRateDetailsWithPagination(searchCondition) ;
		
        if (totalRecords <= 0) {
            return new QueryResult<DcPromotionRateDetailDO>(new ArrayList<DcPromotionRateDetailDO>(), pagination, totalRecords);
        }
        
        List<DcPromotionRateDetailDO> rateDetailList = dcPromotionRateDetailDAO.queryRateDetailsWithPagination(searchCondition, pagination.getStart(), pagination.getSize());
                
        return new QueryResult<DcPromotionRateDetailDO>(rateDetailList, pagination, totalRecords);
	}

	@Override
	public List<DcRobOtherActivityDO> getUserRobActivities(Long promotionItemId) {
		if (IDUtils.isCorrect(promotionItemId)) {
			return dcPromotionRateDetailDAO.getUserRobActivities(promotionItemId);
		}
		return null;
	}
	
	@Override
	public List<DcRobOtherActivityDO> getPromRobActivities(Long promotionId) {
		if (IDUtils.isCorrect(promotionId)) {
			return dcPromotionRateDetailDAO.getPromRobActivities(promotionId);
		}
		return null;
	}
}
