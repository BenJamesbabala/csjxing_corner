package com.doucome.corner.biz.dal.dataobject.dcome;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 用户收货信息
 * 
 * @author langben 2012-8-10
 */
@SuppressWarnings("serial")
public class DcPromotionAwardDO extends AbstractModel {
    private Long   id;

    /**
     * 用户ID
     */
    private Long   userId;
    /**
     * 
     */
    private Long promotionId;
    /**
     * 用户昵称
     */
    private String userNick ;
    
    /**
     * 活动记录id
     */
    private Long promotionItemId;
    
    /**
     * itemId
     */
    private Long itemId;
    /**
     * 商品尺码
     */
    private String itemSize;
    /**
     * 商品颜色
     */
    private String itemColor;
    /**
     * 商品类型.DcItemTypeEnum
     */
    private String itemType = "N";
    /**
     * 收货人姓名
     */
    private String delName;

    /**
     * 手机
     */
    private String delMobile;

    /**
     * 收货省份
     */
    private String delProvince;

    /**
     * 收货市
     */
    private String delCity;

    /**
     * 地区
     */
    private String delArea;

    /**
     * 详细地址
     */
    private String delAddr;
    /**
     * 其他信息.
     */
    private String delOther;
    /**
     * 邮编
     */
    private String delZipcode;

    /**
     * 获奖时票数
     */
    private Integer rateCount;
    
    private Integer itemNum = 1;
    /**
     * 奖品发送状态
     */
    private String sendStatus;
    
    private String promotionType;
    
    private String reviewStatus;
    
    private String checkCode;
    /**
     * 分享状态Y/N
     */
    private String shareStatus = "N";

    private Date   gmtCreate;

    private Date   gmtModified;

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDelName() {
        return delName;
    }

    public void setDelName(String delName) {
        this.delName = delName;
    }

    public String getDelMobile() {
        return delMobile;
    }

    public void setDelMobile(String delMobile) {
        this.delMobile = delMobile;
    }

    public String getDelProvince() {
        return delProvince;
    }

    public void setDelProvince(String delProvince) {
        this.delProvince = delProvince;
    }

    public String getDelCity() {
        return delCity;
    }

    public void setDelCity(String delCity) {
        this.delCity = delCity;
    }

    public String getDelArea() {
        return delArea;
    }

    public void setDelArea(String delArea) {
        this.delArea = delArea;
    }

    public String getDelAddr() {
        return delAddr;
    }

    public void setDelAddr(String delAddr) {
        this.delAddr = delAddr;
    }
    
    public String getDelOther() {
		return this.delOther;
	}
	
	public void setDelOther(String delOther) {
		this.delOther = delOther;
	}
	
    public String getDelZipcode() {
        return delZipcode;
    }

    public void setDelZipcode(String delZipcode) {
        this.delZipcode = delZipcode;
    }
    
    public Long getPromotionItemId() {
        return promotionItemId;
    }
    
    public String getUserNick() {
		return userNick;
	}

    public void setPromotionItemId(Long promotionItemId) {
        this.promotionItemId = promotionItemId;
    }
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
    
    public Long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	
	public String getItemSize() {
		return itemSize;
	}

	public void setItemSize(String itemSize) {
		this.itemSize = itemSize;
	}

	public String getItemColor() {
		return itemColor;
	}

	public void setItemColor(String itemColor) {
		this.itemColor = itemColor;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public Integer getItemNum() {
		return itemNum ;
	}

	public void setItemNum(Integer itemNum) {
		this.itemNum = itemNum;
	}

	public Integer getRateCount() {
		return rateCount;
	}

	public void setRateCount(Integer rateCount) {
		this.rateCount = rateCount;
	}

	public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    
    public String getPromotionType() {
        return promotionType;
    }

    
    public void setPromotionType(String promotionType) {
        this.promotionType = promotionType;
    }

    
    public String getReviewStatus() {
        return reviewStatus;
    }

    
    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    
    public String getCheckCode() {
        return checkCode;
    }

    
    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

	public String getShareStatus() {
		return shareStatus;
	}

	public void setShareStatus(String shareStatus) {
		this.shareStatus = shareStatus;
	}
}