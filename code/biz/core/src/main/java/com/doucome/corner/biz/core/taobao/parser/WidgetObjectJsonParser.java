package com.doucome.corner.biz.core.taobao.parser;

import com.taobao.api.ApiException;
import com.taobao.api.TaobaoParser;
import com.taobao.api.TaobaoResponse;
import com.taobao.api.internal.mapping.Converter;

/**
 * ����JSON�����������
 * 
 * @author carver.gu
 * @since 1.0, Apr 11, 2010
 */
public class WidgetObjectJsonParser<T extends TaobaoResponse> implements TaobaoParser<T> {

	private Class<T> clazz;

	public WidgetObjectJsonParser(Class<T> clazz) {
		this.clazz = clazz;
	}

	public T parse(String rsp) throws ApiException {
		Converter converter = new WidgetJsonConverter();
		return converter.toResponse(rsp, clazz);
	}

	public Class<T> getResponseClass() {
		return clazz;
	}

}
