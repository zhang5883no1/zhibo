package com.xidu.util;
import java.io.IOException;  

import javax.servlet.Filter;  
import javax.servlet.FilterChain;  
import javax.servlet.FilterConfig;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;  
import javax.servlet.http.HttpServletRequest;  

public class XssFilter implements Filter {  
  
  
    /* (non-Javadoc) 
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain) 
     */  
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,  
            ServletException {  
          
            XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(  
            (HttpServletRequest) request);  
            filterChain.doFilter(xssRequest, response);  
          
    }

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}  
  
}  
