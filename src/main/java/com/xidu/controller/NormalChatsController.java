package com.xidu.controller;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.xidu.constant.Constant;
import com.xidu.entity.Chats;
import com.xidu.entity.Customer;
import com.xidu.entity.Info;
import com.xidu.entity.Robot;
import com.xidu.init.GetChatsUtil;
import com.xidu.service.ChatService;
import com.xidu.util.IPUtil;
import com.xidu.util.StringUtil;


/**
 * 登录
 * @author Administrator
 *
 */
public class NormalChatsController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			JSONObject json=new JSONObject();
			GetChatsUtil getChatsUtil=new GetChatsUtil();
			String lastId=request.getParameter("id");
			Customer customer =(Customer)request.getSession().getAttribute("customer");
			
			//审核信息 
			if(customer.getLevel()==100||customer.getLevel()==99){
//				System.out.println("level > 99");
				if(request.getSession().getAttribute("LastValidChatsId")!=null){
					Long validId = Long.valueOf(request.getSession().getAttribute("LastValidChatsId").toString());
//					System.out.println("LastValidChatsId :"+validId);
					List<Chats> validChatList=getChatsUtil.getValidChats(customer.getRoomNo(), Long.valueOf(validId));
//					System.out.println("validChatList size : "+validChatList.size());
					JSONArray validarray=new JSONArray();
					for(Chats c:validChatList){
						validarray.add(c.toJson());
					}
					json.accumulate("validChats", validarray);
					if(validChatList.size()>0){
//						System.out.println("set session validChatList : "+validChatList.get(validChatList.size()-1).getId());
						request.getSession().setAttribute("LastValidChatsId", validChatList.get(validChatList.size()-1).getId());
					}
				}else{
					request.getSession().setAttribute("LastValidChatsId", 0L);
				}
//				System.out.println(" to get falidChats------------");
				json.accumulate("falidChats", getChatsUtil.getFalidChats(customer.getRoomNo()));
//				System.out.println(" get falidChats success-----------------");
			}
			
			//普通信息
			List<Chats> normalChatList=getChatsUtil.getNormalChats(customer.getRoomNo(), Long.valueOf(lastId));
			JSONArray normalArray=new JSONArray();
			for(Chats c:normalChatList){
				normalArray.add(c.toJson());
			}
			json.accumulate("normalChats", normalArray);
			if(normalChatList!=null&&normalChatList.size()>0){
			}
			
			
			//获取私聊
			LinkedList<Chats> simplelist=(LinkedList<Chats>)request.getSession().getAttribute(Constant.SESSION_SIMPLE_CHATS);
			if(simplelist!=null&&simplelist.size()>0){
				request.getSession().setAttribute(Constant.SESSION_SIMPLE_CHATS, new LinkedList<Chats>());
			}
			
			//特殊信息
			Info scrolInfo=new GetChatsUtil().getInfo("scrol", customer.getRoomNo());
			Info floatInfo=new GetChatsUtil().getInfo("float", customer.getRoomNo());
			Info topInfo=new GetChatsUtil().getInfo("top", customer.getRoomNo());
			Info bottomInfo=new GetChatsUtil().getInfo("bottom", customer.getRoomNo());
			
			json.accumulate("scrolInfo", scrolInfo);
			json.accumulate("floatInfo", floatInfo);
			json.accumulate("topInfo", topInfo);
			json.accumulate("bottomInfo", bottomInfo);
			json.accumulate("simpleInfo", simplelist);
			
			out.print(request.getParameter("callback")+"("+json.toString()+")");
			out.flush();
			out.close();
		} catch (Exception e) {
//			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			String content = StringUtil.replaceEmoji(URLDecoder.decode(request.getParameter("content"),"UTF-8"));
			String times = request.getParameter("times");
			String userName = URLDecoder.decode(request.getParameter("userName"),"UTF-8");
			String userId =request.getParameter("userId");
			String toUser =URLDecoder.decode(request.getParameter("toUser"),"UTF-8");
			String isRobot=request.getParameter("isRobot");
			String type=request.getParameter("type");
			
			Customer customer=(Customer)request.getSession().getAttribute("customer");
			boolean flag=true;
			if(isRobot.equals("0")){
				if(userId.equals(customer.getId().toString())){
					flag=false;
				}
			}else if(isRobot.equals("1")){
				if(customer.getRobotList()!=null&&customer.getRobotList().size()>0){
					for(Robot robot:customer.getRobotList()){
						if(robot.getId().toString().equals(userId)){
							flag=false;
						}
					}
				}
			}
			if(flag){
				return;
			}
			ChatService service=new ChatService();
			Chats chats=service.validContentByType(content,type);
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			chats.setDate(sf.format(new Date()));
			chats.setIsRobot(Integer.valueOf(isRobot));
			chats.setRoomNo(customer.getRoomNo());
			chats.setToUser(toUser);
			chats.setUserId(Long.valueOf(userId));
			chats.setUserName(userName);
			chats.setIp("");
			service.addChats(chats, customer);
			
			out.print("");
			out.flush();
			out.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
