package com.doucome.corner.biz.core.qq.impl;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.doucome.corner.biz.core.qq.QqQueryService;
import com.doucome.corner.biz.core.qq.constant.QqApiConstant;
import com.doucome.corner.biz.core.qq.constant.QqUserInfoConstant;
import com.doucome.corner.biz.core.qq.enums.QqGenderEnums;
import com.doucome.corner.biz.core.qq.model.QqCsecWordModel;
import com.doucome.corner.biz.core.qq.model.QqUserInfoModel;
import com.doucome.corner.biz.core.utils.JacksonHelper;
import com.doucome.corner.biz.core.utils.UUIDUtils;
import com.qq.open.OpenApiV3;
import com.qq.open.OpensnsException;

/**
 * 类V3QqApiServiceImpl.java的实现描述：QqApiService基于QQ open api V3的实现
 * 
 * @author ib 2012-7-28 下午05:04:49
 */
public class V3QqQueryServiceImpl implements QqQueryService { 

    private static final Log logger = LogFactory.getLog(V3QqQueryServiceImpl.class);

    private OpenApiV3        openApiV3;

    private HashMap callQqApi(String scriptName, HashMap<String, String> params) {
        try {
            String resp = openApiV3.api(scriptName, params, QqApiConstant.PROTOCOL_HTTP,
                                        QqApiConstant.METHOD_POST);
            HashMap result = JacksonHelper.fromJSON(resp, HashMap.class);
            if ("0".equals(String.valueOf(result.get("ret")))) {
                return result;
            } else {
                logger.error("call qq api fail, result: " + resp);
                return null;
            }
        } catch (OpensnsException e) {
            logger.error("call qq api Error,method: " + scriptName, e);
        }
        return null;
    }

    @Override
    public QqUserInfoModel queryUserInfo(String pf, String openId, String openKey) {
        if (StringUtils.isBlank(pf) || StringUtils.isBlank(openId) || StringUtils.isBlank(openKey)) {
            return null;
        }

        // 填充URL请求参数
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("openid", openId);
        params.put("openkey", openKey);
        params.put("pf", pf);
        HashMap<String, String> map = callQqApi(QqApiConstant.SCRIPT_GET_INFO, params);
        if (map == null) {
            return null;
        }
        QqUserInfoModel userInfo = new QqUserInfoModel();
        userInfo.setOpenId(openId);
        userInfo.setPlatform(pf);
        userInfo.setNickName(map.get(QqUserInfoConstant.NICK_NAME));
        userInfo.setCountry(map.get(QqUserInfoConstant.COUNTRY));
        userInfo.setProvince(map.get(QqUserInfoConstant.PROVINCE));
        userInfo.setCity(map.get(QqUserInfoConstant.CITY));
        userInfo.setFigureUrl(map.get(QqUserInfoConstant.FIGURE_URL));
        String gender = map.get(QqUserInfoConstant.GENDER);
        if (StringUtils.endsWithIgnoreCase(gender, "男")) {
            userInfo.setGender(QqGenderEnums.Male);
        } else if (StringUtils.endsWithIgnoreCase(gender, "女")) {
            userInfo.setGender(QqGenderEnums.Female);
        } else {
            userInfo.setGender(QqGenderEnums.UnKnow);
        }
        return userInfo;
    }

	@Override
	public QqCsecWordModel csecWordFilter(String content, String pf, String openId,String openKey) {
		 // 填充URL请求参数
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("openid", openId);
        params.put("openkey", openKey);
        params.put("pf", pf);
    
		params.put("content", content) ;
	
        params.put("msgid", UUIDUtils.random20()) ;
        HashMap<String, Object> map = callQqApi(QqApiConstant.SCRIPT_WORD_FILTER, params);
        if (map == null) {
            return null ;
        }
        
        QqCsecWordModel model = new QqCsecWordModel() ;
        
        model.setDirty("1".equals(String.valueOf(map.get(QqCsecWordModel.IS_DIRTY)))) ;
        model.setLost("1".equals(String.valueOf(map.get(QqCsecWordModel.IS_LOST)))) ;
        model.setMsg(String.valueOf(map.get(QqCsecWordModel.MSG))) ;
        return model ;
	}
	
	@Override
	public boolean isQzoneFans(String pf, String openId, String openKey) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("openid", openId);
	    params.put("openkey", openKey);
	    params.put("pf", pf);
	    //空间qq号
	    params.put("page_id", "10507600");
	    
	    HashMap<String, Object> result = callQqApi(QqApiConstant.SCRIPT_QZONE_FANS, params);
	    if (result == null) {
	    	return false;
	    }
	    String isFan = String.valueOf(result.get("is_fans"));
	    return "1".equals(isFan);
	}
	
	@Override
	public boolean isWeiboFans(String pf , String openId, String openKey) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("openid", openId);
		params.put("openkey", openKey);
	    params.put("flag", "0");
	    params.put("fopenids", openId) ;
	    params.put("pf", pf);
	    HashMap<String, Object> result = callQqApi(QqApiConstant.SCRIPT_IS_WEIBO_FANS_OR_IDOL, params);
	    if (result == null) {
	    	return false;
	    }
	    String isFan = String.valueOf(result.get("is_fans"));
	    return "1".equals(isFan);
	}
    
    public void setOpenApiV3(OpenApiV3 openApiV3) {
        this.openApiV3 = openApiV3;
    }
}
