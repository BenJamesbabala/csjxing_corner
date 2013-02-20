package com.doucome.corner.web.dcome.authz;

import com.doucome.corner.biz.dcome.enums.DcLoginSourceEnums;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.model.facade.DcUserModel;
import com.doucome.corner.biz.dcome.model.facade.PfModel;

/**
 * 类DcAuthz.java的实现描述：豆蔻authz
 * 
 * @author ib 2012-7-28 下午11:30:23
 */
public interface DcAuthz {
	
	DcUserModel getModel() ;

    boolean isLogin();

    long getUserId();

    String getExternalId();
    
    /**
     * 获取代表浏览器唯一性的id
     * @return
     */
    String getUbid();

    String getPfNick();
    
    DcLoginSourceEnums getLoginSource();
    
    PfModel getPfModel() ;
    
    /**
     * 非重要的浏览器临时属性，不加密，不与用户绑定
     * @param key
     * @return
     */
    String getPromotype(String key);
    /**
     * 非重要的浏览器临时属性，不加密，不与用户绑定
     * @param key
     * @param value
     */
    void addPromotype(String key,String value);
    /**
     * 非重要的浏览器临时属性，不加密，不与用户绑定
     * @param key
     * @return
     */
    String removePromotype(String key);
    
    public DcUserDTO getUser();
    
    /**
     * 是否内部用户
     * @return true:是/false：否
     */
    boolean isPrivateUser();
}
