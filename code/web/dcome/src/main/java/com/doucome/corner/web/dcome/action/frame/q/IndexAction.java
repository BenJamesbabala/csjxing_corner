package com.doucome.corner.web.dcome.action.frame.q;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.business.DcSceneBO;
import com.doucome.corner.biz.dcome.enums.DcQIndexPubStatusEnums;
import com.doucome.corner.biz.dcome.model.DcQIndexConfigDTO;
import com.doucome.corner.biz.dcome.model.facade.DcSceneQIndexFacade;
import com.doucome.corner.biz.dcome.service.DcQIndexConfigService;
import com.doucome.corner.web.dcome.action.DComeBasicAction;

/**
 * QQ �ռ���ҳ
 * @author shenjia.caosj 2012-7-21
 *
 */
@SuppressWarnings("serial")
public class IndexAction extends DComeBasicAction {

	private Long sysId = 1L ;
	
	private String pubStatus = DcQIndexPubStatusEnums.RELEASE.getValue() ;
	
	@Autowired
	private DcQIndexConfigService dcQIndexConfigService ;
	
	private DcQIndexConfigDTO indexConfigDTO ;
	
	private DcSceneQIndexFacade qindexFacade ;
	
	@Autowired
	private DcSceneBO dcSceneBO ;
	
	private Long sceneId ;
	
	@Override
	public String execute() throws Exception {
		
		DcQIndexPubStatusEnums status = DcQIndexPubStatusEnums.toEnum(pubStatus) ;
		
		indexConfigDTO = dcQIndexConfigService.getConfigBySysAndStatus(sysId, status) ;
		
		if(indexConfigDTO == null){
			return SUCCESS ;
		}
		//����
		Long sceneId = indexConfigDTO.getSceneId() ;
		
		this.qindexFacade = dcSceneBO.getQIndexFacade(sceneId);
		
		return SUCCESS ;
	}
	
	/**
	 * Ԥ��
	 * @return
	 * @throws Exception
	 */
	public String preview() throws Exception{
		
		if(this.sceneId == null){
			DcQIndexPubStatusEnums status = DcQIndexPubStatusEnums.toEnum(pubStatus) ;
			
			indexConfigDTO = dcQIndexConfigService.getConfigBySysAndStatus(sysId, status) ;
			
			if(indexConfigDTO == null){
				return SUCCESS ;
			}
			//����
			sceneId = indexConfigDTO.getSceneId() ;
		}
		
		this.qindexFacade = dcSceneBO.getQIndexFacade(sceneId);
		
		return SUCCESS ;
	}

	

	public void setSysId(Long sysId) {
		this.sysId = sysId;
	}

	public void setPubStatus(String pubStatus) {
		this.pubStatus = pubStatus;
	}

	public DcQIndexConfigDTO getIndexConfigDTO() {
		return indexConfigDTO;
	}

	public DcSceneQIndexFacade getQindexFacade() {
		return qindexFacade;
	}



	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}

}
