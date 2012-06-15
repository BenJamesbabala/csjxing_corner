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
     * ��ȡ�û������ۿ۵�֧����id��ǰ̨ʹ�ã�
     * 
     * @return
     */
    public String getAlipayId();

    /**
     * ��¼��������չʾʹ��
     * 
     * @return
     */
    public String getLoginId();

    /**
     * ��ǰ��¼�û���uid
     * 
     * @return
     */
    public String getUid();

    /**
     * ��ȡ�û����ʺ���Ϣ����̨ʹ�ã�
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
    
    /**
     * �Ƿ��ڲ��û�
     * @return true:��/false����
     */
    public boolean isPrivateUser();

}
