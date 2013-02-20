package com.doucome.corner.web.bops.action.dcome.qq;


import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.service.taobao.TaobaoRecommandDecorator;
import com.doucome.corner.biz.core.taobao.dto.TaobaoPromotionDisplayDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaoPromotionInItemDTO;
import com.doucome.corner.biz.dcome.business.DcItemBO;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.web.bops.action.BopsBasicAction;

/**
 * 
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class DcItemSearchAction extends BopsBasicAction{
	
	private static final Log log = LogFactory.getLog(DcItemSearchAction.class) ;

	private String wd;
	
	private String picUrl;
		
	private DcItemDTO item;
	
	@Autowired
    private DcItemBO dcItemBO;
	
	@Autowired
	private DcItemService dcItemService;
	
	@Autowired
	private TaobaoRecommandDecorator taobaoRecommandDecorator ;
	
	public String execute() {
		if (StringUtils.isBlank(wd)) {
			return SUCCESS;
		}
		if (StringUtils.isNumeric(wd)) {
			long id = Long.valueOf(wd);
			item = dcItemService.getItemById(id);
			if (item != null) {
			    if(StringUtils.isNotBlank(picUrl)){
			        item.getPicUrlList().clear();
			        item.getPicUrlList().add(picUrl);
			    }
				return SUCCESS;
			}
			return "item_not_found";
		}

		item = dcItemBO.getDcItemFromTB(wd);
		if (item == null) {
			return "item_not_found";
		}
		String itemId = item.getNativeId() ;
		//查询促销价格
		try {
			TaobaoPromotionDisplayDTO promotionDisplay = taobaoRecommandDecorator.getPromotionUmp(Long.valueOf(itemId), null) ;
			if(promotionDisplay != null && promotionDisplay.getPromotionInItem() != null){
				TaobaoPromotionInItemDTO promotionItem = promotionDisplay.getPromotionInItem() ;
				item.setItemPromPrice(promotionItem.getItemPromoPrice()) ;
			}
		} catch (Exception e){
			log.error(e.getMessage() , e) ;
		}
		
		if(StringUtils.isNotBlank(picUrl)){
            item.getPicUrlList().clear();
            item.getPicUrlList().add(picUrl);
        }
		return SUCCESS;
	}
	
	public String getWd() {
		return wd;
	}

	public void setWd(String wd) {
		this.wd = wd;
	}

    public String getPicUrl() {
        return picUrl;
    }
    
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public DcItemDTO getItem() {
		return item;
	}
}
