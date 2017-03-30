package com.xidu.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xidu.dao.KCBDao;
import com.xidu.dao.RoomDao;
import com.xidu.entity.Info;
import com.xidu.entity.KCB;
import com.xidu.entity.Room;
import com.xidu.init.WebSocketPool;
import com.xidu.service.ChatService;
import com.xidu.util.PropertiesConfig;

public class UpdateController  extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws IOException{
		String type=request.getParameter("type");
		String roomNo=request.getParameter("room");
		if("kcb".equals(type)){
			KCBDao kcbDao=new KCBDao();
			KCB kcb=kcbDao.queryByRoom(Long.valueOf(roomNo));
			WebSocketPool.getInstance().getRoom(roomNo).setKcb(kcb);
		}else if("bottomInfo".equals(type)){
			String chat=PropertiesConfig.readChatData("room"+roomNo);
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Info info=new Info();
			info.setContent(chat);
			info.setCreateDate(sf.format(new Date()));
			info.setRoomNo(roomNo);
			info.setType(4);
			new ChatService().insertIntoDb(info);
			WebSocketPool.getInstance().getRoom(roomNo).setBottomInfo(info);
		}
		response.getWriter().print("success");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
	}
	
}
