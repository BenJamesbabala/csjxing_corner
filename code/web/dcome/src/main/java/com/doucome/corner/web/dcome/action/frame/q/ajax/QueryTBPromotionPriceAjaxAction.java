package com.doucome.corner.web.dcome.action.frame.q.ajax;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.constant.DecimalConstant;
import com.doucome.corner.biz.core.service.taobao.TaobaoRecommandDecorator;
import com.doucome.corner.biz.core.taobao.config.SettleConfigMgr;
import com.doucome.corner.biz.core.taobao.dto.TaobaoPromotionDisplayDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaoPromotionInItemDTO;
import com.doucome.corner.biz.core.utils.DecimalUtils;
import com.doucome.corner.biz.dcome.utils.DcTbCommissionUtils;
import com.doucome.corner.biz.dcome.utils.DcTbCommissionUtils.InternalCommissions;
import com.doucome.corner.biz.zhe.utils.DdzJfbConvertUtils;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.DComeBasicAction;
import com.doucome.corner.web.dcome.model.PromotionPriceModel;

/**
 * 查询淘宝促销价格
 * @author langben 2013-1-22
 *
 */
@SuppressWarnings("serial")
public class QueryTBPromotionPriceAjaxAction extends DComeBasicAction {

	private static final Log log = LogFactory.getLog(QueryTBPromotionPriceAjaxAction.class) ;

	
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
					BigDecimal userCommission = DecimalUtils.multiply(userCommissionRate, promotionPrice) ;
					model.setHasPromotion(true) ;
					model.setPromotionPrice(promotionPrice);
					model.setUserCommissionRate(userCommissionRate);
					
					model.setUserCommission(userCommission) ;
					model.setUserJfb(userCommission.multiply(DecimalConstant.HUNDRED).intValue()) ;
					model.setUserJfbRate(userCommissionRate) ;
					
				}
			}
			
		}catch(Exception e){
			
			json.setCode(JsonModel.CODE_FAIL) ;
			json.setDetail("exception occored .") ;
			
			log.error("Query taobao promotion error ：" + e.getMessage()) ;
			return SUCCESS ;
		}
		
		json.setCode(JsonModel.CODE_SUCCESS) ;
		json.setData(model) ;
		
		return SUCCESS ;
	}

	public JsonModel<PromotionPriceModel> getJson() {
		return json;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUserCommissionRate(BigDecimal userCommissionRate) {
		this.userCommissionRate = userCommissionRate;
	}
	
}
