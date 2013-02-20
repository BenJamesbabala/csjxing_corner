package com.doucome.corner.web.horoscope.context;

/**
 * 
 * @author ze2200
 *
 */
public class HsAuthzContext {

    /**
     * 是否需要重写cookie
     */
    private boolean isRewriteCookie = false;
    
    private Long userId;
    
    private String userNick;
    
    private String openId;
    
    private String openKey;
    
    private String pf;

	public boolean isRewriteCookie() {
		return isRewriteCookie;
	}

	public void setRewriteCookie(boolean isRewriteCookie) {
		this.isRewriteCookie = isRewriteCookie;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getOpenKey() {
		return openKey;
	}

	public void setOpenKey(String openKey) {
		this.openKey = openKey;
	}

	public String getPf() {
		return pf;
	}

	public void setPf(String pf) {
		this.pf = pf;
	}
}