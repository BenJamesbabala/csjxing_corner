package com.doucome.corner.biz.dcome.business;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.ImageUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.CalendarUtils;
import com.doucome.corner.biz.core.exception.UpyunException;
import com.doucome.corner.biz.core.service.upyun.UpYunService;
import com.doucome.corner.biz.core.service.upyun.UpYunUtils;
import com.doucome.corner.biz.core.service.upyun.model.UpyunDataEntity;
import com.doucome.corner.biz.core.utils.ArrayStringUtils;
import com.doucome.corner.biz.core.utils.AvatarUtils;
import com.doucome.corner.biz.core.utils.DcHttpUtils;
import com.doucome.corner.biz.dcome.model.DcPictureModel;

/**
 * 
 * @author langben 2012-7-23
 *
 */
public class DcImageUploadBO implements InitializingBean {
	
	private static final Log log = LogFactory.getLog(DcImageUploadBO.class) ;
	
	private List<String> allowExtensionsList ;
	
	private String allowExtensions ;
	
	/**
	 * ͷ�����50KB
	 */
	private static final int AVATAR_MAX_SIZE = 50 * 1024;
	
	/**
	 * ͼƬ���500KB
	 */
	private static final int IMAGE_MAX_SIZE = 500 * 1024;
	
	@Autowired
	private UpYunService upYunService ;
	
	private String imgBucketname ;
	
	private String itemBucketname ;
	
	/**
	 * �ռ��ϴ���ROOT��ַ
	 */
	private String uploadRoot ;
	
	/**
	 * ��ƷͼƬ�ķ��ʵ�ַ
	 */
		
	@Override
	public void afterPropertiesSet() throws Exception {
		this.allowExtensionsList = ArrayStringUtils.toList(this.allowExtensions) ;
		if(this.allowExtensionsList == null){
			this.allowExtensionsList = new ArrayList<String>() ;
		}
	}
	
