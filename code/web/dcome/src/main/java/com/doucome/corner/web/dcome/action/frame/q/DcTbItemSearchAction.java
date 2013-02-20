package com.doucome.corner.web.dcome.action.frame.q;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.core.enums.OutCodeEnums;
import com.doucome.corner.biz.core.taobao.dto.TaobaoItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.core.utils.OutCodeUtils;
import com.doucome.corner.biz.dcome.service.DcTaobaoService;
import com.doucome.corner.web.dcome.model.TaobaokeItemFacade;

/**
 * 返利查询页面
 * @author langben 2013-1-1
 *
 */
@SuppressWarnings("serial")
public class DcTbItemSearchAction extends QBasicAction {

	@Autowired
	private DcTaobaoService dcTaobaoService ;
	
	private String id ; 
	
	private TaobaokeItemFacade taobaokeItemFacade ;
	
	private TaobaoItemDTO taobaoItemDTO ;
	
	@Override
	public String execute() throws Exception {
		
		long userId = dcAuthz.getUserId() ;
		
		if(IDUtils.isNotCorrect(userId)){
			return SUCCESS ;
		}
		
		if (StringUtils.isBlank(id) || !StringUtils.isNumeric(id)) { // 商品ID有问题
            return SUCCESS;
        }
		
		
		String outCode = OutCodeUtils.encodeOutCode(String.valueOf(userId), OutCodeEnums.DOUCOME_USER_ID) ;
		
		TaobaokeItemDTO itemDTO = dcTaobaoService.conventItem(id, outCode) ;
		
		if(itemDTO != null){
			taobaokeItemFacade = new TaobaokeItemFacade(itemDTO) ;
		} else {
			taobaoItemDTO = dcTaobaoService.getTaobaoItem(id) ;
		}
		
		return SUCCESS ;
	}

	public TaobaokeItemFacade getTaobaokeItemFacade() {
		return taobaokeItemFacade;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TaobaoItemDTO getTaobaoItemDTO() {
		return taobaoItemDTO;
	}

	public String getId() {
		return id;
	}
	
}
