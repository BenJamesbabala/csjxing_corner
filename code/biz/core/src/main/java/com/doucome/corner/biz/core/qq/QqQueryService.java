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
    
    
}
