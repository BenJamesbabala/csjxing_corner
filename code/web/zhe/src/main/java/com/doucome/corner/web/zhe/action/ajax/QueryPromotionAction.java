package com.doucome.corner.web.zhe.action.ajax;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.constant.DecimalConstant;
import com.doucome.corner.biz.core.service.taobao.TaobaoRecommandDecorator;
import com.doucome.corner.biz.core.taobao.dto.TaobaoPromotionDisplayDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaoPromotionInItemDTO;
import com.doucome.corner.biz.core.utils.DecimalUtils;
import com.doucome.corner.web.common.action.BasicAction;
import com.doucome.corner.web.zhe.model.JsonModel;
import com.doucome.corner.web.zhe.model.PromotionPriceModel;

@SuppressWarnings("serial")
public class QueryPromotionAction extends BasicAction {
	
	private static final Log log = LogFactory.getLog(QueryPromotionAction.class) ;

	@Autowired
	private TaobaoRecommandDecorator taobaoRecommandDecorator ; 
	
	private String id ;
	
	private BigDecimal userCommissionRate ;
	
	private JsonModel<PromotionPriceModel> json = new JsonModel<PromotionPriceModel>() ;
	
	@Override
	public String execute() throws Exception {
		
		if(!StringUtils.isNumeric(id) || userCommissionRate == null){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("id and commissionRate must set .") ;
			return SUCCESS ;
		}
		
		PromotionPriceModel model = new PromotionPriceModel() ;
		
		try {
		
		
			Long itemId = Long.valueOf(id) ;
			
			TaobaoPromotionDisplayDTO promotionDTO = taobaoRecommandDecorator.getPromotionUmp(itemId, null) ;
			
			//没有活动价格
			if(promotionDTO == null || promotionDTO.getPromotionInItem() == null){
				model.setHasPromotion(false) ;
			}else{
				//活动价格
				TaobaoPromotionInItemDTO item = promotionDTO.getPromotionInItem() ;
				BigDecimal promotionPrice = item.getItemPromoPrice() ;
				if(promotionPrice == null){
					model.setHasPromotion(false) ;
				}else{
					BigDecimal userCommission = DecimalUtils.multiply(userCommissionRate, promotionPrice).divide(DecimalConstant.HUNDRED) ;
					model.setHasPromotion(true) ;
					model.setPromotionPrice(promotionPrice);
					model.setUserCommissionRate(userCommissionRate);
					model.setUserCommission(userCommission) ;
				}
			}
			
		}catch(Exception e){
			
			json.setCode(JsonModel.CODE_FAIL) ;
			json.setDetail("exception occored .") ;
			
			log.error(e.getMessage() , e) ;
			return SUCCESS ;
		}
		
		json.setCode(JsonModel.CODE_SUCCESS) ;
		json.setData(model) ;
		
		return SUCCESS ;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUserCommissionRate(BigDecimal userCommissionRate) {
		this.userCommissionRate = userCommissionRate;
	}

	public JsonModel<PromotionPriceModel> getJson() {
		return json;
	}
	
	
	
}
