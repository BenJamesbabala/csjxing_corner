package com.doucome.corner.web.zhe.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dal.dataobject.BannerConfigDO;
import com.doucome.corner.web.common.action.BasicAction;
import com.doucome.corner.web.zhe.authz.DdzAuthz;

@SuppressWarnings("serial")
public abstract class DdzBasicAction extends BasicAction {

    @Autowired
    protected DdzAuthz ddzAuthz; 
    
//    @Autowired
//    private DdzBannerConfigService ddzBannerConfigService ;

	public DdzAuthz getDdzAuthz() {
		return ddzAuthz;
	}
	
	/**
	 *  «∑Ò”–÷ß∏∂±¶’À∫≈
	 * @return
	 */
	public String getAlipayAccount(){
		String alipayId = ddzAuthz.getAlipayId() ;
		if ("vip@diandianzhe.com".equals(alipayId)) {
			return null;
		}
		return alipayId;
	}
    
	public Map<String,BannerConfigDO> getBannerConfigMap(){
		
		Map<String,BannerConfigDO> configMap = new HashMap<String,BannerConfigDO>() ;
		
//		List<BannerConfigDO> configList = ddzBannerConfigService.getConfigs() ;
//		
//		if(CollectionUtils.isNotEmpty(configList)){
//			for(BannerConfigDO cf : configList){
//				configMap.put(cf.getBannerType(), cf) ;
//			}
//		}
//		
		return configMap ;
	}
	
}
