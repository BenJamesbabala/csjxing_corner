package com.doucome.corner.web.dcome.action.frame.q.ajax;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.core.enums.OutCodeEnums;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.core.utils.OutCodeUtils;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserClickStatisticsDO;
import com.doucome.corner.biz.dcome.business.DcGameBO;
import com.doucome.corner.biz.dcome.enums.DcGameEnum;
import com.doucome.corner.biz.dcome.enums.DcItemSourceEnum;
import com.doucome.corner.biz.dcome.enums.DcUserClickStatisticsEnums;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.biz.dcome.service.DcTaobaoService;
import com.doucome.corner.biz.dcome.service.DcUserClickStatisticsService;
import com.doucome.corner.biz.dcome.service.DcUserService;
import com.doucome.corner.biz.dcome.utils.DcPromotionOutCodeUtils;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.frame.q.QBasicAction;

/**
 * 跳转到商品的外部URL
 * @author langben 2012-8-28
 *
 */
@SuppressWarnings("serial")
public class RedirectItemAjaxAction extends QBasicAction {
	
	private static final Log log = LogFactory.getLog(RedirectItemAjaxAction.class) ;
	
	private static final String taobao_url = "http://item.taobao.com/item.htm?id=" ;
	
	/**
	 * 豆蔻商品itemId
	 */
	private Long itemId ;
	
	@Autowired
	private DcItemService dcItemService ;
	
	@Autowired
	private DcUserService dcUserService ;
	
	@Autowired
	private DcUserClickStatisticsService dcUserClickStatisticsService ;
	
	@Autowired
	private DcTaobaoService dcTaobaoService ;
	@Autowired
	private DcGameBO dcGameBO;
	
	private JsonModel<String> json = new JsonModel<String>() ;
	
	@Override
	public String execute() throws Exception {
		
		if(IDUtils.isNotCorrect(itemId)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.redirect.itemId.required");
			return SUCCESS ;
		}
		
		Long userId = dcAuthz.getUserId() ;
		
		DcItemDTO item = dcItemService.getItemById(itemId) ;
		
		if(item == null){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.redirect.itemId.required");
			return SUCCESS ;
		}
		
		try {
			//现在只有淘宝商品
			DcItemSourceEnum source = DcItemSourceEnum.getItemSource(item.getSource());
			
			//淘宝商品ID
			String nativeItemId = item.getNativeId() ;
			
			// UserId 和 ItemId 进行编码
			String outCode = OutCodeUtils.encodeOutCode(String.valueOf(userId), OutCodeEnums.DOUCOME_USER_ID) ;
			
			String url = null ;
			try { 
				TaobaokeItemDTO dto = dcTaobaoService.conventItem(nativeItemId, outCode) ;
				
				if(dto == null){
					url = item.getClickUrl();
					if (StringUtils.isEmpty(url)) {
						url = item.getSrcUrl();
					}
				} else {
					url = dto.getClickUrl() ;
				}
			} catch (Exception e){
				log.error(e.getMessage() , e) ;
				url = item.getClickUrl() ;
			}
			
			json.setCode(JsonModel.CODE_SUCCESS) ;
			json.setData(url) ;
			
		} catch (Exception e) {
			json.setCode(JsonModel.CODE_FAIL) ;
			json.setDetail("dcome.error");
			return SUCCESS ;
		}
				
		return SUCCESS ;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public static void main(String[] args) {
		String out = DcPromotionOutCodeUtils.encodeOutCode(9999999L, 9999999L) ;
		System.out.println(out);
		
	}

	public JsonModel<String> getJson() {
		return json;
	}
	
	
	
}
