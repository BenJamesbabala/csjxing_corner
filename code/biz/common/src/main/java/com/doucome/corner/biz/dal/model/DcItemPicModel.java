package com.doucome.corner.biz.dal.model;

/**
 * DcItem ͼƬ��װ��
 * @author langben 2012-11-17
 *
 */
public class DcItemPicModel extends AbstractModel {

	/**
	 * ͼƬURL
	 */
	private String url ;
	
	/**
	 * ͼƬ�߶����ԣ������,������
	 * <p>����ĸ߶��ǺͿ�ȵı��������100pxΪ��������120������100ʱ���߶�Ϊ120��</p>
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
