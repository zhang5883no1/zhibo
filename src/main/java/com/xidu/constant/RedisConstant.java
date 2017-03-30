package com.xidu.constant;  
  
public class RedisConstant {

	public static final String title="ddv";
	public static String normalChats(Long roomid){
		return title+"_roomlist_room"+roomid+"_normalChat";
	}
	
	public static String falidChats(Long roomid){
		return title+"_roomlist_room"+roomid+"_falidChats";
	}
	
	public static String validChats(Long roomid){
		return title+"_roomlist_room"+roomid+"_validChats";
	}
	
	public static String qqs(Long roomid){
		return title+"_roomlist_room"+roomid+"_qq";
	}
	
	public static String kcbs(Long roomid){
		return title+"_roomlist_room"+roomid+"_kcb";
	}
	
	public static String topInfo(Long roomid){
		return title+"_roomlist_room"+roomid+"_topInfo";
	}
	
	public static String scrolInfo(Long roomid){
		return title+"_roomlist_room"+roomid+"_scrolInfo";
	}
	
	public static String floatInfo(Long roomid){
		return title+"_roomlist_room"+roomid+"_floatInfo";
	}
	
	public static String bottomInfo(Long roomid){
		return title+"_roomlist_room"+roomid+"_bottomInfo";
	}
	
	public static String totalPeopleCount(Long roomid){
		return title+"_roomlist_room"+roomid+"_totalPeopleCount";
	}
	
	public static String robots(){
		return title+"_robotlist";
	}
	
	public static String customer(){
		return title+"_allcustomer";
	}
}
