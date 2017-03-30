package com.xidu.init;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.xidu.constant.RedisConstant;
import com.xidu.dto.RoomDto;
import com.xidu.entity.Chats;
import com.xidu.entity.Robot;
import com.xidu.entity.Room;
import com.xidu.redis.RedisUtil;
import com.xidu.util.SerializeUtil;

public class WebSocketPool {
	private static WebSocketPool instance = null;
	private static Map<String, RoomDto> roomMap = null;
	private static List<Robot> robotlist=new LinkedList<Robot>();
	
	private WebSocketPool() {

	}

	public static WebSocketPool getInstance() {
		if (instance == null) {
			instance = new WebSocketPool();
		}
		if (roomMap == null) {
			roomMap = new HashMap<String, RoomDto>();
		}
		return instance;
	}

	public static List<Robot> getRobotlist() {
//		byte[] list=RedisUtil.getString(RedisConstant.robots().getBytes());
//		return (List<Robot>)SerializeUtil.unserialize(list);
		return robotlist;
	}

	public static void setRobotlist(List<Robot> robotlist) {
//		System.out.println("---------------------------------------------");
//		System.out.println("---------------------------------------------");
//		System.out.println(robotlist.size());
//		System.out.println("---------------------------------------------");
//		System.out.println("---------------------------------------------");
//		RedisUtil.setString(RedisConstant.robots().getBytes(), SerializeUtil.serialize(robotlist));
//		System.out.println(SerializeUtil.unserialize(RedisUtil.getString(RedisConstant.robots().getBytes())));
		WebSocketPool.robotlist=robotlist;
	}

	public static void addRoom(String id, Room room) {
		RoomDto dto=new RoomDto();
		dto.setRoom(room);
		roomMap.put(id, dto);
	}
//	
	public static RoomDto getRoom(String roomid){
		return roomMap.get(roomid);
	}
	
	public static void addNormalChat(String roomid,Chats chat){
		roomMap.get(roomid).addNormalChat(chat);
//		RedisUtil.putList(RedisConstant.normalChats(Long.valueOf(roomid)).getBytes(), SerializeUtil.serialize(chat));
	}
	
	
	public static void addValidChat(String roomid,Chats chat){
		roomMap.get(roomid).addValidChat(chat);
//		RedisUtil.putList(RedisConstant.validChats(Long.valueOf(roomid)).getBytes(), SerializeUtil.serialize(chat));
	}
	
	public static void addFalidChat(String roomid,String chat){
		Chats c=new Chats();
		c.setId(Long.valueOf(chat));
		roomMap.get(roomid).addFalidChats(c);
//		RedisUtil.putList(RedisConstant.falidChats(Long.valueOf(roomid)), chat);
	}

}
