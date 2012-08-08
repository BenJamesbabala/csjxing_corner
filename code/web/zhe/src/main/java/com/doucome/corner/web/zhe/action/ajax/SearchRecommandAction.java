package com.doucome.corner.web.zhe.action.ajax;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.zhe.model.TaobaokeItemFacade;
import com.doucome.corner.biz.zhe.service.DdzTaobaokeService;
import com.doucome.corner.web.common.action.BasicAction;
import com.doucome.corner.web.common.model.JsonModel;

/**
 * ��SearchRecommandAction.java��ʵ����������ѯ�Ƽ�
 * 
 * @author ib 2012-4-15 ����05:17:14
 */
public class SearchRecommandAction extends BasicAction {

    private static final int                    MAX_RECOMMAND_SIZE     = 40;
    private static final int                    DEFAULT_RECOMMAND_SIZE = 20;

    @Autowired
    private DdzTaobaokeService ddzTaobaokeService ;
   


    /** ������� */
    private String                              id;

    private Integer                             size;

    /** ��� */
    private JsonModel<List<TaobaokeItemFacade>> json                   = new JsonModel<List<TaobaokeItemFacade>>();

    @Override
    public String execute() throws Exception {
        if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
            //FIXME //�Ƽ�
        }
        return SUCCESS;
    }

    public JsonModel<List<TaobaokeItemFacade>> getJson() {
        return json;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

}
