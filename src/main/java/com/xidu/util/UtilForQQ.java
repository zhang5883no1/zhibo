package com.xidu.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.Properties;

/**
 * 
 * @author Administrator
 *
 */
public class UtilForQQ {
	
	public String getQQ(String param){
		
		String qq = "";
		InputStreamReader im=null;
	    try {
			Properties prop = new Properties(); 
			im=new InputStreamReader(new FileInputStream(new File(ReadConfigProperties.readProperties("currentqqProperties"))), "UTF-8");
			prop.load(im);
			qq = prop.getProperty(param).trim();
			im.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return qq;
//		String filePath="D:/currentqq.properties";
//        Properties props = new Properties();  
//        try {  
//            InputStream in = new BufferedInputStream(new FileInputStream(filePath));  
//            props.load(in);  
//            in.close();  
//            String value = props.getProperty(param);  
//            return value;  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//            return null;  
//        }  
	}
	
	public static void main(String[] args) {
		System.out.println(URLEncoder.encode("去广告"));
		System.out.println(new UtilForQQ().getQQ("room2.name6"));
	}
}
