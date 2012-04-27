package com.doucome.corner.web.common.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings("serial")
public class SystemINIT extends HttpServlet {

	private static final Log logger = LogFactory.getLog(SystemINIT.class) ;
	
	@Override
	public void init() throws ServletException {
		//String path  = getServletContext().getContextPath() ;
		super.init();
	}
}
