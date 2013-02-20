package com.doucome.corner.biz.dal.dataobject.dcome;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 豆蔻用户
 * 
 * @author langben 2012-7-21
 */
public class DcUserDO extends AbstractModel {

	private static final long serialVersionUID = 3922526789052919961L;

	/**
     * 系统生成的UserId
     */
    private Long   userId;
    /**
     * 外部平台id
     */
    private String externalId;
    /**
     * 来源平台
     */
    private String externalPf;
    /**
     * 昵称
     */
    private String nick;

    /**
     * 性别
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
     * 密码
     */
    private String md5Password;

    /**
     * 会员来源
     */
    private String source;
    
    /**
     * 积分点
     */
    private Integer integralCount ;
    /**

     * 冻结的积分
     */
    private Integer frozenIntegralCount ;
    
    /**
     * 连续签到天数
     */
    private Integer checkInCount;
    /**

     * 金蛋个数
     */
    private Integer goldEggCount ;
    
    /**
     * 未读消息
     */
    private Integer unreadMsg ;
    
    /**
     * 新手指引
     */
    private Long newGuide;
    
    private Integer level;
    /**
     * 老虎机得分
     */
    private Integer winnerScore ;
    
    /**
     * 支付宝账号
     */
    private String alipayAccount ;
    
    /**
     * 扩展描述(Map类型的JSON字符串)
     */
    private String extendDesc ;
    
    private Date   gmtModified;

    private Date   gmtCreate;
    
    /**
     * 上次登陆时间
     */
    private Date   gmtLastLogin;
    
    /**
     * 上次签到时间
     */
    private Date gmtLastCheckin ;
    /**
     * 上次分享时间.
     */
    private Date gmtLastShare;
    
    /**
     * 登陆信息
     */
    private String lastLoginTrace ;
    /**
     * 关注空间时间
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
