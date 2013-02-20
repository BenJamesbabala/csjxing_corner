package com.doucome.corner.biz.cache;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Future;

import com.doucome.corner.biz.core.exception.CacheFailedException;

/**
 * 
 * @author langben 2012-2-11
 *
 */
public interface CacheClient {
			
		
	/**
	 * ͨ��ͬ����ʽ��������, �����ݽ��滻��������
	 * �÷���Ч�ʲ����첽��ʽ��, ��������ȷ����, ����ʹ���첽����
	 * @param key
	 * @param value
	 * @return �Ƿ񱣴�ɹ�
	 */
	boolean put(final String key, Serializable value);
	/**
	 * ͨ��ͬ����ʽ��������, �����ݽ��滻��������
	 * �÷���Ч�ʲ����첽��ʽ��, ��������ȷ����, ����ʹ���첽����
	 * @param key
	 * @param value
	 * @param expireTime ����ʱ��,��λ milliseconds
	 * @return �Ƿ�ɹ�
	 */
	boolean put(final String key, Serializable value, long expireTime);
	/**
	 * ͨ����ֵ��ȡ�������
	 * ͬ������
	 * @param <T>
	 * @param key
	 * @return ����Ķ���, ���û��, ���� null
	 */
	<T> T get(final String key);
	
	/**
	 * ͨ��ͬ���ķ�ʽ������ȡ���
	 * @param keys
	 * @return
	 */
	Map<String, Object> getBulk(String... keys);
	
	/**
	 * ͨ��ͬ���ķ�ʽ������ȡ���
	 * @param keys
	 * @return
	 */
	Map<String, Object> getBulk(Collection<String> keys);
	
	/**
	 * ͬ����ʽɾ��key 
	 * @param key
	 * @return
	 */
	boolean delete(String key);

	/**
	 * Increment the given key by the given amount.
	 * @param key
	 * @param by
	 * @return -1, if the key is not found, the value after incrementing otherwise
	 */
	long incr(String key, int by);
	/**
	 * Decrement the given key by the given amount
	 * @param key
	 * @param by
	 * @return -1, if the key is not found, the value after decrementing otherwise
	 */
	long decr(String key, int by);
	
	/**
	 * ��������, �����������, ��ǰ�µ�ֵ��������Ч
	 * @param <T> �������ݵ�����
	 * @param key
	 * @param value
	 * @return �Ƿ񱣴�ɹ�
	 */
	<T> boolean putIfAbsent(final String key , final T value) throws CacheFailedException;
	
	/**
	 * ��������, �����������, ��ǰ�µ�ֵ��������Ч
	 * @param <T> �������ݵ�����
	 * @param key
	 * @param value
	 * @param expireTime ����ʱ��,��λ milliseconds
	 * @return �Ƿ񱣴�ɹ�
	 */
	<T> boolean putIfAbsent(final String key , final T value , final long expireTime) throws CacheFailedException;
	
	/**
	 * ����Ϊ�첽�ӿ�
	 */
	
	/**
	 * ͨ���첽��ʽ��������, �����������, ��ǰ�µ�ֵ��������Ч
	 * ��ͨ��future.get()��ȡ���ʱ, ��ǰ�̲߳Żᱻblock
	 * @param <T> �������ݵ�����
	 * @param key
	 * @param value
	 * @return �Ƿ񱣴�ɹ�
	 */
	<T> Future<Boolean> asyncPutIfAbsent(final String key, final T value);
	/**
	 * ͨ���첽��ʽ��������, �����������, ��ǰ�µ�ֵ��������Ч
	 * ��ͨ��future.get()��ȡ���ʱ, ��ǰ�̲߳Żᱻblock
	 * @param <T> �������ݵ�����
	 * @param key
	 * @param value
	 * @param expireTime ����ʱ�� ,��λ milliseconds
	 * @return �Ƿ񱣴�ɹ�
	 */
	<T> Future<Boolean> asyncPutIfAbsent(final String key, final T value, final long expireTime);
	
	/**
	 * ͨ���첽��ʽ��������, �����ݽ��滻��������
	 * ��ͨ��future.get()��ȡ���ʱ, ��ǰ�̲߳Żᱻblock
	 * @param <T> �������ݵ�����
	 * @param key 
	 * @param value
	 * @return �Ƿ񱣴�ɹ�
	 */
	<T> Future<Boolean> asyncPut(final String key, final T value);
	/**
	 * ͨ���첽��ʽ��������, �����ݽ��滻��������
	 * ��ͨ��future.get()��ȡ���ʱ, ��ǰ�̲߳Żᱻblock
	 * @param <T> �������ݵ�����
	 * @param key
	 * @param value
	 * @param expireTime ����ʱ�� ,��λ milliseconds
	 * @return �Ƿ񱣴�ɹ�
	 */
	<T> Future<Boolean> asyncPut(final String key, final T value, long expireTime);
	
	/**
	 * ͨ���첽��ʽ��ȡ�������
	 * @param <T>
	 * @param key
	 * @return ����Ķ���, ���û��, ���� null
	 */
	<T> Future<T> asyncGet(final String key);
	/**
	 * ͨ���첽��ʽ������ȡ���
	 * @param <T> ���ؽ��������
	 * @param keys
	 * @return
	 */
	Future<Map<String, Object>> asyncGetBulk(final String... keys);
	/**
	 * ͨ���첽��ʽ������ȡ���
	 * @param keys
	 * @return
	 */
	Future<Map<String, Object>> asyncGetBulk(final Collection<String> keys);
	/**
	 * ͨ���첽��ʽɾ���ض��Ļ���
	 * @param key
	 * @return
	 */
	Future<Boolean> asyncDelete(final String key);
	
}
