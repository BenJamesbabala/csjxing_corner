package com.doucome.corner.biz.dcome.model.param;

import java.math.BigDecimal;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.core.utils.PictureUtils;
import com.doucome.corner.biz.dal.model.AbstractModel;
import com.doucome.corner.biz.dcome.enums.DcItemGenWayEnums;
import com.doucome.corner.biz.dcome.enums.DcItemSourceEnum;
import com.doucome.corner.web.common.model.ResultModel;

/**
 * 用户自定义商品属性信息.非线程安全.
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class DcUgcItemModel extends AbstractModel {
	//itemUrl和recommandReason用户录入.
	/**
	 * 商品链接地址.
	 */
	private String itemUrl;
	/**
	 * 推荐理由
	 */
	private String recommandReason;
	/**
	 * 商品图片链接
	 */
	private String pictureUrl;
	/**
	 * 活动价格
	 */
	private BigDecimal promPrice;
	/**
	 * 创建者id.
	 */
	private Long creatorId;
	/**
	 * 创建者nick.
	 */
	private String creatorNick;
	/**
	 * 商品在商品所属网站id
	 */
	private String extItemId;
	/**
	 * 商品来源.
	 */
	private DcItemSourceEnum itemSource;
	
	private Boolean isShare;

	public String getItemUrl() {
		return itemUrl;
	}

	public void setItemUrl(String itemUrl) {
		this.itemUrl = itemUrl;
		itemSource = DcItemSourceEnum.getItemSource(itemUrl);
		this.extItemId = itemSource.getItemId(itemUrl);
	}
	
	public String getRecommandReason() {
		return recommandReason;
	}

	public void setRecommandReason(String recommandReason) {
		this.recommandReason = recommandReason;
	}
	
	public String getPictureUrl() {
		return this.pictureUrl;
	}
	
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = PictureUtils.findOriginalPicUrl(pictureUrl);
	}
    
	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	
	public String getCreatorNick() {
		return this.creatorNick;
	}
	
	public void setCreatorNick(String nick) {
		this.creatorNick = nick;
	}

	public BigDecimal getPromPrice() {
		return promPrice;
	}

	public void setPromPrice(BigDecimal promPrice) {
		this.promPrice = promPrice;
	}

	public DcItemGenWayEnums getGenWay() {
		if (!isShare) {
			return DcItemGenWayEnums.SECRET;
		}
		return DcItemGenWayEnums.USER;
	}
	
	public String getExtItemId() {
		return this.extItemId;
	}
	
	public DcItemSourceEnum getItemSource() {
		return this.itemSource;
	}
	
	public Boolean getIsShare() {
		return isShare == null ? false: isShare;
	}

	public void setIsShare(Boolean isShare) {
		this.isShare = isShare;
	}

	public ResultModel<Boolean> validate() {
		ResultModel<Boolean> result = new ResultModel<Boolean>();
		if (IDUtils.isNotCorrect(creatorId)) {
			result.setFail(ResultModel.CODE_FAIL, ResultModel.DETAIL_USERID_INVALID);
		} else if (!itemSource.isLegalItemId(getExtItemId())) {
			result.setFail(ResultModel.CODE_FAIL, ResultModel.DETAIL_URL_INVALID);
		} else {
			result.setSuccess(ResultModel.CODE_SUCCESS, true);
		}
		return result;
	}
}
