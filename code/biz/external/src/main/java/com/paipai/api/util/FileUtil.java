/**
 * Copyright (C) 1998-2009 TENCENT Inc.All Rights Reserved.		
 * 																	
 * FileName：FileUtil.java					
 *			
 * Description：文件工具类
 * History：
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
 * 文件工具类
 * 可读取HTTP文件和本地文件，可将byte数组内容写到本地文件。
 */
public class FileUtil {
	private final static int FILE_READ_BUFFER_SIZE = 4096;

    /**
     * 读取本地文件内容，到byte数组中
     * @param filePath 文件路径
     * @return 文件内容byte数组。
     * @throws IOException 读取文件异常
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
     * 根据HTTP URL读取文件内容，到byte数组中
     * @param fileUrl 文件URL
     * @param referer 文件引用URL，一般填null。<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;有些网站需要合适的referer才能通过防盗链检查，获得正确的文件。
     * @return 文件内容byte数组。
     * @throws IOException 读取文件异常
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
     * 将fileContent中的数据写入到filePath所指向的文件中
     * @param fileContent 文件内容byte数组
     * @param fileLen 文件内容长度，单位byte
     * @param filePath 目标文件名，包括路径。
     * @throws IOException 写文件异常
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
