package com.doucome.corner.web.zhe.authz;

/**
 * 类DdzSessionOpService.java的实现描述：TODO 类实现描述
 * 
 * @author ib 2012-3-31 上午01:52:07
 */
public interface DdzSessionOperator {

    public boolean load(String uid);
    
    public boolean unload();

    public void setAlipayId(String alipayId);
}