	/**
	 * �ϴ��û�ͷ�� ���ܴ���50KB
	 * @param userId 
	 * @param imgUrl Զ�˵��û�ͷ��
	 */
	public void uploadUserAvatarFromUrl(long userId , String imgUrl) throws UpyunException {
		if(userId < 0){
			throw new IllegalArgumentException("userId max greater than 0!") ;
		}

		HttpClient client = new DefaultHttpClient() ;
		
		HttpEntity entry = null ;
		try {		
			HttpGet get = new HttpGet(imgUrl) ;
			HttpResponse response = client.execute(get) ;
			int statusCode = response.getStatusLine().getStatusCode() ;
			if(statusCode != 200){
				throw new UpyunException("get resource error , errcode :" + statusCode) ;
			}
			entry = response.getEntity() ;
			long len = entry.getContentLength() ;
			if(len > AVATAR_MAX_SIZE){
				throw new IllegalArgumentException("avatar max size is 20KB.") ;
			}
			InputStream is = entry.getContent();  
			byte[] imgBuffer = UpYunUtils.inputStream2Buf(is , (int)entry.getContentLength());
			if(imgBuffer == null){
				throw new IllegalArgumentException("img url is not correct .") ;
			}
			
			String toPath = AvatarUtils.buildAvatarPath(userId) ;
			
			//���������ϴ�
			UpyunDataEntity upyunEntity = new UpyunDataEntity() ;
			upyunEntity.setBucketname(imgBucketname) ;
			upyunEntity.setData(imgBuffer) ;
			upyunEntity.setToPath(toPath) ;
			upYunService.upload(upyunEntity) ;
		}catch (UpyunException e) {
			throw e ;
		}catch(Exception e){
			throw new UpyunException(e.getMessage() , e ) ;
		}finally {
			try {
				EntityUtils.consume(entry);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * �ϴ���ƷͼƬ
	 * <ul>
	 * 	<li>���֧��500KB��ͼƬ�ϴ�</li>
	 * 	<li>ֻ֧�� jpg,gif,png,bmp��׺����ͼƬ
	 * </ul>
	 * @param filePath ͼƬ�ͻ���·��
	 * @return ͼƬ����Ե�ַ��Path�� ,���� 
	 * <p>ͼƬ��ַ��Ӧ �� http://img.doucome.com/item/0/5/4/0545454554.jpg �� �򷵻ص��� /item/0/5/4/0545454554.jpg</p>
	 */
	public DcPictureModel uploadItemPicture(File file, String extName) throws UpyunException {
		if(file == null){
			throw new IllegalArgumentException("null file!") ;
		}
		//�Ƿ�Ϸ���׺
		if(!isAllowExtension(extName)){
			throw new IllegalArgumentException("allow extension is only : " + allowExtensions) ;
		}
		
		long fileSize = file.length() ;		
		
		if(fileSize > IMAGE_MAX_SIZE || fileSize <= 0){
			throw new IllegalArgumentException("IMAGE max size is 500KB.") ;
		}
		
		try {		
			byte[] data = FileUtils.readFileToByteArray(file);
			
			Calendar cal = Calendar.getInstance() ;
			
			StringBuilder sb = new StringBuilder("/product/")
				.append(CalendarUtils.getYear(cal)).append(CalendarUtils.format00(CalendarUtils.getMonth(cal)))
				.append("/")
				.append(CalendarUtils.format00(CalendarUtils.getDay(cal))).append("/");

			//�ļ���
			String imageName = genFileName(cal , file.getPath() , extName) ;
			
			sb.append(imageName) ;
			String toPath = buildUploadPath(sb.toString()) ;
			
			//���������ϴ�
			UpyunDataEntity upyunEntity = new UpyunDataEntity() ;
			upyunEntity.setBucketname(itemBucketname) ;
			upyunEntity.setData(data) ;
			upyunEntity.setToPath(toPath) ;
			upYunService.upload(upyunEntity) ;
			DcPictureModel picModel = new DcPictureModel();
			picModel.setPath(toPath) ;
			
			picModel.setDimension(ImageUtils.getImageDimension(new FileInputStream(file), Workbook.PICTURE_TYPE_JPEG)) ;
			
			return picModel ;
		}catch (UpyunException e) {
			throw e ;
		}catch(Exception e){
			throw new UpyunException(e.getMessage() , e ) ;
		}
	}
	
	/**
	 * ��URL�ϴ�ͼƬ
	 * @param pictureUrl
	 * @return ͼƬ����Ե�ַ��Path�� ,���� 
	 * <p>ͼƬ��ַ��Ӧ �� http://img.doucome.com/item/0/5/4/0545454554.jpg �� �򷵻ص��� /item/0/5/4/0545454554.jpg</p>
	 */
	public DcPictureModel uploadItemPictureFromUrl(String pictureUrl){
		if(StringUtils.isBlank(pictureUrl)){
			throw new IllegalArgumentException("pictureUrl is blank !") ;
		}
		
		String ext = FilenameUtils.getExtension(pictureUrl) ;
		//�Ƿ�Ϸ���׺
		if(!isAllowExtension(ext)){
			throw new IllegalArgumentException("allow extension is only : " + allowExtensions) ;
		}
		
		HttpClient client = new DefaultHttpClient() ;
		
		HttpEntity entry = null ;
		try {		
			HttpGet get = new HttpGet(pictureUrl) ;
			HttpResponse response = client.execute(get) ;
			int statusCode = response.getStatusLine().getStatusCode() ;
			if(statusCode != 200){
				throw new UpyunException("get resource error , errcode :" + statusCode) ;
			}
			entry = response.getEntity() ;
			long len = entry.getContentLength() ;
			if(len > IMAGE_MAX_SIZE){
				throw new IllegalArgumentException("IMAGE max size is 500KB.") ;
			}
			DcPictureModel picModel = new DcPictureModel();
			InputStream is = entry.getContent();  
			
			byte[] imgBuffer = UpYunUtils.inputStream2Buf(is , (int)entry.getContentLength());
			if(imgBuffer == null){
				throw new IllegalArgumentException("img url is not correct .") ;
			}
			
			Calendar cal = Calendar.getInstance() ;
			
			StringBuilder sb = new StringBuilder("/product/")
				.append(CalendarUtils.getYear(cal)).append(CalendarUtils.format00(CalendarUtils.getMonth(cal)))
				.append("/")
				.append(CalendarUtils.format00(CalendarUtils.getDay(cal))).append("/");

			//�ļ���
			String imageName = genFileName(cal , pictureUrl , ext) ;
			
			sb.append(imageName) ;
			String toPath = buildUploadPath(sb.toString()) ;
			
			//���������ϴ�
			UpyunDataEntity upyunEntity = new UpyunDataEntity() ;
			upyunEntity.setBucketname(itemBucketname) ;
			upyunEntity.setData(imgBuffer) ;
			upyunEntity.setToPath(toPath) ;
			upYunService.upload(upyunEntity) ;
		
			
			picModel.setPath(toPath) ;
			InputStream picIs = DcHttpUtils.getInputStream(pictureUrl) ;
			picModel.setDimension(ImageUtils.getImageDimension(picIs, Workbook.PICTURE_TYPE_JPEG)) ;
			
			return picModel ;
		}catch (UpyunException e) {
			throw e ;
		}catch(Exception e){
			throw new UpyunException(e.getMessage() , e ) ;
		} finally {
			try {
				EntityUtils.consume(entry);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ����ͼƬ������
	 * @param time
	 * @param hashObject
	 * @param ext
	 * @return
	 */
	private String genFileName(Calendar time , String hashObject , String ext){
		StringBuilder sb = new StringBuilder() ;
		int hash = hashObject.hashCode() ;
		if(hash < 0){
			hash = hash * - 1;
		}
		sb.append(CalendarUtils.format00(CalendarUtils.getHour(time))).append(CalendarUtils.format00(CalendarUtils.getMinute(time))).append(CalendarUtils.format00(CalendarUtils.getSecond(time))).append(hash) ;
		sb.append(".").append(ext) ;
		return sb.toString() ;
	}
	
	private String buildUploadPath(String toPath){
		return uploadRoot + toPath ;
	}
	
	private boolean isAllowExtension(String ext){
		ext = StringUtils.lowerCase(ext) ;
		for(int i=0 ;i<allowExtensionsList.size();i++){
			String allow = allowExtensionsList.get(i) ;
			if(StringUtils.equals(allow, ext)){
				return true ;
			}
		}
		return false ;
	}

	

	public void setImgBucketname(String imgBucketname) {
		this.imgBucketname = imgBucketname;
	}

	public void setItemBucketname(String itemBucketname) {
		this.itemBucketname = itemBucketname;
	}

	public void setUploadRoot(String uploadRoot) {
		this.uploadRoot = uploadRoot;
	}

	public void setAllowExtensions(String allowExtensions) {
		this.allowExtensions = allowExtensions;
	}

	
	public static void main(String[] args) throws FileNotFoundException {
		Dimension dimension = ImageUtils.getImageDimension(new FileInputStream(new File("d:/11111.JPG")), org.apache.poi.ss.usermodel.Workbook.PICTURE_TYPE_PNG) ;
		System.out.println(dimension);
	}
}
