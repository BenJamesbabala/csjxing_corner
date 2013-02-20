package com.doucome.corner.web.bops.action.dcome.prom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.business.DcItemBO;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.model.DcPromotionAwardDTO;
import com.doucome.corner.biz.dcome.service.DcPromotionAwardService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.common.model.JsonModel;

/**
 * »î¶¯detail.
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class PromotionAwardAjaxAction extends BopsBasicAction {
	
	private Long promotionId;
	
	private JsonModel<List<DcPromotionAwardDTO>> promAwards = new JsonModel<List<DcPromotionAwardDTO>>();
	
	@Autowired
	private DcItemBO dcItemBO ;
	
	@Autowired
	private DcPromotionAwardService dcPromotionAwardService;
	
	private static final Log logger = LogFactory.getLog(PromotionAwardAjaxAction.class);
	
	public String execute() {
		try {
			List<DcPromotionAwardDTO> awards = dcPromotionAwardService.getAwardsByPromId(promotionId);
			
			if(CollectionUtils.isNotEmpty(awards)){
			
				List<Long> idList = getItemIds(awards) ;
				
				Map<Long,DcItemDTO> itemMap = dcItemBO.getItemsMapByIds(idList) ;
			
				for(DcPromotionAwardDTO award : awards){
					Long itemId = award.getItemId() ;
					DcItemDTO item = itemMap.get(itemId) ;
					if (item == null) {
						item = new DcItemDTO();
					}
					award.setDcItemDTO(item) ;
				}
			}
			
			promAwards.setSuccess(JsonModel.CODE_SUCCESS, awards);
		} catch (Exception e) {
			logger.error(e);
			promAwards.setFail(JsonModel.CODE_FAIL, "internal.error");
		}
		return SUCCESS;
	}
	
	private List<Long> getItemIds(List<DcPromotionAwardDTO> awards){
		List<Long> idList = new ArrayList<Long>() ;
		
		if(CollectionUtils.isEmpty(awards)){
			return idList ;
		}
		
		for(DcPromotionAwardDTO dto : awards){
			idList.add(dto.getItemId()) ;
		}
		return idList ;
	}
	
	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}
	
	public JsonModel<List<DcPromotionAwardDTO>> getPromAwards() {
		return this.promAwards;
	}
}
