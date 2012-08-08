package com.doucome.corner.biz.core.qq.model;

import com.doucome.corner.biz.core.model.AbstractUserInfoModel;
import com.doucome.corner.biz.core.qq.enums.QqGenderEnums;

/**
 * 类QqUserInfoModel.java的实现描述：QQ用户信息模型
 * 
 * @author ib 2012-7-28 下午05:07:00
 */
public class QqUserInfoModel extends AbstractUserInfoModel{

    /**
     * OpenID，用户唯一识别id
     */
    private String        openId;
    /**
     * 数据来源平台
     */
    private String        platform;
    /**
     * 昵称
     */
    private String        nickName;
    /**
     * 性别
     */
    private QqGenderEnums gender;
    /**
     * 国家
     */
    private String        country;
    /**
     * 省份
     */
    private String        province;
    /**
     * 城市
     */
    private String        city;
    /**
     * 头像地址
     */
    private String        figureUrl;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public QqGenderEnums getGender() {
        return gender;
    }

    public void setGender(QqGenderEnums gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFigureUrl() {
        return figureUrl;
    }

    public void setFigureUrl(String figureUrl) {
        this.figureUrl = figureUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

}
