package com.xidu.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import net.sf.json.JSONArray;

import com.xidu.constant.RedisConstant;
import com.xidu.entity.Chats;
import com.xidu.entity.Info;
import com.xidu.entity.KCB;
import com.xidu.entity.Room;
import com.xidu.redis.RedisUtil;
import com.xidu.util.SerializeUtil;

public class RoomDto {
	private static final Long maxNormalChats=20L;
	private static final Long maxValidChats=40L;
	private static final Long maxFalidChats=10L;
	private Room room;
	private JSONArray qq=null;
	
	SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	public JSONArray getQq() {
		byte[] list=RedisUtil.getString(RedisConstant.qqs(this.room.getId()).getBytes());
		return (JSONArray)SerializeUtil.unserialize(list);
//		return qq;
	}
	public int getTotalPeopleCount() {
		String count=RedisUtil.getString(RedisConstant.totalPeopleCount(this.room.getId()));
		return Integer.valueOf(count);
//		return totalPeopleCount;
	}
	public void setTotalPeopleCount(int totalPeopleCount) {
//		this.totalPeopleCount = totalPeopleCount;
		RedisUtil.setString(RedisConstant.totalPeopleCount(this.room.getId()), totalPeopleCount+"");
	}
	public void setQq(JSONArray qq) {
//		this.qq = qq;
		RedisUtil.setString(RedisConstant.qqs(this.room.getId()).getBytes(), SerializeUtil.serialize(qq));
	}
	public LinkedList<Chats> getNormalChats() {
		LinkedList<Chats> chats=new LinkedList<Chats>();
		List<byte[]> list=RedisUtil.getAllList(RedisConstant.normalChats(this.room.getId()).getBytes());
		for(byte[] b:list){
			chats.addFirst((Chats)SerializeUtil.unserialize(b));
		}
		return chats;
//		return normalChats;
	}
	
//	public void setNormalChats(LinkedList<Chats> normalChats) {
//		this.normalChats = normalChats;
//	}
	
	public LinkedList<Chats> getValidChats() {
		LinkedList<Chats> chats=new LinkedList<Chats>();
		List<byte[]> list=RedisUtil.getAllList(RedisConstant.validChats(this.room.getId()).getBytes());
		for(byte[] b:list){
			chats.addFirst((Chats)SerializeUtil.unserialize(b));
		}
		return chats;
//		return validChats;
	}
	
//	public void setValidChats(LinkedList<Chats> validChats) {
//		this.validChats = validChats;
//	}
	
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	
	public LinkedList<String> getFalidChats() {
//		return (LinkedList<String>)RedisUtil.getAllList(RedisConstant.falidChats(this.room.getId()));
		LinkedList<String> chats=new LinkedList<String>();
//		System.out.println("get falidChat from redis-----------");
		List<String> list=RedisUtil.getAllList(RedisConstant.falidChats(this.room.getId()));
//		System.out.println("redis falidchats length : "+list.size());
		for(String b:list){
			chats.addFirst(b);
		}
//		System.out.println("return faildChats ");
		return chats;
//		return falidChats;
	}
//	public void setFalidChats(LinkedList<String> falidChats) {
//		this.falidChats = falidChats;
//	}
	
	public void addFalidChats(Chats chat) {
//		while(falidChats.size()>=maxFalidChats){
//			falidChats.removeFirst();
//		}
//		falidChats.addLast(chat);
		while(RedisUtil.getListSize(RedisConstant.falidChats(this.room.getId()))>=maxFalidChats){
			RedisUtil.removeFirstFromList(RedisConstant.falidChats(this.room.getId()));
		}
		RedisUtil.putList(RedisConstant.falidChats(this.room.getId()),chat.getId().toString());
	}
	
	public void addNormalChat(Chats chat) {
//		while(normalChats.size()>=maxNormalChats){
//			normalChats.removeFirst();
//		}
//		normalChats.addLast(chat);
		while(RedisUtil.getListSize(RedisConstant.normalChats(this.room.getId()).getBytes())>=this.maxNormalChats){
			RedisUtil.removeFirstFromList(RedisConstant.normalChats(this.room.getId()).getBytes());
		}
		RedisUtil.putList(RedisConstant.normalChats(this.room.getId()).getBytes(),SerializeUtil.serialize(chat));
	}
	
	public KCB getKcb() {
		byte[] list=RedisUtil.getString(RedisConstant.kcbs(this.room.getId()).getBytes());
		return (KCB)SerializeUtil.unserialize(list);
//		return kcb;
	}
	public void setKcb(KCB kcb) {
		RedisUtil.setString(RedisConstant.kcbs(this.room.getId()).getBytes(), SerializeUtil.serialize(kcb));
//		this.kcb = kcb;
	}
	
