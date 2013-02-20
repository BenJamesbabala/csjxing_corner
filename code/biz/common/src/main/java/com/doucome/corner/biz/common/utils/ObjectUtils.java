package com.doucome.corner.biz.common.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.ReflectionUtils;

import com.doucome.corner.biz.dal.dataobject.dcome.DcUserExchangeApproveDO;

public class ObjectUtils {
	
	private static final Log log = LogFactory.getLog(ObjectUtils.class) ;

	public static Object trimStringProperties(Object t){
		if(t == null){
			return t ;
		}
		
		Class clz = t.getClass() ;
		
		Field[] fieldList = clz.getDeclaredFields() ;
		
		if(fieldList == null || fieldList.length == 0){
			return t ;
		}
		
		
		for(Field f : fieldList){
			if(f.getType() == String.class){
				PropertyDescriptor sd = null ;
				try {
					sd = new PropertyDescriptor(f.getName(),clz);
					Method getMethod = sd.getReadMethod();//获得get方法
					Object val = ReflectionUtils.invokeMethod(getMethod, t) ;
					if(val == null){
						continue ;
					}
					Method setMethod = sd.getWriteMethod() ;
					String strVal = StringUtils.trim((String)val) ;
					ReflectionUtils.invokeMethod(setMethod, t , strVal) ;
				} catch (IntrospectionException e) {
					log.error(e.getMessage() , e) ;
				} catch (Exception e){
					log.error(e.getMessage() , e) ;
				}
			}
		}
		
		return t ;
	}
	
	public static void main(String[] args) {
		DcUserExchangeApproveDO d = new DcUserExchangeApproveDO() ;
		d.setCheckCode("   123432324   ") ;
		d.setDelQq("243546465") ;
		d.setDelAddr("         defef") ;
		Object o = ObjectUtils.trimStringProperties(d) ;
		System.out.println(o);
	}
}
