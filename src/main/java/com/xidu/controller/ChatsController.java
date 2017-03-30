package com.xidu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import com.xidu.dao.ChatsDao;
import com.xidu.entity.Chats;

/**
 * 聊天记录
 * 
 * @author Administrator
 * 
 */
public class ChatsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		String time = request.getParameter("time"); // 获取时间戳
		String page = request.getParameter("page"); // 获取要查询的页数
		String roomNo = request.getParameter("roomNo");   //获取房间号
		Long start = Long.parseLong(page) * 20;   
		List<Chats> chatsList = new ArrayList<Chats>();
		HttpSession session = request.getSession();
		String time2 = String.valueOf(session.getAttribute("time"));
		try {
			if (time.equals(time2)) {     // 如果时间戳通过
				chatsList =new ChatsDao().selectChatsList(start, 20L,Long.parseLong(roomNo));
				String json = JSONArray.fromObject(chatsList).toString();
				response.getWriter().print(json);
			}else{
				response.getWriter().print("0");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
