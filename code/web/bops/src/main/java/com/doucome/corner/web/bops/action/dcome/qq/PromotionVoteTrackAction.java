package com.doucome.corner.web.bops.action.dcome.qq;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionItemSearchCondition;
import com.doucome.corner.biz.dcome.business.DcPromotionBO;
import com.doucome.corner.biz.dcome.model.DcPromotionDTO;
import com.doucome.corner.biz.dcome.model.DcPromotionItemDTO;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.service.DcPromotionItemService;
import com.doucome.corner.biz.dcome.service.DcPromotionService;
import com.doucome.corner.biz.dcome.service.DcUserFilterService;
import com.doucome.corner.biz.dcome.service.DcUserService;
import com.doucome.corner.web.bops.action.BopsBasicAction;

@SuppressWarnings("serial")
public class PromotionVoteTrackAction extends BopsBasicAction{

	@Autowired
	private DcPromotionItemService dcPromotionItemService ;
	
	@Autowired
	private DcPromotionBO dcPromotionBO ;
	
	@Autowired
	private DcPromotionService dcPromotionService ;
	
	@Autowired
	private DcUserFilterService dcUserFilterService ;
	
	@Autowired
	private DcUserService dcUserService ;
	
	private Long userId ;
	
	private DcPromotionDTO curPromotion ;
	
	private List<DcPromotionItemDTO> topRankList ;
	
	private DcPromotionItemDTO userPromotionItem ;
	
	private List<Long> trackUserList ;
	
	private DcUserDTO user ;
	
	@Override
	public String execute() throws Exception {
		curPromotion = dcPromotionService.getCurPromotion() ;
		if(curPromotion != null) {
			Long promotionId = curPromotion.getId() ;
			//ÅÅÐÐ°ñ
			topRankList = dcPromotionBO.getPromotionTopRanks(promotionId, 20);
			fillUsersIntegral(topRankList);
			//°×Ãûµ¥User
			trackUserList = dcUserFilterService.getWhiteListUser() ;
			
			if(IDUtils.isCorrect(userId)) {
				userPromotionItem = dcPromotionItemService.getUsersPromItems(userId	, promotionId) ;
				user = dcUserService.getUser(userId) ;
			}
			
		}
		return SUCCESS ;
	}
	
	private void fillUsersIntegral(List<DcPromotionItemDTO> promItems) {
		List<Long> userIds = new ArrayList<Long>();
		for (DcPromotionItemDTO promItem: promItems) {
			userIds.add(promItem.getUserId());
		}
		List<DcUserDTO> users = dcUserService.queryUsers(userIds);
		for (DcPromotionItemDTO promItem: promItems) {
			for (DcUserDTO user: users) {
				if (user.getUserId().equals(promItem.getUserId())) {
					promItem.setUserIntegral(user.getIntegralCount());
					break;
				}
			}
		}
	}

	public DcPromotionDTO getCurPromotion() {
		return curPromotion;
	}

	public List<DcPromotionItemDTO> getTopRankList() {
		return topRankList;
	}

	public Long getUserId() {
		return userId;
	}

	public List<Long> getTrackUserList() {
		return trackUserList;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public DcPromotionItemDTO getUserPromotionItem() {
		return userPromotionItem;
	}

	public DcUserDTO getUser() {
		return user;
	}
	
}
