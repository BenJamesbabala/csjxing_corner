package com.doucome.corner.biz.core.taobao.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.doucome.corner.biz.core.model.AbstractModel;
import com.doucome.corner.biz.core.utils.ReflectUtils;
import com.taobao.api.domain.PromotionDisplayTop;
import com.taobao.api.domain.PromotionInItem;
import com.taobao.api.domain.PromotionInShop;

/**
 * �Ż���Ϣ����
 * @author shenjia.caosj 2012-3-28
 *
 */
public class TaobaoPromotionDisplayDTO extends AbstractModel {
	
	public TaobaoPromotionDisplayDTO(PromotionDisplayTop promotion){
		
		List<PromotionInItem> promotionInItemList = promotion.getPromotionInItem() ;
		if(CollectionUtils.isNotEmpty(promotionInItemList)){
			for(PromotionInItem item : promotionInItemList){
				this.promotionInItem = new TaobaoPromotionInItemDTO() ;
				ReflectUtils.reflectTo(item, promotionInItem) ;
				break ;
			}
		}
		
		List<PromotionInShop> promotionInShopList = promotion.getPromotionInShop() ;
		if(CollectionUtils.isNotEmpty(promotionInShopList)){
			for(PromotionInShop item : promotionInShopList){
				this.promotionInShop = new TaobaoPromotionInShopDTO() ;
				ReflectUtils.reflectTo(item, promotionInShop) ;
				break ;
			}
		}
		
	}

	/**
	 * 	��Ʒ���Ż���Ϣ
	 */
	private TaobaoPromotionInItemDTO promotionInItem ;
	
	/**
	 * ���̼��Ż���Ϣ
	 */
	private TaobaoPromotionInShopDTO promotionInShop ;

	public TaobaoPromotionInItemDTO getPromotionInItem() {
		return promotionInItem;
	}

	public void setPromotionInItem(TaobaoPromotionInItemDTO promotionInItem) {
		this.promotionInItem = promotionInItem;
	}

	public TaobaoPromotionInShopDTO getPromotionInShop() {
		return promotionInShop;
	}

	public void setPromotionInShop(TaobaoPromotionInShopDTO promotionInShop) {
		this.promotionInShop = promotionInShop;
	}
	
	
}
