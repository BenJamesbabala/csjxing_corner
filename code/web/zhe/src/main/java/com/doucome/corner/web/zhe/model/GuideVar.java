package com.doucome.corner.web.zhe.model;

import com.doucome.corner.biz.core.model.AbstractModel;

/**
 * ����ָ��
 * @author shenjia.caosj 2012-5-10
 *
 */
public class GuideVar extends AbstractModel {
	
	
	

	public GuideVar(boolean newGuide, boolean step1, boolean step2,
			boolean step3, boolean step4) {
		super();
		this.newGuide = newGuide;
		this.step1 = step1;
		this.step2 = step2;
		this.step3 = step3;
		this.step4 = step4;
	}

	/**
	 * ����
	 */
	private boolean newGuide ;
	
	/**
	 * ��һ��
	 */
	private boolean step1 ;
	
	/**
	 * �ڶ���
	 */
	private boolean step2 ;
	
	/**
	 * ������
	 */
	private boolean step3 ;
	
	/**
	 * ���Ĳ�
	 */
	private boolean step4 ;

	public boolean isNewGuide() {
		return newGuide;
	}

	public void setNewGuide(boolean newGuide) {
		this.newGuide = newGuide;
	}

	public boolean isStep1() {
		return step1;
	}

	public void setStep1(boolean step1) {
		this.step1 = step1;
	}

	public boolean isStep2() {
		return step2;
	}

	public void setStep2(boolean step2) {
		this.step2 = step2;
	}

	public boolean isStep3() {
		return step3;
	}

	public void setStep3(boolean step3) {
		this.step3 = step3;
	}

	public boolean isStep4() {
		return step4;
	}

	public void setStep4(boolean step4) {
		this.step4 = step4;
	}

	
	
}
