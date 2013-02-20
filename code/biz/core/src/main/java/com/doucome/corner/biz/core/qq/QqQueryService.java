package com.doucome.corner.biz.core.qq;

import com.doucome.corner.biz.core.qq.model.QqCsecWordModel;
import com.doucome.corner.biz.core.qq.model.QqUserInfoModel;

/**
 * ��QqApiService.java��ʵ������������qq���api
 * 
 * @author ib 2012-7-28 ����04:43:29
 */
public interface QqQueryService {

    QqUserInfoModel queryUserInfo(String pf, String openId, String openKey);
    
    QqCsecWordModel csecWordFilter(String content , String pf, String openId, String openKey);
    /**
     * �Ƿ�����֤�ռ�fans.
     * @param pf
     * @param openId
     * @param openKey
     * @return
     */
    boolean isQzoneFans(String pf, String openId, String openKey);
    
    /**
     * �ж��Ƿ�΢������
     * @param openId
     * @param openKey
     * @return
     */
    boolean isWeiboFans(String pf,String openId , String openKey) ;
}