	public Info getTopInfo() {
//		return topInfo;
		byte[] list=RedisUtil.getString(RedisConstant.topInfo(this.room.getId()).getBytes());
		return (Info)SerializeUtil.unserialize(list);
	}
	
	public void setTopInfo(Info topInfo) {
//		System.out.println("room :"+this.room.getId());
//		System.out.println("set topinfo :"+topInfo.getId());
		if(topInfo!=null){
			try {
				long times=sf.parse(topInfo.getCreateDate()).getTime();
				topInfo.setCreateDate(times+"");
			} catch (ParseException e) {
//				this.topInfo = topInfo;
				RedisUtil.setString(RedisConstant.topInfo(this.room.getId()).getBytes(), SerializeUtil.serialize(topInfo));
			}
		}
//		this.topInfo = topInfo;
		RedisUtil.setString(RedisConstant.topInfo(this.room.getId()).getBytes(), SerializeUtil.serialize(topInfo));
	}
	public Info getScrolInfo() {
//		return scrolInfo;
		byte[] list=RedisUtil.getString(RedisConstant.scrolInfo(this.room.getId()).getBytes());
		return (Info)SerializeUtil.unserialize(list);
	}
	public void setScrolInfo(Info scrolInfo) {
//		System.out.println("room :"+this.room.getId());
//		System.out.println("set scroll :"+scrolInfo.getId());
		if(scrolInfo!=null){
			try {
				long times=sf.parse(scrolInfo.getCreateDate()).getTime();
				scrolInfo.setCreateDate(times+"");
			} catch (ParseException e) {
				RedisUtil.setString(RedisConstant.scrolInfo(this.room.getId()).getBytes(), SerializeUtil.serialize(scrolInfo));
//				this.scrolInfo = scrolInfo;
			}
		}
		RedisUtil.setString(RedisConstant.scrolInfo(this.room.getId()).getBytes(), SerializeUtil.serialize(scrolInfo));
//		this.scrolInfo = scrolInfo;
	}
	
	public Info getFloatInfo() {
//		return floatInfo;
		byte[] list=RedisUtil.getString(RedisConstant.floatInfo(this.room.getId()).getBytes());
		return (Info)SerializeUtil.unserialize(list);
	}
	
	public void setFloatInfo(Info floatInfo) {
//		System.out.println("room :"+this.room.getId());
//		System.out.println("set floatInfo :"+floatInfo.getId());
		if(floatInfo!=null){
			try {
				long times=sf.parse(floatInfo.getCreateDate()).getTime();
				floatInfo.setCreateDate(times+"");
			} catch (Exception e) {
				e.printStackTrace();
//				this.floatInfo=floatInfo;
				RedisUtil.setString(RedisConstant.floatInfo(this.room.getId()).getBytes(), SerializeUtil.serialize(floatInfo));
			}
		}
//		this.floatInfo = floatInfo;
		RedisUtil.setString(RedisConstant.floatInfo(this.room.getId()).getBytes(), SerializeUtil.serialize(floatInfo));
	}
	public Info getBottomInfo() {
//		return bottomInfo;
		byte[] list=RedisUtil.getString(RedisConstant.bottomInfo(this.room.getId()).getBytes());
		return (Info)SerializeUtil.unserialize(list);
	}
	public void setBottomInfo(Info bottomInfo) {
//		System.out.println("room :"+this.room.getId());
//		System.out.println("set bottomInfo :"+bottomInfo.getId());
//		this.bottomInfo=bottomInfo;
		RedisUtil.setString(RedisConstant.bottomInfo(this.room.getId()).getBytes(), SerializeUtil.serialize(bottomInfo));
	}
	
	public void addValidChat(Chats chat) {
//		while(validChats.size()>=maxValidChats){
//			validChats.removeFirst();
//		}
//		validChats.addLast(chat);
		while(RedisUtil.getListSize(RedisConstant.validChats(this.room.getId()).getBytes())>=this.maxValidChats){
			RedisUtil.removeFirstFromList(RedisConstant.validChats(this.room.getId()).getBytes());
		}
		RedisUtil.putList(RedisConstant.validChats(this.room.getId()).getBytes(),SerializeUtil.serialize(chat));
	}
	
	public void removeValid(String vid) {
		List<byte[]> bs=RedisUtil.getAllList(RedisConstant.validChats(this.room.getId()).getBytes());
		for(int i=0;i<bs.size();i++){
			Chats c=(Chats)SerializeUtil.unserialize(bs.get(i));
			if(c.getId().toString().equals(vid)){
				RedisUtil.removeByIndexFromList(RedisConstant.validChats(this.room.getId()).getBytes(),i);
			}
		}
		// TODO Auto-generated method stub
//		for(int i=0;i<validChats.size();i++){
//			if(validChats.get(i).getId().toString().equals(vid)){
//				validChats.remove(i);
//				return;
//			}
//		}
	}
	
}
