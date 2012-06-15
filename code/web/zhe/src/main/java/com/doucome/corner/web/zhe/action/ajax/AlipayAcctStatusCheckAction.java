package com.doucome.corner.web.zhe.action.ajax;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.doucome.corner.biz.core.utils.JacksonHelper;
import com.doucome.corner.biz.core.utils.ValidateUtil;
import com.doucome.corner.web.zhe.action.DdzBasicAction;
import com.doucome.corner.web.zhe.model.AlipayAcctStatusCheck;
import com.doucome.corner.web.zhe.model.JsonModel;

/**
 * 
 * @author shenjia.caosj 2012-4-27
 *
 */
@SuppressWarnings("serial")
public class AlipayAcctStatusCheckAction extends DdzBasicAction {

	private static final String url = "https://memberprod.alipay.com/account/reg/acctStatusCheck.json" ;
	
	private String acctname ;
	
	private static final String _input_charset = "utf-8" ;
	
	private JsonModel<AlipayAcctStatusCheck> json = new JsonModel<AlipayAcctStatusCheck> () ;
	
	/**
	 * {"nick":"blu***","acctstatus":"taobaouser","acctType":"email","stat":"ok"}
	 * {"acctstatus":"available","acctType":"email","stat":"ok"}
	 */
	
	@Override
	public String execute() throws Exception {
		String acctype = null ;
		if(ValidateUtil.checkIsEmail(acctname)){
			acctype = "email" ;
		}else if(ValidateUtil.checkIsMobile(acctname)){
			acctype = "mobile" ;
		}
		
		if(StringUtils.isBlank(acctname)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("input acctname cant be blank.") ;
			return SUCCESS ;
		}
		
		String acctnameEnc = URLEncoder.encode(acctname,"utf-8") ;
		String postUrl = url + "?_input_charset=" + _input_charset + "&acctType=" + acctype +"&acctname=" + acctnameEnc ;
		try {
			HttpPost post = new HttpPost(postUrl) ;
			HttpClient httpClient = new DefaultHttpClient() ;
			HttpResponse respose = httpClient.execute(post) ;
			
			String entryString = toEntryString(respose.getEntity()) ;
	
	
			StatusLine status = respose.getStatusLine() ;
			if(status != null && status.getStatusCode() == 200){
				AlipayAcctStatusCheck data = JacksonHelper.fromJSON(entryString, AlipayAcctStatusCheck.class) ;
				json.setData(data) ;
				json.setCode(JsonModel.CODE_SUCCESS) ;
			} else {
				json.setCode(JsonModel.CODE_FAIL);
				json.setDetail(status.getStatusCode() + "|" + entryString) ;
			}
		}catch(Exception e){
			json.setCode(JsonModel.CODE_FAIL) ;
			json.setDetail(e.getMessage()) ;
		}
			
		
		return SUCCESS ;
	}
	
	private String toEntryString (HttpEntity entry){
		if(entry == null){
			return null ;
		}
		try {
			return EntityUtils.toString(entry) ;
		} catch (ParseException e) {
			return null ;
		} catch (IOException e) {
			return null ;
		}
	}

	public JsonModel<AlipayAcctStatusCheck> getJson() {
		return json;
	}

	public void setAcctname(String acctname) {
		this.acctname = acctname;
	}
	
	
}
