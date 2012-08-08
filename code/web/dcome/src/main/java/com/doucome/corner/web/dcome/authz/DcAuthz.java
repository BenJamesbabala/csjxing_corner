package com.doucome.corner.web.dcome.authz;

import com.doucome.corner.biz.dcome.enums.DcLoginSourceEnums;
import com.doucome.corner.web.dcome.authz.model.PfModel;

/**
 * 类DcAuthz.java的实现描述：豆蔻authz
 * 
 * @author ib 2012-7-28 下午11:30:23
 */
public interface DcAuthz {

    boolean isLogin();

    long getUserId();

    String getExternalId();

    String getPfNick();
    
    DcLoginSourceEnums getLoginSource();
    
    PfModel getPfModel() ;
    /**
     * 是否内部用户
     * @return true:是/false：否
     */
    boolean isPrivateUser();
}
