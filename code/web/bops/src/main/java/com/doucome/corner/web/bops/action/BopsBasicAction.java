package com.doucome.corner.web.bops.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.web.bops.action.utils.FiledErrorHelper;
import com.doucome.corner.web.bops.authz.BopsAuthz;
import com.doucome.corner.web.common.action.BasicAction;

@SuppressWarnings("serial")
public abstract class BopsBasicAction extends BasicAction {
	
	protected static final String BOPS_ERROR = "bopsError" ;

    @Autowired
    protected BopsAuthz bopsAuthz;
    
    private FiledErrorHelper fieldErrorHelper = new FiledErrorHelper(){
    	@Override
    	public String getErrorText(String fieldName) {
    		Map<String , List<String>> fieldErrors = BopsBasicAction.this.getFieldErrors() ;
    		if(fieldErrors == null || fieldErrors.isEmpty()){
        		return null ;
        	}
        	List<String> errorList = fieldErrors.get(fieldName) ;
        	if(CollectionUtils.isEmpty(errorList)) {
        		return null ;
        	}
        	String errorKey = errorList.get(0) ;
        	String messageText = getText(errorKey) ;
        	return messageText ;
    	}
    } ;
    
    public BopsAuthz getBopsAuthz() {
        return bopsAuthz;
    }
    
    public FiledErrorHelper getFieldErrorHelper(){
    	return fieldErrorHelper ;
    }
}
