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
     * 获取用户生成折扣的支付宝id（前台使用）
     * 
     * @return
     */
    public String getAlipayId();

    /**
     * 登录名，仅作展示使用
     * 
     * @return
     */
    public String getLoginId();

    /**
     * 当前登录用户的uid
     * 
     * @return
     */
    public String getUid();

    /**
     * 获取用户的帐号信息（后台使用）
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
    
    /**
     * 是否内部用户
     * @return true:是/false：否
     */
    public boolean isPrivateUser();

}
