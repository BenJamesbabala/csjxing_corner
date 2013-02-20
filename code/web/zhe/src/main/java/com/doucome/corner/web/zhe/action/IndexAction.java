package com.doucome.corner.web.zhe.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.doucome.corner.biz.dal.dataobject.DdzAccountDO;
import com.doucome.corner.web.zhe.model.GuideVar;

/**
 * Ê×Ò³
 * 
 * @author langben 2012-2-24
 */
@SuppressWarnings("serial")
public class IndexAction extends DdzBasicAction {
	
	private static final Log log = LogFactory.getLog(IndexAction.class) ;

	private GuideVar guideVar = new GuideVar(true , true , true , false , false ) ;
    
    private boolean isNotify = false ;
	
    
    @Override
    public String execute() throws Exception {
    	
    	DdzAccountDO ddzAccountDO = ddzAuthz.getAccount() ;
    	
    	if(ddzAccountDO != null){
    		isNotify = (ddzAccountDO.getNotifyCount() > 0) ;
    	}
    	
        return SUCCESS;
    }
    
	public GuideVar getGuideVar() {
		return guideVar;
	}

	public boolean getIsNotify() {
		return isNotify;
	}
    
}
