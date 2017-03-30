package com.xidu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 读取config.properties文件中的文件路径
 * @author Administrator
 *
 */
public class ReadConfigProperties {
	
	//获取文件路径
	public static String readProperties(String key){
		Properties prop = new Properties(); 
        InputStream in = ReadConfigProperties.class.getResourceAsStream("/config.properties");   
        String path = "";
        try {
			prop.load(in);
			path = prop.getProperty(key).trim();
		} catch (IOException e) {
			e.printStackTrace();
		}   
		return path;
	}
	
	
	public static void main(String[] args) {
		System.out.println(ReadConfigProperties.readProperties("adminProperties"));
	}


	public static String getPropertyValue(String string, String string2) {
		// TODO Auto-generated method stub  
		Properties prop = new Properties(); 
        InputStream in = ReadConfigProperties.class.getResourceAsStream(string);   
        String path = "";
        try {
			prop.load(in);
			path = prop.getProperty(string2).trim();
		} catch (IOException e) {
			e.printStackTrace();
		}   
		return path;
	}


	public static int getPropertyValueInt(String string, String string2) {
		// TODO Auto-generated method stub 
		String val=getPropertyValue(string,string2);
		int result=0;
		try {
			result=Integer.valueOf(val);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}


	public static boolean getPropertyValueBoolean(String string, String string2) {
		// TODO Auto-generated method stub  
		String val=getPropertyValue(string,string2);
		boolean result=false;
		try {
			result=Boolean.valueOf(val);;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	



}
