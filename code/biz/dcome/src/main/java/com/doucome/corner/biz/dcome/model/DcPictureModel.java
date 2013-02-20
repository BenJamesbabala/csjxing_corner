package com.doucome.corner.biz.dcome.model;

import java.awt.Dimension;

import com.doucome.corner.biz.dal.model.DcItemPicModel;
import com.doucome.corner.biz.dcome.utils.DcItemUtils;

public class DcPictureModel {

	private String path ;
	
	private Dimension dimension;
	
	public DcItemPicModel toItemPicModel(){
		DcItemPicModel picModel = new DcItemPicModel() ;
		picModel.setUrl(path) ;
		int heightProp = DcItemUtils.getItemPicHeightProp(dimension) ;
		picModel.setHeightProp(heightProp) ;
		return picModel ;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}
	
}
