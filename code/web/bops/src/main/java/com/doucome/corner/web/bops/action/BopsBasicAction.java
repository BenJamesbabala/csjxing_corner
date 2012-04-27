package com.doucome.corner.web.bops.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.web.bops.authz.BopsAuthz;
import com.opensymphony.xwork2.ActionSupport;

public abstract class BopsBasicAction extends ActionSupport {

    @Autowired
    private BopsAuthz bopsAuthz;

    public BopsAuthz getBopsAuthz() {
        return bopsAuthz;
    }

}
