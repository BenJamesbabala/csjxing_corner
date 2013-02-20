package com.doucome.corner.web.bops.action.dcome.prom;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.dcome.enums.DcItemRecommTypeEnums;
import com.doucome.corner.biz.dcome.enums.DcYesOrNoEnum;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.common.model.JsonModel;

/**
 * Item ajax
 * @author langben 2012-10-15
 *
 */
public class ItemOperateAjaxAction extends BopsBasicAction {

	private JsonModel<Integer> json = new JsonModel<Integer>();
	
	@Autowired
	private DcItemService dcItemService ;
	
	private Long itemId ;
	
	private String recommType ;
	
	private String postalEnable;
	
	private Long categoryId;
	
	/**
	 * 
	 * @return
	 */
	public String updateRecommType(){
		
		if(IDUtils.isNotCorrect(itemId)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.itemId.notCorrect") ;
			return SUCCESS ;
		}
		
		DcItemRecommTypeEnums it = DcItemRecommTypeEnums.toEnum(recommType) ;
		int effectCount = dcItemService.updateRecommTypeById(itemId, it) ;
		
		json.setCode(JsonModel.CODE_SUCCESS) ;
		json.setData(effectCount) ;
		return SUCCESS ;
	}
	
	public String updatePostalEnable() {
		if(IDUtils.isNotCorrect(itemId)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.itemId.notCorrect") ;
			return SUCCESS ;
		}
		DcYesOrNoEnum temp = DcYesOrNoEnum.toEnum(postalEnable);
		if (temp == DcYesOrNoEnum.UNKNOWN) {
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.postalEnable.invalid") ;
			return SUCCESS ;
		}
		int count = dcItemService.updatePostalEnable(itemId, temp);
		json.setCode(JsonModel.CODE_SUCCESS) ;
		json.setData(count) ;
		return SUCCESS;
	}
	
	/**
	 * 更新商品类目id.
	 * @return
	 */
	public String updateCategoryId() {
		int count = dcItemService.updateCategoryId(itemId, categoryId);
		if (count > 0) {
			json.setSuccess(JsonModel.CODE_SUCCESS, count);
		} else {
			json.setFail(JsonModel.CODE_FAIL, "no.item");
		}
		return SUCCESS;
	}

	public JsonModel<Integer> getJson() {
		return json;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public void setRecommType(String recommType) {
		this.recommType = recommType;
	}
	
	public void setPostalEnable(String postalEnable) {
		this.postalEnable = postalEnable;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
}
