package com.doucome.corner.web.bops.action.dcome.qq;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.business.DcImageUploadBO;
import com.doucome.corner.biz.dcome.model.DcPictureModel;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.bops.model.JsonModel;

/**
 * Í¼Æ¬ÉÏ´«.
 * @author ze2200 2012-7-25
 *
 */
@SuppressWarnings("serial")
public class PicUploadAjaxAction extends BopsBasicAction {
	
	private JsonModel<String> result = new JsonModel<String>();
	
	private File picFile;
	
	private String picFileFileName;
	
	private String picFileContentType;
	
	@Autowired
	private DcImageUploadBO dcImageUploadBO;
	
	private static final Log logger = LogFactory.getLog(PicUploadAjaxAction.class);
	
	@Override
	public String execute() throws Exception {
		String picUrl = null;
		try {
			String extName = FilenameUtils.getExtension(picFileFileName);
			DcPictureModel model = dcImageUploadBO.uploadItemPicture(picFile, extName);
			if(model != null){
				picUrl = model.getPath() ;
			}

			result.setCode(JsonModel.CODE_SUCCESS);
			result.setData(picUrl);
		} catch(IllegalArgumentException e) {
			//image problem
			logger.error(e);
			result.setCode(JsonModel.CODE_ILL_ARGS);
			result.setDetail(e.getMessage());
		} catch (Exception e) {
			//image server exception.
			logger.error(e);
			result.setCode(JsonModel.CODE_FAIL);
			result.setDetail(e.getMessage());
		}
		
		return SUCCESS ;
	}
	
	public JsonModel<String> getResult() {
		return this.result;
	}

	public File getPicFile() {
		return picFile;
	}

	public void setPicFile(File picFile) {
		this.picFile = picFile;
	}
	
	public void setPicFileFileName(String picFileFileName) {
		this.picFileFileName = picFileFileName;
	}

	public void setPicFileContentType(String picFileContentType) {
		this.picFileContentType = picFileContentType;
	}
}
