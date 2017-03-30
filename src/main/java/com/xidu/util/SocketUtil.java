package com.xidu.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SocketUtil {
		public static String convertUTF8ToString(String s) {  
	        if (s == null || s.equals("")) {  
	            return null;  
	        }  
	          
	        try {  
	            s = s.toUpperCase();  
	  
	            int total = s.length() / 2;  
	            int pos = 0;  
	  
	            byte[] buffer = new byte[total];  
	            for (int i = 0; i < total; i++) {  
	  
	                int start = i * 2;  
	  
	                buffer[i] = (byte) Integer.parseInt(  
	                        s.substring(start, start + 2), 16);  
	                pos++;  
	            }  
	  
	            return new String(buffer, 0, pos, "UTF-8");  
	              
	        } catch (UnsupportedEncodingException e) {  
	            e.printStackTrace();  
	        }  
	        return s;  
	    }  
		
		public static void main(String[] args) throws UnsupportedEncodingException {
			String s=convertUTF8ToString("10=HTBY1|11=HTBY888");
			System.out.println(URLEncoder.encode("10=HTBY1|11=HTBY888", "UTF-8"));
			System.out.println(s.length());
			System.out.println(s);
		}
}
