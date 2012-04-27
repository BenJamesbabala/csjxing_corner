package com.doucome.corner.web.zhe.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.service.impl.DefaultUriService;
import com.doucome.corner.web.common.action.BasicAction;
import com.doucome.corner.web.zhe.authz.DdzAuthz;

@SuppressWarnings("serial")
public abstract class DdzBasicAction extends BasicAction {

    @Autowired
    protected DdzAuthz ddzAuthz; 

    protected void redirect(String url) throws IOException {
        HttpServletResponse response = getResponse();
        response.sendRedirect(url);
    }

    protected void redirectToUrlName(String urlName) throws IOException {
        String url = DefaultUriService.getFactoryURI(urlName);
        redirect(url);

    }

	public DdzAuthz getDdzAuthz() {
		return ddzAuthz;
	}

    
}
