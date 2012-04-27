package com.doucome.corner.web.bops.authz;

import com.doucome.corner.biz.dal.dataobject.BopsAdminDO;
import com.doucome.corner.biz.dal.dataobject.DdzAccountDO;
import com.doucome.corner.biz.dal.dataobject.DdzUserDO;

/**
 * ��Authz.java��ʵ����������¼��֤��Ϣ
 * 
 * @author ib 2012-3-24 ����01:27:16
 */
public interface BopsAuthz {

    /**
     * �ж��û��Ƿ��¼״̬
     * 
     * @return
     */
    public boolean isLogin();
    
    public String getAdminId();

    /**
     * ��ȡ�ʺ���Ϣ
     * 
     * @return
     */
    public BopsAdminDO getAdminDO();


}
