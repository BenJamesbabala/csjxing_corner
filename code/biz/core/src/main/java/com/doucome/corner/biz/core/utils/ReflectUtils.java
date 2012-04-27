package com.doucome.corner.biz.core.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import org.springframework.util.ReflectionUtils;

public class ReflectUtils {

	public static <T> void reflectTo(T src , T target){
		
		Class clazz = target.getClass() ;
		Field[] fields = src.getClass().getDeclaredFields() ;
		for(Field field : fields){
			String fieldName = field.getName() ;
			Field targetField = ReflectionUtils.findField(clazz, fieldName) ;
			if(targetField == null){
				continue ;
			}
			PropertyDescriptor sourceDescriptor = null ;
			try {
				sourceDescriptor = new PropertyDescriptor(fieldName,src.getClass());
			} catch (IntrospectionException e) {
				continue ;
			}
			Method getMethod = sourceDescriptor.getReadMethod();//获得get方法
			Object sourceValue = ReflectionUtils.invokeMethod(getMethod, src) ;
			if(sourceValue == null){
				continue ;
			}
			Class targetType = targetField.getType() ;
			Class sourceType = field.getType() ;
			PropertyDescriptor targetDescriptor = null ;
			try {
				targetDescriptor = new PropertyDescriptor(fieldName, target.getClass()) ;
			} catch (IntrospectionException e) {
				continue ;
			}
			Method setMethod = targetDescriptor.getWriteMethod() ;
			if(setMethod != null){
				if(targetType.equals(sourceType)){
					ReflectionUtils.invokeMethod(setMethod, target, sourceValue) ;
				}else if(targetType.equals(String.class)){
					ReflectionUtils.invokeMethod(setMethod, target, String.valueOf(sourceValue)) ;
				}else if(targetType.equals(BigDecimal.class)){
					ReflectionUtils.invokeMethod(setMethod , target , new BigDecimal(String.valueOf(sourceValue))) ;
				}else{
					//未知
				}
			}
		}
	}
	
	public static <T> boolean setField(T clazz, String fieldName, Object value) {
		Field field = null; 
		PropertyDescriptor descriptor = null;
		try {
			descriptor = new PropertyDescriptor(fieldName, clazz.getClass());
		} catch (IntrospectionException e) {
			return false;
		}
		Method method = descriptor.getWriteMethod();
		try {
			ReflectionUtils.invokeMethod(method, clazz, value);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
