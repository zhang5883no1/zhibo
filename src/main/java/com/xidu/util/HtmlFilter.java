package com.xidu.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Pattern;

public class HtmlFilter {
	public static String getNoHTMLString(String content){  
	    
	    if(null==content) return "";  
	    
	    java.util.regex.Pattern p_script;   
         java.util.regex.Matcher m_script;   
         java.util.regex.Pattern p_style;   
         java.util.regex.Matcher m_style;   
         java.util.regex.Pattern p_html;   
         java.util.regex.Matcher m_html;   
	          
	     try {   
	         String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";  
	         //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }   
	         String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";   
	               //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }   
	               String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式   
	             
	               p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);   
	               m_script = p_script.matcher(content);   
	               content = m_script.replaceAll(""); //过滤script标签  
	               p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);   
	               m_style = p_style.matcher(content);   
	               content = m_style.replaceAll(""); //过滤style标签   
	               
	               content=content.replaceAll("<img", "zcimg");
            	   p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);   
            	   m_html = p_html.matcher(content);   
            	   content = m_html.replaceAll(""); //过滤html标签   
            	   content= content.replaceAll("zcimg", "<img");
	           }catch(Exception e) {   
	                   return "";  
	           }   
	    
	   return content;  
	}  
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		String s="10=HTBY1|11=HTBY888";
		byte[] b=s.getBytes("UTF8");
		System.out.println(b.length);
		for(byte bb:b){
			System.out.print(bb);
		}
	}
}
