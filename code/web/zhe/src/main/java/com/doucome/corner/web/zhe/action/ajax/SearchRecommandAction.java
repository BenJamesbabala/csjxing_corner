package com.doucome.corner.web.zhe.action.ajax;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.doucome.corner.biz.core.service.taobao.TaobaoRecommandDecorator;
import com.doucome.corner.biz.core.taobao.dto.TaobaoFavoriteItemDTO;
import com.doucome.corner.biz.core.taobao.enums.TaobaoRecommendTypeEnums;
import com.doucome.corner.biz.core.taobao.model.TaobaoRecommendItemCondition;
import com.doucome.corner.biz.zhe.model.TaobaokeItemFacade;
import com.doucome.corner.biz.zhe.service.DdzTaobaoRecommendService;
import com.doucome.corner.web.common.action.BasicAction;
import com.doucome.corner.web.zhe.model.JsonModel;

/**
 * 类SearchRecommandAction.java的实现描述：查询推荐
 * 
 * @author ib 2012-4-15 下午05:17:14
 */
public class SearchRecommandAction extends BasicAction {

    private static final int                    MAX_RECOMMAND_SIZE     = 40;
    private static final int                    DEFAULT_RECOMMAND_SIZE = 20;

    @Autowired
    private TaobaoRecommandDecorator            taobaoRecommandDecorator;

    @Autowired
    private DdzTaobaoRecommendService           ddzTaobaoRecommendService;

    /** 输入参数 */
    private String                              id;

    private Integer                             size;

    /** 输出 */
    private JsonModel<List<TaobaokeItemFacade>> json                   = new JsonModel<List<TaobaokeItemFacade>>();

    @Override
    public String execute() throws Exception {
        if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
            TaobaoRecommendItemCondition recommendCondition = new TaobaoRecommendItemCondition();
            if (size == null || size < 0) {
                size = DEFAULT_RECOMMAND_SIZE;
            } else {
                size = size > MAX_RECOMMAND_SIZE ? MAX_RECOMMAND_SIZE : size;
            }
            recommendCondition.setCount(size.longValue());
            recommendCondition.setRecommendType(TaobaoRecommendTypeEnums.SAME_STYLE);            
            List<TaobaoFavoriteItemDTO> recoList = taobaoRecommandDecorator.getRecommandItemsByItem(Long.valueOf(id) ,recommendCondition);
            List<TaobaokeItemFacade> recommendList = ddzTaobaoRecommendService.batchConventer(recoList, null);
            json.setData(recommendList);
            json.setCode(JsonModel.CODE_SUCCESS);
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
