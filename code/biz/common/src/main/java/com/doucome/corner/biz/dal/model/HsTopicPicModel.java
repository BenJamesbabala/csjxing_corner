package com.doucome.corner.biz.dal.model;

/**
 * –«ª∞Ã‚Õº∆¨
 * @author ze2200
 *
 */
public class HsTopicPicModel extends AbstractModel {

	private static final long serialVersionUID = 1L;

	/**
	 * Õº∆¨URL
	 */
	private String path;
	
	private int width;
	
	private int height;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
