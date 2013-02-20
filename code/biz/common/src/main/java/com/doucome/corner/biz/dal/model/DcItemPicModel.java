package com.doucome.corner.biz.dal.model;

/**
 * DcItem 图片封装类
 * @author langben 2012-11-17
 *
 */
public class DcItemPicModel extends AbstractModel {

	/**
	 * 图片URL
	 */
	private String url ;
	
	/**
	 * 图片高度属性，多个“,”隔开
	 * <p>这里的高度是和宽度的比例（宽度100px为基数，如120代表宽度100时，高度为120）</p>
	 */
	private int heightProp ;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getHeightProp() {
		return heightProp;
	}

	public void setHeightProp(int heightProp) {
		this.heightProp = heightProp;
	}
	
	public int getHeight(int width){
		if(heightProp <= 0){
			return width ;
		}
		return width * heightProp / 100 ;
	}
}
