package com.doucome.corner.biz.dal;

import java.util.List;

import com.doucome.corner.biz.dal.dataobject.DdzConfigDO;

/**
 * ��DdzConfigDAO.java��ʵ������������
 * 
 * @author ib 2012-6-23 ����06:58:35
 */
public interface DdzConfigDAO {

    public List<DdzConfigDO> queryForModule(String module);

}
