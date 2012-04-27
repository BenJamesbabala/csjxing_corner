package com.doucome.corner.web.zhe.authz;

import com.doucome.corner.biz.dal.dataobject.DdzAccountDO;
import com.doucome.corner.biz.dal.dataobject.DdzUserDO;

/**
 * 类Authz.java的实现描述：登录认证信息
 * 
 * @author ib 2012-3-24 下午01:27:16
 */
public interface DdzAuthz {

    /**
     * 判断用户是否登录状态
     * 
     * @return
     */
    public boolean isLogin();

    /**
     * 获取用户的支付宝id
     * 
     * @return
     */
    public String getAlipayId();

    public String getUid(); 

    /**
     * 获取帐号信息
     * 
     * @return
     */
    public DdzAccountDO getAccount();

    /**
     * 获取User信息
     * 
     * @return
     */
    public DdzUserDO getUser();

}
