package com.doucome.corner.web.dcome.action.frame.q;

import com.doucome.corner.biz.core.model.URLModel;
import com.doucome.corner.biz.core.utils.HttpUrlHelper;
import com.doucome.corner.web.common.utils.TaobaoUrlUtils;

/**
 * ·µÀû²éÑ¯Ò³Ãæ
 * 
 * @author langben 2013-1-1
 * 
 */
@SuppressWarnings("serial")
public class DcTbSearchAction extends QBasicAction {

	private String wd;

	private String id;

	private String domain;

	@Override
	public String execute() throws Exception {

		URLModel model = HttpUrlHelper.parseURL(wd);
		if (model != null) {
			// taobao url
			id = TaobaoUrlUtils.parseItemId(model);
			domain = model.getHost();
		}

		return "item" ;
	}

	public void setWd(String wd) {
		this.wd = wd;
	}

	public String getId() {
		return id;
	}

	public String getDomain() {
		return domain;
	}

}
