package com.doucome.corner.biz.dcome.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionItemSearchCondition;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionItemSearchCondition.OrderEnum;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionItemDO;
import com.doucome.corner.biz.dal.dcome.DcPromotionItemDAO;
import com.doucome.corner.biz.dcome.cache.DcPromotionItemCache;
import com.doucome.corner.biz.dcome.constant.DcPromotionItemConstant;
import com.doucome.corner.biz.dcome.enums.DcShareStatusEnum;
import com.doucome.corner.biz.dcome.exception.DcDuplicateKeyException;
import com.doucome.corner.biz.dcome.model.DcPromotionItemDTO;
import com.doucome.corner.biz.dcome.model.util.DcPromotionItemUtils;
import com.doucome.corner.biz.dcome.service.DcPromotionItemService;

/**
 * 
 * @author ze2200
 *
 */
public class DcPromotionItemServiceImpl implements DcPromotionItemService {
	
	@Autowired
	private DcPromotionItemCache dcPromotionItemCache ;
	
	@Autowired
	private DcPromotionItemDAO dcPromotionItemDAO;

	@Override
	public Long createPromotionItem(DcPromotionItemDO promotionItem) throws DcDuplicateKeyException {
		if (promotionItem == null) {
			return null;
		}
		try {
			Long id = dcPromotionItemDAO.insertPromotionItem(promotionItem);
			return id;
		} catch (DuplicateKeyException e) {
			throw new DcDuplicateKeyException(e);
		}
	}
	
	@Override
	public DcPromotionItemDTO getPromotionItemById(Long promotionItemId) {
		DcPromotionItemDTO dto = dcPromotionItemCache.get(promotionItemId) ;
		if(dto == null){
			DcPromotionItemDO item = dcPromotionItemDAO.queryPromotionItemById(promotionItemId) ;
			if(item != null){
				dto = new DcPromotionItemDTO(item) ;
				dcPromotionItemCache.set(dto) ;
			}
		}
		return dto ;
	}

	@Override
	public List<DcPromotionItemDTO> getPromotionItemsNoPagination(DcPromotionItemSearchCondition condition, Pagination pagination) {
		return getPromotionItemsNoPagination(condition, pagination.getStart(),  pagination.getSize()) ;
	}
	
	@Override
	public List<DcPromotionItemDTO> getPromotionItemsNoPagination(DcPromotionItemSearchCondition condition, int start, int size) {
		if(start < 1 || size < 0){
			throw new IllegalArgumentException("start or size not right .") ;
		}
		List<DcPromotionItemDTO> dtoList =  new ArrayList<DcPromotionItemDTO>();
		List<DcPromotionItemDO> itemList = dcPromotionItemDAO.queryPromotionItemsWithPagination(condition, start,  size) ;
		if(CollectionUtils.isNotEmpty(itemList)){
			for(DcPromotionItemDO pi : itemList){
				dtoList.add(new DcPromotionItemDTO(pi));
			}
		}
		
		return dtoList ;
	}
	
    @Override
    public List<DcPromotionItemDTO> getPromotionItemsFromDB(DcPromotionItemSearchCondition condition,
                                                            Pagination pagination) {
        List<DcPromotionItemDO> itemList = dcPromotionItemDAO.queryPromotionItemsWithPagination(condition, pagination.getStart(), pagination.getSize());
        List<DcPromotionItemDTO> dtoList = new ArrayList<DcPromotionItemDTO>();
        if (CollectionUtils.isNotEmpty(itemList)) {
            for (DcPromotionItemDO pi : itemList) {
                dtoList.add(new DcPromotionItemDTO(pi));
            }
        }
        return dtoList;
    }

	@Override
	public int incrRateCountById(Long promotionItemId) {
		int effectCount = dcPromotionItemDAO.incrRateCountById(promotionItemId, 1) ;
		triggerCacheModified(promotionItemId) ;
		return effectCount ;
	}

	@Override
	public int decrRateCountById(Long promotionItemId) {
		int effectCount = dcPromotionItemDAO.decrRateCountById(promotionItemId, 1) ;
		triggerCacheModified(promotionItemId) ;
		return effectCount ;
	}

	@Override
	public int incrRateCountByIdAndUser(Long promotionItemId, Long userId) {
		int effectCount = dcPromotionItemDAO.incrRateCountByIdAndUser(promotionItemId, 1 , userId) ;
		triggerCacheModified(promotionItemId) ;
		return effectCount ;
	}
	
