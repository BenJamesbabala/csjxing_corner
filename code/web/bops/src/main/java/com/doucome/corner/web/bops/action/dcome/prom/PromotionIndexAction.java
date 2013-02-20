package com.doucome.corner.web.bops.action.dcome.prom;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.model.DcPromotionDTO;
import com.doucome.corner.biz.dcome.service.DcPromotionService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 活动创建.
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class PromotionIndexAction extends BopsBasicAction implements ModelDriven<DcPromotionDTO> {
	
	private DcPromotionDTO promotion = new DcPromotionDTO();
	
	@Autowired
	private DcPromotionService dcPromotionService;
	
	private static final Log logger = LogFactory.getLog(PromotionIndexAction.class);
	
	public String execute() {
		
		
		return SUCCESS;
	}
	
	@Override
	public DcPromotionDTO getModel() {
		return this.promotion;
	}
}
