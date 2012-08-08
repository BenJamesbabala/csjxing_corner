/**
 * Copyright (C) 1998-2009 TENCENT Inc.All Rights Reserved.		
 * 																	
 * FileName��Base64Util.java					
 *			
 * Description��base64������빤��
 * History��
 *  1.0  2009-04-01        Create	
 */

package com.paipai.api.util;

import java.io.IOException;

import sun.misc.BASE64Encoder;

/**
 * base64������빤��
 * 
 */
public class Base64Util
{
	public static void main(String[] args)
	{
		byte[] arrByte = new byte[1];
		arrByte[0] = 0;
		System.out.println("Dict:");
		for(byte i = 0; i < 64; i++)
		{
			System.out.print(String.format("%2d %c\t", i, Base64Util.encode(arrByte).charAt(0)));
			arrByte[0] += 4;
			if((i + 1) % 8 == 0)
			{
				System.out.println("");
			}
		}
		System.out.print(Base64Util.encode("12334jjgfddgh".getBytes()));
	}

	/**
	 * �����������ݱ���Ϊbase64�ַ���
	 * @param srcContent ��Ҫ���������
	 * @return String ���������������Ϊnull���򷵻�null��
	 */
	public static String encode(byte[] srcContent)
	{
		if(srcContent == null)
		{
			return null;
		}
		//sun��ʵ�ֻ�ÿ76���ַ���������һ���س�����Ҫɾ����
		return new BASE64Encoder().encode(srcContent).replace("\r\n", "");
	}

	/**
	 * �����������ݱ���Ϊ������URL�������ַ�����
	 * ����Ĭ��base64�ֵ��е�+��=��URL�����У���������ĺ��壬��Ҫ��ת��
	 * ��+ת��Ϊ-
	 * ��=ת��Ϊ~
	 * @param srcContent ��Ҫ����ΪURL����������
	 * @return String ���������������Ϊnull���򷵻�null��
	 */
	public static String encodeURLParam(byte[] srcContent)
	{
		return encode(srcContent).replace('+', '-').replace('=', '~');
	}

	/**
	 * ��base64�ַ�������ΪԴ��������
	 * ��encode��Ϊ����Ĺ���
	 * @param base64Code base64�����ַ���
	 * @return byte[] ���������������Ϊnull�����ʧ�ܣ��򷵻�null��
	 */
	public static byte[] decode(String base64Code)
	{
		if(base64Code == null)
		{
			return null;
		}
		
		try
		{
			return new sun.misc.BASE64Decoder().decodeBuffer(base64Code);
		}
		catch(IOException exception)
		{
			return null;
		}
	}

	/**
	 * ��base64�ַ�������ΪԴ��������
	 * ��encodeURLParam��Ϊ����Ĺ���
	 * @param base64Code base64�����ַ���
	 * @return byte[] ���������������Ϊnull�����ʧ�ܣ��򷵻�null��
	 */
	public static byte[] decodeURLParam(String base64Code) throws IOException
	{
		return decode(base64Code.replace('-', '+').replace('~', '='));
	}
}
