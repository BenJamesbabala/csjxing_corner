package com.doucome.corner.web.dcome.authz;

import com.doucome.corner.biz.dcome.enums.DcLoginSourceEnums;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.model.facade.DcUserModel;
import com.doucome.corner.biz.dcome.model.facade.PfModel;

/**
 * ��DcAuthz.java��ʵ����������ޢauthz
 * 
 * @author ib 2012-7-28 ����11:30:23
 */
public interface DcAuthz {
	
	DcUserModel getModel() ;

    boolean isLogin();

    long getUserId();

    String getExternalId();
    
    /**
     * ��ȡ���������Ψһ�Ե�id
     * @return
     */
    String getUbid();

    String getPfNick();
    
    DcLoginSourceEnums getLoginSource();
    
    PfModel getPfModel() ;
    
    /**
     * ����Ҫ���������ʱ���ԣ������ܣ������û���
     * @param key
     * @return
     */
    String getPromotype(String key);
    /**
     * ����Ҫ���������ʱ���ԣ������ܣ������û���
     * @param key
     * @param value
     */
    void addPromotype(String key,String value);
    /**
     * ����Ҫ���������ʱ���ԣ������ܣ������û���
     * @param key
     * @return
     */
    String removePromotype(String key);
    
    public DcUserDTO getUser();
    
    /**
     * �Ƿ��ڲ��û�
     * @return true:��/false����
     */
    boolean isPrivateUser();
}
