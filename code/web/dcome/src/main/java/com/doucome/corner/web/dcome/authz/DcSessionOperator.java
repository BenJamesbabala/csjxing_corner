package com.doucome.corner.web.dcome.authz;

/**
 * @author ib 2012-7-29 обнГ05:06:32
 */
public interface DcSessionOperator {

    public boolean load(long userId, String nick, String password, String loginSource);

    public boolean unload();

}
