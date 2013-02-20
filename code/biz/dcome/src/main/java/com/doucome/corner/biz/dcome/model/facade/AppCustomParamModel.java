package com.doucome.corner.biz.dcome.model.facade;

import com.doucome.corner.biz.core.utils.JacksonHelper;
import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 分享链接自定义json格式参数对应数据结构.
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class AppCustomParamModel extends AbstractModel {
	/**
	 * 用户id.
	 */
	private Long shareUserId;
	/**
	 * 分享内容.item/prom
	 */
	private String shareContent;
	
	private Long shareObjId;
	
	public AppCustomParamModel() {
		
	}

	public Long getShareUserId() {
		return shareUserId;
	}

	public void setShareUserId(Long shareUserId) {
		this.shareUserId = shareUserId;
	}

	public String getShareContent() {
		return shareContent;
	}

	public void setShareContent(String shareContent) {
		this.shareContent = shareContent;
	}

	public Long getShareObjId() {
		return shareObjId;
	}

	public void setShareObjId(Long shareObjId) {
		this.shareObjId = shareObjId;
	}
	
	public static void main(String[] args) {
		
		AppCustomParamModel model = JacksonHelper.fromJSON("{\"shareUserId\":10001,\"shareContent\":\"prom\",\"shareObjId\":585}", AppCustomParamModel.class);
		System.out.println(model);
	}
}
