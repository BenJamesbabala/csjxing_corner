package com.doucome.corner.web.common.action;

import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.core.service.ShortUrlService;

/**
 * 类ShortUrlAction.java的实现描述：短url Action
 * 
 * @author ib 2012-3-13 上午02:48:32
 */
@SuppressWarnings("serial")
public class ShortUrlAction extends BasicAction {

    private String          shorten;

    private ShortUrlService shortUrlService;

    @Override
    public String execute() throws Exception {
        String url = shortUrlService.queryByShorten(shorten);
        if (StringUtils.isNotBlank(url)) {
            getResponse().sendRedirect(url);
            return null;
        }
        return SUCCESS;
    }

   
    public void setShorten(String shorten) {
		this.shorten = shorten;
	}


	public void setShortUrlService(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

}
