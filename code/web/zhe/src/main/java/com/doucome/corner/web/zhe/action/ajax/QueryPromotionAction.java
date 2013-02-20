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
import com.doucome.corner.biz.zhe.utils.DdzJfbConvertUtils;
import com.doucome.corner.web.common.action.BasicAction;
import com.doucome.corner.web.common.model.JsonModel;
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
			json.setDetail("dcome.promotion.query.commissionRate.require") ;
			return SUCCESS ;
		}
		
		PromotionPriceModel model = new PromotionPriceModel() ;
		
		try {
		
		
			Long itemId = Long.valueOf(id) ;
			
			TaobaoPromotionDisplayDTO promotionDTO = taobaoRecommandDecorator.getPromotionUmp(itemId, null) ;
			
			//û�л�۸�
			if(promotionDTO == null || promotionDTO.getPromotionInItem() == null){
				model.setHasPromotion(false) ;
			}else{
				//��۸�
				TaobaoPromotionInItemDTO item = promotionDTO.getPromotionInItem() ;
				BigDecimal promotionPrice = item.getItemPromoPrice() ;
				if(promotionPrice == null){
					model.setHasPromotion(false) ;
				}else{
					BigDecimal userCommission = DecimalUtils.multiply(userCommissionRate, promotionPrice).divide(DecimalConstant.HUNDRED) ;
					model.setHasPromotion(true) ;
					model.setPromotionPrice(promotionPrice);
					model.setUserCommissionRate(userCommissionRate);
					model.setUserJfbRate(userCommissionRate) ;
					model.setUserCommission(userCommission) ;
					model.setUserJfb(DdzJfbConvertUtils.calcJfbAmount(promotionPrice, userCommissionRate.divide(DecimalConstant.HUNDRED, 4, BigDecimal.ROUND_HALF_EVEN))) ;
					
				}
			}
			
		}catch(Exception e){
			
			json.setCode(JsonModel.CODE_FAIL) ;
			json.setDetail("exception occored .") ;
			
			log.error("��ѯ�����۸����" + e.getMessage()) ;
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
