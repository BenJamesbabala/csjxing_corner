package com.doucome.corner.biz.dal.dcome;

import com.doucome.corner.biz.dal.dataobject.dcome.DcItemDO;

import java.util.List;

/**
 * ��Ʒ��Ϣͬ��dao.
 * @author ze2200
 *
 */
public interface DcItemSyncDAO {
	
	List<DcItemDO> getDcItems();
	
	Integer updateDcItems(List<DcItemDO> dcItems);
}
