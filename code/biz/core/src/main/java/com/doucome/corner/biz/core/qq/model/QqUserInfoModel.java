package com.doucome.corner.biz.core.qq.model;

import com.doucome.corner.biz.core.model.AbstractUserInfoModel;
import com.doucome.corner.biz.core.qq.enums.QqGenderEnums;

/**
 * ��QqUserInfoModel.java��ʵ��������QQ�û���Ϣģ��
 * 
 * @author ib 2012-7-28 ����05:07:00
 */
public class QqUserInfoModel extends AbstractUserInfoModel{

    /**
     * OpenID���û�Ψһʶ��id
     */
    private String        openId;
    /**
     * ������Դƽ̨
     */
    private String        platform;
    /**
     * �ǳ�
     */
    private String        nickName;
    /**
     * �Ա�
     */
    private QqGenderEnums gender;
    /**
     * ����
     */
    private String        country;
    /**
     * ʡ��
     */
    private String        province;
    /**
     * ����
     */
    private String        city;
    /**
     * ͷ���ַ
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
