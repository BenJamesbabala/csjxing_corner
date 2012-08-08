package com.doucome.corner.web.dcome.authz;

import com.doucome.corner.biz.dcome.enums.DcLoginSourceEnums;
import com.doucome.corner.web.dcome.authz.model.PfModel;

/**
 * ��DcAuthz.java��ʵ����������ޢauthz
 * 
 * @author ib 2012-7-28 ����11:30:23
 */
public interface DcAuthz {

    boolean isLogin();

    long getUserId();

    String getExternalId();

    String getPfNick();
    
    DcLoginSourceEnums getLoginSource();
    
    PfModel getPfModel() ;
    /**
     * �Ƿ��ڲ��û�
     * @return true:��/false����
     */
    boolean isPrivateUser();
}
