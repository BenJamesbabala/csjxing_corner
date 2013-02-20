package com.doucome.corner.web.dcome.action.frame.q.ajax;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.dcome.business.DcPromotionBO;
import com.doucome.corner.biz.dcome.model.DcPromotionDTO;
import com.doucome.corner.biz.dcome.model.DcPromotionSnapInfoDTO;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.frame.q.QBasicAction;

/**
 * 豆蔻愿望墙统计数据.
 *
 */
@SuppressWarnings("serial")
public class DcMostWishAjaxAction extends QBasicAction {
	
	private static final Log log = LogFactory.getLog(DcMostWishAjaxAction.class) ;
	
	private JsonModel<List<DcPromotionSnapInfoDTO>> json = new JsonModel<List<DcPromotionSnapInfoDTO>>();
	@Autowired
	private DcPromotionBO dcPromotionBO;
	
	private Integer size = 4;
	private Integer page = 1;
	
	@Override
	public String execute(){
		DcPromotionDTO promotion = getPromotion();
		if (promotion == null) {
			json.setFail(JsonModel.CODE_FAIL, "no.promotion");
			return SUCCESS;
		}
		if (IDUtils.isNotCorrect(getUserId())) {
			json.setFail(JsonModel.CODE_FAIL, "no.login");
			return SUCCESS;
		}
		try {
			size = (size == null || size > 20 || size < 4) ? 4: size;
			page = (page == null || page < 1) ? 1: page;
			List<DcPromotionSnapInfoDTO> snapInfos = dcPromotionBO.getMostWishItems(promotion.getId());
			json.setSuccess(JsonModel.CODE_SUCCESS, snapInfos);
		} catch (Exception e) {
			log.error(e);
			json.setFail(JsonModel.CODE_FAIL, "internal.error");
		}
		return SUCCESS ;
	}
	
	public void setSize(Integer size) {
		this.size = size;
	}
	
	public void setPage(Integer page) {
		this.page = page;
	}
	
	public JsonModel<List<DcPromotionSnapInfoDTO>> getJson() {
		return json;
	}
}
