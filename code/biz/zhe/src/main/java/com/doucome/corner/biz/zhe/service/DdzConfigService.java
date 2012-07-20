package com.doucome.corner.biz.zhe.service;

import java.util.List;

import com.doucome.corner.biz.dal.dataobject.DdzConfigDO;

public interface DdzConfigService {

    public List<DdzConfigDO> queryForModule(String module);

}
