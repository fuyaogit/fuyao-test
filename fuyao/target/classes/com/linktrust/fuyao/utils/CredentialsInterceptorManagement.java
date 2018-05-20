/**  
 * @Project: linktrust-appAssess
 * @Title: CredentialsInterceptor.java
 * @Package: com.linktrust.edu.assess.controller
 * @Description: TODO
 * @author: Guoq@
 * @since: 2017年6月2日 上午10:36:10
 * @Copyright: 2017. All rights reserved.
 * @Version: v1.0   
 */
package com.linktrust.fuyao.utils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**  
 * @ClassName CredentialsInterceptor
 * @Description: TODO  
 * @author: Guoq@
 * @since: 2017年6月2日 上午10:36:10 
 */
@Controller
public class CredentialsInterceptorManagement implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		/*
		 * This is a part of security, you cannot do that. If you want to allow
		 * credentials then your Access-Control-Allow-Origin must not use *. You
		 * will have to specify the exact domain.
		 */
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,PUT,DELETE,POST");
		response.setHeader("Access-Control-Allow-Headers", "Origin,No-Cache,X-Requested-With,If-Modified-Since,Pragma,Last-Modified,Cache-Control,Expires,Content-Type,X-E4M-With");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}
}
