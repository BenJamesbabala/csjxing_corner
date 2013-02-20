package com.doucome.corner.web.bops.action.dcome.qq;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.taobao.enums.TaobaoCommentEnum;
import com.doucome.corner.biz.core.utils.ParameterUtils;
import com.doucome.corner.biz.dcome.business.DcItemBO;
import com.doucome.corner.biz.dcome.model.DcCommentDTO;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.common.model.JsonModel;

/**
 * 商品评论异步接口.
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class TaobaoCommentAjaxAction extends BopsBasicAction {
	/**
	 * 商品的淘宝数字id.
	 */
	private Long nativeItemId;
	/**
	 * 商品销售者id.
	 */
	private Long sellerUserId;
	/**
	 * 商品来源
	 */
	private String itemSource;
	
	private JsonModel<List<DcCommentDTO>> result = new JsonModel<List<DcCommentDTO>>();
	@Autowired
	private DcItemBO dcItemBO;
	
	public String execute() {
		TaobaoCommentEnum commentEnum = TaobaoCommentEnum.getInstance(itemSource);
		if (!ParameterUtils.isLongIdValid(nativeItemId) || !ParameterUtils.isLongIdValid(sellerUserId)
				|| commentEnum == null) {
			result.setCode(JsonModel.CODE_FAIL);
			result.setDetail("invalid.request.param");
			return SUCCESS;
		}
		List<DcCommentDTO> itemComments =
			dcItemBO.getTaobaoComments(nativeItemId, sellerUserId, commentEnum);
		if (itemComments == null) {
			result.setCode(JsonModel.CODE_FAIL);
			result.setDetail("request.comment.failed");
			return SUCCESS;
		}
		result.setCode(JsonModel.CODE_SUCCESS);
		result.setData(itemComments);
		return SUCCESS;
	}

	public JsonModel<List<DcCommentDTO>> getResult() {
		return result;
	}

	public void setNativeItemId(Long nativeItemId) {
		this.nativeItemId = nativeItemId;
	}

	public void setSellerUserId(Long sellerUserId) {
		this.sellerUserId = sellerUserId;
	}

	public void setItemSource(String itemSource) {
		this.itemSource = itemSource;
	}
}
