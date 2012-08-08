package com;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class UpYunDemo {
	
	public static void main(String[] args) throws Exception{
		/// ��ʼ���ռ�
		UpYun upyun = new UpYun("doucome-img", "langben", "caoshenjia");
		System.out.println("SDK �汾 "+upyun.version());
		/// �����Ƿ��ӡ������Ϣ
		upyun.debug = true;
		
		/// ��ȡ�ռ�ռ�ô�С
		//long x = upyun.getBucketUsage();
		
		/// ��ȡĳ��Ŀ¼�Ŀռ�ռ�ô�С
		//long x = upyun.getFolderUsage("/aa1");
		
		/// �ϴ��ļ�
		
		String url = "http://s4.img.guang.com/topic/2012/7/19/764_1_1703417679767_130X45.jpg" ;
		
		DefaultHttpClient client = new DefaultHttpClient() ;
		
		HttpGet get = new HttpGet(url) ;
				
		HttpResponse r = client.execute(get) ;
		
		HttpEntity entry = r.getEntity() ;
		
		InputStream is = entry.getContent();  
		byte[] b = inputstreamtobuf(is , (int)entry.getContentLength());
		
		EntityUtils.consume(entry);
				
		/// ���ô��ϴ��ļ��� Content-MD5 ֵ���������Ʒ�����յ����ļ�MD5ֵ���û����õĲ�һ�£����ر� 406 Not Acceptable ����
		upyun.setContentMD5(UpYun.md5(b));
		//upyun.setContentMD5(UpYun.md5(file));
		/// ���ô��ϴ��ļ��� ������Կ��ע�⣺��֧��ͼƬ�գ���������Կ���޷�����ԭ�ļ�URLֱ�ӷ��ʣ���� URL ������� ������ͼ�����־��+��Կ�� ���з��ʣ�
		/// ������ͼ�����־��Ϊ ! ����ԿΪ bac���ϴ��ļ�·��Ϊ /folder/test.jpg ����ô��ͼƬ�Ķ�����ʵ�ַΪ�� http://�ռ�����/folder/test.jpg!bac
		//upyun.setFileSecret("bac");
		
		System.out.println(upyun._writeBufferTo(b ,"/google2.jpg" , true));
		// System.out.println(upyun.writeFile("/a/b/c/google.jpg", file, true)); //���Զ���������Ŀ¼�����10����
		/// ��ȡ�ϴ��ļ������Ϣ����ͼƬ�ռ��з������ݣ�
		System.out.println(upyun.getWritedFileInfo("x-upyun-width")); // ͼƬ���
		System.out.println(upyun.getWritedFileInfo("x-upyun-height")); // ͼƬ�߶�
		System.out.println(upyun.getWritedFileInfo("x-upyun-frames")); // ͼƬ֡��
		System.out.println(upyun.getWritedFileInfo("x-upyun-file-type")); // ͼƬ����
		
		
		

		// ��ͨ�ϴ�ģʽ
		/////// upyun.writeFile("/11/22/33/test.txt", "test file content", true); /// �Ƿ��Զ���������Ŀ¼�����10����
		/*byte[] data = new byte[(int)file.length()];
		is.read(data, 0, (int)file.length());
		is.close();
		System.out.println(upyun.writeFile("/jp1299041532485000.jpg", data));*/
		
		/*System.out.println(upyun.writeFile("/test.txt", "test file content"));
		*/
		
		/*
		// ����������ģʽ�ϴ��ļ�����ʡ�ڴ棩
		/////// upyun.writeFile("/11/22/33/test.txt", file, true); /// �Ƿ��Զ���������Ŀ¼�����10����
		System.out.println(upyun.writeFile("/google-background.jpg", file));
		is.close();
		*/
		
		/// ��ȡ�ļ�
		// String datas = upyun.readFile("/test.txt");
		
		/// ��ȡ�ļ���Ϣ ���� new HashMap<String, String>(); �� null
		// System.out.println(upyun.getFileInfo("/google-background.jpg"));
		
		/// ����������ģʽ�����ļ�����ʡ�ڴ棩
		/*
		File file1 = new File("/tmp/test.jpg");
		System.out.println(upyun.readFile("/test.jpg", file1));
		*/
		
		/// ɾ���ļ�
		// upyun.deleteFile("/google-background.jpg")
		
		/// ����Ŀ¼
		// upyun.mkDir("/test");
		//// upyun.mkDir("/a/b/c/d/e/f/test"); //���Զ���������Ŀ¼�����10����
		
		/// ɾ��Ŀ¼
		// upyun.rmDir("/test");

		
		/// ��ȡĿ¼
		/*List<UpYun.FolderItem> items = upyun.readDir("/");
		for(int i=0;i<items.size();i++){
			System.out.println(items.get(i).name);
			System.out.println(items.get(i).type);
		}*/
	}
	
	
	
	public static byte[] inputstreamtobuf(InputStream ins , int l) throws Exception {
		int length = l ;
		byte[] content = new byte[l];
		int offset = 0 ;

		int bufsize = 4096 ;
		while(true){
			int end = offset + bufsize ;
			int readsize = ins.read(content,offset , bufsize) ;
			offset += readsize ;
			if(offset >= length){
				break ;
			}
			if(readsize == -1){
				break ;
			}
		}
		
		return content ;
	}
}
