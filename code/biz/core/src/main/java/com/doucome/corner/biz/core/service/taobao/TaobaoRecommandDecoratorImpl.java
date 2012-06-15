package com.doucome.corner.biz.core.service.taobao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;

import com.doucome.corner.biz.core.constant.LogConstant;
import com.doucome.corner.biz.core.exception.TaobaoRemoteException;
import com.doucome.corner.biz.core.taobao.dto.TaobaoFavoriteItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaoPromotionDisplayDTO;
import com.doucome.corner.biz.core.taobao.model.TaobaoRecommendItemCondition;
import com.taobao.api.ApiException;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.FavoriteItem;
import com.taobao.api.domain.PromotionDisplayTop;
import com.taobao.api.request.CategoryrecommendItemsGetRequest;
import com.taobao.api.request.ItemrecommendItemsGetRequest;
import com.taobao.api.request.UmpPromotionGetRequest;
import com.taobao.api.response.CategoryrecommendItemsGetResponse;
import com.taobao.api.response.ItemrecommendItemsGetResponse;
import com.taobao.api.response.UmpPromotionGetResponse;

public class TaobaoRecommandDecoratorImpl extends AbstractTaobaoService implements TaobaoRecommandDecorator {
	
	private static final Log taobaoLog = LogFactory.getLog(LogConstant.taobao_log) ;
 		
	@Override
	public List<TaobaoFavoriteItemDTO> getRecommandItemsByItem(Long itemId , TaobaoRecommendItemCondition condition) {
		ItemrecommendItemsGetRequest req = new ItemrecommendItemsGetRequest();
		req.setItemId(itemId);
		req.setRecommendType(condition.getRecommendType().getCode());
		req.setCount(condition.getCount());
		req.setExt(condition.getExt());
		
		TaobaoClient client= taobaoClientWrapper.newClient() ;
		
		try{
			ItemrecommendItemsGetResponse response = client.execute(req);
			boolean isSuccess = response.isSuccess() ;
			if(isSuccess){
				List<TaobaoFavoriteItemDTO> returnList = new ArrayList<TaobaoFavoriteItemDTO> () ;
				List<FavoriteItem> list = response.getValues() ;
				if(!CollectionUtils.isEmpty(list)){
					for(FavoriteItem item : list){
						TaobaoFavoriteItemDTO tfi = new TaobaoFavoriteItemDTO(item) ;
						returnList.add(tfi) ;
					}
				}
				return returnList ;
			}
			if(taobaoLog.isErrorEnabled()){
				taobaoLog.error("input " + condition + "  response : " + response.getBody()) ;
			} 
			throwTaobaoErrorResponse(response) ;
			return null ;
			
		}catch(ApiException e){
			throw new TaobaoRemoteException(e.getErrMsg() , e , e.getErrCode()) ;
		}
	}
	
	@Override
	public List<TaobaoFavoriteItemDTO> getRecommendItemsByCategory(Long categoryId, TaobaoRecommendItemCondition condition) {
		CategoryrecommendItemsGetRequest req = new CategoryrecommendItemsGetRequest();
		req.setCategoryId(categoryId) ;
		req.setRecommendType(condition.getRecommendType().getCode());
		req.setCount(condition.getCount());
		req.setExt(condition.getExt());
		
		TaobaoClient client= taobaoClientWrapper.newClient() ;
		
		try{
			CategoryrecommendItemsGetResponse response = client.execute(req);
			boolean isSuccess = response.isSuccess() ;
			if(isSuccess){
				List<TaobaoFavoriteItemDTO> returnList = new ArrayList<TaobaoFavoriteItemDTO> () ;
				List<FavoriteItem> list = response.getFavoriteItems() ;
				if(!CollectionUtils.isEmpty(list)){
					for(FavoriteItem item : list){
						TaobaoFavoriteItemDTO tfi = new TaobaoFavoriteItemDTO(item) ;
						returnList.add(tfi) ;
					}
				}
				return returnList ;
			}
			if(taobaoLog.isErrorEnabled()){
				taobaoLog.error("input " + condition + "  response : " + response.getBody()) ;
			} 
			throwTaobaoErrorResponse(response) ;
			return null ;
			
		}catch(ApiException e){
			throw new TaobaoRemoteException(e.getErrMsg() , e , e.getErrCode()) ;
		}
	}

	@Override
	public TaobaoPromotionDisplayDTO getPromotionUmp(Long itemId, String channelKey) {
		
		UmpPromotionGetRequest req = new UmpPromotionGetRequest();
		req.setItemId(itemId);
		if(StringUtils.isNotBlank(channelKey)){
			req.setChannelKey(channelKey);
		}
		TaobaoClient client= taobaoClientWrapper.newClient() ;
		try{
			UmpPromotionGetResponse response = client.execute(req);
			boolean isSuccess = response.isSuccess() ;
			if(isSuccess){
				PromotionDisplayTop promotion = response.getPromotions() ;
				if(promotion == null){
					return null ;
				}
				TaobaoPromotionDisplayDTO promotionDTO = new TaobaoPromotionDisplayDTO(promotion) ;
				return promotionDTO ;
			}
			if(taobaoLog.isErrorEnabled()){
				taobaoLog.error("input " + itemId + "  response : " + response.getBody()) ;
			} 
			throwTaobaoErrorResponse(response) ;
			return null ;
		}catch(ApiException e){
			throw new TaobaoRemoteException(e.getErrMsg() , e , e.getErrCode()) ;
		}
		
	}

	
	
	
	
	
	
	/**
	 * ----------------------------------------------------------
	 * 
	 */
	
}
