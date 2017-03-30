package com.xidu.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

public class PropertiesConfig {

	/** 
     * 根据KEY，读取文件对应的值 
     * @param filePath 文件路径，即文件所在包的路径，例如：java/util/config.properties 
     * @param key 键 
     * @return key对应的值 
     */  
    public static String readData(String filePath, String key) {  
        //获取绝对路径  
//        filePath = PropertiesConfig.class.getResource("/" + "config.properties").toString();  
        //截掉路径的”file:“前缀  
//        filePath = filePath.substring(6);  
    	filePath=ReadConfigProperties.readProperties("currentqqProperties");
        Properties props = new Properties();  
        try {  
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));  
            props.load(in);  
            in.close();  
            String value = props.getProperty(key);  
            return value;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
    /** 
     * 修改或添加键值对 如果key存在，修改, 反之，添加。 
     * @param filePath 文件路径，即文件所在包的路径，例如：java/util/config.properties 
     * @param key 键 
     * @param value 键对应的值 
     */  
    public static void writeData(String filePath, String key, String value) {  
        //获取绝对路径  
//      filePath = PropertiesConfig.class.getResource("/" + "config.properties").toString(); 
    	filePath=ReadConfigProperties.readProperties("configProperties");
        //截掉路径的”file:/“前缀  
//        filePath = filePath.substring(6);  
        Properties prop = new Properties();  
        try {  
            File file = new File(filePath);  
            if (!file.exists())  
                file.createNewFile();  
            InputStream fis = new FileInputStream(file);  
            prop.load(fis);  
            //一定要在修改值之前关闭fis  
            fis.close();  
            OutputStream fos = new FileOutputStream(filePath);  
            prop.setProperty(key, value);  
            //保存，并加入注释  
            prop.store(fos, "Update '" + key + "' value");  
            fos.close();  
        } catch (IOException e) {  
            System.err.println("Visit " + filePath + " for updating " + value + " value error");  
        }  
    }  
    
    public static void writeData(String value) {  
        //获取绝对路径  
//      filePath = PropertiesConfig.class.getResource("/" + "config.properties").toString(); 
    	String filePath=ReadConfigProperties.readProperties("sqlProperties");
    	Date d=new Date();
    	String key=d.getYear()+"."+d.getMonth()+"."+d.getDay()+"."+d.getHours()+"."+d.getMinutes()+"."+d.getSeconds();
    	
        //截掉路径的”file:/“前缀  
//        filePath = filePath.substring(6);  
        Properties prop = new Properties();  
        try {  
            File file = new File(filePath);  
            if (!file.exists())  
                file.createNewFile();  
            InputStream fis = new FileInputStream(file);  
            prop.load(fis);  
            //一定要在修改值之前关闭fis  
            fis.close();  
            OutputStream fos = new FileOutputStream(filePath);  
            prop.setProperty(key, value);  
            //保存，并加入注释  
            prop.store(fos, "Update '" + key + "' value");  
            fos.close();  
        } catch (IOException e) {  
            System.err.println("Visit " + filePath + " for updating " + value + " value error");  
        }  
    }  
      
    
    public static String readChatData(String key) {  
        //获取绝对路径  
//        filePath = PropertiesConfig.class.getResource("/" + "config.properties").toString();  
        //截掉路径的”file:“前缀  
//        filePath = filePath.substring(6);  
		String v = "";
		InputStreamReader im=null;
	    try {
			Properties prop = new Properties(); 
			im=new InputStreamReader(new FileInputStream(new File(ReadConfigProperties.readProperties("footnoticeProperties"))), "UTF-8");
			prop.load(im);
			v = prop.getProperty(key).trim();
			im.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return v;
    }  
    
    public static void main(String[] args) {  
        System.out.println(PropertiesConfig.readData("com/xiewanzhi/property/config.properties", "port"));  
//      PropertiesConfig.writeData("com/xiewanzhi/property/config.properties", "port", "12345");  
    }  
}
