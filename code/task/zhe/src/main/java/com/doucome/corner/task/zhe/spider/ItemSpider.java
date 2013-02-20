package com.doucome.corner.task.zhe.spider;

import com.doucome.corner.biz.dal.dataobject.SpiderConfigDO;
import com.doucome.corner.biz.dal.dataobject.SpiderItemDO;

public interface ItemSpider {

	public int spider(SpiderConfigDO config);

	public boolean insertSpiderItemDO(SpiderItemDO spiderItemDO);
}
