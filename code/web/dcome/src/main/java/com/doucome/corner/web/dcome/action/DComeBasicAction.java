package com.doucome.corner.web.dcome.action;

import com.doucome.corner.biz.dcome.enums.DcLoginSourceEnums;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.web.common.action.BasicAction;
import com.doucome.corner.web.dcome.authz.DcAuthz;

@SuppressWarnings("serial")
public abstract class DComeBasicAction extends BasicAction {

	protected static final String DCOME_QQ_ERROR = "dcomeQQError" ;
	
    protected DcAuthz dcAuthz;
    
    public DcAuthz getDcAuthz() {
        return dcAuthz;
    }
    
    public void setDcAuthz(DcAuthz dcAuthz) {
        this.dcAuthz = dcAuthz;
    }
    
    public Long getUserId(){
    	return dcAuthz.getUserId() ;
    }
    
    public String getPfNick(){
    	return dcAuthz.getPfNick() ;
    }
    
    public String getLoginSource(){
    	DcLoginSourceEnums source = dcAuthz.getLoginSource() ;
    	if(source == null){
    		return null ;
    	}
    	return source.getValue() ;
    }
    
    public DcUserDTO getUser() {
    	return dcAuthz.getUser() ;
    }
   
}
