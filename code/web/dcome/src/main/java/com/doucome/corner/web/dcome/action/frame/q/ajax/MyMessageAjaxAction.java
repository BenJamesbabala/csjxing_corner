package com.doucome.corner.web.dcome.action.frame.q.ajax;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionAwardCondition;
import com.doucome.corner.biz.dcome.business.DcItemBO;
import com.doucome.corner.biz.dcome.enums.DcPromotionAwardReviewStatusEnums;
import com.doucome.corner.biz.dcome.enums.DcShareStatusEnum;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.model.DcPromotionAwardDTO;
import com.doucome.corner.biz.dcome.service.DcPromotionAwardService;
import com.doucome.corner.biz.dcome.service.DcUserService;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.DComeBasicAction;

/**
 * 我的消息
 * @author langben 2012-9-8
 *
 */
@SuppressWarnings("serial")
public class MyMessageAjaxAction  extends DComeBasicAction {
	
	private static final Log log = LogFactory.getLog(MyMessageAjaxAction.class) ;
	
	/**
	 * 清空未读消息
	 */
	private String clear ;
	
	/**
	 * 消息
	 */
	private JsonModel<List<DcPromotionAwardDTO>> json = new JsonModel<List<DcPromotionAwardDTO>>();
	
	@Autowired
	private DcPromotionAwardService dcPromotionAwardService ;
	
	@Autowired
	private DcUserService dcUserService ;
	
	@Autowired
	private DcItemBO dcItemBO ;
	
	/**
	 * 现在没有消息表，直接读取中奖信息
	 */
	@Override
	public String execute() throws Exception {
		long userId = getUserId() ;
		
		try {
		
			if(StringUtils.isNotBlank(clear)){
				dcUserService.clearUnreadMsgCountByUser(userId) ;
			}
			
			DcPromotionAwardCondition searchCondition = new DcPromotionAwardCondition() ;
			searchCondition.setReviewStatus(DcPromotionAwardReviewStatusEnums.SUCCESS.getStatus()) ;
			searchCondition.setUserId(userId) ;
			List<DcPromotionAwardDTO> messageList = dcPromotionAwardService.getAwardsNoPagination(searchCondition , new Pagination(1)) ;
			if(CollectionUtils.isNotEmpty(messageList)){
				for (DcPromotionAwardDTO promAward: messageList) {
					if (!DcShareStatusEnum.YES.getValue().equals(promAward.getShareStatus())) {
						promAward.setCheckCode("");
					}
				}
				List<Long> idList = getItemIds(messageList) ;
				Map<Long,DcItemDTO> itemsMap = dcItemBO.getItemsMapByIds(idList) ;
				if(MapUtils.isNotEmpty(itemsMap)){
					for(DcPromotionAwardDTO award : messageList){
						award.setDcItemDTO(itemsMap.get(award.getItemId())) ;
					}
				}
			}
			json.setCode(JsonModel.CODE_SUCCESS) ;
			json.setData(messageList) ;
		} catch(Exception e){
			log.error(e.getMessage() , e) ;
			json.setCode(JsonModel.CODE_FAIL) ;
			json.setDetail("dcome.error") ;
			return SUCCESS ;
		}
		return SUCCESS ;
	}

	private List<Long> getItemIds(List<DcPromotionAwardDTO> list){
		List<Long> idList = new ArrayList<Long>();
		if(CollectionUtils.isEmpty(list)){
			return idList ;
		}
		for(DcPromotionAwardDTO award : list){
			idList.add(award.getItemId()) ;
		}
		return idList ;
	}
	

	public JsonModel<List<DcPromotionAwardDTO>> getJson() {
		return json;
	}



	public void setClear(String clear) {
		this.clear = clear;
	}
	
	
}
