package com.doucome.corner.web.zhe.authz;

/**
 * ��DdzSessionOpService.java��ʵ��������TODO ��ʵ������
 * 
 * @author ib 2012-3-31 ����01:52:07
 */
public interface DdzSessionOperator {

    public boolean load(String uid);
    
    public boolean unload();

    public void setAlipayId(String alipayId);
}
