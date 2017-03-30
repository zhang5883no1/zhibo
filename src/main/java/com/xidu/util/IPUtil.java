package com.xidu.util;

import javax.servlet.http.HttpServletRequest;

public class IPUtil {
	public String getRemortIP(HttpServletRequest request) { 
	    if (request.getHeader("x-forwarded-for") == null) { 
	        return request.getRemoteAddr(); 
	    } 
	    
	    String ips=request.getHeader("x-forwarded-for"); 
	    if(ips.length()>0){
	    	return ips.split(",")[0];
	    }
	    
	    return request.getHeader("x-forwarded-for"); 
	}   
}
