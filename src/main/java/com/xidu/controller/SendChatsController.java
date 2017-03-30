package com.xidu.controller;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xidu.dao.ChatsDao;
import com.xidu.dao.CustomerDao;
import com.xidu.entity.Chats;
import com.xidu.entity.Customer;
import com.xidu.init.WebSocketPool;


/**
 * 发送聊天记录
 * @author Administrator
 *
 */
public class SendChatsController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		CustomerDao customerDao=new CustomerDao();
		ChatsDao chatsDao=new ChatsDao();
		try {
			String time = request.getParameter("time");  //获取时间戳
			String font = request.getParameter("font");  //获取字体大小
			String content = URLDecoder.decode(request.getParameter("content"),"UTF-8");
			String userName = URLDecoder.decode(request.getParameter("userName"),"UTF-8");  //获取发言人昵称
			String userId = request.getParameter("userId");   //获取发言人id
			String date = request.getParameter("date");
			String toUser = URLDecoder.decode(request.getParameter("toUser"),"UTF-8");
			String isRobot = request.getParameter("isRobot");   //是否是机器人，0：不是
			String roomNo = request.getParameter("roomNo");
			String validUser = URLDecoder.decode(request.getParameter("validUser"),"UTF-8");  //审核人
			String faceImg = URLDecoder.decode(request.getParameter("faceImg"),"UTF-8");
		    Customer customer = customerDao.selectCustomerById(Long.parseLong(userId),roomNo);   //根据id获取用户
		    Long level = customer.getLevel(); //获取用户等级
		    //如果用户等级为99和100，则为内部人员，发言直接审核通过
		    Integer valid = 0;  //是否审核     0:未审核  1：已审核
		    if(level == 99 || level == 100){
		    	valid = 1;   //已审核
		    }else{
		    	valid = 0;   //未审核
		    }
		    if(time == session.getAttribute(time)){
		    	Chats chats = new Chats();
			    chats.setContent("<div style=\"font-size:"+font+"px\">"+content+"</div>");      //<div style="font-size: 14px"></div>  
			    chats.setUserName(userName);
			    chats.setUserId(Long.parseLong(userId));
			    SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    chats.setDate(sf.format(new Date()));
			    chats.setToUser(toUser);
			    chats.setIsRobot(Integer.parseInt(isRobot));
			    chats.setRoomNo(roomNo);
			    chats.setValidUser(validUser);
			    chats.setFaceImg(faceImg);
			    chats.setValid(valid);
			    chatsDao.addChats(chats);
			    
			    if(chats.getValid()==0){
			    	WebSocketPool.getInstance().addValidChat(customer.getRoomNo(), chats);
			    }else if(chats.getValid()==1){
			    	WebSocketPool.getInstance().addNormalChat(customer.getRoomNo(), chats);
			    }
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}  finally{
		}
	}
}
