package com.doucome.corner.biz.core.model.page;

import com.doucome.corner.biz.core.model.AbstractModel;

/**
 * ��ҳBean
 * 
 * @author shenjia.caosj 2012-1-12
 * 
 */
public class Pagination extends AbstractModel {

	/**
	 * Ĭ��ÿҳ20
	 */
	private static final int DEFAULT_SIZE = 20;

	@SuppressWarnings("unused")
	private Pagination() {

	}

	/**
	 * 
	 * @param start
	 *            ҳ start from 1
	 * @param size
	 *            ÿҳ��¼��
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
	 * ��ʼ��¼��,start from 1
	 */
	protected int page;

	/**
	 * һ��ȡ����¼����
	 */
	protected int size;

	/**
	 * ��ҳ��
	 */
	protected int totalPages = -1 ;
	
	/**
	 * ���ҳ��
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
	 * ���ҳ����size
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
	 * ������ҳ��
	 * 
	 * @param pagination
	 * @param totalRecords
	 * @return ��ҳ��
	 */
	public static int calcTotalPages(Pagination pagination, long totalRecords) {
		int size = pagination.getSize();// ҳ��С
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
