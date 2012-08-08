package com.doucome.corner.web.bops.action.dcome.qq;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.business.DcQIndexConfigBO;
import com.doucome.corner.biz.dcome.enums.DcQIndexPubStatusEnums;
import com.doucome.corner.web.bops.action.BopsBasicAction;

@SuppressWarnings("serial")
public class ReleaseAction extends  BopsBasicAction{
	
	private static final Log log = LogFactory.getLog(ReleaseAction.class) ;

	private Long systemId ;
	
	private Long sceneId ;
	
	private String pubStatus ;
	
	@Autowired
	private DcQIndexConfigBO dcQIndexConfigBO ;
	
	/**
	 * 预发布首页
	 * @return
	 */
	public String preRelease(){
		
		if(systemId == null || sceneId == null){
			return BOPS_ERROR ;
		}
		
		try {
			dcQIndexConfigBO.release(systemId, sceneId, DcQIndexPubStatusEnums.PRE_RELEASE) ;
		}catch(Exception e){
			log.error(e.getMessage() , e) ;
			return BOPS_ERROR ;
		}
		
		this.pubStatus = DcQIndexPubStatusEnums.PRE_RELEASE.getValue() ;
		
		return SUCCESS ;
	}
	
	/**
	 * 发布首页
	 * @return
	 */
	public String release(){
		if(systemId == null || sceneId == null){
			return BOPS_ERROR ;
		}
		
		try {
			dcQIndexConfigBO.release(systemId, sceneId, DcQIndexPubStatusEnums.RELEASE) ;
		}catch(Exception e){
			log.error(e.getMessage() , e) ;
			return BOPS_ERROR ;
		}
		
		this.pubStatus = DcQIndexPubStatusEnums.RELEASE.getValue() ;
		
		return SUCCESS ;
	}

	public void setSystemId(Long systemId) {
		this.systemId = systemId;
	}

	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}

	public String getPubStatus() {
		return pubStatus;
	}

	public Long getSystemId() {
		return systemId;
	}

	public Long getSceneId() {
		return sceneId;
	}
	
	
}
