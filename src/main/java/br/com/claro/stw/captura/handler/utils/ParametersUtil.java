package br.com.claro.stw.captura.handler.utils;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public interface ParametersUtil {

	static String get(HttpServletRequest request) {
	    StringBuffer posted = new StringBuffer();
	    Enumeration<?> e = request.getParameterNames();
//	    if (e != null) {
//	        posted.append("?");
//	    }
	    while (e.hasMoreElements()) {
	        if (posted.length() > 1) {
	            posted.append("&");
	        }
	        String curr = (String) e.nextElement();
	        posted.append(curr + "=");
	        if (curr.contains("password") 
	          || curr.contains("pass")
	          || curr.contains("pwd")) {
	            posted.append("*****");
	        } else {
	            posted.append(request.getParameter(curr));
	        }
	    }
//	    String ip = request.getHeader("X-FORWARDED-FOR");
//	    String ipAddr = (ip == null) ? getRemoteAddr(request) : ip;
//	    if (ipAddr!=null && !ipAddr.equals("")) {
//	        posted.append("&_psip=" + ipAddr); 
//	    }
	    return posted.toString();
	}
	
	static String getRemoteAddr(HttpServletRequest request) {
	    String ipFromHeader = request.getHeader("X-FORWARDED-FOR");
	    if (ipFromHeader != null && ipFromHeader.length() > 0) {
	        return ipFromHeader;
	    }
	    return request.getRemoteAddr();
	}
}
