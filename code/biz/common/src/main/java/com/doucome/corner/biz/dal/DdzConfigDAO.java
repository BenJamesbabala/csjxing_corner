package com.doucome.corner.biz.dal;

import java.util.List;

import com.doucome.corner.biz.dal.dataobject.DdzConfigDO;

/**
 * 类DdzConfigDAO.java的实现描述：配置
 * 
 * @author ib 2012-6-23 下午06:58:35
 */
public interface DdzConfigDAO {

    public List<DdzConfigDO> queryForModule(String module);

}
