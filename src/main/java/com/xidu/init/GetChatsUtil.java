package com.xidu.init;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.xidu.entity.Chats;
import com.xidu.entity.Info;

public class GetChatsUtil {

	public List<Chats> getNormalChats(String roomid,Long id){
		System.out.println("-------------------------------------");
		System.out.println("lastid : "+id);
		LinkedList<Chats> normalChats=WebSocketPool.getInstance().getRoom(roomid).getNormalChats();
		boolean f=false;
		List<Chats> chats=new ArrayList<Chats>();
		for(int i=0;i<normalChats.size();i++){
			System.out.println("iterator id : "+normalChats.get(i).getId());
			if(f){
				System.out.println("f is true ");
				chats.add(normalChats.get(i));
			}
			if(normalChats.get(i).getId().equals(id)){
				System.out.println("last id equals id");
				f=true;
			}
		}
		if(chats.size()==0&&f==false){
			System.out.println("return all");
			return normalChats;
		}
		return chats;
	}
	
	public List<Chats> getValidChats(String roomid,Long id){
		LinkedList<Chats> validChats=WebSocketPool.getInstance().getRoom(roomid).getValidChats();
		boolean f=false;
		List<Chats> chats=new ArrayList<Chats>();
		for(int i=0;i<validChats.size();i++){
			if(f){
				chats.add(validChats.get(i));
			}
			if(validChats.get(i).getId().equals(id)){
				f=true;
			}
		}
		if(chats.size()==0&&f==false){
			return validChats;
		}
		return chats;
	}
	
	public Chats ValidChats(String roomid,Long chatId){
		Chats chat=null;
		LinkedList<Chats> validChats=WebSocketPool.getInstance().getRoom(roomid).getValidChats();
		for(int i=0;i<validChats.size();i++){
			if(validChats.get(i).getId().equals(chatId)){
				chat=validChats.get(i);
				validChats.remove(i);
			}
		}
		return chat;
	}
	
	public Info getInfo(String type,String roomid){
		Date date=new Date();
		if("top".equals(type)){
			return WebSocketPool.getInstance().getRoom(roomid).getTopInfo();
		}else if("scrol".equals(type)){
			Info info=WebSocketPool.getInstance().getRoom(roomid).getScrolInfo();
			return info;
		}else if("float".equals(type)){
			Info info=WebSocketPool.getInstance().getRoom(roomid).getFloatInfo();
			return info;
		}else if("bottom".equals(type)){
			Info info=WebSocketPool.getInstance().getRoom(roomid).getBottomInfo();
			return info;
		}
		return null;
	}
	
	
	public List<String> getFalidChats(String roomid){
		return WebSocketPool.getInstance().getRoom(roomid).getFalidChats();
	}
	public static void main(String[] args) {
		Long a=1L;
		Long b=1L;
		System.out.println(a==b);
	}
}
