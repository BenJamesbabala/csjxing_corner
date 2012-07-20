package com.doucome.corner.web.zhe.action;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.doucome.corner.biz.dal.dataobject.DdzConfigDO;
import com.doucome.corner.biz.zhe.service.DdzConfigService;
import com.doucome.corner.web.common.action.BasicAction;

/**
 * 类SitemapAction.java的实现描述：生成sitemap使用的link
 * 
 * @author ib 2012-6-24 下午07:20:44
 */
@SuppressWarnings("serial")
public class SitemapAction extends BasicAction {

    @Autowired
    private DdzConfigService  ddzConfigService;
    private List<DdzConfigDO> configs;

    @Override
    public String execute() throws Exception {

        configs = ddzConfigService.queryForModule("sitemap");
        return SUCCESS;
    }

    public List<DdzConfigDO> getConfigs() {
        return configs;
    }

}
