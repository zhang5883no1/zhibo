package com.xidu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.xidu.entity.Chats;
import com.xidu.entity.Customer;
import com.xidu.init.GetChatsUtil;
import com.xidu.init.WebSocketPool;
import com.xidu.service.ChatService;


/**
 * 登录
 * @author Administrator
 *
 */
public class ValidInfoController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response){
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			String id =request.getParameter("id");
			String valid=request.getParameter("valid");
			Chats chat=new Chats();
			Customer customer=(Customer)request.getSession().getAttribute("customer");
			if(customer.getLevel()==100L||customer.getLevel()==99L){
				chat=new ChatService().validChats(id, valid, customer.getRoomNo(),customer.getId());
			}
			
			out.print(request.getParameter("callback")+"("+chat.toJson().toString()+")");
			out.flush();
			out.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
