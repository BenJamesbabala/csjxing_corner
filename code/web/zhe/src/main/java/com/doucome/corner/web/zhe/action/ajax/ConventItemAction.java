package com.doucome.corner.web.zhe.action.ajax;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.URLModel;
import com.doucome.corner.biz.core.service.taobao.TaobaokeServiceDecorator;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.core.taobao.fields.TaobaokeFields;
import com.doucome.corner.biz.core.utils.HttpUrlHelper;
import com.doucome.corner.biz.core.utils.ValidateUtil;
import com.doucome.corner.biz.zhe.condition.EatDiscountCondition;
import com.doucome.corner.biz.zhe.enums.OutCodeEnums;
import com.doucome.corner.biz.zhe.model.TaobaokeItemFacade;
import com.doucome.corner.biz.zhe.service.DdzAccountService;
import com.doucome.corner.biz.zhe.service.DdzEatDiscountService;
import com.doucome.corner.biz.zhe.utils.OutCodeUtils;
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

	private JsonModel<TaobaokeItemFacade> json = new JsonModel<TaobaokeItemFacade>() ;
	
	@Autowired
	private TaobaokeServiceDecorator taobaokeServiceDecorator ;
	
	@Autowired
	private DdzEatDiscountService ddzEatDiscountService ;
	
	@Autowired
	private DdzAccountService ddzAccountService ;
	
	private String alipayId ;
	
	private String wd ;
	
	@Override
	public String execute() throws Exception {
		if(StringUtils.isNotBlank(wd)){
			URLModel model = HttpUrlHelper.parseURL(wd) ;
			if(model != null){
				String itemId = TaobaoUrlUtils.parseItemId(model);
				String outCode = null ;
				
				if(StringUtils.isNotBlank(alipayId)){
					
					if(!ValidateUtil.checkIsAlipay(alipayId)){
						json.setCode(JsonModel.CODE_ALIPAY_ERR) ;
						json.setCode("input alipay error .") ;
						return SUCCESS ;
					}
					
					String accId = ddzAccountService.getAccountIdByAlipayId(alipayId) ;
					outCode = OutCodeUtils.encodeOutCode(accId, OutCodeEnums.DDZ_ACCOUNT_ID) ;
				}
				
				TaobaokeItemDTO dto = taobaokeServiceDecorator.conventItem(itemId, outCode ,TaobaokeFields.taoke_item_fields ) ;
				EatDiscountCondition condition = new EatDiscountCondition()  ;
	            condition.setNeedPromotionPrice(true) ;
				TaobaokeItemFacade item = ddzEatDiscountService.eatDiscount(dto,condition) ;
				json.setData(item);
				json.setCode(JsonModel.CODE_SUCCESS) ;
				if(item == null){
					json.setCode(JsonModel.CODE_ILL_ARGS) ;
					json.setCode("item not exists .") ;
				}
			}else{
				//不是URL
				json.setCode(JsonModel.CODE_ILL_ARGS) ;
				json.setCode("url error .") ;
			}
		}else{
			//输入空
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("input blank.") ;
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


	public void setAlipayId(String alipayId) {
		this.alipayId = alipayId;
	}


	public void setWd(String wd) {
		this.wd = wd;
	}
	
}
