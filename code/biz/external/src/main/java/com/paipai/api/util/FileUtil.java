/**
 * Copyright (C) 1998-2009 TENCENT Inc.All Rights Reserved.		
 * 																	
 * FileName��FileUtil.java					
 *			
 * Description���ļ�������
 * History��
 *  1.0  2009-04-13    hokyhu    Create	
 */

package com.paipai.api.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * �ļ�������
 * �ɶ�ȡHTTP�ļ��ͱ����ļ����ɽ�byte��������д�������ļ���
 */
public class FileUtil {
	private final static int FILE_READ_BUFFER_SIZE = 4096;

    /**
     * ��ȡ�����ļ����ݣ���byte������
     * @param filePath �ļ�·��
     * @return �ļ�����byte���顣
     * @throws IOException ��ȡ�ļ��쳣
     */
    public static byte[] readLocalFile(String filePath) throws IOException
    {
    	ByteArrayOutputStream fileContent = null;
    	FileInputStream fileInputStream = null;
        try
        {
        	fileContent = new ByteArrayOutputStream();
        	File file = new File(filePath);
        	if(file.exists() && file.isDirectory())
        	{
        		throw new IOException("File '" + file + "' exists but is a directory");
        	}
        	fileInputStream = new FileInputStream(new File(filePath));
        	byte[] buffer = new byte[FILE_READ_BUFFER_SIZE];
            int n = 0;
            while(-1 != (n = fileInputStream.read(buffer)))
            {
            	fileContent.write(buffer, 0, n);
            }
        }
        finally
        {
            if(fileInputStream != null)
            {
            	fileInputStream.close();
            }
            if(fileContent != null)
            {
            	fileContent.close();
            }
        }
        return fileContent.toByteArray();
    }

    /**
     * ����HTTP URL��ȡ�ļ����ݣ���byte������
     * @param fileUrl �ļ�URL
     * @param referer �ļ�����URL��һ����null��<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��Щ��վ��Ҫ���ʵ�referer����ͨ����������飬�����ȷ���ļ���
     * @return �ļ�����byte���顣
     * @throws IOException ��ȡ�ļ��쳣
     */
    public static byte[] readHttpFile(String fileUrl, String referer) throws IOException
    {
    	InputStream inputStream = null;
    	ByteArrayOutputStream fileContent = null;

		try {
			URL url = new URL(fileUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			if(referer != null)
			{
				connection.setRequestProperty("Referer", referer);
			}
			inputStream = connection.getInputStream();
			fileContent = new ByteArrayOutputStream();
			byte[] buffer = new byte[FILE_READ_BUFFER_SIZE];
            int n = 0;
            while(-1 != (n = inputStream.read(buffer)))
            {
            	fileContent.write(buffer, 0, n);
            }
		}
        finally
        {
            if(inputStream != null)
            {
            	inputStream.close();
            }
            if(fileContent != null)
            {
            	fileContent.close();
            }
        }
        return fileContent.toByteArray();
    }

    /**
     * ��fileContent�е�����д�뵽filePath��ָ����ļ���
     * @param fileContent �ļ�����byte����
     * @param fileLen �ļ����ݳ��ȣ���λbyte
     * @param filePath Ŀ���ļ���������·����
     * @throws IOException д�ļ��쳣
     */
    public static void writeLocalFile(byte[] fileContent, int fileLen, String filePath) throws IOException
    {
    	FileOutputStream fileOutputStream = null;

		try
		{
			fileOutputStream = new FileOutputStream(filePath);
			fileOutputStream.write(fileContent, 0, fileLen);
		}
        finally
        {
            if(fileOutputStream != null)
            {
            	fileOutputStream.close();
            }
        }
    }
}
