package com.doucome.corner.biz.dal.dao.ibatis;

import com.doucome.corner.biz.dal.AlipayDao;
import com.doucome.corner.biz.dal.dataobject.AlipayItemDO;

public class IBatisAlipayDAO implements AlipayDao {

	@Override
	public Long logPayDetail(AlipayItemDO payDetail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long batchLogPayDetail(AlipayItemDO[] payDetail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updatePayResult(AlipayItemDO resultDetail) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean batchUpdatePayResult(AlipayItemDO[] resultDetails) {
		// TODO Auto-generated method stub
		return false;
	}

}
