package com.doucome.corner.biz.dcome.utils;

import com.doucome.corner.biz.dal.model.HsTopicPicModel;
import com.doucome.corner.biz.dcome.model.DcPictureModel;

/**
 * 
 * @author ze2200
 *
 */
public class HsUtils {
	/**
	 * 
	 * @param picModel
	 * @return
	 */
	public static HsTopicPicModel convert(DcPictureModel picModel) {
		if (picModel == null) {
			return null;
		}
		HsTopicPicModel hsPicModel = new HsTopicPicModel();
		hsPicModel.setPath(picModel.getPath());
		int width = (int) picModel.getDimension().getWidth();
		int height = (int) picModel.getDimension().getHeight();
		//width over hs bigest width
		if (width > 660) {
			height = 660 * height / width;
			width = 660;
		}
		hsPicModel.setWidth(width);
		hsPicModel.setHeight(height);
		return hsPicModel;
	}
	
}