package com.doucome.corner.biz.zhe.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.dal.DdzConfigDAO;
import com.doucome.corner.biz.dal.dataobject.DdzConfigDO;
import com.doucome.corner.biz.zhe.service.DdzConfigService;

/**
 * ��DdzConfigServiceImpl.java��ʵ��������DdzConfigService��ʵ����
 * 
 * @author ib 2012-6-23 ����11:21:16
 */
public class DdzConfigServiceImpl implements DdzConfigService {

    private DdzConfigDAO ddzConfigDAO;

    /*
     * (non-Javadoc)
     * @see com.doucome.corner.biz.zhe.service.DdzConfigService#queryForModule(java.lang.String)
     */
    @Override
    public List<DdzConfigDO> queryForModule(String module) {
        if (StringUtils.isBlank(module)) {
            return null;
        }
        return ddzConfigDAO.queryForModule(module);
    }

    public void setDdzConfigDAO(DdzConfigDAO ddzConfigDAO) {
        this.ddzConfigDAO = ddzConfigDAO;
    }

}
