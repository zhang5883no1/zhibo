package com.xidu.init;

import java.util.List;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.xidu.dao.InfoDao;
import com.xidu.dao.KCBDao;
import com.xidu.dao.RobotDao;
import com.xidu.dao.RoomDao;
import com.xidu.entity.Info;
import com.xidu.entity.KCB;
import com.xidu.entity.Room;


public class InitStart implements HttpSessionListener {
	static {
		RoomDao roomDao=new RoomDao();
		InfoDao infoDao=new InfoDao();
		KCBDao kcbDao=new KCBDao();
		List<Room> roomlist = roomDao.getallRoom();
//		System.out.println("init start ---------------------");
		for(Room room:roomlist){
			WebSocketPool.getInstance().addRoom(room.getId().toString(),room);
			Info scrolInfo=infoDao.selectInfoByType(new Info(1,room.getId().toString()));
			Info floatInfo=infoDao.selectInfoByType(new Info(2,room.getId().toString()));
			Info topInfo=infoDao.selectInfoByType(new Info(3,room.getId().toString()));
			Info bottomInfo=infoDao.selectInfoByType(new Info(4,room.getId().toString()));
//			System.out.println("room id : "+ room.getId());
//			System.out.println("float id:"+floatInfo.getId());
//			System.out.println("scrolInfo id:"+scrolInfo.getId());
//			System.out.println("topInfo id:"+topInfo.getId());
//			System.out.println("bottomInfo id:"+bottomInfo.getId());
			WebSocketPool.getInstance().getRoom(room.getId().toString()).setFloatInfo(floatInfo);
			WebSocketPool.getInstance().getRoom(room.getId().toString()).setScrolInfo(scrolInfo);
			WebSocketPool.getInstance().getRoom(room.getId().toString()).setTopInfo(topInfo);
			WebSocketPool.getInstance().getRoom(room.getId().toString()).setBottomInfo(bottomInfo);
			
			KCB kcb=kcbDao.queryByRoom(room.getId());
			WebSocketPool.getInstance().getRoom(room.getId().toString()).setKcb(kcb);
			WebSocketPool.getInstance().setRobotlist(new RobotDao().getRobotByDate());
			
		}
		
	}

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
