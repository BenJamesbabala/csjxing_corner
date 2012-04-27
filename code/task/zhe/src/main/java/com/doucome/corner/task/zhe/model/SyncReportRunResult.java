package com.doucome.corner.task.zhe.model;

import java.util.ArrayList;
import java.util.List;

import com.doucome.corner.biz.core.model.AbstractModel;
import com.doucome.corner.biz.core.utils.JacksonHelper;

/**
 * ͬ���������н�����Ľ��
 * 
 * @author shenjia.caosj 2012-4-3
 * 
 */
public class SyncReportRunResult {

	private SyncReportCodeEnums code = SyncReportCodeEnums.SUCCESS;

	private String detailMsg;

	/**
	 * �ܹ��ı����Ա��
	 */
	private int totalCount;

	/**
	 * �ɹ��ı����Ա��
	 */
	private int successCount;

	/**
	 * ͬ��ʧ�ܵ�ҳ��
	 */
	private List<Page> failPages;

	public SyncReportCodeEnums getCode() {
		return code;
	}

	public void setCode(SyncReportCodeEnums code) {
		this.code = code;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(int successCount) {
		this.successCount = successCount;
	}

	public boolean isSuccess() {
		return code == SyncReportCodeEnums.SUCCESS;
	}

	public String getDetailMsg() {
		return detailMsg;
	}

	public void setDetailMsg(String detailMsg) {
		this.detailMsg = detailMsg;
	}

	public List<Page> getFailPages() {
		return failPages;
	}

	public void setFailPages(List<Page> failPages) {
		this.failPages = failPages;
	}

	public static void main(String[] args) {
		SyncReportRunResult r = new SyncReportRunResult();
		r.setCode(SyncReportCodeEnums.ASSIGN_ERROR);
		r.setDetailMsg("fewfewfewgregre") ;
		r.setSuccessCount(1);
		r.setTotalCount(100);
		List<Page> failPages = new ArrayList<Page>() ;
		failPages.add(new Page(1,2,3)) ;
		failPages.add(new Page(1,2,3)) ;
		failPages.add(new Page(1,2,3)) ;
 		r.setFailPages(failPages) ;
		System.out.println(r);

	}

	public static class Page extends AbstractModel {

		public Page(int page, int start, int size) {
			this.page = page;
			this.start = start;
			this.size = size;
		}

		private int page;
		private int start;
		private int size;

		public int getPage() {
			return page;
		}

		public void setPage(int page) {
			this.page = page;
		}

		public int getStart() {
			return start;
		}

		public void setStart(int start) {
			this.start = start;
		}

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

	}

	@Override
	public String toString() {
		return JacksonHelper.toJSON(this) ;
	}
}
