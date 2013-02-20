package com.doucome.corner.web.dcome.action.frame.q;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.dal.condition.dcome.DcCategorySearchCondition;
import com.doucome.corner.biz.dal.condition.dcome.DcItemSearchCondition;
import com.doucome.corner.biz.dcome.enums.DcItemGenWayEnums;
import com.doucome.corner.biz.dcome.enums.DcItemStatusEnum;
import com.doucome.corner.biz.dcome.model.DcCategoryDTO;
import com.doucome.corner.biz.dcome.service.DcCategoryService;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.web.dcome.authz.constants.DcPromotypeConstants;

/**
 * QQ ¿Õ¼äÊ×Ò³
 * @author langben 2012-7-21
 *
 */
@SuppressWarnings("serial")
public class DcPgcItemsAction extends QBasicAction {
//	private Map<Long,DcItemDTO> itemMap;
	private int firstPage;
	private int totalPage;
	
	private List<DcCategoryDTO> categories;
		
	@Autowired
	private DcItemService dcItemService ;
	@Autowired
	private DcCategoryService dcCategoryService;
	
	@Override
	public String execute() throws Exception {
		
		DcItemSearchCondition itemCondition = new DcItemSearchCondition() ;
		itemCondition.setGenWay(DcItemGenWayEnums.PROFESSIONAL.getValue()) ;
		itemCondition.setItemStatus(DcItemStatusEnum.NORMAL.getValue());
		long totalSize = dcItemService.countItemsWithPagination(itemCondition);
		totalPage = getTotalPage(totalSize, 15);
		firstPage = getItemPage(totalSize);
		DcCategorySearchCondition condition = new DcCategorySearchCondition();
		//condition.setCategoryLevel("0");
		categories = dcCategoryService.getCategories(condition);
		return SUCCESS ;
	}
	
	private int getTotalPage(long totalSize,int size){
	    Pagination pagination = new Pagination(1,size);
	    return Pagination.calcTotalPages(pagination, totalSize);
	}

    private int getItemPage(long totalSize) {
        String factor = dcAuthz.getPromotype(DcPromotypeConstants.INDEX_ITEM_FACTOR);
        try {
            if (StringUtils.isNotBlank(factor)) {
                Integer page = Integer.valueOf(factor);
                return page;
            }
        } catch (Exception e) {
        }
        int random = RandomUtils.nextInt(10) + 1;
        if (random * 12 > totalSize) {
            random = 1;
        }
        dcAuthz.addPromotype(DcPromotypeConstants.INDEX_ITEM_FACTOR, String.valueOf(random));
        return random;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }
    
    public int getTotalPage() {
        return totalPage;
    }
    
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    
    public List<DcCategoryDTO> getCategories() {
    	return this.categories;
    }

}
