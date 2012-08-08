package com.doucome.corner.web.bops.action.dcome.qq;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.business.DcSceneBO;
import com.doucome.corner.biz.dcome.model.DcSceneDTO;
import com.doucome.corner.web.bops.action.BopsBasicAction;

/**
 * …Ë÷√ ◊“≥
 * @author shenjia.caosj 2012-7-26
 *
 */
@SuppressWarnings("serial")
public class IndexConfigAction  extends BopsBasicAction {

	private Long sceneId ;
	
	private DcSceneDTO sceneDTO ;
	
	@Autowired
	private DcSceneBO dcSceneBO ;
	
	@Override
	public String execute() throws Exception {
		
		if(sceneId == null){
			return INPUT ;
		}
		
		this.sceneDTO = dcSceneBO.getSceneJoinItemsById(sceneId) ;
		
		return super.execute();
	}

	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}

	public DcSceneDTO getSceneDTO() {
		return sceneDTO;
	}
	
	
}
