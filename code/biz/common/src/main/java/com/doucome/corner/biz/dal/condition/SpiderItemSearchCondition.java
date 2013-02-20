package com.doucome.corner.biz.dal.condition;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.doucome.corner.biz.dal.model.OrderAndSortModel;
import com.doucome.corner.biz.dal.model.OrderAndSortModel.Sort;

/**
 * 类SpiderItemSearchCondition.java的实现描述：爬虫商品搜索条件
 * 
 * @author ib 2012-9-15 下午2:00:33
 */
public class SpiderItemSearchCondition {

    private Long    id;

    private Date    gmtModifyStart;

    private Date    gmtModifyEnd;

    private String  keywords;

    private String  categoryKeywords;

    private String  category;

    private String  orderBy;

    /**
     * 价格区间
     */
    private Integer priceStart;

    private Integer priceEnd;
    /**
     * 店铺等级区间
     */
    private Integer creditStart;

    private Integer creditEnd;
    /**
     * 月销量区间
     */
    private Integer volumeStart;

    private Integer volumeEnd;
    /**
     * 返利区间
     */
    private Integer commissionStart;

    private Integer commissionEnd;

    public Map<String, Object> toMap() {
        Map<String, Object> condition = new HashMap<String, Object>();

        condition.put("id", id);
        condition.put("gmtModifyStart", gmtModifyStart);
        condition.put("gmtModifyEnd", gmtModifyEnd);
        condition.put("keywords", keywords);
        condition.put("categoryKeywords", categoryKeywords);
        condition.put("category", category);
        condition.put("priceStart", priceStart);
        condition.put("priceEnd", priceEnd);
        condition.put("creditStart", creditStart);
        condition.put("creditEnd", creditEnd);
        condition.put("volumeStart", volumeStart);
        condition.put("volumeEnd", volumeEnd);
        if (commissionStart != null) {
            BigDecimal start = new BigDecimal(commissionStart);
            start = start.divide(new BigDecimal(100));
            condition.put("commissionStart", start);
        }
        if (commissionEnd != null) {
            BigDecimal end = new BigDecimal(commissionEnd);
            end = end.divide(new BigDecimal(100));
            condition.put("commissionEnd", end);
        }

        OrderAndSortModel osm = new OrderAndSortModel(orderBy, Sort.desc);
        if (osm.isFormat()) {
            condition.put("orderBy", osm.getOrder());
        }
        return condition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtModifyStart() {
        return gmtModifyStart;
    }

    public void setGmtModifyStart(Date gmtModifyStart) {
        this.gmtModifyStart = gmtModifyStart;
    }

    public Date getGmtModifyEnd() {
        return gmtModifyEnd;
    }

    public void setGmtModifyEnd(Date gmtModifyEnd) {
        this.gmtModifyEnd = gmtModifyEnd;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPriceStart() {
        return priceStart;
    }

    public void setPriceStart(Integer priceStart) {
        this.priceStart = priceStart;
    }

    public Integer getPriceEnd() {
        return priceEnd;
    }

    public void setPriceEnd(Integer priceEnd) {
        this.priceEnd = priceEnd;
    }

    public Integer getCreditStart() {
        return creditStart;
    }

    public void setCreditStart(Integer creditStart) {
        this.creditStart = creditStart;
    }

    public Integer getCreditEnd() {
        return creditEnd;
    }

    public void setCreditEnd(Integer creditEnd) {
        this.creditEnd = creditEnd;
    }

    public Integer getVolumeStart() {
        return volumeStart;
    }

    public void setVolumeStart(Integer volumeStart) {
        this.volumeStart = volumeStart;
    }

    public Integer getVolumeEnd() {
        return volumeEnd;
    }

    public void setVolumeEnd(Integer volumeEnd) {
        this.volumeEnd = volumeEnd;
    }

    public Integer getCommissionStart() {
        return commissionStart;
    }

    public void setCommissionStart(Integer commissionStart) {
        this.commissionStart = commissionStart;
    }

    public Integer getCommissionEnd() {
        return commissionEnd;
    }

    public void setCommissionEnd(Integer commissionEnd) {
        this.commissionEnd = commissionEnd;
    }

    public String getCategoryKeywords() {
        return categoryKeywords;
    }

    public void setCategoryKeywords(String categoryKeywords) {
        this.categoryKeywords = categoryKeywords;
    }

}
