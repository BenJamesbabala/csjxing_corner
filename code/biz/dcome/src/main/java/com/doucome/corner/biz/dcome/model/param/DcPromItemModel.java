package com.doucome.corner.biz.dcome.model.param;

import com.doucome.corner.biz.dal.model.AbstractModel;
import com.doucome.corner.web.common.model.ResultModel;

/**
 * ���Ʒ����.
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class DcPromItemModel extends AbstractModel  {
	
	private Long itemId;
	/**
	 * �û�id
	 */
	private Long userId;
	/**
	 * �û��ǳ�.
	 */
	private String userNick;
	/**
	 * ��Ʒ��Դ
	 */
//	private DcItemSourceEnum itemSource;
	
//	public String getItemUrl() {
//		return itemUrl;
//	}
//	
//	public void setItemUrl(String itemUrl) {
//		this.itemUrl = itemUrl;
//		this.itemSource = DcItemSourceEnum.getItemSource(itemUrl);
//		this.extItemId = itemSource.getItemId(itemUrl);
//	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	
	public String getUserNick() {
		return this.userNick;
	}
	
//	public String getExtItemId() {
//		return this.extItemId;
//	}
//	
//	public void setExtItemId(String extItemId) {
//		extItemId = this.extItemId;
//	}
	
	public Long getItemId() {
		return this.itemId;
	}
	
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	
	/**
	 * ����У��.
	 * @return
	 */
	public ResultModel<Boolean> validate() {
		ResultModel<Boolean> result = new ResultModel<Boolean>();
//		if (!itemSource.isLegalItemId(getExtItemId())) {
//			result.setFail(ActionResultModel.CODE_FAIL, ActionResultModel.DETAIL_ITEMURL_INVALID);
//		} else {
//			result.setSuccess(ActionResultModel.CODE_SUCCESS, true);
//		}
		return result;
	}
}
