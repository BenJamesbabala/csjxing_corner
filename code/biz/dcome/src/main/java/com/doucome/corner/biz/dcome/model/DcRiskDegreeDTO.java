package com.doucome.corner.biz.dcome.model;

import com.doucome.corner.biz.dal.model.AbstractModel;
import com.doucome.corner.biz.dcome.enums.DcBlockDegreeEnums;

/**
 * 
 * @author ib 2012-8-13 ����12:55:11
 */
public class DcRiskDegreeDTO extends AbstractModel{

    private static final long serialVersionUID = -133529983388086740L;

    /**
     * �Ƿ���Ҫ����
     */
    private boolean            isNeedToBlock;

    /**
     * ���صȼ�
     */
    private DcBlockDegreeEnums degree;

    public boolean isNeedToBlock() {
        return isNeedToBlock;
    }

    public void setNeedToBlock(boolean isNeedToBlock) {
        this.isNeedToBlock = isNeedToBlock;
    }

    public DcBlockDegreeEnums getDegree() {
        return degree;
    }

    public void setDegree(DcBlockDegreeEnums degree) {
        this.degree = degree;
    }

}
