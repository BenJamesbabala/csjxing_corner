package com.doucome.corner.web.zhe.action.ajax;

import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.core.model.URLModel;
import com.doucome.corner.biz.core.service.taobao.TaobaokeServiceDecorator;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.core.taobao.fields.TaobaokeFields;
import com.doucome.corner.biz.core.utils.HttpUrlHelper;
import com.doucome.corner.biz.zhe.condition.EatDiscountCondition;
import com.doucome.corner.biz.zhe.model.TaobaokeItemFacade;
import com.doucome.corner.biz.zhe.service.DdzEatDiscountService;
import com.doucome.corner.web.common.action.BasicAction;
import com.doucome.corner.web.zhe.model.JsonModel;
import com.doucome.corner.web.zhe.utils.TaobaoUrlUtils;

/**
 * 转换商品接口
 * @author shenjia.caosj 2012-3-8
 *
 */
@SuppressWarnings("serial")
public class ConventItemAction extends BasicAction{
	
	private static final String CODE_NO_DISCOUNT = "no_dis";
	
	private static final String CODE_ERROR_URL = "err_url";

	private JsonModel<TaobaokeItemFacade> json = new JsonModel<TaobaokeItemFacade>() ;
	
	private String wd ;
			
	private TaobaokeServiceDecorator taobaokeServiceDecorator ;
	
	private DdzEatDiscountService ddzEatDiscountService ;
	
	@Override
	public String execute() throws Exception {
		if(StringUtils.isNotBlank(wd)){
			URLModel model = HttpUrlHelper.parseURL(wd) ;
			if(model != null){
				String itemId = TaobaoUrlUtils.parseItemId(model);
				TaobaokeItemDTO dto = taobaokeServiceDecorator.conventItem(itemId, null ,TaobaokeFields.taoke_item_fields ) ;
				EatDiscountCondition condition = new EatDiscountCondition()  ;
	            condition.setNeedPromotionPrice(true) ;
				TaobaokeItemFacade item = ddzEatDiscountService.eatDiscount(dto,condition) ;
				json.setData(item);
				if(item == null){
					json.setCode(CODE_NO_DISCOUNT) ;
				}
			}else{
				//不是URL
				json.setCode(CODE_ERROR_URL) ;
			}
		}else{
			//输入空
			json.setCode(CODE_ERROR_URL) ;
		}
				
		return SUCCESS ;
	}

	
	/**
	 * ---------------------------------------------------------------
	 * @return
	 */
	public JsonModel<TaobaokeItemFacade> getJson() {
		return json;
	}

	public void setWd(String wd) {
		this.wd = wd;
	}

	public void setTaobaokeServiceDecorator(
			TaobaokeServiceDecorator taobaokeServiceDecorator) {
		this.taobaokeServiceDecorator = taobaokeServiceDecorator;
	}

	public void setDdzEatDiscountService(DdzEatDiscountService ddzEatDiscountService) {
		this.ddzEatDiscountService = ddzEatDiscountService;
	}
	
	
}
