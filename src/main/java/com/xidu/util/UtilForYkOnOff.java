package com.xidu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;


/**
 * 游客功能开关工具类
 * @author Administrator
 *
 */
public class UtilForYkOnOff {

public String getYkOnOff(){
		
	    String param = "yk_on_off";
		String yk_on_off = "";
		InputStream im=null;
	    try {
			Properties prop = new Properties(); 
			im = UtilForYkOnOff.class.getResourceAsStream("/yk_on_off.properties");   
			//im = new InputStreamReader(new FileInputStream(new File("D:\\currentqq.properties")), "UTF-8");
			prop.load(im);
			yk_on_off = prop.getProperty(param).trim();
			im.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return yk_on_off; 
	}
	

public static void main(String[] args) {
	System.out.println(new UtilForYkOnOff().getYkOnOff());
}
	
}
