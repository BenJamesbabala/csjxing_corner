package com.doucome.corner.web.bops.action.dcome.qq;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.dal.dataobject.dcome.DcCatCountDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcSceneDetailDO;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.biz.dcome.service.DcSceneDetailService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.bops.model.JsonModel;

/**
 * 场景下的商品操作
 * @author langben 2012-7-24
 *
 */
@SuppressWarnings("serial")
public class SceneItemOperateAjaxAction extends  BopsBasicAction {
	
	private static final int MAX_ITEM_COUNT_PER_CAT = 10 ;

	private JsonModel<Boolean> json = new JsonModel<Boolean>() ;
	
	@Autowired
	private DcSceneDetailService dcSceneDetailService ;
	
	@Autowired
	private DcItemService dcItemService ;
	
	private long itemId ;
	
	private long sceneId ;
	
	/**
	 * 移除一个场景下的商品
	 * @return
	 * @throws Exception
	 */
	public String remove() throws Exception {
		
		if(itemId <= 0 || sceneId <= 0){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("itemId and sceneId must set .") ;
			return SUCCESS ;
		}
		
		int count = dcSceneDetailService.removeSceneDetail(itemId, sceneId) ;
		
		json.setCode(JsonModel.CODE_SUCCESS) ;
		json.setDetail("effic count : " + count ) ;
		return SUCCESS ;
	}

	public String add() throws Exception {
		
		if(itemId <= 0 || sceneId <= 0){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.addSceneItem.sceneId.required") ;
			return SUCCESS ;
		}
		
		DcItemDTO item = dcItemService.getItemById(itemId) ;
		if(item == null){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.addSceneItem.itemId.notExists") ;
			return SUCCESS ;
		}
		
		List<Long> itemIdList = dcSceneDetailService.getItemsByScene(sceneId, 50) ;
		if(CollectionUtils.isNotEmpty(itemIdList)) {
			
			//是否达到添加上线
			List<DcCatCountDO> catCountList = dcItemService.groupCategoryCountByIds(itemIdList) ;
			if(CollectionUtils.isNotEmpty(catCountList)) {
				for(DcCatCountDO catCount : catCountList){
					if(item.getCategoryId().equals(catCount.getCategoryId())){
						int count = NumberUtils.parseInt(catCount.getItemCount()) ;
						if(count >= MAX_ITEM_COUNT_PER_CAT) {
							//该类目下商品到达上限
							json.setCode(JsonModel.CODE_ILL_ARGS) ;
							json.setDetail("dcome.addSceneItem.category.maxItemSize") ;
							return SUCCESS ;
						}
						break ;
					}
				}
			}
			
		}
		DcSceneDetailDO detail = new DcSceneDetailDO() ;
		detail.setItemId(itemId);
		detail.setSceneId(sceneId) ;
		Long id = dcSceneDetailService.createSceneDetail(detail) ;
		
		json.setCode(JsonModel.CODE_SUCCESS) ;
		json.setDetail("insert is  : " + id ) ;
		
		return SUCCESS ;
		
	}
	
	
	public JsonModel<Boolean> getJson() {
		return json;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public void setSceneId(long sceneId) {
		this.sceneId = sceneId;
	}

	
}
