package com.doucome.corner.biz.dal.dcome;

import com.doucome.corner.biz.dal.dataobject.dcome.DcItemDO;

import java.util.List;

/**
 * 商品信息同步dao.
 * @author ze2200
 *
 */
public interface DcItemSyncDAO {
	
	List<DcItemDO> getDcItems();
	
	Integer updateDcItems(List<DcItemDO> dcItems);
}
