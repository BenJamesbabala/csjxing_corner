package com.doucome.corner.biz.core.utils;


/**
 * 位操作工具
 * @author langben 2012-9-6
 *
 */
public class BitOperateUtils {
	
	private static final long long0 = 0L ;
	private static final long long1 = 1L ;
	
	public static enum BitEnums {
		bit0 , bit1 ;
		public boolean toBoolean(){
			if(this == bit0){
				return false ;
			} else {
				return true ;
			}
		}
	}
	
	public static BitEnums getBit(long value , int index) {
		
		if(index > 62 || index < 0){
			throw new IllegalArgumentException("index out of bounds 0 - 62 :" + index) ;
		}
		
		long moveValue = value >> index ;
		
		long newValue = (moveValue & long1) ;
		
		return newValue == 0 ?  BitEnums.bit0 : BitEnums.bit1;
	}
	
	/**
	 * 
	 * @param value 原始值
	 * @param index start from 0
	 * @param setTo 0 | 1
	 * @return 设置后的值
	 */
	public static long setBit(long value , int index , BitEnums setTo){
		if(index > 62 || index < 0){
			throw new IllegalArgumentException("index out of bounds 0 - 62 :" + index) ;
		}
		long moveValue = (long1 << index) ;
		if(setTo == BitEnums.bit1) {
			return value | moveValue ;
		} else {
			moveValue = ~moveValue;
			return value & moveValue ;
		}
	}

	public static void main(String[] args) {
		long value = 0L ;
		//value =  BitOperateUtils.setBit(value, 3, BitEnums.bit1) ;
		long newValue = BitOperateUtils.setBit(value, 62, BitEnums.bit1) ;
		newValue = BitOperateUtils.setBit(newValue, 6, BitEnums.bit1) ;
		newValue = BitOperateUtils.setBit(newValue, 0, BitEnums.bit1) ;
		newValue = BitOperateUtils.setBit(newValue, 16, BitEnums.bit1) ;
		System.out.println(Long.toString(newValue,2));
		System.out.println(BitOperateUtils.getBit(newValue, 62)) ;
	}
}
