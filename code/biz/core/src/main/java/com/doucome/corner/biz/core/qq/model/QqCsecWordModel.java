package com.doucome.corner.biz.core.qq.model;

/**
 * ���д�
 * @author shenjia.caosj 2012-8-2
 *
 */
public class QqCsecWordModel {
	
	public static final String IS_DIRTY = "is_dirty";
	
	public static final String IS_LOST = "is_lost" ;
	
	public static final String MSG = "msg";

	/**
	 * �ж��ı����Ƿ������дʡ�0��û�У� 1���С�
	 */
	private boolean isDirty ;
	
	/**
	 * �ж��Ƿ������ݶ�ʧ�����Ӧ�ò�ʹ��cache������Ҫ���Ĵ˲�����
0���߲����أ�û�����ݶ�ʧ�����Ի��档
1���в������ݶ�ʧ����󣬲�Ҫ���档
	 */
	private boolean isLost ;
	
	/**
	 * ������󣬷��ش�����Ϣ��
		����ɹ���
		��1������ı��к��и߶����дʻ��ʱ����ֱ�ӷ���"�ı��������д�"�������ر�*�������ı���
		��2������ı��к���������������дʻ㣬�����д��滻��*��Ȼ���ı����ء�
	 */
	private String msg ;

	public boolean isDirty() {
		return isDirty;
	}

	public void setDirty(boolean isDirty) {
		this.isDirty = isDirty;
	}

	public boolean isLost() {
		return isLost;
	}

	public void setLost(boolean isLost) {
		this.isLost = isLost;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
