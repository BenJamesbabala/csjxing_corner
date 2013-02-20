package com.doucome.corner.biz.apps.namefate.business;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.apps.namefate.enums.NamefateUserGuideEnums;
import com.doucome.corner.biz.apps.namefate.model.NamefateUserDTO;
import com.doucome.corner.biz.apps.namefate.service.NamefateUserService;
import com.doucome.corner.biz.common.utils.NumberUtils;

public class NamefateUserBO {

	@Autowired
	private NamefateUserService namefateUserService ;
	
	public void updateGuideDone(NamefateUserDTO user , NamefateUserGuideEnums guide) {
		long oldValue = NumberUtils.parseLong(user.getNewGuide()) ;
		long doneValue = guide.getDoneValue(oldValue) ;
		namefateUserService.updateNewGuide(user.getUserId(), doneValue) ;
	}
}
