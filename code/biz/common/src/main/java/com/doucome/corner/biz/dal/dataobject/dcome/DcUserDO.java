package com.doucome.corner.biz.dal.dataobject.dcome;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * ��ޢ�û�
 * 
 * @author langben 2012-7-21
 */
public class DcUserDO extends AbstractModel {

	private static final long serialVersionUID = 3922526789052919961L;

	/**
     * ϵͳ���ɵ�UserId
     */
    private Long   userId;
    /**
     * �ⲿƽ̨id
     */
    private String externalId;
    /**
     * ��Դƽ̨
     */
    private String externalPf;
    /**
     * �ǳ�
     */
    private String nick;

    /**
     * �Ա�
     */
    private String gender;

    /**
     * mobile
     */
    private String mobile;

    /**
     * email
     */
    private String email;

    /**
     * ����
     */
    private String md5Password;

    /**
     * ��Ա��Դ
     */
    private String source;
    
    /**
     * ���ֵ�
     */
    private Integer integralCount ;
    /**

     * ����Ļ���
     */
    private Integer frozenIntegralCount ;
    
    /**
     * ����ǩ������
     */
    private Integer checkInCount;
    /**

     * �𵰸���
     */
    private Integer goldEggCount ;
    
    /**
     * δ����Ϣ
     */
    private Integer unreadMsg ;
    
    /**
     * ����ָ��
     */
    private Long newGuide;
    
    private Integer level;
    /**
     * �ϻ����÷�
     */
    private Integer winnerScore ;
    
    /**
     * ֧�����˺�
     */
    private String alipayAccount ;
    
    /**
     * ��չ����(Map���͵�JSON�ַ���)
     */
    private String extendDesc ;
    
    private Date   gmtModified;

    private Date   gmtCreate;
    
    /**
     * �ϴε�½ʱ��
     */
    private Date   gmtLastLogin;
    
    /**
     * �ϴ�ǩ��ʱ��
     */
    private Date gmtLastCheckin ;
    /**
     * �ϴη���ʱ��.
     */
    private Date gmtLastShare;
    
    /**
     * ��½��Ϣ
     */
    private String lastLoginTrace ;
    /**
     * ��ע�ռ�ʱ��
     */
    private Date gmtFollowQzone;

    public String getLastLoginTrace() {
		return lastLoginTrace;
	}

	public void setLastLoginTrace(String lastLoginTrace) {
		this.lastLoginTrace = lastLoginTrace;
	}

	public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getExternalPf() {
        return externalPf;
    }

    public void setExternalPf(String externalPf) {
        this.externalPf = externalPf;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMd5Password() {
        return md5Password;
    }

    public void setMd5Password(String md5Password) {
        this.md5Password = md5Password;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtLastLogin() {
        return gmtLastLogin;
    }

    public void setGmtLastLogin(Date gmtLastLogin) {
        this.gmtLastLogin = gmtLastLogin;
    }

	public Integer getIntegralCount() {
		return integralCount;
	}

	public void setIntegralCount(Integer integralCount) {
		this.integralCount = integralCount;
	}

	public Integer getCheckInCount() {
		return checkInCount;
	}

	public void setCheckInCount(Integer checkInCount) {
		this.checkInCount = checkInCount;
	}

	public Integer getGoldEggCount() {
		return goldEggCount == null ? 0: goldEggCount;
	}

	public void setGoldEggCount(Integer goldEggCount) {
		this.goldEggCount = goldEggCount;
	}

	public Date getGmtLastCheckin() {
		return gmtLastCheckin;
	}

	public void setGmtLastCheckin(Date gmtLastCheckin) {
		this.gmtLastCheckin = gmtLastCheckin;
	}

	public Integer getUnreadMsg() {
		return unreadMsg;
	}

	public void setUnreadMsg(Integer unreadMsg) {
		this.unreadMsg = unreadMsg;
	}

	public Long getNewGuide() {
		return newGuide;
	}

	public void setNewGuide(Long newGuide) {
		this.newGuide = newGuide;
	}

	public Date getGmtLastShare() {
		return gmtLastShare;
	}

	public void setGmtLastShare(Date gmtLastShare) {
		this.gmtLastShare = gmtLastShare;
	}

	public Integer getFrozenIntegralCount() {
		return frozenIntegralCount;
	}

	public void setFrozenIntegralCount(Integer frozenIntegralCount) {
		this.frozenIntegralCount = frozenIntegralCount;
	}
	

	public Date getGmtFollowQzone() {
		return gmtFollowQzone;
	}

	public void setGmtFollowQzone(Date gmtFollowQzone) {
		this.gmtFollowQzone = gmtFollowQzone;
	}

	public Integer getWinnerScore() {
		return winnerScore;
	}

	public void setWinnerScore(Integer winnerScore) {
		this.winnerScore = winnerScore;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getAlipayAccount() {
		return alipayAccount;
	}

	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}

	public String getExtendDesc() {
		return extendDesc;
	}

	public void setExtendDesc(String extendDesc) {
		this.extendDesc = extendDesc;
	}
}
