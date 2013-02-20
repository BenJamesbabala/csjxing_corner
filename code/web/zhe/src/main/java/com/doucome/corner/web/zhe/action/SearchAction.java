package com.doucome.corner.web.zhe.action;

import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.util.UriTemplate;

import com.doucome.corner.biz.core.constant.Constant;
import com.doucome.corner.biz.core.model.URLModel;
import com.doucome.corner.biz.core.utils.HttpUrlHelper;
import com.doucome.corner.biz.core.utils.MD5Util;
import com.doucome.corner.biz.zhe.enums.SearchWayEnums;
import com.doucome.corner.web.common.action.BasicAction;
import com.doucome.corner.web.common.utils.TaobaoUrlUtils;

/**
 * ²éÑ¯Èë¿Ú
 * @author langben 2012-4-1
 *
 */
@SuppressWarnings("serial")
public class SearchAction extends BasicAction  {

    private String	wd;
    
    private String mark;
    
    private String id ;
    
    private boolean userGuide ;
    
    private String domain ;

    /**
     * @see {@link UriTemplate}
     */
    @Override
    public String execute() throws Exception {
    	URLModel model = HttpUrlHelper.parseURL(wd);
        if (model != null) {
            // taobao url
            id = TaobaoUrlUtils.parseItemId(model);
            domain = model.getHost() ;
            if (StringUtils.isNotBlank(id)) {
            	if(userGuide){
            		return "itemGuide" ;
            	}
            }
        }
        
        return SearchWayEnums.ITEM.getValue();
    }


    /**
         * 
     * @return
     */

    public String getWd() {
        return wd;
    }

   
    public void setWd(String wd) {
        this.wd = wd;
    }
    

    
    public boolean isUserGuide() {
		return userGuide;
	}


	public void setUserGuide(boolean userGuide) {
		this.userGuide = userGuide;
	}


	public String getMark() {
        return mark;
    }


    
    public void setMark(String mark) {
        this.mark = mark;
    }


    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getDomain() {
		return domain;
	}


	public void setDomain(String domain) {
		this.domain = domain;
	}

	
}
