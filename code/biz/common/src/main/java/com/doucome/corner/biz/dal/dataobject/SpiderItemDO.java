package com.doucome.corner.biz.dal.dataobject;

import java.math.BigDecimal;
import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 类SpiderItemDO.java的实现描述：外部爬取商品
 * 
 * @author ib 2012-9-8 下午10:21:46
 */
public class SpiderItemDO extends AbstractModel {

    private static final long serialVersionUID = -3045065012521088889L;

    private long              id;

    private String            spiderFrom;

    private String            extId;

    private long              itemId;

    private String            picUrl;

    private String            category;

    private BigDecimal        price;

    private BigDecimal        rate;

    private BigDecimal        commission;
    /**
     * 30天内交易量,比如：20
     */
    private Long              volume;
    /**
     * 卖家信用等级 , 比如：12
     */
    private Long              creditScore;

    private String            title;

    private Date              gmtCreate;

    private Date              gmtModified;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSpiderFrom() {
        return spiderFrom;
    }

    public void setSpiderFrom(String spiderFrom) {
        this.spiderFrom = spiderFrom;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Long getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Long creditScore) {
        this.creditScore = creditScore;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
