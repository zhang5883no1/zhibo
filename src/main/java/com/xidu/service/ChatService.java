package com.xidu.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.xidu.dao.ChatsDao;
import com.xidu.dao.InfoDao;
import com.xidu.entity.Chats;
import com.xidu.entity.Customer;
import com.xidu.entity.Info;
import com.xidu.entity.Robot;
import com.xidu.init.GetChatsUtil;
import com.xidu.init.WebSocketPool;
import com.xidu.util.HtmlFilter;

public class ChatService {

	public Chats addChats(Chats chats, Customer customer) {
		Chats result = null;
		result=initChats(chats,customer);
		
		if (chats.getType() == 0||chats.getType() == 10||chats.getType() == 11) {
			result = sendAll(chats);
		} else if (chats.getType() == 1||chats.getType() == 20||chats.getType() == 21) {
			result = sendSimple(chats);
		} else if (chats.getType() == 2&&(customer.getLevel()==99||customer.getLevel()==100)) {
			return sendTop(chats);
		} else if (chats.getType() == 3&&(customer.getLevel()==99||customer.getLevel()==100)) {
			return sendScrol(chats);
		} else if (chats.getType() == 4&&(customer.getLevel()==99||customer.getLevel()==100)) {
			return sendFloat(chats);
		} else if (chats.getType() == 5&&(customer.getLevel()==99||customer.getLevel()==100)) {
			return sendBottom(chats);
		} else if (chats.getType() == 30) {
			result = sendToUser(chats);
		} else {
			return null;
		}
		
		result=insertIntoDb(result);
		if(result.getValid()==0){
			WebSocketPool.getInstance().addValidChat(customer.getRoomNo(), result);
		}else if(result.getValid()==1){
			WebSocketPool.getInstance().addNormalChat(customer.getRoomNo(), result);
		}else if(result.getValid()==9){
			
		}
		return result;
	}

	private Chats initChats(Chats chats, Customer customer) {
		// 设置是否需要审核
		if (customer.getLevel() > 90) {
			chats.setValid(1);
		}

		// 设置头像
		if (chats.getIsRobot() == 0) {
			chats.setFaceImg(customer.getLevel().toString());
		} else if (chats.getIsRobot() == 1) {
			for (Robot robot : customer.getRobotList()) {
				if (robot.getId() .equals( chats.getUserId())) {
					chats.setFaceImg(robot.getLevel().toString());
				}
			}
		}

		// 设置房间
		chats.setRoomNo(customer.getRoomNo());

		return chats;
	}

	private Chats sendAll(Chats chats) {
		// TODO Auto-generated method stub
		return chats;
	}

	private Chats sendFloat(Chats chats) {
		// TODO Auto-generated method stub
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Info info=new Info();
		info.setContent(chats.getContent());
		info.setCreateDate(sf.format(new Date()));
		info.setRoomNo(chats.getRoomNo());
		info.setType(2);
		insertIntoDb(info);
		WebSocketPool.getInstance().getRoom(chats.getRoomNo()).setFloatInfo(info);
		return chats;
	}
	
	private Chats sendBottom(Chats chats) {
		// TODO Auto-generated method stub
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Info info=new Info();
		info.setContent(chats.getContent());
		info.setCreateDate(sf.format(new Date()));
		info.setRoomNo(chats.getRoomNo());
		info.setType(4);
		insertIntoDb(info);
		WebSocketPool.getInstance().getRoom(chats.getRoomNo()).setBottomInfo(info);
		return chats;
	}

	private Chats sendScrol(Chats chats) {
		// TODO Auto-generated method stub
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// TODO Auto-generated method stub
		Info info=new Info();
		info.setContent(chats.getContent());
		info.setCreateDate(sf.format(new Date()));
		info.setRoomNo(chats.getRoomNo());
		info.setType(1);
		insertIntoDb(info);
		WebSocketPool.getInstance().getRoom(chats.getRoomNo()).setScrolInfo(info);
		return chats;
	}

	private Chats sendTop(Chats chats) {
		// TODO Auto-generated method stub
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// TODO Auto-generated method stub
		Info info=new Info();
		info.setContent(chats.getContent());
		info.setCreateDate(sf.format(new Date()));
		info.setRoomNo(chats.getRoomNo());
		info.setType(3);
		insertIntoDb(info);
		WebSocketPool.getInstance().getRoom(chats.getRoomNo()).setTopInfo(info);
		return chats;
	}

	private Chats sendSimple(Chats chats) {
		// TODO Auto-generated method stub
		return chats;
	}
	
	private Chats sendToUser(Chats chats) {
		// TODO Auto-generated method stub
		chats.setValid(9);
		return chats;
	}

	private Chats insertIntoDb(Chats chats) {
		ChatsDao chatsDao=new ChatsDao();
		int id=chatsDao.addChats(chats);
		chats.setId(Long.valueOf(id));
		return chats;
	}
	
	public Info insertIntoDb(Info info) {
		InfoDao infoDao=new InfoDao();
		infoDao.addInfo(info);
		return info;
	}
	
	private Chats UpdateIntoDb(Chats chats) {
		ChatsDao chatsDao=new ChatsDao();
		chatsDao.editChats(chats);
		return chats;
	}
	
	public Chats validContentByType(String content,String type){
		Chats chats=new Chats();
		content=HtmlFilter.getNoHTMLString(content);
		if("all".equals(type)){
			chats.setType(0);
		}else if("simple".equals(type)){
			chats.setType(1);
		}else if("top".equals(type)){
			chats.setType(2);
		}else if("scrol".equals(type)){
			chats.setType(3);
		}else if("float".equals(type)){
			chats.setType(4);
		}else if("bottom".equals(type)){
			chats.setType(5);
		}else if("normal".equals(type)){
			chats.setType(1);
		}else if("red".equals(type)){
			chats.setType(10);
		}else if("black".equals(type)){
			chats.setType(11);
		}else if("redSimple".equals(type)){
			chats.setType(20);
		}else if("blackSimple".equals(type)){
			chats.setType(21);
		}else if("toPeople".equals("type")){
			chats.setType(30);
		}
		chats.setContent(content);
		return chats;
	}
	
	public Chats validChats(String id,String valid,String roomid, Long validUserId){
		Chats chat=new GetChatsUtil().ValidChats(roomid, Long.valueOf(id));
		if(chat!=null){
			chat.setValidUser(validUserId.toString());
			if("0".equals(valid)){
				chat.setValid(2);
				UpdateIntoDb(chat);
				WebSocketPool.getInstance().addFalidChat(roomid, chat.getId().toString());
			}else if("1".equals(valid)){
				chat.setValid(1);
				UpdateIntoDb(chat);
				WebSocketPool.getInstance().addNormalChat(roomid, chat);
			}
			WebSocketPool.getInstance().getRoom(roomid).removeValid(chat.getId().toString());
		}
		return chat;
	}
}
