package com.doucome.corner.biz.dcome.cache;

import com.doucome.corner.biz.dcome.model.DcAuctionItemDTO;

/**
 * ���ۻ���
 * @author langben 2012-7-28
 *
 */
public interface DcAuctionItemCache {
	/**
	 * 
	 * @param id
	 * @return
	 */
	DcAuctionItemDTO getAuctionItem(Long id);
	/**
	 * �����������.
	 * @param auctionItem
	 * @return
	 */
	boolean cacheAuctionItem(DcAuctionItemDTO auctionItem);
	/**
	 * �Ƴ�����
	 * @param id
	 * @return
	 */
	boolean removeAuctionItem(Long id);
}
