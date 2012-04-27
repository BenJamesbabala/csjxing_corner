package com.doucome.corner.biz.core.model.page;

import com.doucome.corner.biz.core.model.AbstractModel;

/**
 * 分页Bean
 * 
 * @author shenjia.caosj 2012-1-12
 * 
 */
public class Pagination extends AbstractModel {

	/**
	 * 默认每页20
	 */
	private static final int DEFAULT_SIZE = 20;

	@SuppressWarnings("unused")
	private Pagination() {

	}

	/**
	 * 
	 * @param start
	 *            页 start from 1
	 * @param size
	 *            每页记录数
	 */
	public Pagination(int page, int size) {
		this.page = page;
		this.size = size;
		normalizePagination() ;
	}

	public Pagination(int page) {
		this(page, DEFAULT_SIZE);
	}

	/**
	 * 开始记录数,start from 1
	 */
	protected int page;

	/**
	 * 一次取出记录条数
	 */
	protected int size;

	/**
	 * 总页数
	 */
	protected int totalPages = -1 ;
	
	/**
	 * 最大页数
	 */
	protected int maxPages = -1 ;
	
	public int getMaxPages() {
		return maxPages;
	}

	public void setMaxPages(int maxPages) {
		this.maxPages = maxPages;
		normalizePagination() ;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
		normalizePagination() ;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
		normalizePagination() ;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
		normalizePagination() ;
	}
	
	public int getStart(){
		int start = (this.page - 1) * size + 1 ;
		if(start < 1){
			start = 1 ;
		}
		return start ;
	}

	/**
	 * 检查页数和size
	 */
	private void normalizePagination(){
		if(maxPages != -1){
			if(maxPages < 1){
				maxPages = 1 ;
			}
		}
		if(size < 1){
			size = DEFAULT_SIZE ;
		}
		if(totalPages != -1){
			if(maxPages != -1){
				if(totalPages > maxPages){
					totalPages = maxPages ;
				}
			}
			if(page > totalPages){
				page = totalPages ;
			}
			
		}
		if(maxPages != -1){
			if(page > maxPages){
				page = maxPages ;
			}
		}
		if(page < 1){
			page = 1 ;
		}
		
	}
	
	/**
	 * 计算总页数
	 * 
	 * @param pagination
	 * @param totalRecords
	 * @return 总页数
	 */
	public static int calcTotalPages(Pagination pagination, long totalRecords) {
		int size = pagination.getSize();// 页大小
		long pages = (totalRecords + size - 1) / size;
		if (pages < 0) {
			pages = 0;
		}
		if (pages > Integer.MAX_VALUE) {
			pages = Integer.MAX_VALUE;
		}
		return (int) pages;
	}

}
