package com.doucome.corner.web.zhe.authz;

import com.doucome.corner.biz.dal.dataobject.DdzAccountDO;
import com.doucome.corner.biz.dal.dataobject.DdzUserDO;

/**
 * ��Authz.java��ʵ����������¼��֤��Ϣ
 * 
 * @author ib 2012-3-24 ����01:27:16
 */
public interface DdzAuthz {

    /**
     * �ж��û��Ƿ��¼״̬
     * 
     * @return
     */
    public boolean isLogin();

    /**
     * ��ȡ�û���֧����id
     * 
     * @return
     */
    public String getAlipayId();

    public String getUid(); 

    /**
     * ��ȡ�ʺ���Ϣ
     * 
     * @return
     */
    public DdzAccountDO getAccount();

    /**
     * ��ȡUser��Ϣ
     * 
     * @return
     */
    public DdzUserDO getUser();

}
