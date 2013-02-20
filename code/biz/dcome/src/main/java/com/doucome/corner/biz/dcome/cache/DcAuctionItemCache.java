package com.doucome.corner.biz.dcome.cache;

import com.doucome.corner.biz.dcome.model.DcAuctionItemDTO;

/**
 * ÆÀÂÛ»º´æ
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
	 * »º´æ»ý·ÖÅÄÂô.
	 * @param auctionItem
	 * @return
	 */
	boolean cacheAuctionItem(DcAuctionItemDTO auctionItem);
	/**
	 * ÒÆ³ý»º´æ
	 * @param id
	 * @return
	 */
	boolean removeAuctionItem(Long id);
}
