package com.xidu.constant;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Constant {

	public static final String SESSION_CUSTOMER="customer";
	public static final String SESSION_WAIT_QUEUE="wait_queue_index";
	
	public static final String SESSION_IS_OUT="isout";
	public static final String SESSION_SIMPLE_CHATS="simple_chats";
	
	public static final String LAST_LOGIN_INFO="last_login_info";
	public static final String LOGIN_FLAG="1";
	public static final String LOGOUT_FLAG="0";
	
	public static final String LAST_LIVE_TIME="liveTime";
	
	
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d="2016-08-08 00:00:00";
		System.out.println(sf.parse(d).getTime());
	}
}
