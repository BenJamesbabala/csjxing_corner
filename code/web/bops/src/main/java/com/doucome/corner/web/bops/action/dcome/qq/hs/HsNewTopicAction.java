package com.doucome.corner.web.bops.action.dcome.qq.hs;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.utils.JacksonHelper;
import com.doucome.corner.biz.dal.model.HsTopicPicModel;
import com.doucome.corner.biz.dcome.business.DcImageUploadBO;
import com.doucome.corner.biz.dcome.model.DcPictureModel;
import com.doucome.corner.biz.dcome.model.star.HsTopicDTO;
import com.doucome.corner.biz.dcome.service.horoscope.HsTopicService;
import com.doucome.corner.biz.dcome.utils.HsUtils;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.opensymphony.xwork2.ModelDriven;

/**
 * »’‘À ∆
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class HsNewTopicAction extends BopsBasicAction implements ModelDriven<HsTopicDTO> {

	private HsTopicDTO hsTopic =  new HsTopicDTO() ;
	
	@Autowired
	private HsTopicService hsTopicService;
	
    private File picFile;
	
	private String picFileFileName;
	
	@Autowired
	private DcImageUploadBO dcImageUploadBO;
	
	@Override
	public String execute() {
		HsTopicPicModel picModel = uploadPic();
		if (hsTopic.getId() != null) {
			if (picModel != null) {
				hsTopic.setPicture(JacksonHelper.toJSON(picModel));
			}
			hsTopicService.updateHsTopic(hsTopic);
		} else {
			if (picModel == null) {
				return BOPS_ERROR;
			}
			hsTopic.setPicture(JacksonHelper.toJSON(picModel));
			hsTopicService.createHsTopic(hsTopic);
		}
		return SUCCESS ;
	}
	
	/**
	 * 
	 * @return
	 */
	private HsTopicPicModel uploadPic() {
		String extName = FilenameUtils.getExtension(picFileFileName);
		DcPictureModel picModel = dcImageUploadBO.uploadItemPicture(picFile, extName);
		if (picModel == null) {
			return null;
		}
		return HsUtils.convert(picModel);
	}
	
	@Override
	public HsTopicDTO getModel() {
		return hsTopic ;
	}

	public void setPicFile(File picFile) {
		this.picFile = picFile;
	}

	public String getPicFileFileName() {
		return picFileFileName;
	}

	public void setPicFileFileName(String picFileFileName) {
		this.picFileFileName = picFileFileName;
	}
}
