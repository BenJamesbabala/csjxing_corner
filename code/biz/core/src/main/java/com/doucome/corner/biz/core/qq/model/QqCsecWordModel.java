package com.doucome.corner.biz.core.qq.model;

/**
 * 敏感词
 * @author shenjia.caosj 2012-8-2
 *
 */
public class QqCsecWordModel {
	
	public static final String IS_DIRTY = "is_dirty";
	
	public static final String IS_LOST = "is_lost" ;
	
	public static final String MSG = "msg";

	/**
	 * 判断文本中是否有敏感词。0：没有； 1：有。
	 */
	private boolean isDirty ;
	
	/**
	 * 判断是否有数据丢失。如果应用不使用cache，不需要关心此参数。
0或者不返回：没有数据丢失，可以缓存。
1：有部分数据丢失或错误，不要缓存。
	 */
	private boolean isLost ;
	
	/**
	 * 如果错误，返回错误信息。
		如果成功：
		（1）如果文本中含有高度敏感词汇的时候，则直接返回"文本中有敏感词"，不返回被*替代后的文本；
		（2）如果文本中含有其它级别的敏感词汇，则将敏感词替换成*，然后将文本返回。
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
