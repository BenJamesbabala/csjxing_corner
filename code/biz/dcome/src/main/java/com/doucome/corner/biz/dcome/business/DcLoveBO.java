package com.doucome.corner.biz.dcome.business;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import com.doucome.corner.biz.dal.dataobject.dcome.DcLoveDetailDO;
import com.doucome.corner.biz.dcome.exception.DuplicateLoveException;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.biz.dcome.service.DcLoveDetailService;

/**
 * ϲ��
 * @author shenjia.caosj 2012-7-22
 *
 */
public class DcLoveBO {
	
	private static final Log log  = LogFactory.getLog(DcLoveBO.class) ;
	
	@Autowired
	private DcItemService dcItemService ;
	
	@Autowired
	private DcLoveDetailService dcLoveDetailService ;
	
	/**
	 * �û����ϲ��
	 * @param userId
	 * @param itemId
	 * @throws DuplicateLoveException �Ѿ���ӹ�ϲ��
	 */
	public void addLove(long userId , long itemId) throws DuplicateLoveException {

		DcLoveDetailDO detail = dcLoveDetailService.getDetailByUserAndItem(userId, itemId) ;
		if(detail != null){ //�Ѿ���ӹ�ϲ��
			throw new DuplicateLoveException() ;
		}
		try {
			dcLoveDetailService.createDetail(itemId, userId) ;
			dcItemService.incrLoveCount(itemId) ;
		} catch(DuplicateKeyException e){
			//�ظ�ϲ��
			throw new DuplicateLoveException() ;
		} catch (Exception e) {
			log.error(e.getMessage() , e) ;
		}
	}
}