	@Override
	public List<DcPromotionItemDTO> getUsersPromItems(Long userId, int pageNum) {
		if (!IDUtils.isCorrect(userId)) {
			return new ArrayList<DcPromotionItemDTO>();
		}
		DcPromotionItemSearchCondition condition = new DcPromotionItemSearchCondition();
		condition.setUserId(userId);
		Pagination pagination = new Pagination(pageNum);
		List<DcPromotionItemDTO> promItemDTOs = getPromotionItemsNoPagination(condition , pagination);
		return promItemDTOs ;
	}
	
	@Override
	public DcPromotionItemDTO getUsersPromItems(Long userId, Long promotionId) {
		if (!IDUtils.isCorrect(userId) || !IDUtils.isCorrect(promotionId)) {
			return null;
		}
		DcPromotionItemSearchCondition condition = new DcPromotionItemSearchCondition();
		condition.setPromotionId(promotionId);
		condition.setUserId(userId);
		List<DcPromotionItemDTO> promItemDTOs = getPromotionItemsNoPagination(condition, new Pagination(1,1));
		if (CollectionUtils.isEmpty(promItemDTOs)) {
			return null;
		} else {
			return promItemDTOs.iterator().next();
		}
	}

	@Override
	public int drawRateByHour(Long promotionItemId, int hour) {
		if(!DcPromotionItemUtils.isDrawHour(hour)) {
			throw new IllegalArgumentException("hour max between [" + DcPromotionItemConstant.MIN_HOUR + "] and [" + DcPromotionItemConstant.MAX_HOUR + "]") ;
		}
		return dcPromotionItemDAO.drawRateCountByHour(promotionItemId, hour) ;
	}

	@Override
	public int decrDrawCountByHourAndId(Long promotionItemId, int hour) {
		if(hour > DcPromotionItemConstant.MAX_HOUR || hour < DcPromotionItemConstant.MIN_HOUR) {
			throw new IllegalArgumentException("hour max between [" + DcPromotionItemConstant.MIN_HOUR + "] and [" + DcPromotionItemConstant.MAX_HOUR + "]") ;
		}
		
		int effectCount = dcPromotionItemDAO.decrDrawCountByHourAndId(promotionItemId, hour, 1) ;
		triggerCacheModified(promotionItemId) ;
		return effectCount ;
	}
	
	@Override
	public Integer markPromItemShared(Long promItemId) {
		if (!IDUtils.isCorrect(promItemId)) {
			return 0;
		}
		int count = dcPromotionItemDAO.updatePromItemShareStatus(promItemId, DcShareStatusEnum.YES.getValue());
		triggerCacheModified(promItemId);
		return count;
	}
	
	@Override
	public int getPromItemRank(Long promotionId, Long promItemId) {
		Map<Long, Integer> ranks = dcPromotionItemCache.getPromRanks(promotionId);
		if (ranks == null) {
			ranks = getTop50Ranks(promotionId);
		}
		Integer rank = ranks.get(promItemId);
		if (rank == null) {
			rank = getRandomRank(50, 100);
		}
		ranks.put(promItemId, rank);
		dcPromotionItemCache.cachePromRanks(promotionId, ranks);
		return rank;
	}
	
	private Map<Long, Integer> getTop50Ranks(Long promotionId) {
		DcPromotionItemSearchCondition condition = new DcPromotionItemSearchCondition();
		condition.setPromotionId(promotionId);
		condition.setOrder(OrderEnum.rate_count);
		Pagination page = new Pagination(1, 50);
		List<DcPromotionItemDTO> promItems = getPromotionItemsFromDB(condition, page);
		Map<Long, Integer> ranks = new HashMap<Long, Integer>();
		if (!CollectionUtils.isEmpty(promItems)) {
			for (int i = 0; i < promItems.size(); i++) {
				ranks.put(promItems.get(i).getId(), i + 1);
			}
		}
		return ranks;
	}
	
	private Integer getRandomRank(int rankBottom, int rankTop) {
		Random rand = new Random(System.currentTimeMillis());
		return rankBottom + rand.nextInt(rankTop - rankBottom);
	}
	
	@Override
	public int updateNewGuide(Long promItemId, Long newGuide) {
		int effectCount = dcPromotionItemDAO.updateNewGuide(promItemId , newGuide) ;
		triggerCacheModified(promItemId) ;
		return effectCount ;
	}
	
	private void triggerCacheModified(Long promotionItemId) {
		dcPromotionItemCache.remove(promotionItemId) ;
	}

	@Override
	public int updateUserNickById(Long promItemId, String userNick) {
		int effectCount = dcPromotionItemDAO.updateUserNickById(promItemId , userNick) ;
		triggerCacheModified(promItemId) ;
		return effectCount ;
	}

	@Override
	public int updateRateCountById(Long promItemId, int rateCount) {
		int effectCount = dcPromotionItemDAO.updateRateCountById(promItemId , rateCount) ;
		triggerCacheModified(promItemId) ;
		return effectCount ;
	}
	
}
