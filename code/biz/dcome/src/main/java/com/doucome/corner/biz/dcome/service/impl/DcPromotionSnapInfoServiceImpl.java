package com.doucome.corner.biz.dcome.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionSnapInfoDO;
import com.doucome.corner.biz.dal.dcome.DcPromotionSnapInfoDAO;
import com.doucome.corner.biz.dcome.cache.DcPromotionSnapInfoCache;
import com.doucome.corner.biz.dcome.model.DcPromotionSnapInfoDTO;
import com.doucome.corner.biz.dcome.service.DcPromotionSnapInfoService;

public class DcPromotionSnapInfoServiceImpl implements DcPromotionSnapInfoService {
	@Autowired
	private DcPromotionSnapInfoDAO dcPromotionSnapInfoDAO ;
	@Autowired
	private DcPromotionSnapInfoCache dcPromotionSnapInfoCache ;
	
	private static final Pagination defaultPagination = new Pagination(1 , 20) ;
	
	@Override
	public long createPromotionSnap(DcPromotionSnapInfoDO snapInfo) {
		return dcPromotionSnapInfoDAO.insertPromotionSnap(snapInfo) ;
	}
	
	@Override
	public List<DcPromotionSnapInfoDTO> getPromotionSnapInfosDesc(Long promotionId) {
		List<DcPromotionSnapInfoDTO> dtoList = dcPromotionSnapInfoCache.getCache(promotionId, defaultPagination.getStart(), defaultPagination.getSize());
		if (CollectionUtils.isEmpty(dtoList)) {
			dtoList = getPromotionSnapsFromDb(promotionId, defaultPagination);
			dcPromotionSnapInfoCache.setCache(promotionId, defaultPagination.getStart(), defaultPagination.getSize(), dtoList);
		}
		return dtoList;
	}
	
	@Override
	public List<DcPromotionSnapInfoDTO> getPromotionSnapsFromDb(Long promotionId, Pagination page) {
		List<DcPromotionSnapInfoDTO> dtoList = new ArrayList<DcPromotionSnapInfoDTO>() ;
		List<DcPromotionSnapInfoDO> list = dcPromotionSnapInfoDAO.queryPromotionSnapWithPagination(promotionId, page.getStart(), page.getSize() )  ;
		if(CollectionUtils.isNotEmpty(list)){
			for(DcPromotionSnapInfoDO d : list){
				dtoList.add(new DcPromotionSnapInfoDTO(d)) ;
			}
		}
		return dtoList;
	}

	@Override
	public int incrWishCountByPromotionAndItem(Long promotionId, Long itemId) {
		int effectCount = dcPromotionSnapInfoDAO.incrWishCountByPromotionAndItem(promotionId, itemId , 1) ;
		triggerCacheModified(promotionId, defaultPagination.getStart(), defaultPagination.getSize() ) ;
		return effectCount ;
	}

	private void triggerCacheModified(Long promotionId , int start , int size) {
		dcPromotionSnapInfoCache.remove(promotionId, defaultPagination.getStart(), defaultPagination.getSize()) ;
	}
}
