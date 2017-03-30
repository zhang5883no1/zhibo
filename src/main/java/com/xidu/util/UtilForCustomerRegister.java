package com.xidu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 为用户注册准备的工具类，用来区分不同直播间分配不同的业务员
 * @author Administrator
 *
 */
public class UtilForCustomerRegister {
	
	private static UtilForCustomerRegister instance = null;
	private static Map<String,Long> roomMap = new HashMap<String,Long>();     //String：房间号    Long:该房间号下注册的人数
	
	public static UtilForCustomerRegister getInstance() {
		if (instance == null) {
			instance = new UtilForCustomerRegister();
		}
		return instance;
	}
	
	
        public static String getUserId(String roomNo){
		
		Map<String,Long> roomMap = UtilForCustomerRegister.getRoomMap();
		Long sum = 0L;
		String userId = "";
		Properties prop = new Properties(); 
        //InputStream in = UtilForCustomerRegister.class.getResourceAsStream("/config.properties");   
        try {   
        	InputStream in = new FileInputStream(new File(ReadConfigProperties.readProperties("adminProperties")));
            prop.load(in);   
            String param1 = prop.getProperty("room"+roomNo).trim();
            String[] userIds = param1.split(",");  //所属业务员数组
            in.close();
            if(!roomMap.isEmpty()){    //如果map不为空，则map中已经有了数据
            	for (String key : roomMap.keySet()){
            		if(key.equals(roomNo)){    //如果map中已经存在该房间号，则取出该房间号下已经存在的人数
            			sum = roomMap.get(key) + 1;
            			break;
            		}else{
            			sum = 1L;
            		}
                    
                }
            }else{
            	sum = 1L;
            }
            userId = userIds[(int)(((sum % userIds.length) == 0 ? userIds.length - 1 :((sum % userIds.length) - 1)))];   //获得了所属业务员
        } catch (IOException e) {   
            e.printStackTrace();   
        }
        String result = userId + "," + sum;
		return result;
	}


		public static Map<String, Long> getRoomMap() {
			return roomMap;
		}


		public static void setRoomMap(Map<String, Long> roomMap) {
			UtilForCustomerRegister.roomMap = roomMap;
		}
	
	


}
