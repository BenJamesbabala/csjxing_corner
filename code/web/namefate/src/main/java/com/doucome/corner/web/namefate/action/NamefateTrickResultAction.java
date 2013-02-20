package com.doucome.corner.web.namefate.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.apps.namefate.model.NamefateTrickDTO;
import com.doucome.corner.biz.apps.namefate.model.NamefateUserDTO;
import com.doucome.corner.biz.apps.namefate.service.NamefateTrickService;
import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.core.constant.URIConstant;
import com.doucome.corner.biz.core.service.ShortUrlService;
import com.doucome.corner.biz.core.service.UriService;

@SuppressWarnings("serial")
public class NamefateTrickResultAction extends NamefateBasicAction {

	@Autowired
	private NamefateTrickService namefateTrickService ;
	
	@Autowired
	private ShortUrlService shortUrlService ;
	
	@Autowired
	private UriService uriService ;
	
	private Long trickId ;
	
	private NamefateTrickDTO trick ;
	
	
	
	/**
	 * ∂Ã¡¥
	 */
	private String shorten ;
	
	@Override
	public String execute() throws Exception {
		
		trick = namefateTrickService.getTrickById(trickId) ;
		
		Long userId = getUserId() ;
		if(IDUtils.isNotCorrect(userId)) {
			return SUCCESS ;
		}
		NamefateUserDTO user = getUser() ;
		
		String externalId = user.getExternalId() ;
		
		String urlAddr = uriService.getURI(URIConstant.NAMEFATE_SERVER) + "/frame/namefate/index/" + externalId ;
		
		this.shorten = shortUrlService.insertUrl(urlAddr) ;
		
		return SUCCESS ;
	}

	public NamefateTrickDTO getTrick() {
		return trick;
	}

	public void setTrickId(Long trickId) {
		this.trickId = trickId;
	}

	public String getShorten() {
		return shorten;
	}
	
}
